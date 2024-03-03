package me.mrlion.lionplugin;

import me.mrlion.lionplugin.commands.*;
import me.mrlion.lionplugin.listeners.SitListener;
import org.bukkit.plugin.java.JavaPlugin;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("msg").setExecutor(new MsgCommand(this));
        getCommand("lreload").setExecutor(new ReloadCommand(this));
        getCommand("lwhitelist").setExecutor(new WhitelistCommand(this));


        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.JoinQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.BlockedCommandsListener(this), this);
        getServer().getPluginManager().registerEvents(new SitListener(this), this);
        getLogger().info("LionPlugin has been enabled!");

        saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("LionPlugin has been disabled!");
    }
}