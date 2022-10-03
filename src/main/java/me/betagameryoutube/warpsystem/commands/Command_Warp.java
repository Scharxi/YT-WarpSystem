package me.betagameryoutube.warpsystem.commands;

import me.betagameryoutube.warpsystem.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Command_Warp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("warp.use")) {
                if(args.length == 1) {
                    File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
                    if(file.exists()) {
                        YamlConfiguration yfile = YamlConfiguration.loadConfiguration(file);
                        World world = Bukkit.getWorld(yfile.getString("World"));
                        double X = yfile.getDouble("X");
                        double Y = yfile.getDouble("Y");
                        double Z = yfile.getDouble("Z");
                        float Yaw = (float) yfile.getDouble("Yaw");
                        float Pitch = (float) yfile.getDouble("Pitch");
                        Location loc = new Location(world, X, Y, Z, Yaw, Pitch);
                        if(loc != null) {
                            p.teleport(loc);
                            p.sendMessage(Config.getPrefix() + "§aYou have been teleportet!");
                        } else {
                            p.sendMessage(Config.getPrefix() + "§cThat Warp does not exist!");
                        }
                    } else {
                        p.sendMessage(Config.getPrefix() + "§cThat Warp does not exist!");
                    }
                } else {
                    p.sendMessage(Config.getPrefix() + "§7Usage: §c/warp <Warp>");
                }
            } else {
                p.sendMessage(Config.getPrefix() + "§cNo Permission!");
            }
        } else {
            sender.sendMessage(Config.getPrefix() + "§cYou must be a Player!");
        }
        return false;
    }
}
