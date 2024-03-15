package me.mrlion.lionplugin;

import org.bukkit.plugin.java.JavaPlugin;

import static me.mrlion.lionplugin.util.CommandRegister.registerCommands;
import static me.mrlion.lionplugin.util.ListenerRegister.registerListeners;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            registerCommands();
            registerListeners();
            getConfig();
            saveDefaultConfig();
            getLogger().info("LionPlugin has been enabled!");
        } catch (Exception e) {
            getLogger().severe("Error occurred while enabling LionPlugin:");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("LionPlugin has been disabled!");
    }
}
