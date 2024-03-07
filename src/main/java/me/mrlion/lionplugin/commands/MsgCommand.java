package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

import java.util.Objects;

public class MsgCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public MsgCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 2) {
            String msgUsage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("msg.usage")));
            player.sendMessage(msgUsage);
            return true;
        }

        Player recipient = Bukkit.getPlayer(args[0]);
        if (recipient == null) {
            String playerNotFound = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("msg.player-not-found")));
            player.sendMessage(playerNotFound);
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }

        String outgoingMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("msg.outgoing-message-format"))
                .replace("{sender}", player.getName())
                .replace("{receiver}", recipient.getName())
                .replace("{message}", message.toString()));
        player.sendMessage(outgoingMessage);

        String incomingMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("msg.incoming-message-format"))
                .replace("{sender}", player.getName())
                .replace("{receiver}", recipient.getName())
                .replace("{message}", message.toString()));
        recipient.sendMessage(incomingMessage);

        return true;
    }
}