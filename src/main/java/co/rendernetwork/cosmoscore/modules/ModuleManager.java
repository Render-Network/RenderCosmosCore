package co.rendernetwork.cosmoscore.modules;

import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.utils.LoggerUtil;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModuleManager {

    private final Main instance = Main.getInstance();
    private static final Map<CosmosModule, ModuleDescription> modules = new HashMap<>();

    public void loadAllModules() {

        File folder = new File(instance.getDataFolder().getAbsolutePath() + File.separator + "modules");
        //noinspection ResultOfMethodCallIgnored
        folder.mkdirs();
        if (!folder.exists()) //noinspection ResultOfMethodCallIgnored
            folder.mkdir();

        if (folder.listFiles() == null) return;

        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (!file.getName().toLowerCase().endsWith(".jar")) continue;

            JarFile jar = null;
            InputStream stream = null;

            LoggerUtil.info("[Module] Loading module \"" + file.getName() + "\"...");

            try {
                jar = new JarFile(file);
                JarEntry entry = jar.getJarEntry("moduleinfo.json");

                if (entry == null) {
                    LoggerUtil.warning("The module \"" + file.getName() + "\" does not have a moduleinfo.json file! If this jar file is not a module, you can ignore this message.");
                    continue;
                }

                stream = jar.getInputStream(entry);

                ModuleDescription description = new Gson().fromJson(CharStreams.toString(new InputStreamReader(stream, Charsets.UTF_8)), ModuleDescription.class);

                URLClassLoader url = new URLClassLoader(
                        new URL[] {file.toURI().toURL()},
                        this.getClass().getClassLoader()
                );

                Class<? extends CosmosModule> clazz =  Class.forName(description.getMain(), true, url).asSubclass(CosmosModule.class);
                CosmosModule module =  clazz.getDeclaredConstructor().newInstance();

                modules.put(module, description);
                module.onLoad();

                LoggerUtil.info("[Module] Module \"" + file.getName() + "\" has been loaded.");

            } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException | NullPointerException e) {

                e.printStackTrace();
                LoggerUtil.warning("Failed to load the module \"" + file.getName() + "\"! Please contact the developers of this module to resolve this issue.");

            } finally {

                if (jar != null) {
                    try {
                        jar.close();
                    } catch (IOException ignored) {
                        // Ignored
                    }
                }

                if (stream != null) {
                    try {
                        stream.close();
                    } catch (IOException ignored) {
                        // Ignored
                    }
                }

            }

        }

    }

    public void unloadAllModules() {
        if (modules.isEmpty()) return;
        modules.forEach((module, description) -> {
            module.onDrop();
            description.setActivated(false);
        });
        modules.clear();
    }

    public void reloadAllModules() {
        unloadAllModules();
        loadAllModules();
    }

    public boolean disableModule(@NotNull CosmosModule module) {
        if (!module.getDescription().isActivated()) return false;
        LoggerUtil.info("[Module] Disabling module \"" + module.getDescription().getName() + "\"...");
        module.onDrop();
        modules.get(module).setActivated(false);
        LoggerUtil.info("[Module] Module \"" + module.getDescription().getName() + "\" has been disabled.");
        return true;
    }

    public boolean enableModule(@NotNull CosmosModule module) {
        if (module.getDescription().isActivated()) return false;
        LoggerUtil.info("[Module] Enabling module \"" + module.getDescription().getName() + "\"...");
        module.onLoad();
        modules.get(module).setActivated(true);
        LoggerUtil.info("[Module] Module \"" + module.getDescription().getName() + "\" has been enabled.");
        return true;
    }

    public static boolean isActivated(CosmosModule module) {
        return modules.get(module).isActivated();
    }

    public static ModuleDescription getModuleDescription(CosmosModule module) {
        return Objects.requireNonNull(modules.get(module));
    }

    public static CosmosModule getModule(String name) {
        for (Map.Entry<CosmosModule, ModuleDescription> entry : modules.entrySet()) {
            if (!entry.getValue().getIdentifier().equalsIgnoreCase(name)) continue;
            return entry.getKey();
        }
        for (Map.Entry<CosmosModule, ModuleDescription> entry : modules.entrySet()) {
            if (!(entry.getValue().getName().equalsIgnoreCase(name))) continue;
            return entry.getKey();
        }
        return null;
    }

    public static Map<CosmosModule, ModuleDescription> getAllModules() {
        return modules;
    }

}
