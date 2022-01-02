package co.rendernetwork.cosmoscore.events;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.events.listeners.*;
import com.google.common.collect.ImmutableList;
import org.bukkit.event.Listener;

import java.util.List;

public class ListenerManager {

    Main instance = Main.getInstance();

    public ListenerManager() {
        registerListeners();
    }

    private final List<Listener> listeners = ImmutableList.of(

            new SlowModeListener(),
            new TitleResourcePackListener(),
            new UserDataListener(),
            new CommandSpyListener(),
            new MenuListener()

    );

    private void registerListeners() {
        for (Listener listener : listeners) {
            instance.getServer().getPluginManager().registerEvents(listener, instance);
        }
    }

}
