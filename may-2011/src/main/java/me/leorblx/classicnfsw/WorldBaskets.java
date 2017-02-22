package me.leorblx.classicnfsw;

import me.leorblx.classicnfsw.jaxb.BasketTransType;
import me.leorblx.classicnfsw.jaxb.CommerceSessionTransType;

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
                
                carslots.addCar(data);
                
                return data;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "";
    }
}
