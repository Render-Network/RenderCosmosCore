package co.rendernetwork.cosmoscore;

import co.rendernetwork.cosmoscore.commands.CommandManager;
import co.rendernetwork.cosmoscore.configuration.LanguageConfig;
import co.rendernetwork.cosmoscore.configuration.SettingsConfig;
import co.rendernetwork.cosmoscore.entity.UserAutoSave;
import co.rendernetwork.cosmoscore.handlers.HandlerManager;
import co.rendernetwork.cosmoscore.events.ListenerManager;
import co.rendernetwork.cosmoscore.entity.User;
import co.rendernetwork.cosmoscore.modules.ModuleManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private LanguageConfig language;
    private SettingsConfig settings;
    private HandlerManager handlerManager;
    private ModuleManager moduleManager;
    private CommandManager commandManager;

    @Override
    public void onEnable() {

        instance = this;

        // Configs
        language = new LanguageConfig();
        settings = new SettingsConfig();

        // Handlers
        handlerManager = new HandlerManager();

        // Configs
        initializeConfigs();

        // Commands and Listeners
        commandManager = new CommandManager();
        new ListenerManager();

        reload();

        // Autosave
        new UserAutoSave();

        // Modules
        moduleManager = new ModuleManager();
        moduleManager.loadAllModules();

    }

    @Override
    public void onDisable() {
        User.saveAll();
        moduleManager.unloadAllModules();
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

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void initializeConfigs() {
        language.createIfNotExists();
        settings.createIfNotExists();
        saveDefaultConfig();
    }

}
