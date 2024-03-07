package me.mrlion.lionplugin.listeners

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import java.util.*

class BlockedCommandsListener(private val plugin: LionPlugin) : Listener {
    @EventHandler
    fun onCommandPreprocess(event: PlayerCommandPreprocessEvent) {
        val message = event.message.substring(1)
        val parts = message.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val commandName = parts[0].lowercase(Locale.getDefault())

        val blockedCommands = plugin.config.getStringList("blocked-commands")

        if (blockedCommands.contains(commandName)) {
            if (!event.player.hasPermission("lionplugin.blockedcommands")) {
                event.isCancelled = true
                val noPermsMessage = Objects.requireNonNull(plugin.config.getString("blocked-commands-no-perms"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
                event.player.sendMessage(noPermsMessage)
            }
        }
    }
}
