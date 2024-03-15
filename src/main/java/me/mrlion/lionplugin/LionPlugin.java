package me.mrlion.lionplugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.logging.Level;
import static me.mrlion.lionplugin.util.CommandRegister.registerCommands;
import static me.mrlion.lionplugin.util.ListenerRegister.registerListeners;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            registerCommands(this);
            registerListeners(this);
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
