package co.rendernetwork.cosmoscore.utils;

import co.rendernetwork.cosmoscore.Main;

public class LoggerUtil {

    public static void info(String string) {
        Main.getInstance().getLogger().info(string);
    }

    public static void warning(String string) {
        Main.getInstance().getLogger().warning(string);
    }

    public static void severe(String string) {
        Main.getInstance().getLogger().severe(string);
    }

}
