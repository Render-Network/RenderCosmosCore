package co.rendernetwork.cosmoscore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.ConditionFailedException;
import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.commands.admin.ModuleCommand;
import co.rendernetwork.cosmoscore.commands.admin.SystemCommand;
import co.rendernetwork.cosmoscore.commands.staff.ChatCommand;
import co.rendernetwork.cosmoscore.commands.staff.CommandSpyCommand;
import co.rendernetwork.cosmoscore.modules.ModuleDescription;
import co.rendernetwork.cosmoscore.modules.ModuleManager;
import com.google.common.collect.ImmutableList;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    PaperCommandManager commandManager;

    public CommandManager() {

        this.commandManager = new PaperCommandManager(Main.getInstance());

        commandManager.enableUnstableAPI("brigadier");
        commandManager.enableUnstableAPI("help");

        loadLocaleSettings();
        registerConditions();
        registerCompletions();

        registerAllCommands();

    }

    private void registerAllCommands() {

        registerCommand(new SystemCommand());
        registerCommand(new ChatCommand());
        registerCommand(new CommandSpyCommand());
        registerCommand(new ModuleCommand());

    }

    public void registerCommand(BaseCommand baseCommand) {
        commandManager.registerCommand(baseCommand);
    }

    private void registerConditions() {
        commandManager.getCommandConditions().addCondition(Integer.class, "limits", (c, exec, value) -> {
            if (value == null) {
                return;
            }
            if (c.hasConfig("min") && c.getConfigValue("min", 0) > value) {
                throw new ConditionFailedException("The minimum value is " + c.getConfigValue("min", 0) + ".");
            }
            if (c.hasConfig("max") && c.getConfigValue("max", 3) < value) {
                throw new ConditionFailedException("The maximum value is " + c.getConfigValue("max", 3) + ".");
            }
        });
    }

    private void loadLocaleSettings() {

        commandManager.usePerIssuerLocale(false);

        commandManager.setFormat(MessageType.ERROR, ChatColor.RED, ChatColor.WHITE);
        commandManager.setFormat(MessageType.HELP, ChatColor.AQUA, ChatColor.WHITE, ChatColor.DARK_GRAY, ChatColor.GRAY);
        commandManager.setFormat(MessageType.SYNTAX, 1, ChatColor.RED);
        commandManager.setFormat(MessageType.INFO, ChatColor.RED, ChatColor.WHITE);

    }

    private void registerCompletions() {
        commandManager.getCommandCompletions().registerCompletion("enabledModulesList", c -> {
            List<String> list = new ArrayList<>();
            for (ModuleDescription description : ModuleManager.getAllModules().values()) {
                if (description.isActivated()) {
                    list.add(description.getIdentifier());
                }
            }
            if (list.isEmpty()) return null;
            return ImmutableList.copyOf(list);
        });
        commandManager.getCommandCompletions().registerCompletion("disabledModulesList", c -> {
            List<String> list = new ArrayList<>();
            for (ModuleDescription description : ModuleManager.getAllModules().values()) {
                if (!description.isActivated()) {
                    list.add(description.getIdentifier());
                }
            }
            if (list.isEmpty()) return null;
            return ImmutableList.copyOf(list);
        });
        commandManager.getCommandCompletions().registerCompletion("allModulesList", c -> {
            List<String> list = new ArrayList<>();
            for (ModuleDescription description : ModuleManager.getAllModules().values()) {
                list.add(description.getIdentifier());
            }
            if (list.isEmpty()) return null;
            return ImmutableList.copyOf(list);
        });
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }
}
