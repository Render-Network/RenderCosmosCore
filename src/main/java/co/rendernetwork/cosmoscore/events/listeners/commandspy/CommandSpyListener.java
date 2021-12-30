package co.rendernetwork.cosmoscore.events.listeners.commandspy;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.entity.User;
import co.rendernetwork.cosmoscore.language.Lang;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpyListener implements Listener {

    Main instance = Main.getInstance();

    @EventHandler
    public void onCommandRan(PlayerCommandPreprocessEvent event) {

        if (event.isCancelled()) return;

        String message = event.getMessage();
        Player player = event.getPlayer();

        for (Player target : instance.getServer().getOnlinePlayers()) {

            if (!target.hasPermission("cosmos.commandspy")) continue;

            User user = User.get(target);
            if (user == null) continue;

            if (user.isCommandSpyEnabled()) {
                target.sendMessage(Lang.COMMANDSPY_MESSAGE.get(player)
                        .replace("%message%", message));
            }

        }

    }

}
