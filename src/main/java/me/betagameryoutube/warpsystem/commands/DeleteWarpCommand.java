package me.betagameryoutube.warpsystem.commands;

import me.betagameryoutube.warpsystem.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.regex.Pattern;

public class DeleteWarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length != 1) {
            sender.sendMessage(Config.getPrefix() + "§7Usage: §c/delwarp <Warp>");
            return true;
        }

        if (!Pattern.matches("[a-zA-Z]+", args[0].toLowerCase())) {
            sender.sendMessage(Config.getPrefix() + "§cWarpname can only contain letters!");
            return true;
        }

        if (!(sender instanceof Player)) {
            File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
            if (file.exists()) {
                file.delete();
                sender.sendMessage(Config.getPrefix() + "§aThe Warp was deleted!");
            } else {
                sender.sendMessage(Config.getPrefix() + "§cThat Warp does not exist!");
            }
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("warps.delete")) {
            p.sendMessage(Config.getPrefix() + "§cNo Permission!");
            return true;
        }

        File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
        if (file.exists()) {
            file.delete();
            p.sendMessage(Config.getPrefix() + "§aThe Warp was deleted!");
        } else {
            p.sendMessage(Config.getPrefix() + "§cThat Warp does not exist!");
        }

        return false;
    }
}
