package me.mrlion.lionplugin.commands

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class HealCommand(private val plugin: LionPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender is Player) {
            val player = sender

            if (!player.hasPermission("lionplugin.heal")) {
                val noPermsMessage = Objects.requireNonNull(plugin.config.getString("heal.no-permissions"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
                if (noPermsMessage != null) {
                    if (noPermsMessage.isEmpty()) {
                        sender.sendMessage(ChatColor.RED.toString() + "You don't have permission to use this command.")
                    } else {
                        sender.sendMessage(noPermsMessage)
                    }
                }
                return true
            }

            if (args.size == 0) {
                player.health = 20.0
                player.sendMessage(Objects.requireNonNull(plugin.config.getString("heal.self-heal-message"))?.let { ChatColor.translateAlternateColorCodes('&', it) })
                plugin.logger.info(player.name + " has been healed.")
                return true
            }

            if (args[0].equals("all", ignoreCase = true)) {
                for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                    onlinePlayer.health = 20.0
                    onlinePlayer.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.config.getString("heal.other-heal-message")!!.replace("{healer}", player.name)))
                }
                plugin.logger.info("All players have been healed!")
                return true
            }

            val target = Bukkit.getPlayer(args[0])
            if (target == null) {
                player.sendMessage(ChatColor.RED.toString() + plugin.config.getString("heal.player-not-found-message"))
                return true
            }

            target.health = 20.0
            target.sendMessage(Objects.requireNonNull(plugin.config.getString("heal.other-heal-message"))?.let { ChatColor.translateAlternateColorCodes('&', it.replace("{healer}", player.name)) })
            plugin.logger.info(target.name + " has been healed by " + player.name)
        } else {
            if (args.isEmpty() || !args[0].equals("all", ignoreCase = true)) {
                sender.sendMessage(ChatColor.RED.toString() + plugin.config.getString("heal.console-usage-message"))
                return true
            }

            if (args[0].equals("all", ignoreCase = true)) {
                for (onlinePlayer in Bukkit.getOnlinePlayers()) {
                    onlinePlayer.health = 20.0
                    onlinePlayer.sendMessage(Objects.requireNonNull(plugin.config.getString("heal.other-heal-message"))?.let { ChatColor.translateAlternateColorCodes('&', it.replace("{healer}", "Console")) })
                }
                plugin.logger.info("All players have been healed!")
                return true
            }
        }

        return true
    }
}
