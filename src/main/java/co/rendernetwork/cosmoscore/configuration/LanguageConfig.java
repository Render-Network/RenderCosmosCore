package co.rendernetwork.cosmoscore.configuration;

import co.rendernetwork.cosmoscore.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LanguageConfig {

    private final Main instance = Main.getInstance();

    private File languageFile;
    private final FileConfiguration languageConfig = new YamlConfiguration();;

    public LanguageConfig() {}

    public FileConfiguration get() {
        return languageConfig;
    }

    public void createFile() {

        languageFile = new File(instance.getDataFolder(), "language.yml");

        if (!languageFile.exists()) {
            instance.saveResource("language.yml", false);
        }

        try {
            languageConfig.load(languageFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public void reload() {
        try {
            languageConfig.load(languageFile);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

}
