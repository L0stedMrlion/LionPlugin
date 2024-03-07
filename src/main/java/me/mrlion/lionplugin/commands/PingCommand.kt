package me.mrlion.lionplugin.commands

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PingCommand(private val plugin: LionPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(ChatColor.RED.toString() + "This command cant be used by console!")
            return true
        }
        val player = sender

        var pingMessage = plugin.config.getString("ping.ping-message")

        if (pingMessage.isNullOrEmpty()) {
            pingMessage = "&aYour ping is: &b" + getPing(player) + "ms"
        } else {
            pingMessage = ChatColor.translateAlternateColorCodes('&', pingMessage)
            pingMessage = pingMessage.replace("{ping}", getPing(player).toString())
        }

        player.sendMessage(pingMessage)

        return true
    }

    private fun getPing(player: Player): Int {
        return player.ping
    }
}
