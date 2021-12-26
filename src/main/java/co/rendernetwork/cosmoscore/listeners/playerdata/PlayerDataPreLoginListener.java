package co.rendernetwork.cosmoscore.listeners.playerdata;

import co.rendernetwork.cosmoscore.player.CPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class PlayerDataPreLoginListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {

        UUID uuid = event.getUniqueId();

        CPlayer cPlayer = new CPlayer(uuid);
        CPlayer.getPlayers().add(cPlayer);

        cPlayer.createIfNotExists();

    }

}
