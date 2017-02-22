package me.leorblx.classicnfsw;

import me.leorblx.classicnfsw.core.HttpState;
import me.leorblx.classicnfsw.core.XmlUtils;
import me.leorblx.classicnfsw.jaxb.CarSlotInfoTrans;
import me.leorblx.classicnfsw.jaxb.CustomCarType;
import me.leorblx.classicnfsw.jaxb.ExtraCustomCarType;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WorldCarslots
{
    public void addCar(String carData)
    {
        Long personaId = HttpState.getPersonaId();
        String carslotContent = XmlUtils.getFromFile("www/nfsw/Engine.svc/personas/" + personaId + "/carslots.xml");
        CarSlotInfoTrans carSlotInfoTrans = XmlUtils.unmarshal(carslotContent, CarSlotInfoTrans.class);

        ExtraCustomCarType extraCustomCar = XmlUtils.unmarshal(carData, ExtraCustomCarType.class);
        
        CustomCarType customCar = new CustomCarType();
        
        // Internal
        customCar.setApiId(extraCustomCar.getApiId());
        customCar.setBaseCarId(extraCustomCar.getBaseCarId());
        customCar.setLevel(extraCustomCar.getLevel());
        customCar.setRating(extraCustomCar.getRating());
        customCar.setResalePrice(extraCustomCar.getResalePrice());
        customCar.setPreset(extraCustomCar.getIsPreset());
        customCar.setPhysicsProfile(extraCustomCar.getPhysicsProfile());
        customCar.setDurability(extraCustomCar.getDurability());
        customCar.setHeat(extraCustomCar.getHeat());
        
        // Visual
        customCar.setVinyls(extraCustomCar.getVinyls());
        customCar.setVisualParts(extraCustomCar.getVisualParts());
        customCar.setPerformanceParts(extraCustomCar.getPerformanceParts());
        customCar.setPaints(extraCustomCar.getPaints());
        
        carSlotInfoTrans.getCarsOwnedByPersonaList().add(customCar);
        carSlotInfoTrans.setDefaultOwnedCarIndex(carSlotInfoTrans.getCarsOwnedByPersonaList().getCustomCarList().indexOf(customCar));
        carSlotInfoTrans.setOwnedCarSlotsCount(carSlotInfoTrans.getOwnedCarSlotsCount() + 1);

        String marshalled = XmlUtils.marshal(carSlotInfoTrans);

        try {
            Files.write(Paths.get("www/nfsw/Engine.svc/personas/" + personaId + "/carslots.xml"), marshalled.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
