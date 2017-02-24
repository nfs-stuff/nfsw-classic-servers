package me.leorblx.classicnfsw;

import me.leorblx.classicnfsw.core.XmlUtils;
import me.leorblx.classicnfsw.jaxb.CustomCarType;
import me.leorblx.classicnfsw.jaxb.PhysicsProfileTransType;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class BasketDebug
{
    public static void main(String[] args) {
        File basketFolder = Paths.get("www/basket").toFile();
        
        if (!basketFolder.exists()) return;
        
        File[] files = basketFolder.listFiles();
        
        if (files == null) return;

        Map<String, Long> hashes = new HashMap<>();
        
        for (File file : files) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

                CustomCarType customCar = XmlUtils.unmarshal(content, CustomCarType.class);
                PhysicsProfileTransType physicsProfile = customCar.getPhysicsProfile();
                
                hashes.put(file.getName(), physicsProfile.getAttribHash());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        for (Long hash : hashes.values()) {
            List<String> withHash = hashes.entrySet().stream().filter(e -> Objects.equals(e.getValue(), hash))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());
            System.out.println("hash=" + hash + ", files=" + withHash);
        }
    }
}
