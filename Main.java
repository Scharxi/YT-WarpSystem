package de.beta.warpsys;

import de.beta.warpsys.warps.Command_Delwarp;
import de.beta.warpsys.warps.Command_Setwarp;
import de.beta.warpsys.warps.Command_Warp;
import de.beta.warpsys.warps.Command_Warps;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        loadConfig();
        getCommand("setwarp").setExecutor(new Command_Setwarp());
        getCommand("warp").setExecutor(new Command_Warp());
        getCommand("delwarp").setExecutor(new Command_Delwarp());
        getCommand("warps").setExecutor(new Command_Warps());
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
