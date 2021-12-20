package co.rendernetwork.cosmoscore.playerdata;

import org.bukkit.Bukkit;

import java.util.*;

public class PlayerDataHandler {

    private final Map<UUID, PlayerData> playerDataList = new HashMap<>();

    public PlayerData getPlayer(UUID uuid) {
        if (playerDataList.get(uuid) == null) {
            addPlayer(uuid);
        }
        return playerDataList.get(uuid);
    }

    public void addPlayer(UUID uuid) {
        playerDataList.remove(uuid);
        playerDataList.put(uuid, new PlayerData(uuid));
    }

    public void removePlayer(UUID uuid) {
        playerDataList.remove(uuid);
    }

    public void reload() {
        Bukkit.getOnlinePlayers().forEach(player ->
                addPlayer(player.getUniqueId())
        );
    }

}