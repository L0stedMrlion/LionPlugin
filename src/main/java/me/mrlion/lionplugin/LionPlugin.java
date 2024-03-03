package me.mrlion.lionplugin;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bstats.bukkit.Metrics;

public class LionPlugin extends JavaPlugin {
    private Set<UUID> vanishedPlayers;
    @Override
    public void onEnable() {

        int pluginId = 21197;
        Metrics metrics = new Metrics(this, pluginId);

        vanishedPlayers = new HashSet<>();

        getCommand("fly").setExecutor(new me.mrlion.lionplugin.commands.FlyCommand(this));
        getCommand("heal").setExecutor(new me.mrlion.lionplugin.commands.HealCommand(this));
        getCommand("msg").setExecutor(new me.mrlion.lionplugin.commands.MsgCommand(this));
        getCommand("lreload").setExecutor(new me.mrlion.lionplugin.commands.ReloadCommand(this));
        getCommand("lwhitelist").setExecutor(new me.mrlion.lionplugin.commands.WhitelistCommand(this));
        getCommand("ping").setExecutor(new me.mrlion.lionplugin.commands.PingCommand(this));
        getCommand("vanish").setExecutor(new me.mrlion.lionplugin.commands.VanishCommand(this));

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

    public Set<UUID> getVanishedPlayers() {
        return vanishedPlayers;
    }
}
