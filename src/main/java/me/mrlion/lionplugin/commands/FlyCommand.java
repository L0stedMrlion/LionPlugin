package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FlyCommand implements CommandExecutor {
    private final LionPlugin plugin;

    public FlyCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "This command can't be used by console!");
            return true;
        }

        if (!player.hasPermission("lionplugin.fly")) {
            String noPermsMessage = Objects.requireNonNull(plugin.getConfig().getString("fly.no-permissions"));
            if (!noPermsMessage.isEmpty()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermsMessage));
            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            }
            return true;
        }

        if (player.getAllowFlight()) {
            player.setAllowFlight(false);
            plugin.getLogger().info(player.getName() + " has disabled flight mode.");
        } else {
            player.setAllowFlight(true);
            plugin.getLogger().info(player.getName() + " has enabled flight mode.");
        }

        return true;
    }
}
