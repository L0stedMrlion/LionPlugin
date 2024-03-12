package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.ChatColor;
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
            String reloadNoPerms = plugin.getConfig().getString("reload.no-perms");
            assert reloadNoPerms != null;
            reloadNoPerms = ChatColor.translateAlternateColorCodes('&', reloadNoPerms);
            sender.sendMessage(reloadNoPerms);
            return true;
        }

        try {
            plugin.reloadConfig();
            String configReloaded = plugin.getConfig().getString("reload.config-reloaded");
            assert configReloaded != null;
            configReloaded = ChatColor.translateAlternateColorCodes('&', configReloaded);
            sender.sendMessage(configReloaded);
            plugin.getLogger().info("Configuration reloaded by " + sender.getName());
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "An error occurred while reloading the configuration. Please check the server logs for details.");
            e.printStackTrace();
        }
        return true;
    }
}
