package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.ChatColor;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {

    private final LionPlugin plugin;
    private final Set<UUID> vanishedPlayers;

    public VanishCommand(LionPlugin plugin) {
        this.plugin = plugin;
        this.vanishedPlayers = plugin.getVanishedPlayers();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by players.");
            return true;
        }

        Player player = (Player) sender;
        String vanishedMessage = plugin.getConfig().getString("vanish.vanished-message");
        String unvanishedMessage = plugin.getConfig().getString("vanish.unvanished-message");

        if (vanishedPlayers.contains(player.getUniqueId())) {
            // Unvanish the player
            vanishedPlayers.remove(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', unvanishedMessage));
            // Remove potion effects
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.removePotionEffect(PotionEffectType.SATURATION);
            // Make player visible again
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                onlinePlayer.showPlayer(plugin, player);
            }
        } else {
            // Vanish the player
            vanishedPlayers.add(player.getUniqueId());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', vanishedMessage));
            // Apply potion effects
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false, false));
            // Make player invisible to others
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                onlinePlayer.hidePlayer(plugin, player);
            }
        }
        return true;
    }
}