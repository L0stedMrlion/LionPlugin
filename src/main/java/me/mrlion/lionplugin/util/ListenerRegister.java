package me.mrlion.lionplugin.util;

import me.mrlion.lionplugin.listeners.BlockedCommandsListener;
import me.mrlion.lionplugin.listeners.JoinQuitListener;
import me.mrlion.lionplugin.listeners.SitListener;
import me.mrlion.lionplugin.listeners.InvalidCommandListener;
import org.bukkit.plugin.PluginManager;

public class ListenerRegister {

    public static void registerListeners() {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new BlockedCommandsListener(), plugin);
        pluginManager.registerEvents(new JoinQuitListener(), plugin);
        pluginManager.registerEvents(new SitListener(), plugin);
        pluginManager.registerEvents(new InvalidCommandListener(), plugin);
    }
}
