package co.rendernetwork.cosmoscore.listeners.playerdata;

import co.rendernetwork.cosmoscore.entity.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class PlayerDataQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        UUID uuid = event.getPlayer().getUniqueId();

        User user = User.get(uuid);
        if (user == null) return;

        user.save();

        User.getAllUsers().remove(user);
    }

}
