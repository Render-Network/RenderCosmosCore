package co.rendernetwork.cosmoscore.commands.staff;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.rendernetwork.cosmoscore.Main;
import co.rendernetwork.cosmoscore.language.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("chat")
@CommandPermission("cosmos.chat")
public class ChatCommand extends BaseCommand {

    private final Main instance = Main.getInstance();

    @Subcommand("clear")
    @CommandPermission("cosmos.chat.clear")
    @CommandCompletion("@empty")
    @Description("Clears the chat for all players.")
    public void onClear(CommandSender sender) {
        instance.getHandlerManager().getChatHandler().clearChat(sender);

    }

    @Subcommand("mute")
    @CommandPermission("cosmos.chat.mute")
    @CommandCompletion("@empty")
    @Description("Mutes the chat for all players.")
    public void onMute(CommandSender sender) {
        if (instance.getHandlerManager().getChatHandler().getMuted()) {
            instance.getHandlerManager().getChatHandler().setMuted(false);
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(Lang.CHAT_UNMUTED.get(sender));
            }
            instance.getLogger().info(Lang.CHAT_UNMUTED.get(sender));
        } else {
            instance.getHandlerManager().getChatHandler().setMuted(true);
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendMessage(Lang.CHAT_MUTED.get(sender));
            }
            instance.getLogger().info(Lang.CHAT_MUTED.get(sender));
        }
    }

    @Subcommand("slow")
    @CommandPermission("cosmos.chat.slow")
    @CommandCompletion("@empty")
    @Description("Slows the chat for all players.")
    @Private
    public void onSlow(CommandSender sender) {
        sender.sendMessage(
                Lang.CHAT_SLOWMODE_GET.get()
                        .replace("%cooldown%", String.valueOf(instance.getHandlerManager().getChatHandler().getSlowModeDelay() / 1000L))
        );
    }

    @Subcommand("slow")
    @CommandPermission("cosmos.chat.slow")
    @CommandCompletion("@range:0-10")
    @Description("Slows the chat for all players.")
    @Syntax("<number>")
    public void onSlow(CommandSender sender, @Conditions("limits:min=0,max=10") Integer number) {
        sender.sendMessage(Lang.CHAT_SLOWMODE.get(sender).replace("%cooldown%", String.valueOf(number)));
        instance.getHandlerManager().getChatHandler().setSlowModeDelay((long) number * 1000L);
    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
