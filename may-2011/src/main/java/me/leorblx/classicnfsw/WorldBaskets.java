package me.leorblx.classicnfsw;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.XmlUtils;
import me.leorblx.classicnfsw.jaxb.BasketTransType;
import me.leorblx.classicnfsw.jaxb.CarSlotInfoTrans;
import me.leorblx.classicnfsw.jaxb.CustomCarType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WorldBaskets
{
    private static WorldCarslots carslots = new WorldCarslots();
    
    public String purchase(BasketTransType basket)
    {
        String productId = basket.getBasketItems().getBasketItemTrans().getProductId();
        Path path = Paths.get("www/basket/" + productId + ".xml");

        if (Files.exists(path)) {
            try {
                String data = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

                final String pathName = "www/nfsw/Engine.svc/personas/" + HttpState.getPersonaId() + "/carslots.xml";
                Path path2 = Paths.get(pathName);
                CarSlotInfoTrans carSlotInfoTrans = XmlUtils.unmarshal(XmlUtils.getFromFile(pathName), CarSlotInfoTrans.class);

                CustomCarType basketCar = XmlUtils.unmarshal(data, CustomCarType.class);
                
                carslots.addCar(data);
                
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "OtherEror";
    }
}
