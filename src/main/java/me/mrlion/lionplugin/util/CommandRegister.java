package me.mrlion.lionplugin.util;

import me.mrlion.lionplugin.LionPlugin;
import me.mrlion.lionplugin.commands.FlyCommand;
import me.mrlion.lionplugin.commands.HealCommand;
import me.mrlion.lionplugin.commands.MsgCommand;
import me.mrlion.lionplugin.commands.PingCommand;
import me.mrlion.lionplugin.commands.ReloadCommand;

import java.util.Objects;

public class CommandRegister {

    public static void registerCommands(LionPlugin plugin) {
        Objects.requireNonNull(plugin.getCommand("fly")).setExecutor(new FlyCommand(plugin));
        Objects.requireNonNull(plugin.getCommand("heal")).setExecutor(new HealCommand(plugin));
        Objects.requireNonNull(plugin.getCommand("msg")).setExecutor(new MsgCommand(plugin));
        Objects.requireNonNull(plugin.getCommand("lreload")).setExecutor(new ReloadCommand(plugin));
        Objects.requireNonNull(plugin.getCommand("ping")).setExecutor(new PingCommand(plugin));
    }
}
