package co.rendernetwork.cosmoscore.configuration;

import org.bukkit.configuration.file.FileConfiguration;

public interface Config {

    FileConfiguration get();
    void createIfNotExists();
    void reload();
    void save();

}
