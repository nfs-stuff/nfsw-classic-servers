package me.leorblx.classicnfsw.http.controller;

import me.leorblx.classicnfsw.WorldBaskets;
import me.leorblx.classicnfsw.WorldCommerce;
import me.leorblx.classicnfsw.core.Router;
import me.leorblx.classicnfsw.core.XmlUtils;
import me.leorblx.classicnfsw.jaxb.*;
import me.leorblx.classicnfsw.xmpp.XmppSrv;
import me.leorblx.classicnfsw.xmpp.jaxb.XMPP_PowerupActivatedType;
import me.leorblx.classicnfsw.xmpp.jaxb.XMPP_ResponseTypePowerupActivated;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Personas extends Router
{
    private long getPersonaId()
    {
        String[] targetSplitted = getTarget().split("/");

        return Long.valueOf(targetSplitted[4]);
    }

    private long getDefaultCarId()
    {
        long carId = 0;
        String[] targetSplitted = getTarget().split("/");
        if (targetSplitted.length == 7) {
            carId = Long.valueOf(targetSplitted[6]);
        }
        return carId;
    }

    public String carslots()
    {
        return "fileref:personas/" + getPersonaId() + "/carslots.xml";
    }

    public String powerups()
    {
        return "fileref:personas/" + getPersonaId() + "/powerups.xml";
    }

    public String powerup()
    {
        final String[] split = getTarget().split("/");
        String action = split[6];
        Long hash = Long.valueOf(split[7]);

        if (!action.equals("activated")) return "";

        XMPP_ResponseTypePowerupActivated powerupActivatedResponse = new XMPP_ResponseTypePowerupActivated();
        XMPP_PowerupActivatedType powerupActivated = new XMPP_PowerupActivatedType();
        powerupActivated.setId(hash);
        powerupActivated.setTargetPersonaId(Long.valueOf(getParam("targetId")));
        powerupActivated.setPersonaId(getLoggedPersonaId());
        powerupActivatedResponse.setPowerupActivated(powerupActivated);

        for (String receiver : getParam("receivers").split("-")) {
            Long receiverPersonaId = Long.valueOf(receiver);
            if (receiverPersonaId > 10) {
                XmppSrv.sendMsg(receiverPersonaId, XmlUtils.marshal(powerupActivatedResponse));
            }
        }
        return "";
    }

    public String inventoryobject()
    {
        return "fileref:personas/" + getPersonaId() + "/inventoryobject.xml";
    }

    public String defaultcar()
    {
        return "fileref:personas/" + getPersonaId() + "/defaultcar.xml";
    }

    public String commerce()
    {
        String commerceXml = readInputStream();

        CommerceSessionResultTransType commerceSessionResultTransType = new CommerceSessionResultTransType();
        commerceSessionResultTransType.setUpdatedCar(new WorldCommerce().save(commerceXml));
        commerceSessionResultTransType.setInvalidBasket("");
        commerceSessionResultTransType.setInventoryItems(new InventoryItemsType());
        commerceSessionResultTransType.setStatus("Success");

        return XmlUtils.marshal(commerceSessionResultTransType);
    }

    public String baskets()
    {
        WorldBaskets baskets = new WorldBaskets();
        BasketTransType basket = XmlUtils.unmarshal(readInputStream(), BasketTransType.class);
//        CommerceSessionTransType session = XmlUtils.unmarshal(readInputStream(), CommerceSessionTransType.class);

        CommerceResultTransType commerceResultTransType = new CommerceResultTransType();
        PurchasedCarsType purchasedCarsType = new PurchasedCarsType();

        InventoryItemTransType inventoryItemTransType = new InventoryItemTransType();
        InventoryItemsType inventoryItemsType = new InventoryItemsType();
        inventoryItemsType.setInventoryItemTrans(inventoryItemTransType);

        commerceResultTransType.setCommerceItems("");
        commerceResultTransType.setInvalidBasket("");
        commerceResultTransType.setInventoryItems(inventoryItemsType);

        commerceResultTransType.setPurchasedCars(purchasedCarsType);
        commerceResultTransType.setStatus("Success");

        String car = baskets.purchase(basket);

        if (!car.isEmpty()) {
            final CustomCarType customCarTrans = XmlUtils.unmarshal(car, CustomCarType.class);

            purchasedCarsType.setCustomCarTrans(customCarTrans);

            commerceResultTransType.setPurchasedCars(purchasedCarsType);
        }

        return XmlUtils.marshal(commerceResultTransType);
    }

    public String cars()
    {
        String input = readInputStream();
        CustomCarType customCar = XmlUtils.unmarshal(input, CustomCarType.class);

        CarSlotInfoTrans carslots = XmlUtils.unmarshal(
                XmlUtils.getFromFile("www/nfsw/Engine.svc/personas/" + getLoggedPersonaId() + "/carslots.xml"),
                CarSlotInfoTrans.class
        );

        CustomCarType find = carslots.getCarsOwnedByPersonaList().getCustomCarList().stream()
                .filter(c -> c.getApiId() == customCar.getApiId())
                .findFirst()
                .orElse(null);

        if (find != null) {
            System.out.println(find);
            int index = carslots.getCarsOwnedByPersonaList().getCustomCarList().indexOf(find);

            find.setPerformanceParts(customCar.getPerformanceParts());
            find.setVinyls(customCar.getVinyls());
            find.setPaints(customCar.getPaints());
            find.setVisualParts(customCar.getVisualParts());

            carslots.getCarsOwnedByPersonaList().getCustomCarList().set(index, find);

            try {
                Files.write(Paths.get("www/nfsw/Engine.svc/personas/" + getLoggedPersonaId() + "/carslots.xml"), XmlUtils.marshal(carslots).getBytes(StandardCharsets.UTF_8));

                return XmlUtils.marshal(find);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}