package me.betagameryoutube.warpsystem.commands;

import me.betagameryoutube.warpsystem.PluginConfig;
import me.betagameryoutube.warpsystem.WarpSystem;
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
            sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§7Usage: §c/delwarp <Warp>");
            return true;
        }

        if (!Pattern.matches("[a-zA-Z]+", args[0].toLowerCase())) {
            sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cWarpname can only contain letters!");
            return true;
        }

        if (!(sender instanceof Player)) {
            File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
            if (file.exists()) {
                file.delete();
                sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§aThe Warp was deleted!");
            } else {
                sender.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cThat Warp does not exist!");
            }
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("warps.delete")) {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cNo Permission!");
            return true;
        }

        File file = new File("./plugins/Warps/warps/" + args[0].toLowerCase() + ".yml");
        if (file.exists()) {
            file.delete();
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§aThe Warp was deleted!");
        } else {
            p.sendMessage(WarpSystem.instance().pluginConfig().getPrefix() + "§cThat Warp does not exist!");
        }

        return false;
    }
}
