package co.rendernetwork.cosmoscore.configuration;

import co.rendernetwork.cosmoscore.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LanguageConfig implements IConfig {

    private final Main instance = Main.getInstance();

    private File file;
    private final FileConfiguration config = new YamlConfiguration();;

    @Override
    public FileConfiguration get() {
        return config;
    }

    @Override
    public void createIfNotExists() {

        file = new File(instance.getDataFolder(), "language.yml");

        if (!file.exists()) {
            instance.saveResource("language.yml", false);
        }

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void reload() {
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void save() {
        try {
            config.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
