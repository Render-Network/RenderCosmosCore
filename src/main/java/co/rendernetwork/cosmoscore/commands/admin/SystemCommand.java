package co.rendernetwork.cosmoscore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.player.CPlayer;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import org.bukkit.command.CommandSender;

@CommandAlias("system")
@CommandPermission("cosmos.admin")
public class SystemCommand extends BaseCommand {

    private final Main instance = Main.getInstance();

    @Subcommand("reload")
    @CommandPermission("cosmos.admin.reload")
    @Description("Reloads RenderCosmosCore")
    public void onReload(CommandSender sender) {

        instance.createConfigs();

        instance.reloadConfig();
        instance.getLanguage().reload();
        instance.getSettings().reload();
        CPlayer.reloadAllPlayerData();

        sender.sendMessage(ColorUtil.color("&aAll data has been reloaded."));

    }

    @Subcommand("reload playerdata")
    @CommandPermission("cosmos.admin.reload.playerdata")
    @Description("Reloads player data for all players")
    public void onReloadPlayerData(CommandSender sender) {

        CPlayer.reloadAllPlayerData();

        sender.sendMessage(ColorUtil.color("&aPlayer Data for all players has been reloaded."));

    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}