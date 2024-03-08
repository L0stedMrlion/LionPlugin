package me.mrlion.lionplugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class WhitelistListener implements Listener {

    private final FileConfiguration config;

    public WhitelistListener(JavaPlugin plugin) {
        this.config = plugin.getConfig();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String playerName = event.getPlayer().getName();
        if (event.getPlayer().isOp()) {
            return;
        }
        if (!config.getStringList("whitelist.whitelisted-players").contains(playerName)) {
            String kickMessage = Objects.requireNonNull(config.getString("whitelist.kick-message"));
            kickMessage = ChatColor.translateAlternateColorCodes('&', kickMessage);
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessage);
        }
    }
}
