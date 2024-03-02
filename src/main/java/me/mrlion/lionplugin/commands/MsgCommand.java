package me.mrlion.lionplugin.commands;

import me.mrlion.lionplugin.LionPlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MsgCommand implements CommandExecutor {

    private final LionPlugin plugin;

    public MsgCommand(LionPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        // Check if the correct number of arguments is provided
        if (args.length < 2) {
            player.sendMessage("Usage: /msg <player> <message>");
            return true;
        }

        // Get the recipient player
        Player recipient = Bukkit.getPlayer(args[0]);
        if (recipient == null) {
            player.sendMessage("Player not found!");
            return true;
        }

        // Concatenate the message
        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            message.append(args[i]).append(" ");
        }

        String outgoingMessage = plugin.getConfig().getString("msg.outgoing-message-format")
                .replace("{sender}", player.getName())
                .replace("{receiver}", recipient.getName())
                .replace("{message}", message.toString());
        player.sendMessage(outgoingMessage);

        String incomingMessage = plugin.getConfig().getString("msg.incoming-message-format")
                .replace("{sender}", player.getName())
                .replace("{receiver}", recipient.getName())
                .replace("{message}", message.toString());
        recipient.sendMessage(incomingMessage);

        return true;
    }
}
