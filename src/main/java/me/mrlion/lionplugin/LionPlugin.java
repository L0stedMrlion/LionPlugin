package me.mrlion.lionplugin;

import org.bukkit.plugin.java.JavaPlugin;
import me.mrlion.lionplugin.util.CommandRegister;
import me.mrlion.lionplugin.util.ListenerRegister;

import java.util.logging.Level;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            CommandRegister.registerCommands(this);
            ListenerRegister.registerListeners(this);
            getConfig();
            saveDefaultConfig();
            getLogger().info("LionPlugin has been enabled!");
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Error occurred while enabling LionPlugin:", e);
            getLogger().log(Level.SEVERE, "Plugin was disabled for your own safety!");
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("LionPlugin has been disabled!");
    }
}
