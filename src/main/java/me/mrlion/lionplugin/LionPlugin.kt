package me.mrlion.lionplugin

import me.mrlion.lionplugin.commands.*
import me.mrlion.lionplugin.listeners.BlockedCommandsListener
import me.mrlion.lionplugin.listeners.InvalidCommandListener
import me.mrlion.lionplugin.listeners.JoinQuitListener
import me.mrlion.lionplugin.listeners.SitListener
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

class LionPlugin : JavaPlugin() {
    var vanishedPlayers: MutableSet<UUID>? = null
        private set

    override fun onEnable() {
        vanishedPlayers = HashSet()

        Objects.requireNonNull(getCommand("fly"))?.setExecutor(FlyCommand(this))
        Objects.requireNonNull(getCommand("heal"))?.setExecutor(HealCommand(this))
        Objects.requireNonNull(getCommand("msg"))?.setExecutor(MsgCommand(this))
        Objects.requireNonNull(getCommand("lreload"))?.setExecutor(ReloadCommand(this))
        Objects.requireNonNull(getCommand("ping"))?.setExecutor(PingCommand(this))
        Objects.requireNonNull(getCommand("vanish"))?.setExecutor(VanishCommand(this))

        server.pluginManager.registerEvents(JoinQuitListener(this), this)
        server.pluginManager.registerEvents(BlockedCommandsListener(this), this)
        server.pluginManager.registerEvents(SitListener(this), this)
        server.pluginManager.registerEvents(InvalidCommandListener(this), this)

        logger.info("LionPlugin has been enabled!")
        saveDefaultConfig()
        reloadConfig()
    }

    override fun onDisable() {
        logger.info("LionPlugin has been disabled!")
    }
}
