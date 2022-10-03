package de.beta.warpsys;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    public static String getPrefix() {
        File file = new File("./plugins/Warps/config.yml");
        YamlConfiguration yfile = YamlConfiguration.loadConfiguration(file);
        return yfile.getString("Prefix").replace("&", "§");
    }

}
