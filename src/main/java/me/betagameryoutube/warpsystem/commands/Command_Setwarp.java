package me.betagameryoutube.warpsystem.commands;

import me.betagameryoutube.warpsystem.Config;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class Command_Setwarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("warps.set")) {
                if(args.length == 1) {
                    if(Pattern.matches("[a-zA-Z]+", args[0].toLowerCase())) {
                        File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
                        if(!file.exists()) {
                            YamlConfiguration yfile = YamlConfiguration.loadConfiguration(file);
                            Location loc = p.getLocation();
                            yfile.set("World", loc.getWorld().getName());
                            yfile.set("X", loc.getX());
                            yfile.set("Y", loc.getY());
                            yfile.set("Z", loc.getZ());
                            yfile.set("Yaw", loc.getYaw());
                            yfile.set("Pitch", loc.getPitch());
                            try {
                                yfile.save(file);
                            } catch (IOException exception) {
                                exception.printStackTrace();
                            }
                            p.sendMessage(Config.getPrefix() + "§aWarp set!");
                        } else {
                            p.sendMessage(Config.getPrefix() + "§cThis Warp already exist!");
                        }
                    } else {
                        p.sendMessage(Config.getPrefix() + "§cWarpname can only contain letters!");
                    }
                } else {
                    p.sendMessage(Config.getPrefix() + "§7Usage: §c/setwarp <Warpname>");
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