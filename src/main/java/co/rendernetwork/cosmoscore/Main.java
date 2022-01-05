package co.rendernetwork.cosmoscore;

import co.rendernetwork.cosmoscore.commands.CommandManager;
import co.rendernetwork.cosmoscore.configuration.LanguageConfig;
import co.rendernetwork.cosmoscore.configuration.SettingsConfig;
import co.rendernetwork.cosmoscore.entity.UserAutoSave;
import co.rendernetwork.cosmoscore.handlers.HandlerManager;
import co.rendernetwork.cosmoscore.events.ListenerManager;
import co.rendernetwork.cosmoscore.entity.User;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private LanguageConfig language;
    private SettingsConfig settings;
    private HandlerManager handlerManager;

    @Override
    public void onEnable() {

        instance = this;
        language = new LanguageConfig();
        settings = new SettingsConfig();

        handlerManager = new HandlerManager();

        createConfigs();

        new CommandManager();
        new ListenerManager();

        reload();
        new UserAutoSave();

    }

    @Override
    public void onDisable() {
        User.saveAll();
        instance = null;
    }

    private void reload() {
        User.reloadAllUserData();
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

    public HandlerManager getHandlerManager() {
        return handlerManager;
    }

    public void createConfigs() {
        language.createIfNotExists();
        settings.createIfNotExists();
        saveDefaultConfig();
    }

}
