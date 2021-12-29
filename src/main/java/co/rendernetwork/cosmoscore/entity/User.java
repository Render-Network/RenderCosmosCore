package co.rendernetwork.cosmoscore.entity;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.entity.data.UserData;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class User {

    Main instance = Main.getInstance();

    File file;
    YamlConfiguration config = new YamlConfiguration();
    UserData userData;

    private final UUID uuid;

    public User(UUID uuid) {

        this.uuid = uuid;

        this.file = new File(instance.getDataFolder() + File.separator + "playerdata", uuid.toString() + ".yml");

        if (exists())
            userData = UserData.deserialize(file, uuid);

    }

    private boolean exists() {
        return file.exists();
    }

    public void createIfNotExists() {
        if (!exists()) {
            userData = new UserData("None", false);
            save();
        }
    }

    public UUID getUniqueId() {
        return uuid;
    }

    private UserData getUserData() {
        return userData;
    }

    public void save() {
        config.set(uuid.toString(), userData.serialize());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCommandSpyEnabled(boolean state) {
        userData.setCommandSpyEnabled(state);
        save();
    }

    public boolean isCommandSpyEnabled() {
        return userData.isCommandSpyEnabled();
    }

    public String getLastKnownName() {
        return userData.getLastKnownName();
    }

    // STATICS - It's not static abuse if it's actually supposed to be used this way.

    private static final List<User> userMap = new ArrayList<>();

    public static List<User> getAllUsers() {
        return userMap;
    }

    public static User get(Player player) {
        boolean online = player != null;
        if (!online) return null;
        for (User cPlayer : userMap) {
            if (cPlayer.getUniqueId().equals(player.getUniqueId())) return cPlayer;
        }
        return null;
    }

    public static User get(UUID uuid) {
        boolean online = Bukkit.getPlayer(uuid) != null;
        if (!online) return null;
        for (User cPlayer : userMap) {
            if (cPlayer.getUniqueId().equals(uuid)) return cPlayer;
        }
        return null;
    }

    public static void saveAll() {
        for (User cPlayer : userMap) {
            cPlayer.save();
        }
    }

    public static void reloadAllUserData() {

        if (!userMap.isEmpty()) userMap.clear();
        if (Bukkit.getOnlinePlayers().size() == 0) return;

        for (Player player : Bukkit.getOnlinePlayers()) {
            User cPlayer = new User(player.getUniqueId());
            userMap.add(cPlayer);
            cPlayer.createIfNotExists();
        }

    }

}
