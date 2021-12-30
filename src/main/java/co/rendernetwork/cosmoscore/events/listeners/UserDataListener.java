package co.rendernetwork.cosmoscore.events.listeners;

import co.rendernetwork.cosmoscore.entity.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class UserDataListener implements Listener {

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent event) {

        UUID uuid = event.getUniqueId();

        User user = new User(uuid);
        User.getAllUsers().remove(user);
        User.getAllUsers().add(user);

        user.createIfNotExists();

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        User user = User.get(uuid);
        if (user == null) return;

        user.save();

        User.getAllUsers().remove(user);
    }

}
