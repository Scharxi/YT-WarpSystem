package me.betagameryoutube.warpsystem;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public final class PluginConfig implements Config {

    private final WarpSystem plugin;
    private YamlConfiguration config;
    private File file;

    public PluginConfig(WarpSystem plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        file = new File("./plugins/Warps/config.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    @Override
    public YamlConfiguration getConfigFile() {
        return config;
    }

    @Override
    public WarpSystem getPluginInstance() {
        return plugin;
    }

    @Override
    public void save() {
        try {
            this.config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPrefix() {
        return getConfigFile().getString("Prefix").replace("&", "§");
    }
}
