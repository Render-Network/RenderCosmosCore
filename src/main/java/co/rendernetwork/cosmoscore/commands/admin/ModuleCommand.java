package co.rendernetwork.cosmoscore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.language.Lang;
import co.rendernetwork.cosmoscore.modules.CosmosModule;
import co.rendernetwork.cosmoscore.modules.ModuleDescription;
import co.rendernetwork.cosmoscore.modules.ModuleManager;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Iterator;

@CommandAlias("module|modman")
@CommandPermission("cosmos.admin.module")
public class ModuleCommand extends BaseCommand {

    private final Main instance = Main.getInstance();

    @Subcommand("reload")
    @CommandPermission("cosmos.admin.module.reload")
    @Description("Reloads modules")
    @CommandCompletion("@nothing")
    public void onReload(CommandSender sender) {
        instance.getModuleManager().reloadAllModules();
        sender.sendMessage(ColorUtil.format("&aAll modules has been reloaded."));
    }

    @Subcommand("disable")
    @CommandPermission("cosmos.admin.module.disable")
    @Description("Disables module")
    @CommandCompletion("@enabledModulesList")
    public void onDisable(CommandSender sender, String moduleName) {
        CosmosModule module = ModuleManager.getModule(moduleName);
        if (module == null) {
            sender.sendMessage(Lang.MODULE_NOT_FOUND.get());
            return;
        }
        boolean state = instance.getModuleManager().disableModule(module);
        if (state) {
            sender.sendMessage(Lang.MODULE_DISABLED.get().replace("%module%", module.getDescription().getName()));
            return;
        }
        sender.sendMessage(ColorUtil.format("&cThe module &9" + module.getDescription().getName() + " &cis already disabled."));
    }

    @Subcommand("enable")
    @CommandPermission("cosmos.admin.module.enable")
    @Description("Enables module")
    @CommandCompletion("@disabledModulesList")
    public void onEnable(CommandSender sender, String moduleName) {
        CosmosModule module = ModuleManager.getModule(moduleName);
        if (module == null) {
            sender.sendMessage(Lang.MODULE_NOT_FOUND.get());
            return;
        }
        boolean state = instance.getModuleManager().enableModule(module);
        if (state) {
            sender.sendMessage(Lang.MODULE_ENABLED.get().replace("%module%", module.getDescription().getName()));
            return;
        }
        sender.sendMessage(ColorUtil.format("&cThe module &9" + module.getDescription().getName() + " &cis already enabled."));
    }

    @Subcommand("list")
    @CommandPermission("cosmos.admin.module.list")
    @Description("Lists all modules")
    @CommandCompletion("@nothing")
    public void onList(CommandSender sender) {
        sender.sendMessage("");
        sender.sendMessage(ColorUtil.format("&9&lMODULES &7(" + ModuleManager.getAllModules().size() + ")"));
        StringBuilder builder = new StringBuilder();
        Iterator<ModuleDescription> iterator = ModuleManager.getAllModules().values().iterator();
        while (iterator.hasNext()) {
            ModuleDescription description = iterator.next();
            if (iterator.hasNext()) {
                if (description.isActivated()) {
                    builder.append(ColorUtil.format("&e" + description.getName() + " &7(" + description.getIdentifier() + ")" + "&f, "));
                } else {
                    builder.append(ColorUtil.format("&c" + description.getName() + " &7(" + description.getIdentifier() + ")" + "&f, "));
                }
            } else {
                if (description.isActivated()) {
                    builder.append(ColorUtil.format("&e" + description.getName() + " &7(" + description.getIdentifier() + ")" + "&f."));
                } else {
                    builder.append(ColorUtil.format("&c" + description.getName() + " &7(" + description.getIdentifier() + ")" + "&f."));
                }
            }
        }
        sender.sendMessage(builder.toString());
        sender.sendMessage("");
    }

    @Subcommand("info")
    @CommandPermission("cosmos.admin.module.list")
    @Description("Lists all modules")
    @CommandCompletion("@allModulesList")
    @Private
    public void onList(CommandSender sender, String moduleName) {
        CosmosModule module = ModuleManager.getModule(moduleName);
        if (module == null) {
            sender.sendMessage(Lang.MODULE_NOT_FOUND.get());
            return;
        }
        if (module.getDescription().isActivated()) {
            sender.sendMessage(ColorUtil.format("&9" + module.getDescription().getName() + "&*"));
        } else {
            sender.sendMessage(ColorUtil.format("&9&l" + module.getDescription().getName() + "&c*"));
        }
        sendModuleInfoToSender(sender, module);
    }

    private void sendModuleInfoToSender(CommandSender sender, CosmosModule module) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> iterator = Arrays.stream(module.getDescription().getAuthors()).iterator();
        while (iterator.hasNext()) {
            String author = iterator.next();
            if (iterator.hasNext()) {
                builder.append(author)
                        .append("&7, &f");
            } else {
                builder.append(author);
            }
        }
        sender.sendMessage(ColorUtil.format(" &8- &eIdentifier&7: &f" + module.getDescription().getIdentifier()));
        sender.sendMessage(ColorUtil.format(" &8- &eAuthors&7: &f" + builder));
        sender.sendMessage(ColorUtil.format(" &8- &eVersion&7: &f" + module.getDescription().getVersion()));
        sender.sendMessage(ColorUtil.format(" &8- &eDescription&7: &f" + module.getDescription().getDescription()));
    }

}
