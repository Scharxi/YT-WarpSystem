package me.betagameryoutube.warpsystem.commands;

import me.betagameryoutube.warpsystem.PluginConfig;
import me.betagameryoutube.warpsystem.WarpSystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.regex.Pattern;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cYou must be a Player!");
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("warps.use")) {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cNo Permission!");
            return true;
        }

        if (args.length != 1) {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§7Usage: §c/warp <Warp>");
            return true;
        }

        if (!Pattern.matches("[a-zA-Z]+", args[0].toLowerCase())) {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cWarpname can only contain letters!");
            return true;
        }

        File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
        if (file.exists()) {
            YamlConfiguration yfile = YamlConfiguration.loadConfiguration(file);
            World world = Bukkit.getWorld(yfile.getString("World"));
            double X = yfile.getDouble("X");
            double Y = yfile.getDouble("Y");
            double Z = yfile.getDouble("Z");
            float Yaw = (float) yfile.getDouble("Yaw");
            float Pitch = (float) yfile.getDouble("Pitch");
            Location loc = new Location(world, X, Y, Z, Yaw, Pitch);

            // TODO: Check if values are valid
            p.teleport(loc);
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§aYou have been teleported!");
        } else {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cThat Warp does not exist!");
        }
        return false;
    }
}
