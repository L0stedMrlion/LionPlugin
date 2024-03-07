package me.mrlion.lionplugin.commands

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class VanishCommand(private val plugin: LionPlugin) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(ChatColor.RED.toString() + "This command cant be used by console!")
            return true
        }

        val player = sender

        if (!player.hasPermission("lionplugin.vanish")) {
            val noPermsMessage = plugin.config.getString("vanish.no-permissions")
            if (noPermsMessage != null && noPermsMessage.isNotEmpty()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermsMessage))
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You don't have permission to use this command!")
            }
            return true
        }

        val vanishedPlayers = plugin.vanishedPlayers ?: return true
        val vanishedMessage = plugin.config.getString("vanish.vanished-message")
        val unvanishedMessage = plugin.config.getString("vanish.unvanished-message")

        if (vanishedPlayers.contains(player.uniqueId)) {
            vanishedPlayers.remove(player.uniqueId)
            if (unvanishedMessage != null && unvanishedMessage.isNotEmpty()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', unvanishedMessage))
            }
            player.removePotionEffect(PotionEffectType.NIGHT_VISION)
            player.removePotionEffect(PotionEffectType.SATURATION)
            for (onlinePlayer in plugin.server.onlinePlayers) {
                onlinePlayer.showPlayer(plugin, player)
            }
        } else {
            vanishedPlayers.add(player.uniqueId)
            if (vanishedMessage != null && vanishedMessage.isNotEmpty()) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', vanishedMessage))
            }
            player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, Int.MAX_VALUE, 0, false, false))
            player.addPotionEffect(PotionEffect(PotionEffectType.SATURATION, Int.MAX_VALUE, 0, false, false))
            for (onlinePlayer in plugin.server.onlinePlayers) {
                onlinePlayer.hidePlayer(plugin, player)
            }
        }
        return true
    }
}
