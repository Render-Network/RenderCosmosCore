package co.rendernetwork.cosmoscore.player;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.player.data.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.bukkit.Bukkit.getPlayer;

public class CPlayer {

    Main instance = Main.getInstance();

    File file;
    YamlConfiguration config = new YamlConfiguration();
    PlayerData playerData;

    private final UUID uuid;

    @SuppressWarnings("unchecked")
    public CPlayer(UUID uuid) {

        this.uuid = uuid;

        this.file = new File(instance.getDataFolder() + File.separator + "playerdata", uuid.toString() + ".yml");

        if (exists()) {

            try {
                config.load(file);
            } catch (InvalidConfigurationException | IOException e) {
                e.printStackTrace();
            }

            try {
                playerData = new PlayerData((Map<String, Object>) Objects.requireNonNull(config.get(uuid.toString())));
            } catch (NullPointerException e) {
                Bukkit.getLogger().info("Failed to load Player Data for " + uuid + ".");
            }

        }

    }

    private boolean exists() {
        return file.exists();
    }

    public void createIfNotExists() {
        playerData = new PlayerData("None", false);
        save();
    }

    public void updatePlayerData(PlayerData playerData) {
        save();
        this.playerData = playerData;
    }

    public UUID getUniqueId() {
        return uuid;
    }

    public PlayerData getPlayerData() {
        return playerData;
    }

    private void save() {
        config.set(uuid.toString(), playerData.serialize());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // STATICS - It's not static abuse if it's actually supposed to be used this way.

    private static final List<CPlayer> cPlayerMap = new ArrayList<>();

    public static List<CPlayer> getPlayers() {
        return cPlayerMap;
    }

    public static CPlayer getPlayer(Player player) {
        boolean online = player != null;
        if (!online) return null;
        for (CPlayer cPlayer : cPlayerMap) {
            if (cPlayer.getUniqueId().equals(player.getUniqueId())) return cPlayer;
        }
        return null;
    }

    public static CPlayer getPlayer(UUID uuid) {
        boolean online = Bukkit.getPlayer(uuid) != null;
        if (!online) return null;
        for (CPlayer cPlayer : cPlayerMap) {
            if (cPlayer.getUniqueId().equals(uuid)) return cPlayer;
        }
        return null;
    }

}
