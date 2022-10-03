package me.betagameryoutube.warpsystem;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class Config {
    public static String getPrefix() {
        File file = new File("./plugins/Warps/config.yml");
        YamlConfiguration yfile = YamlConfiguration.loadConfiguration(file);
        return yfile.getString("Prefix").replace("&", "§");
    }
}
