package me.mrlion.lionplugin.util;

import me.mrlion.lionplugin.LionPlugin;
import me.mrlion.lionplugin.listeners.JoinQuitListener;
import me.mrlion.lionplugin.listeners.SitListener;
import org.bukkit.plugin.PluginManager;

public class ListenerRegister {

    public static void registerListeners(LionPlugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new JoinQuitListener(plugin), plugin);
        pluginManager.registerEvents(new SitListener(), plugin);
    }
}
