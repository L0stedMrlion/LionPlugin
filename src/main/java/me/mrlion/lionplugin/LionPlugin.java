package me.mrlion.lionplugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("fly")).setExecutor(new me.mrlion.lionplugin.commands.FlyCommand(this));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new me.mrlion.lionplugin.commands.HealCommand(this));
        Objects.requireNonNull(getCommand("msg")).setExecutor(new me.mrlion.lionplugin.commands.MsgCommand(this));
        Objects.requireNonNull(getCommand("lreload")).setExecutor(new me.mrlion.lionplugin.commands.ReloadCommand(this));
        Objects.requireNonNull(getCommand("ping")).setExecutor(new me.mrlion.lionplugin.commands.PingCommand(this));

        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.BlockedCommandsListener(this), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.JoinQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.SitListener(), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.InvalidCommandListener(), this);
        getServer().getPluginManager().registerEvents(new me.mrlion.lionplugin.listeners.WhitelistListener(this), this);

        getLogger().info("LionPlugin has been enabled!");
        saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {

        getLogger().info("LionPlugin has been disabled!");
    }
}

