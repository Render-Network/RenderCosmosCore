package co.rendernetwork.cosmoscore.utils;

import org.bukkit.ChatColor;

public final class ColorUtil {
    public static String format(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
