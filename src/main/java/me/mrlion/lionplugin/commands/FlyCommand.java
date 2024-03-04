package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Objects;

public class FlyCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public FlyCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("fly.player-only"))));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("lionplugin.fly")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("fly.no-permission-fly"))));
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
