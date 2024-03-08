package me.mrlion.lionplugin.listeners;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class BlockedCommandsListener implements Listener {
    private final LionPlugin plugin;

    public BlockedCommandsListener(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().substring(1);
        String[] parts = message.split(" ");
        String commandName = parts[0].toLowerCase(Locale.getDefault());

        if (plugin.getConfig().contains("blocked-commands")) {
            List<String> blockedCommands = plugin.getConfig().getStringList("blocked-commands");

            if (blockedCommands.contains(commandName)) {
                if (!event.getPlayer().hasPermission("lionplugin.blockedcommands")) {
                    event.setCancelled(true);
                    String noPermsMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("blocked-commands-no-perms")));
                    event.getPlayer().sendMessage(noPermsMessage);
                }
            }
        }
    }
}
