package me.mrlion.lionplugin.util;

import net.md_5.bungee.api.ChatColor;

public class ColorUtils {

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}