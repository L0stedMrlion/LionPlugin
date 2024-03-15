package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Level;

public class ReloadCommand implements CommandExecutor {
    private final LionPlugin plugin;

    public ReloadCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!sender.hasPermission("lionplugin.reload")) {
            String reloadNoPerms = Objects.requireNonNull(plugin.getConfig().getString("reload.no-permissions"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadNoPerms));
            return true;
        }

        try {
            String reloadMessage = Objects.requireNonNull(plugin.getConfig().getString("reload.config-reloaded"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadMessage));
            plugin.reloadConfig();
        } catch (Exception e) {
            String reloadError = Objects.requireNonNull(plugin.getConfig().getString("reload.error"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', reloadError));
            plugin.getLogger().log(Level.SEVERE, "Error occurred while reloading the config:", e);
        }
        return true;
    }
}
