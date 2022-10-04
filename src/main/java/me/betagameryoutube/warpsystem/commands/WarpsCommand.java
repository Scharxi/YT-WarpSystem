package me.betagameryoutube.warpsystem.commands;

import me.betagameryoutube.warpsystem.PluginConfig;
import me.betagameryoutube.warpsystem.WarpSystem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WarpsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0) {
            sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§7Usage: §c/warps");
            return true;
        }

        if (!(sender instanceof Player)) {
            File xdir = new File("./plugins/Warps/warps");
            if (!xdir.exists()) {
                xdir.mkdirs();
            }
            List<String> warpList = new ArrayList<>();
            File[] dir = new File("./plugins/Warps/warps").listFiles();
            for (File file : dir) {
                if (file.isFile()) {
                    warpList.add(file.getName().replace(".yml", ""));
                }
            }
            String warps = warpList.toString().replace("[", "").replace("]", "");
            if (warpList.isEmpty()) {
                sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§bThere are no Warps!");
                return true;
            }

            sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§bWarps: §e" + warps);
            return true;
        }

        Player p = (Player) sender;
        if (!p.hasPermission("warps.list")) {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cNo Permission!");
            return true;
        }

        File xdir = new File("./plugins/Warps/warps");
        if (!xdir.exists()) {
            xdir.mkdirs();
        }
        List<String> warpList = new ArrayList<>();
        File[] dir = new File("./plugins/Warps/warps").listFiles();
        for (File file : dir) {
            if (file.isFile()) {
                warpList.add(file.getName().replace(".yml", ""));
            }
        }
        String warps = warpList.toString().replace("[", "").replace("]", "");
        if (warpList.isEmpty()) {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§bThere are no Warps!");
            return true;
        }
        p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§bWarps: §e" + warps);
        return false;
    }
}