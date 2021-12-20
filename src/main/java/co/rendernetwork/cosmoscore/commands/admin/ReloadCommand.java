package co.rendernetwork.cosmoscore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import org.bukkit.command.CommandSender;

@CommandAlias("basecommand")
@CommandPermission("cosmos.admin")
public class ReloadCommand extends BaseCommand {

    private final Main instance = Main.getInstance();

    @Subcommand("reload")
    @CommandPermission("cosmos.admin.reload")
    @Description("Reloads RenderCosmosCore")
    public void onReload(CommandSender sender) {

        instance.createConfigs();

        instance.reloadConfig();
        instance.getLanguage().reload();
        instance.getSettings().reload();
        instance.getHandlerManager().getPlayerData().reload();

        sender.sendMessage(ColorUtil.color("&aThe config has been reloaded."));

    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
