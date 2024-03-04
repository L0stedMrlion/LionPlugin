package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

import java.util.Objects;

public class ReloadCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public ReloadCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lionplugin.reload")) {
            String noPermsMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("other.no-perms")));
            if (noPermsMessage.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            } else {
                sender.sendMessage(noPermsMessage);
            }
            return true;
        }

        plugin.reloadConfig();
        String configReloadedMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("reload.config-reloaded")));
        sender.sendMessage(configReloadedMessage);
        plugin.getLogger().info("Config was reloaded!");
        return true;
    }
}

