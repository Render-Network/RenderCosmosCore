package co.rendernetwork.cosmoscore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.entity.User;
import co.rendernetwork.cosmoscore.gui.menus.SystemMenu;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("system")
@CommandPermission("cosmos.admin")
public class SystemCommand extends BaseCommand {

    private final Main instance = Main.getInstance();

    @Default
    public void onRun(Player player) {
        new SystemMenu(player).open();
    }

    @Subcommand("reload")
    @CommandPermission("cosmos.admin.reload")
    @Description("Reloads RenderCosmosCore")
    public void onReload(CommandSender sender) {

        instance.initializeConfigs();

        instance.reloadConfig();
        instance.getLanguage().reload();
        instance.getSettings().reload();
        User.reloadAllUserData();

        instance.getModuleManager().reloadAllModules();

        sender.sendMessage(ColorUtil.format("&aAll data and modules has been reloaded."));

    }

    @Subcommand("reload playerdata")
    @CommandPermission("cosmos.admin.reload.playerdata")
    @Description("Reloads player data for all players")
    public void onReloadPlayerData(CommandSender sender) {

        User.reloadAllUserData();

        sender.sendMessage(ColorUtil.format("&aPlayer Data for all players has been reloaded."));

    }

    @Subcommand("reload modules")
    @CommandPermission("cosmos.admin.reload.modules")
    @Description("Reloads modules")
    public void onReloadModules(CommandSender sender) {
        instance.getModuleManager().reloadAllModules();
        sender.sendMessage(ColorUtil.format("&aAll modules has been reloaded."));
    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
