package me.mrlion.lionplugin.listeners;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.ChatColor;

import java.util.List;

public class BlockedCommandsListener implements Listener {

    private final LionPlugin plugin;

    public BlockedCommandsListener(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage().substring(1);
        String[] parts = message.split(" ");
        String commandName = parts[0].toLowerCase();

        List<String> blockedCommands = plugin.getConfig().getStringList("blocked-commands");

        if (blockedCommands.contains(commandName)) {
            if (!event.getPlayer().hasPermission("lionplugin.blockedcommands." + commandName)) {
                event.setCancelled(true);
                String noPermsMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("blocked-commands-no-perms"));
                event.getPlayer().sendMessage(noPermsMessage);
            }
        }
    }
}
