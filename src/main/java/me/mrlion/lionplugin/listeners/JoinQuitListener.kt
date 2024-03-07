package me.mrlion.lionplugin.listeners

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

class JoinQuitListener(private val plugin: LionPlugin) : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val playerName = event.player.name
        var joinMessage = Objects.requireNonNull(plugin.config.getString("join-message"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
        if (joinMessage != null) {
            joinMessage = joinMessage.replace("{player}", playerName)
        }
        event.joinMessage = joinMessage
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val playerName = event.player.name
        var quitMessage = Objects.requireNonNull(plugin.config.getString("quit-message"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
        if (quitMessage != null) {
            quitMessage = quitMessage.replace("{player}", playerName)
        }
        event.quitMessage = quitMessage
    }
}
