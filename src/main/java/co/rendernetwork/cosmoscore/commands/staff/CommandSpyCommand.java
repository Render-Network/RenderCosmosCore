package co.rendernetwork.cosmoscore.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import co.rendernetwork.cosmoscore.language.Lang;
import co.rendernetwork.cosmoscore.entity.User;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("commandspy|cspy")
@CommandPermission("cosmos.commandspy")
public class CommandSpyCommand extends BaseCommand {

    // Toggle for the player
    @Default
    @Subcommand("toggle")
    @Description("Toggles Command Spy")
    @CommandCompletion("@empty")
    public void onToggle(Player player) {

        User user = User.get(player);

        if (user.isCommandSpyEnabled()) {
            user.setCommandSpyEnabled(false);
            player.sendMessage(Lang.COMMANDSPY_DISABLED.get(player));
        } else {
            user.setCommandSpyEnabled(true);
            player.sendMessage(Lang.COMMANDSPY_ENABLED.get(player));
        }

    }

    // Toggle for other players
    @Subcommand("toggle")
    @CommandCompletion("@players")
    @Private
    public void onToggle(CommandSender sender, OnlinePlayer onlinePlayer) {

        Player player = onlinePlayer.getPlayer();
        User user = User.get(player);

        if (user.isCommandSpyEnabled()) {
            user.setCommandSpyEnabled(false);
            sender.sendMessage(Lang.COMMANDSPY_DISABLED.get(player));
        } else {
            user.setCommandSpyEnabled(true);
            sender.sendMessage(Lang.COMMANDSPY_ENABLED.get(player));
        }

    }

    // Enable for the player
    @Subcommand("enable")
    @Description("Enables Command Spy")
    @CommandCompletion("@empty")
    public void onEnable(Player player) {

        User user = User.get(player);

        user.setCommandSpyEnabled(true);
        player.sendMessage(Lang.COMMANDSPY_ENABLED.get(player));

    }

    // Enable for other players
    @Subcommand("enable")
    @CommandCompletion("@players")
    @Private
    public void onEnable(CommandSender sender, OnlinePlayer onlinePlayer) {

        Player player = onlinePlayer.getPlayer();

        User user = User.get(player);

        user.setCommandSpyEnabled(true);
        sender.sendMessage(Lang.COMMANDSPY_ENABLED.get(player));

    }

    // Disable for the player
    @Subcommand("disable")
    @Description("Disables Command Spy")
    @CommandCompletion("@empty")
    public void onDisable(Player player) {

        User user = User.get(player);

        user.setCommandSpyEnabled(false);
        player.sendMessage(Lang.COMMANDSPY_DISABLED.get(player));

    }

    // Disable for other players
    @Subcommand("disable")
    @CommandCompletion("@players")
    @Private
    public void onDisable(CommandSender sender, OnlinePlayer onlinePlayer) {

        Player player = onlinePlayer.getPlayer();

        User user = User.get(player);

        user.setCommandSpyEnabled(false);
        sender.sendMessage(Lang.COMMANDSPY_DISABLED.get(player));

    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
