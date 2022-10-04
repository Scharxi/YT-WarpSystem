package me.betagameryoutube.warpsystem;

import org.bukkit.configuration.file.YamlConfiguration;

public interface Config {
    void load();

    YamlConfiguration getConfigFile();

    WarpSystem getPluginInstance();

    void save();
}
