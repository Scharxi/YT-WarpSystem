package me.betagameryoutube.warpsystem;

import me.betagameryoutube.warpsystem.commands.DeleteWarpCommand;
import me.betagameryoutube.warpsystem.commands.SetWarpCommand;
import me.betagameryoutube.warpsystem.commands.WarpCommand;
import me.betagameryoutube.warpsystem.commands.WarpsCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class WarpSystem extends JavaPlugin {
    @Override
    public void onEnable() {
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
        File file = new File("./plugins/Warps/config.yml");
        YamlConfiguration yfile = YamlConfiguration.loadConfiguration(file);
        yfile.addDefault("Prefix", "&aWarpSystem &7» ");
        yfile.options().copyDefaults(true);
        try {
            yfile.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
