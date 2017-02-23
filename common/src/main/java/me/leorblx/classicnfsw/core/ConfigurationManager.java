package me.leorblx.classicnfsw.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Code from Smbarbour's RavenBot, with modifications.
 * https://github.com/MCUpdater/RavenBot/blob/master/src/main/java/org/mcupdater/ravenbot/SettingsManager.java
 */
public class ConfigurationManager
{
    private static ConfigurationManager instance;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Configuration configuration;
    private final Path configFile = new File(".").toPath().resolve("config.json");
    private final Logger log = LoggerFactory.getLogger("ConfigurationManager");

    public static ConfigurationManager getInstance()
    {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private ConfigurationManager()
    {
        if (!configFile.toFile().exists()) {
            log.info("Creating default config...");
            this.configuration = getDefaultConfig();
            saveConfig();
            log.info("Please re-start the server.");
            System.exit(Constants.NEWLY_CREATED_CONFIG);
        }
        loadConfig();
    }

    private void loadConfig()
    {
        try {
            checkBadEscapes(configFile);

            BufferedReader reader = Files.newBufferedReader(configFile, StandardCharsets.UTF_8);
            this.configuration = gson.fromJson(reader, Configuration.class);
            reader.close();
            log.info("Config loaded!");
            checkOldConfigFile();
        } catch (IOException e) {
            log.error("While loading config");
            log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    public Configuration getConfiguration()
    {
        return configuration;
    }

    public void saveConfig()
    {
        String jsonOut = gson.toJson(this.configuration);
        try {
            BufferedWriter writer = Files.newBufferedWriter(configFile, StandardCharsets.UTF_8);
            writer.append(jsonOut);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Configuration getDefaultConfig()
    {
        Configuration configuration = new Configuration();
        configuration.setXmppIp("127.0.0.1");
        configuration.setDebugEnabled(false);
        configuration.setHttpPort(1337);
        configuration.setXmppServerType("offline");

        return configuration;
    }

    private void checkOldConfigFile()
    {
        boolean modified = false;

        Configuration defaults = getDefaultConfig();
        if (configuration.getXmppIp() == null) {
            configuration.setXmppIp(defaults.getXmppIp());
            modified = true;
        }
        
        if (modified)
            saveConfig();
    }

    private void checkBadEscapes(Path filePath) throws IOException
    {
        final byte FORWARD_SLASH = 47;    //  this would be a /
        final byte BACKSLASH = 92;  //  this would be a \

        boolean modified = false;
        byte[] bytes = Files.readAllBytes(filePath);
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == BACKSLASH && !SystemUtils.IS_OS_WINDOWS) {
                // only replace on non-Windows machines, we can't make assumptions
                modified = true;
                bytes[i] = FORWARD_SLASH;
            }
        }

        if (modified) {
            Files.write(filePath, bytes);
        }
    }
}