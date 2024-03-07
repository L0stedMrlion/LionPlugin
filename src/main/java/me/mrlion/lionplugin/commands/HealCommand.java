package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HealCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public HealCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("lionplugin.heal")) {
                String noPermsMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("heal.no-permissions")));
                if (noPermsMessage.isEmpty()) {
                    sender.sendMessage(ChatColor.RED + "You don't have permission to use this command.");
                } else {
                    sender.sendMessage(noPermsMessage);
                }
                return true;
            }

            if (args.length == 0) {
                player.setHealth(20);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("heal.self-heal-message"))));
                plugin.getLogger().info(player.getName() + " has been healed.");
                return true;
            }

            if (args[0].equalsIgnoreCase("all")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.setHealth(20);
                    onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("heal.other-heal-message").replace("{healer}", player.getName())));
                }
                plugin.getLogger().info("All players have been healed!");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + plugin.getConfig().getString("heal.player-not-found-message"));
                return true;
            }

            target.setHealth(20);
            target.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("heal.other-heal-message")).replace("{healer}", player.getName())));
            plugin.getLogger().info(target.getName() + " has been healed by " + player.getName());
        } else {

            if (args.length == 0 || !args[0].equalsIgnoreCase("all")) {
                sender.sendMessage(ChatColor.RED + plugin.getConfig().getString("heal.console-usage-message"));
                return true;
            }

            if (args[0].equalsIgnoreCase("all")) {
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    onlinePlayer.setHealth(20);
                    onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("heal.other-heal-message")).replace("{healer}", "Console")));
                }
                plugin.getLogger().info("All players have been healed!");
                return true;
            }
        }

        return true;
    }
}
