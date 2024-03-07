package me.mrlion.lionplugin.commands

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.util.*

class ReloadCommand(private val plugin: LionPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (!sender.hasPermission("lionplugin.reload")) {
            val noPermsMessage = Objects.requireNonNull(plugin.config.getString("other.no-perms"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
            if (noPermsMessage != null) {
                if (noPermsMessage.isEmpty()) {
                    sender.sendMessage(ChatColor.RED.toString() + "You don't have permission to use this command.")
                } else {
                    sender.sendMessage(noPermsMessage)
                }
            }
            return true
        }

        plugin.reloadConfig()
        val configReloadedMessage = Objects.requireNonNull(plugin.config.getString("reload.config-reloaded"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
        sender.sendMessage(configReloadedMessage)
        plugin.logger.info("Config was successfully reloaded")
        return true
    }
}

