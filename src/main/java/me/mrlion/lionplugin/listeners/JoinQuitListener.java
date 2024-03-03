package me.mrlion.lionplugin.listeners;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.ChatColor;

public class JoinQuitListener implements Listener {

    private final LionPlugin plugin;

    public JoinQuitListener(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getName();
        String joinMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-message"));
        joinMessage = joinMessage.replace("{player}", playerName);
        event.setJoinMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        String playerName = event.getPlayer().getName();
        String quitMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("quit-message"));
        quitMessage = quitMessage.replace("{player}", playerName);
        event.setQuitMessage(quitMessage);
    }
}
