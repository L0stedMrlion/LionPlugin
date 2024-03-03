package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class ReloadCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public ReloadCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lionplugin.reload")) {
            String noPermissionMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload.no-permission-reload"));
            sender.sendMessage(noPermissionMessage);
            return true;
        }

        plugin.reloadConfig();
        String configReloadedMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("reload.config-reloaded"));
        sender.sendMessage(configReloadedMessage);
        plugin.getLogger().info("Config was reloaded!");
        return true;
    }
}

