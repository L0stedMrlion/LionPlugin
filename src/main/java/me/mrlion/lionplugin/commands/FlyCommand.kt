package me.mrlion.lionplugin.commands

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class FlyCommand(private val plugin: LionPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(ChatColor.RED.toString() + "This command cant be used by console!")
            return true
        }

        if (!sender.hasPermission("lionplugin.fly")) {
            val noPermsMessage = Objects.requireNonNull(plugin.config.getString("fly.no-permissions"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
            if (noPermsMessage != null) {
                if (noPermsMessage.isEmpty()) {
                    sender.sendMessage(ChatColor.RED.toString() + "You don't have permission to use this command!")
                } else {
                    sender.sendMessage(noPermsMessage)
                }
            }
            return true
        }

        if (sender.allowFlight) {
            sender.allowFlight = false
            plugin.logger.info(sender.name + " has disabled flight mode.")
        } else {
            sender.allowFlight = true
            plugin.logger.info(sender.name + " has enabled flight mode.")
        }

        return true
    }
}
