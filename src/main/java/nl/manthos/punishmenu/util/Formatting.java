package nl.manthos.punishmenu.util;

import org.bukkit.ChatColor;

public class Formatting {

    public static String format(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
