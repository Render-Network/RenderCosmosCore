package co.rendernetwork.cosmoscore;

import co.rendernetwork.cosmoscore.commands.CommandManager;
import co.rendernetwork.cosmoscore.configuration.LanguageConfig;
import co.rendernetwork.cosmoscore.configuration.SettingsConfig;
import co.rendernetwork.cosmoscore.listeners.ListenerManager;
import co.rendernetwork.cosmoscore.handlers.ChatHandler;
import co.rendernetwork.cosmoscore.playerdata.PlayerDataManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private LanguageConfig language;
    private SettingsConfig settings;
    private ChatHandler chatManager;
    private PlayerDataManager playerDataManager;

    @Override
    public void onEnable() {

        instance = this;
        language = new LanguageConfig();
        settings = new SettingsConfig();

        createConfigs();

        chatManager = new ChatHandler();
        playerDataManager = new PlayerDataManager();
        playerDataManager.reload();

        new CommandManager();
        new ListenerManager();

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }

    public LanguageConfig getLanguage() {
        return language;
    }

    public SettingsConfig getSettings() {
        return settings;
    }

    public ChatHandler getChatManager() {
        return chatManager;
    }

    public PlayerDataManager getPlayerData() {
        return playerDataManager;
    }

    public void createConfigs() {
        language.createFile();
        settings.createFile();
        saveDefaultConfig();
    }

}
