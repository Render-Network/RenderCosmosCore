package co.rendernetwork.cosmoscore.playerdata;

import co.rendernetwork.cosmoscore.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerData {

    Main instance = Main.getInstance();

    UUID uuid;
    private File file;
    private final FileConfiguration config = new YamlConfiguration();

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public FileConfiguration getPlayerData() {
        return config;
    }

    public void createFile() {

        file = new File(instance.getDataFolder() + File.separator + "playerdata", uuid.toString() + ".yml");

        if (!file.exists()) {
            try {
                file.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            config.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }
    }

}
