package me.mrlion.lionplugin.commands

import me.mrlion.lionplugin.LionPlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class MsgCommand(private val plugin: LionPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(ChatColor.RED.toString() + "This command cant be used by console!")
            return true
        }

        val player = sender

        if (args.size < 2) {
            val msgUsage = Objects.requireNonNull(plugin.config.getString("msg.usage"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
            player.sendMessage(msgUsage)
            return true
        }

        val recipient = Bukkit.getPlayer(args[0])
        if (recipient == null) {
            val playerNotFound = Objects.requireNonNull(plugin.config.getString("msg.player-not-found"))?.let { ChatColor.translateAlternateColorCodes('&', it) }
            player.sendMessage(playerNotFound)
            return true
        }

        val message = StringBuilder()
        for (i in 1 until args.size) {
            message.append(args[i]).append(" ")
        }

        val outgoingMessage = Objects.requireNonNull(plugin.config.getString("msg.outgoing-message-format"))
                ?.replace("{sender}", player.name)
                ?.replace("{receiver}", recipient.name)?.let {
                    ChatColor.translateAlternateColorCodes('&', it
                            .replace("{message}", message.toString()))
                }
        player.sendMessage(outgoingMessage)

        val incomingMessage = Objects.requireNonNull(plugin.config.getString("msg.incoming-message-format"))
                ?.replace("{sender}", player.name)
                ?.replace("{receiver}", recipient.name)?.let {
                    ChatColor.translateAlternateColorCodes('&', it
                            .replace("{message}", message.toString()))
                }
        recipient.sendMessage(incomingMessage)

        return true
    }
}