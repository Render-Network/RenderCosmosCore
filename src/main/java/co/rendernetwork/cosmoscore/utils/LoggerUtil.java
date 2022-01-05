package co.rendernetwork.cosmoscore.utils;

import co.rendernetwork.cosmoscore.Main;

public class LoggerUtil {

    public static void log(String string) {
        Main.getInstance().getLogger().info(string);
    }

}
