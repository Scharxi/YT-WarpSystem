package me.betagameryoutube.warpsystem;

import me.betagameryoutube.warpsystem.commands.DeleteWarpCommand;
import me.betagameryoutube.warpsystem.commands.SetWarpCommand;
import me.betagameryoutube.warpsystem.commands.WarpCommand;
import me.betagameryoutube.warpsystem.commands.WarpsCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class WarpSystem extends JavaPlugin {

    private static WarpSystem instance;
    private PluginConfig pluginConfig;

    @Override
    public void onEnable() {

        if (instance == null) {
           instance = this;
        }

        pluginConfig = new PluginConfig(this);
        pluginConfig.load();

        loadConfig();
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("delwarp").setExecutor(new DeleteWarpCommand());
        getCommand("warps").setExecutor(new WarpsCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void loadConfig() {
        YamlConfiguration config = pluginConfig().getConfigFile();
        config.addDefault("Prefix", "&aWarpSystem &7» ");
        config.options().copyDefaults(true);
        pluginConfig.save();
    }

    public static WarpSystem instance() {
        return instance;
    }

    public PluginConfig pluginConfig() {
        return pluginConfig;
    }
}
