package co.rendernetwork.cosmoscore.handlers;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.language.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatHandler {

    private final Main instance = Main.getInstance();

    private volatile boolean muted;
    private final Map<UUID, Long> slowMode = new HashMap<>();
    private long slowModeDelay;

    public ChatHandler() {
        slowModeDelay = instance.getSettings().get().getLong("slowmode_delay");
        Bukkit.getLogger().info(String.valueOf(instance.getSettings().get().getLong("slowmode_delay")));
    }

    public void clearChat(CommandSender sender) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("cosmos.chat.clear.exempt")) {
                for (int i = 0; i < 100; i++) {
                    player.sendMessage("");
                }
                player.sendMessage(Lang.CHAT_CLEARED.get(player));
                continue;
            }
            player.sendMessage(Lang.CHAT_CLEARED_EXEMPT.get(player));
        }

        Bukkit.getLogger().info(Lang.CHAT_CLEARED.get(sender));

    }

    public void setMuted(Boolean muted) {
        this.muted = muted;
    }

    public boolean getMuted() {
        return muted;
    }

    public void setSlowMode(UUID uuid, boolean state) {
        if (state) {
            slowMode.remove(uuid);
            slowMode.put(uuid, System.currentTimeMillis());
        } else {
            slowMode.remove(uuid);
        }
    }

    public void setSlowModeDelay(long slowModeDelay) {
        // In millis
        instance.getSettings().get().set("slowmode_delay", slowModeDelay);
        instance.getSettings().save();
        this.slowModeDelay = slowModeDelay;
    }

    public long getSlowModeDelay() {
        return slowModeDelay;
    }

    public boolean isSlowed(UUID uuid) {

        long lastTalkTime = slowMode.getOrDefault(uuid, 0L);
        long delay = slowModeDelay;
        long currentTime = System.currentTimeMillis();

        return lastTalkTime + delay >= currentTime;

    }

    public long getLastSlowed(UUID uuid) {
        return slowMode.getOrDefault(uuid, 0L);
    }

}
