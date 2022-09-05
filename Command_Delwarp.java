package de.beta.warpsys.warps;

import de.beta.warpsys.Config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class Command_Delwarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("warp.delete")) {
                if(args.length == 1) {
                    File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
                    if(file.exists()) {
                        file.delete();
                        p.sendMessage(Config.getPrefix() + "§aThe Warp was deleted!");
                    } else {
                        p.sendMessage(Config.getPrefix() + "§cThat Warp does not exist!");
                    }
                } else {
                    p.sendMessage(Config.getPrefix() + "§7Usage: §c/delwarp <Warp>");
                }
            } else {
                p.sendMessage(Config.getPrefix() + "§cNo Permission!");
            }
        } else {
            if(args.length == 1) {
                File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
                if(file.exists()) {
                    file.delete();
                    sender.sendMessage(Config.getPrefix() + "§aThe Warp was deleted!");
                } else {
                    sender.sendMessage(Config.getPrefix() + "§cThat Warp does not exist!");
                }
            } else {
                sender.sendMessage(Config.getPrefix() + "§7Usage: §c/delwarp <Warp>");
            }
        }
        return false;
    }
}
