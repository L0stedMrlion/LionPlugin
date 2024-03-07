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

        if (!sender.hasPermission("lionplugin.vanish")) {
            val noPermsMessage = plugin.config.getString("vanish.no-permissions")
            if (!noPermsMessage.isNullOrEmpty()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', noPermsMessage))
            } else {
                sender.sendMessage(ChatColor.RED.toString() + "You don't have permission to use this command!")
            }
            return true
        }

        val vanishedPlayers = plugin.vanishedPlayers ?: return true
        val vanishedMessage = plugin.config.getString("vanish.vanished-message")
        val unvanishedMessage = plugin.config.getString("vanish.unvanished-message")

        if (vanishedPlayers.contains(sender.uniqueId)) {
            vanishedPlayers.remove(sender.uniqueId)
            if (!unvanishedMessage.isNullOrEmpty()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', unvanishedMessage))
            }
            sender.removePotionEffect(PotionEffectType.NIGHT_VISION)
            sender.removePotionEffect(PotionEffectType.SATURATION)
            for (onlinePlayer in plugin.server.onlinePlayers) {
                onlinePlayer.showPlayer(plugin, sender)
            }
        } else {
            vanishedPlayers.add(sender.uniqueId)
            if (!vanishedMessage.isNullOrEmpty()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', vanishedMessage))
            }
            sender.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, Int.MAX_VALUE, 0, false, false))
            sender.addPotionEffect(PotionEffect(PotionEffectType.SATURATION, Int.MAX_VALUE, 0, false, false))
            for (onlinePlayer in plugin.server.onlinePlayers) {
                onlinePlayer.hidePlayer(plugin, sender)
            }
        }
        return true
    }
}
