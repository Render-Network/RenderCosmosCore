package co.rendernetwork.cosmoscore.configuration;

import org.bukkit.configuration.file.FileConfiguration;

public interface IConfig {

    FileConfiguration get();
    void createIfNotExists();
    void reload();
    void save();

}
