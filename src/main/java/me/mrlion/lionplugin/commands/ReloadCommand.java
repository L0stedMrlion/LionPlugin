package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public ReloadCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lionplugin.reload")) {
            sender.sendMessage(plugin.getConfig().getString("reload.no-permission-reload"));
            return true;
        }

        plugin.reloadConfig();
        sender.sendMessage(plugin.getConfig().getString("reload.config-reloaded"));
        plugin.getLogger().info("Config.yml was reloaded!");
        return true;
    }
}
