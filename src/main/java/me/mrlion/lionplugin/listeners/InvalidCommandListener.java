package me.mrlion.lionplugin.listeners;

import org.bukkit.command.Command;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class InvalidCommandListener implements Listener {
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String[] parts = event.getMessage().split(" ");
        String commandName = parts.length > 0 ? parts[0].substring(1) : "";

        Command command = event.getPlayer().getServer().getPluginCommand(commandName);

        if (command == null) {
            event.setCancelled(true);
        }
    }
}
