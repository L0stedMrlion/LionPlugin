package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Objects;

public class ReloadCommand implements CommandExecutor {
    private final LionPlugin plugin;

    public ReloadCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("lionplugin.reload")) {
            String noPermsMessage = Objects.requireNonNull(plugin.getConfig().getString("other.no-perms"));
            if (noPermsMessage.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermsMessage));
            }
            return true;
        }

        plugin.reloadConfig();
        String configReloadedMessage = Objects.requireNonNull(plugin.getConfig().getString("reload.config-reloaded"));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configReloadedMessage));
        plugin.getLogger().info("Config was successfully reloaded");
        return true;
    }
}
