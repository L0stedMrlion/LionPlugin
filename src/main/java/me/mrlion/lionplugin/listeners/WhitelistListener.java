package me.mrlion.lionplugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public class WhitelistListener implements Listener {

    private final JavaPlugin plugin;
    private List<String> whitelistedPlayers;
    private String kickMessage;

    public WhitelistListener(JavaPlugin plugin) {
        this.plugin = plugin;
        this.reloadConfig();
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void reloadConfig() {
        FileConfiguration config = plugin.getConfig();
        whitelistedPlayers = config.getStringList("whitelist.whitelisted-players");
        kickMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(config.getString("whitelist.kick-message")));
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        String playerName = event.getPlayer().getName();
        if (event.getPlayer().isOp() || whitelistedPlayers.contains(playerName)) {
            return;
        }
        event.disallow(PlayerLoginEvent.Result.KICK_OTHER, kickMessage);
    }
}
