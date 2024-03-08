package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PingCommand implements CommandExecutor {
    private final LionPlugin plugin;

    public PingCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can't be used by console!");
            return true;
        }

        Player player = (Player) sender;
        String pingMessage = plugin.getConfig().getString("ping.ping-message");

        if (pingMessage == null || pingMessage.isEmpty()) {
            pingMessage = "&aYour ping is: &b" + getPing(player) + "ms";
        } else {
            pingMessage = ChatColor.translateAlternateColorCodes('&', pingMessage);
            pingMessage = pingMessage.replace("{ping}", String.valueOf(getPing(player)));
        }

        player.sendMessage(pingMessage);
        return true;
    }

    private int getPing(Player player) {
        return player.getPing();
    }
}
