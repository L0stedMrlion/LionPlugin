package me.mrlion.lionplugin;

import me.mrlion.lionplugin.commands.*;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand(this));
        Objects.requireNonNull(getCommand("msg")).setExecutor(new MsgCommand(this));
        Objects.requireNonNull(getCommand("lreload")).setExecutor(new ReloadCommand(this));
        Objects.requireNonNull(getCommand("ping")).setExecutor(new PingCommand(this));

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
