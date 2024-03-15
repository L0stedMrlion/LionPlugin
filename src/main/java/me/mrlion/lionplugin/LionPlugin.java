package me.mrlion.lionplugin;

import org.bukkit.plugin.java.JavaPlugin;
import me.mrlion.lionplugin.util.CommandRegister;
import me.mrlion.lionplugin.util.ListenerRegister;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        try {
            CommandRegister.registerCommands(this);
            ListenerRegister.registerListeners(this);
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
