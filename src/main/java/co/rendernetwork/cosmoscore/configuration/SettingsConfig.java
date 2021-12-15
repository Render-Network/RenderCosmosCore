package co.rendernetwork.cosmoscore.configuration;

import co.rendernetwork.cosmoscore.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SettingsConfig {

    private final Main instance = Main.getInstance();

    private File settingsFile;
    private final FileConfiguration settingsConfig = new YamlConfiguration();;

    public SettingsConfig() {}

    public FileConfiguration get() {
        return settingsConfig;
    }

    public void createFile() {

        settingsFile = new File(instance.getDataFolder(), "settings.yml");

        if (!settingsFile.exists()) {
            instance.saveResource("settings.yml", false);
        }

        try {
            settingsConfig.load(settingsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public void reload() {
        try {
            settingsConfig.load(settingsFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            settingsConfig.save(settingsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
