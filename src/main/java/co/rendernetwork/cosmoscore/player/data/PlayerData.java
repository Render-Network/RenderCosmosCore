package co.rendernetwork.cosmoscore.player.data;

import co.rendernetwork.cosmoscore.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    public static final String LAST_KNOWN_NAME = "lastKnownName";
    public static final String COMMANDSPY_ENABLED = "commandSpyEnabled";

    private final Main instance = Main.getInstance();

    private final String lastKnownName;
    private final boolean commandSpyEnabled;

    public PlayerData(String lastKnownName, boolean commandSpyEnabled) {

        this.lastKnownName = lastKnownName;
        this.commandSpyEnabled = commandSpyEnabled;

    }

    public PlayerData(Map<String, Object> serializedMap) {

        this.lastKnownName = (String) serializedMap.get(LAST_KNOWN_NAME);
        this.commandSpyEnabled = (boolean) serializedMap.get(COMMANDSPY_ENABLED);

    }

    public String getLastKnownName() {
        return lastKnownName;
    }

    public boolean isCommandSpyEnabled() {
        return commandSpyEnabled;
    }

    public Map<String, Object> serialize() {

        final Map<String, Object> mapSerialize = new HashMap<>();

        mapSerialize.put(LAST_KNOWN_NAME, lastKnownName);
        mapSerialize.put(COMMANDSPY_ENABLED, commandSpyEnabled);

        return mapSerialize;

    }

}
