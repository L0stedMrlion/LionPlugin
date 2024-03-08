package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class HealCommand implements CommandExecutor {
    private final LionPlugin plugin;

    public HealCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player player) {

            if (!player.hasPermission("lionplugin.heal")) {
                String noPermsMessage = Objects.requireNonNull(plugin.getConfig().getString("heal.no-permissions"));
                if (!noPermsMessage.isEmpty()) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermsMessage));
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                }
                return true;
            }

            if (args.length == 0) {
                player.setHealth(20.0);
                player.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("heal.self-heal-message")));
                plugin.getLogger().info(player.getName() + " has been healed.");
                return true;
            }

            if (args[0].equalsIgnoreCase("all")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.setHealth(20.0);
                    onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("heal.other-heal-message")).replace("{healer}", player.getName())));
                }
                plugin.getLogger().info("All players have been healed!");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + Objects.requireNonNull(plugin.getConfig().getString("heal.player-not-found-message")));
                return true;
            }

            target.setHealth(20.0);
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("heal.other-heal-message")).replace("{healer}", player.getName())));
            plugin.getLogger().info(target.getName() + " has been healed by " + player.getName());
        } else {
            if (args.length == 0 || !args[0].equalsIgnoreCase("all")) {
                sender.sendMessage(ChatColor.RED + Objects.requireNonNull(plugin.getConfig().getString("heal.console-usage-message")));
                return true;
            }

            if (args[0].equalsIgnoreCase("all")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.setHealth(20.0);
                    onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("heal.other-heal-message")).replace("{healer}", "Console")));
                }
                plugin.getLogger().info("All players have been healed!");
                return true;
            }
        }
        return true;
    }
}
