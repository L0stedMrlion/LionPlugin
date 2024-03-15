package me.mrlion.lionplugin.util;

import org.bukkit.ChatColor;

public class ColorUtils {

    /**
     * Colorizes a string containing hex color codes.
     *
     * @param message The message containing hex color codes.
     * @return The colorized message.
     */
    public static String colorize(String message) {
        char[] hexDigits = message.toCharArray();
        StringBuilder colorizedMessage = new StringBuilder();
        StringBuilder hexCode = new StringBuilder();

        for (int i = 0; i < hexDigits.length; i++) {
            char currentChar = hexDigits[i];

            if (currentChar == '&') {
                if (i + 1 < hexDigits.length && hexDigits[i + 1] == '#') {
                    hexCode.append(currentChar);
                    hexCode.append(hexDigits[i + 1]);
                    i++;
                } else {
                    colorizedMessage.append(ChatColor.COLOR_CHAR);
                    colorizedMessage.append(currentChar);
                }
            } else if (!hexCode.isEmpty() && isHexDigit(currentChar)) {
                hexCode.append(currentChar);
                if (hexCode.length() == 7) { // Reached the end of the hex color code
                    colorizedMessage.append(ChatColor.valueOf(hexCode.toString()));
                    hexCode.setLength(0); // Reset the hex code StringBuilder
                }
            } else {
                colorizedMessage.append(currentChar);
            }
        }

        return colorizedMessage.toString();
    }

    /**
     * Checks if a character is a valid hex digit (0-9, A-F, a-f).
     *
     * @param c The character to check.
     * @return True if the character is a hex digit, false otherwise.
     */
    private static boolean isHexDigit(char c) {
        return Character.isDigit(c) || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
}
