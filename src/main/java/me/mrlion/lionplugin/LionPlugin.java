package me.mrlion.lionplugin;

import me.mrlion.lionplugin.commands.FlyCommand;
import me.mrlion.lionplugin.commands.HealCommand;
import me.mrlion.lionplugin.commands.MsgCommand;
import me.mrlion.lionplugin.commands.ReloadCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class LionPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("msg").setExecutor(new MsgCommand(this));
        getCommand("lreload").setExecutor(new ReloadCommand(this));
        getLogger().info("\uD83E\uDD81 LionPlugin has been enabled!");
        saveDefaultConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("🦁 LionPlugin has been disabled!");
    }
}
