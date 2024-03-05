package me.mrlion.lionplugin.listeners;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;

import java.util.List;
import java.util.Objects;

public class WhitelistListener implements Listener {

    private final LionPlugin plugin;

    public WhitelistListener(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("whitelist-enabled")) {
            String playerName = event.getPlayer().getName();
            if (!event.getPlayer().isOp() && !plugin.getConfig().getStringList("whitelisted-players").contains(playerName)) {
                List<String> kickMessages = plugin.getConfig().getStringList("whitelist-kick-message");
                for (String kickMessage : kickMessages) {
                    event.getPlayer().kickPlayer(ChatColor.translateAlternateColorCodes('&', kickMessage));
                }
            }
        }
    }
}
