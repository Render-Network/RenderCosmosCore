package co.rendernetwork.cosmoscore.language;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import org.bukkit.command.CommandSender;

public enum Lang {

    CHAT_SLOWMODE("chat_slowmode", ""),
    CHAT_CLEARED("chat_cleared", ""),
    CHAT_CLEARED_EXEMPT("chat_cleared_exempt", ""),
    CHAT_MUTED("chat_muted", ""),
    CHAT_UNMUTED("chat_unmuted", ""),
    CHAT_SLOWMODE_GET("chat_slowmode_get", ""),
    CHAT_SLOWMODE_PLAYER("chat_slowmode_player", ""),
    COMMANDSPY_ENABLED("commandspy_enabled", ""),
    COMMANDSPY_DISABLED("commandspy_disabled", ""),
    COMMANDSPY_MESSAGE("commandspy_message", ""),
    MODULE_NOT_FOUND("module_not_found", ""),
    MODULE_ENABLED("module_enabled", ""),
    MODULE_DISABLED("module_disabled", ""),
    FAILED_TO_PERFORM_COMMAND("failed_to_perform_command", "");

    Main instance = Main.getInstance();

    String path;
    String defaultMessage;

    Lang(String path, String defaultMessage) {
        this.path = path;
        this.defaultMessage = defaultMessage;
    }

    public String get() {
        String string = instance.getLanguage().get().getString(path);
        if (string == null) string = defaultMessage;
        return ColorUtil.format(string);
    }

    public String get(CommandSender sender) {
        String string = instance.getLanguage().get().getString(path);
        if (string == null) string = defaultMessage;
        return ColorUtil.format(string).replace("%player%", sender.getName());
    }

    public String getRaw() {
        String string = instance.getLanguage().get().getString(path);
        if (string == null) string = defaultMessage;
        return string;
    }

}
