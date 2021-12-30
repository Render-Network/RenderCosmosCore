package co.rendernetwork.cosmoscore.events.listeners.slowmode;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.handlers.ChatHandler;
import co.rendernetwork.cosmoscore.language.Lang;
import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class SlowModeListener implements Listener {

    Main instance = Main.getInstance();
    ChatHandler chatHandler = instance.getHandlerManager().getChatHandler();

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("cosmos.chat.slow.exempt")) {
            chatHandler.setSlowMode(player.getUniqueId(), false);
            return;
        }

        long lastTalkTime = chatHandler.getLastSlowed(player.getUniqueId());
        long delay = chatHandler.getSlowModeDelay();
        long currentTime = System.currentTimeMillis();

        if (!chatHandler.isSlowed(player.getUniqueId())) {
            chatHandler.setSlowMode(player.getUniqueId(), true);
        } else {
            event.setCancelled(true);
            player.sendMessage(Lang.CHAT_SLOWMODE_PLAYER.get().replace("%cooldown%", String.valueOf( ((lastTalkTime + delay - currentTime) / 1000) + 1 )));
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        chatHandler.setSlowMode(player.getUniqueId(), false);

    }

}
