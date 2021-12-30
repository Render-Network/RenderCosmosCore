package co.rendernetwork.cosmoscore.events.listeners.playerdata;

import co.rendernetwork.cosmoscore.entity.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import java.util.UUID;

public class PlayerDataPreLoginListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {

        UUID uuid = event.getUniqueId();

        User user = new User(uuid);
        User.getAllUsers().remove(user);
        User.getAllUsers().add(user);

        user.createIfNotExists();

    }

}
