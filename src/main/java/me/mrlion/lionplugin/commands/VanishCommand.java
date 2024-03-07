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
import java.util.Objects;
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

        if (!player.hasPermission("lionplugin.vanish")) {
            String noPermsMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("vanish.no-permissions")));
            if (noPermsMessage.isEmpty()) {
                sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            } else {
                sender.sendMessage(noPermsMessage);
            }
            return true;
        }

        String vanishedMessage = plugin.getConfig().getString("vanish.vanished-message");
        String unvanishedMessage = plugin.getConfig().getString("vanish.unvanished-message");

        if (vanishedPlayers.contains(player.getUniqueId())) {
            vanishedPlayers.remove(player.getUniqueId());
            assert unvanishedMessage != null;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', unvanishedMessage));
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            player.removePotionEffect(PotionEffectType.SATURATION);
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                onlinePlayer.showPlayer(plugin, player);
            }
        } else {
            vanishedPlayers.add(player.getUniqueId());
            assert vanishedMessage != null;
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', vanishedMessage));
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 0, false, false));
            for (Player onlinePlayer : plugin.getServer().getOnlinePlayers()) {
                onlinePlayer.hidePlayer(plugin, player);
            }
        }
        return true;
    }
}