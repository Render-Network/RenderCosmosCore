package me.dynmie.rn.testmodule.commands.impl;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import org.bukkit.command.CommandSender;

@CommandAlias("test")
public class TestCommand extends BaseCommand {

    @Default
    @Subcommand("test")
    @Description("Test command to do test things.")
    @CommandCompletion("@empty")
    public void onTest(CommandSender sender) {
        sender.sendMessage(ColorUtil.format("&aThis works!"));
    }

    @HelpCommand
    public void onHelp(CommandHelp help) {
        help.showHelp();
    }

}
