package co.rendernetwork.cosmoscore.entity;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.utils.LoggerUtil;
import org.bukkit.scheduler.BukkitRunnable;

public class UserAutoSave extends BukkitRunnable {

    public UserAutoSave() {
        Main instance = Main.getInstance();
        long interval = instance.getConfig().getLong("autosave.interval", 48000);
        runTaskTimerAsynchronously(instance, interval, interval);
    }

    @Override
    public void run() {
        long time = System.currentTimeMillis();
        LoggerUtil.info("Saving player data...");
        User.saveAll();
        User.reloadAllUserData();
        long elapsed = System.currentTimeMillis() - time;
        elapsed = elapsed * 100; // To seconds
        LoggerUtil.info(String.format("Saved player data. (%ds)", elapsed));
    }

}
