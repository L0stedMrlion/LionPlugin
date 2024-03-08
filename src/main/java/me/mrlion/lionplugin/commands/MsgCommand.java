package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MsgCommand implements CommandExecutor {
    private final LionPlugin plugin;

    public MsgCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can't be used by console!");
            return true;
        }

        if (args.length < 2) {
            String msgUsage = Objects.requireNonNull(plugin.getConfig().getString("msg.usage"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msgUsage));
            return true;
        }

        Player recipient = Bukkit.getPlayer(args[0]);
        if (recipient == null) {
            String playerNotFound = Objects.requireNonNull(plugin.getConfig().getString("msg.player-not-found"));
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound));
            return true;
        }

        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            messageBuilder.append(args[i]).append(" ");
        }
        String message = messageBuilder.toString().trim();

        String outgoingMessage = Objects.requireNonNull(plugin.getConfig().getString("msg.outgoing-message-format"))
                .replace("{sender}", sender.getName())
                .replace("{receiver}", recipient.getName())
                .replace("{message}", message);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', outgoingMessage));

        String incomingMessage = Objects.requireNonNull(plugin.getConfig().getString("msg.incoming-message-format"))
                .replace("{sender}", sender.getName())
                .replace("{receiver}", recipient.getName())
                .replace("{message}", message);
        recipient.sendMessage(ChatColor.translateAlternateColorCodes('&', incomingMessage));

        return true;
    }
}
