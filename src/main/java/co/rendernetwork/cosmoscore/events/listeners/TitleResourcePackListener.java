package co.rendernetwork.cosmoscore.events.listeners;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.utils.ByteArray;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class TitleResourcePackListener implements Listener {

    Main instance = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        String title = ColorUtil.format(instance.getConfig().getString("resourcepack.title"));
        String subtitle = ColorUtil.format(instance.getConfig().getString("resourcepack.subtitle"));
        String url = instance.getConfig().getString("resourcepack.url");
        byte[] hash = ByteArray.decodeUsingBigInteger(instance.getConfig().getString("resourcepack.hash"));

        subtitle = subtitle.replace("%player%", player.getName());

        if (url != null && hash != null) {
            player.sendTitle(title, subtitle, 20, 2000, 20);
            player.setResourcePack(url, hash);
        }

    }

    @EventHandler
    public void onResource(PlayerResourcePackStatusEvent event) {

        Player player = event.getPlayer();
        String title = ColorUtil.format(instance.getConfig().getString("title"));
        String subtitle = ColorUtil.format(instance.getConfig().getString("subtitle"));
        String kickMessage = ColorUtil.format(instance.getConfig().getString("resourcepack.kickmessage"));
        subtitle = subtitle.replace("%player%", player.getName());

        PlayerResourcePackStatusEvent.Status status = event.getStatus();

        if (!(status == PlayerResourcePackStatusEvent.Status.DECLINED)) {
            if (status == PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED) {
                player.resetTitle();
                player.sendTitle(title, subtitle, 20, 40, 20);
            }
        } else {
            if (instance.getConfig().getBoolean("resourcepack.kick")) {
                if (player.isOnline()) player.kickPlayer(kickMessage.replace("\\n", "\n"));
            }
        }

    }

}
