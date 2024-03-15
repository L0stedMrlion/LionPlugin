package me.mrlion.lionplugin.listeners;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class JoinQuitListener implements Listener {
    private final LionPlugin plugin;

    public JoinQuitListener() {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        String joinMessage = plugin.getConfig().getString("join-message");
        if (joinMessage != null) {
            joinMessage = ChatColor.translateAlternateColorCodes('&', joinMessage.replace("{player}", playerName));
        }
        event.setJoinMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        String quitMessage = plugin.getConfig().getString("quit-message");
        if (quitMessage != null) {
            quitMessage = ChatColor.translateAlternateColorCodes('&', quitMessage.replace("{player}", playerName));
        }
        event.setQuitMessage(quitMessage);
    }
}
