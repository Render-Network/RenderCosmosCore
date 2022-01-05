package co.rendernetwork.cosmoscore.entity;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.utils.LoggerUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class UserAutoSave extends BukkitRunnable {

    public UserAutoSave() {
        Main instance = Main.getInstance();
        long interval = instance.getConfig().getLong("autosave.interval", 12000); // Interval in ticks (10 mins)
        runTaskTimerAsynchronously(instance, interval, interval);
    }

    @Override
    public void run() {
        LoggerUtil.log("Saving player data...");
        User.saveAll();
        User.reloadAllUserData();
        LoggerUtil.log("Saved player data.");
    }

}
