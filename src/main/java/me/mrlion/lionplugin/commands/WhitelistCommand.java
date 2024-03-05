package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Objects;

public class WhitelistCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public WhitelistCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lionplugin.whitelist")) {
            String noPermsMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("other.no-perms")));
            if (noPermsMessage.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            } else {
                sender.sendMessage(noPermsMessage);
            }
            return true;
        }

        boolean whitelistEnabled = plugin.getConfig().getBoolean("whitelist-enabled");

        if (args.length != 1 || (!args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off"))) {
            String usageWhitelist = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("whitelist.cmd-usage")));
            if (usageWhitelist.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "Usage /lwhitelist <on/off>");
            } else {
                sender.sendMessage(usageWhitelist);
            }
            return true;
        }

        String mode = args[0].toLowerCase();
        if ((mode.equals("on") && whitelistEnabled) || (mode.equals("off") && !whitelistEnabled)) {
            sender.sendMessage(ChatColor.RED + "Whitelist is already " + (whitelistEnabled ? "enabled" : "disabled") + ".");
            return true;
        }

        if (mode.equals("on")) {
            plugin.getConfig().set("whitelist-enabled", true);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.GREEN + "Whitelist has been enabled.");
        } else {
            List<String> whitelist = plugin.getConfig().getStringList("whitelisted-players");
            whitelist.clear();
            plugin.getConfig().set("whitelist-enabled", false);
            plugin.getConfig().set("whitelisted-players", whitelist);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.RED + "Whitelist has been disabled.");
        }

        return true;
    }
}
