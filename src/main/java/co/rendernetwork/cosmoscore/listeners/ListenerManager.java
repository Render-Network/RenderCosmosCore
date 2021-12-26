package co.rendernetwork.cosmoscore.listeners;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.listeners.playerdata.PlayerDataPreLoginListener;
import co.rendernetwork.cosmoscore.listeners.playerdata.PlayerDataQuitListener;
import co.rendernetwork.cosmoscore.listeners.resourcepack.TitleResourcePackListener;
import co.rendernetwork.cosmoscore.listeners.slowmode.SlowModeListener;
import com.google.common.collect.ImmutableList;
import org.bukkit.event.Listener;

import java.util.List;

public class ListenerManager {

    Main instance = Main.getInstance();

    public ListenerManager() {
        registerListeners();
    }

    private final List<Listener> listeners = ImmutableList.of(

            // SLOWMODE
            new SlowModeListener(),

            // RESOURCEPACK
            new TitleResourcePackListener(),

            // PLAYERDATA
            new PlayerDataPreLoginListener(),
            new PlayerDataQuitListener()

    );

    private void registerListeners() {
        for (Listener listener : listeners) {
            instance.getServer().getPluginManager().registerEvents(listener, instance);
        }
    }

}
