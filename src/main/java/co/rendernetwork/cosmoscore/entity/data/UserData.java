package co.rendernetwork.cosmoscore.entity.data;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserData {

    public static final String LAST_KNOWN_NAME = "lastKnownName";
    public static final String COMMANDSPY_ENABLED = "commandSpyEnabled";

    private String lastKnownName;
    private boolean commandSpyEnabled;

    public UserData(String lastKnownName, boolean commandSpyEnabled) {

        this.lastKnownName = lastKnownName;
        this.commandSpyEnabled = commandSpyEnabled;

    }

    public String getLastKnownName() {
        return lastKnownName;
    }

    public void setLastKnownName(String lastKnownName) {
        this.lastKnownName = lastKnownName;
    }

    public boolean isCommandSpyEnabled() {
        return commandSpyEnabled;
    }

    public void setCommandSpyEnabled(boolean state) {
        commandSpyEnabled = state;
    }

    public Map<String, Object> serialize() {

        final Map<String, Object> mapSerialize = new HashMap<>();

        mapSerialize.put(LAST_KNOWN_NAME, lastKnownName);
        mapSerialize.put(COMMANDSPY_ENABLED, commandSpyEnabled);

        return mapSerialize;

    }

    // STATICS

    public static UserData deserialize(File file, UUID uuid) {

        FileConfiguration config = new YamlConfiguration();
        if (!file.exists()) return null;

        try {
            config.load(file);
        } catch (InvalidConfigurationException | IOException e) {
            e.printStackTrace();
        }

        String lastKnownName = config.getString(uuid + "." + UserData.LAST_KNOWN_NAME);
        boolean commandSpyEnabled = config.getBoolean(uuid + "." + UserData.COMMANDSPY_ENABLED);

        return new UserData(lastKnownName, commandSpyEnabled);

    }

}
