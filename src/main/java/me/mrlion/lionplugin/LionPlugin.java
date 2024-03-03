package me.mrlion.lionplugin;

import org.bukkit.plugin.java.JavaPlugin;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new me.mrlion.lionplugin.commands.FlyCommand(this));
        getCommand("heal").setExecutor(new me.mrlion.lionplugin.commands.HealCommand(this));
        getCommand("msg").setExecutor(new me.mrlion.lionplugin.commands.MsgCommand(this));
        getCommand("lreload").setExecutor(new me.mrlion.lionplugin.commands.ReloadCommand(this));
        getCommand("lwhitelist").setExecutor(new me.mrlion.lionplugin.commands.WhitelistCommand(this));
        getCommand("ping").setExecutor(new me.mrlion.lionplugin.commands.PingCommand(this));


        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.JoinQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.BlockedCommandsListener(this), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.WhitelistListener(this), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.SitListener(this), this);
        getLogger().info("LionPlugin has been enabled!");

        saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("LionPlugin has been disabled!");
    }
}