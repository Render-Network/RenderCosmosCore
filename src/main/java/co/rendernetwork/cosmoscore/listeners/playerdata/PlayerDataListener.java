package co.rendernetwork.cosmoscore.listeners.playerdata;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.playerdata.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerDataListener implements Listener {

    Main instance = Main.getInstance();

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {

        UUID uuid = event.getUniqueId();

        instance.getHandlerManager().getPlayerData().addPlayer(uuid);
        PlayerData playerData = instance.getHandlerManager().getPlayerData().getPlayer(uuid);

        playerData.createFile();

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        UUID uuid = event.getPlayer().getUniqueId();

        instance.getHandlerManager().getPlayerData().removePlayer(uuid);

    }

}
