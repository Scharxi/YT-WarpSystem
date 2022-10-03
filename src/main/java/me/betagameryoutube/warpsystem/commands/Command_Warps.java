package me.betagameryoutube.warpsystem.commands;

import me.betagameryoutube.warpsystem.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Command_Warps implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("warps.list")) {
                if (args.length == 0) {
                    File xdir = new File("./plugins/Warps/warps");
                    if (!xdir.exists()) {
                        xdir.mkdirs();
                    }
                    List<String> warplist = new ArrayList<>();
                    File[] dir = new File("./plugins/Warps/warps").listFiles();
                    for (File file : dir) {
                        if (file.isFile()) {
                            warplist.add(file.getName().replace(".yml", ""));
                        }
                    }
                    String warps = warplist.toString().replace("[", "").replace("]", "");
                    if (warplist.isEmpty()) {
                        p.sendMessage(Config.getPrefix() + "§bThere are no Warps!");
                    } else {
                        p.sendMessage(Config.getPrefix() + "§bWarps: §e" + warps);
                    }
                } else {
                    p.sendMessage(Config.getPrefix() + "§7Usage: §c/warps");
                }
            } else {
                p.sendMessage(Config.getPrefix() + "§cNo Permission!");
            }
        } else {
            if (args.length == 0) {
                File xdir = new File("./plugins/Warps/warps");
                if (!xdir.exists()) {
                    xdir.mkdirs();
                }
                List<String> warplist = new ArrayList<>();
                File[] dir = new File("./plugins/Warps/warps").listFiles();
                for (File file : dir) {
                    if (file.isFile()) {
                        warplist.add(file.getName().replace(".yml", ""));
                    }
                }
                String warps = warplist.toString().replace("[", "").replace("]", "");
                if (warplist.isEmpty()) {
                    sender.sendMessage(Config.getPrefix() + "§bThere are no Warps!");
                } else {
                    sender.sendMessage(Config.getPrefix() + "§bWarps: §e" + warps);
                }
            } else {
                sender.sendMessage(Config.getPrefix() + "§7Usage: §c/warps");
            }
        }
        return false;
    }
}