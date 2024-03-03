package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import java.util.List;

public class WhitelistCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public WhitelistCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /lwhitelist <player>");
            return true;
        }

        String playerName = args[0];
        List<String> whitelist = plugin.getConfig().getStringList("whitelisted-players");

        if (whitelist.contains(playerName)) {
            whitelist.remove(playerName);
            plugin.getConfig().set("whitelisted-players", whitelist);
            plugin.saveConfig();
            String removalMessage = plugin.getConfig().getString("whitelist.removed-message");
            if (removalMessage != null) {
                removalMessage = ChatColor.translateAlternateColorCodes('&', removalMessage.replace("{player}", playerName));
                sender.sendMessage(removalMessage);
            }
            plugin.getLogger().info(playerName + " was removed from the whitelist!");
        } else {
            whitelist.add(playerName);
            plugin.getConfig().set("whitelisted-players", whitelist);
            plugin.saveConfig();
            String additionMessage = plugin.getConfig().getString("whitelist.added-message");
            if (additionMessage != null) {
                additionMessage = ChatColor.translateAlternateColorCodes('&', additionMessage.replace("{player}", playerName));
                sender.sendMessage(additionMessage);
            }
            plugin.getLogger().info(playerName + " was added to the whitelist!");
        }

        return true;
    }
}
