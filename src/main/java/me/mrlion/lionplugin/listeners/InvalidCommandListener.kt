package me.mrlion.lionplugin.listeners

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.command.Command
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class InvalidCommandListener(plugin: LionPlugin?) : Listener {
    @EventHandler
    fun onPlayerCommand(event: PlayerCommandPreprocessEvent) {
        val commandName = event.message.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0].substring(1)
        val command: Command? = event.player.server.getPluginCommand(commandName)

        if (command == null) {
            event.isCancelled = true
        }
    }
}
