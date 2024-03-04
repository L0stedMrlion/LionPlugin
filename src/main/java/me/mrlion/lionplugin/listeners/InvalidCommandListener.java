package me.mrlion.lionplugin.listeners;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.ChatColor;

import java.util.Objects;

public class InvalidCommandListener implements Listener {

    private final LionPlugin plugin;

    public InvalidCommandListener(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String commandName = event.getMessage().split(" ")[0].substring(1); // Get the command name
        Command command = event.getPlayer().getServer().getPluginCommand(commandName);

        if (command == null) {
            event.setCancelled(true);
            String customMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("invalidmessage.custom-command-not-found-message")));
            event.getPlayer().sendMessage(customMessage);
        }
    }
}
