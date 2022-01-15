package me.dynmie.rn.testmodule.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import co.rendernetwork.cosmoscore.Main;
import com.google.common.collect.ImmutableList;
import me.dynmie.rn.testmodule.commands.impl.TestCommand;

public class ModuleCommandManager {

    PaperCommandManager manager = Main.getInstance().getCommandManager().getCommandManager();

    private final ImmutableList<BaseCommand> commands = ImmutableList.of(
            // OTHER
            new TestCommand()
    );

    public void registerAllCommands() {
        commands.forEach(command -> manager.registerCommand(command));
    }

}
