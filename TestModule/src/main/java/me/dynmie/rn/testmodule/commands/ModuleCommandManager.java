package me.dynmie.rn.testmodule.commands;

import co.aikar.commands.BaseCommand;
import co.rendernetwork.cosmoscore.commands.CommandManager;
import com.google.common.collect.ImmutableList;
import me.dynmie.rn.testmodule.commands.impl.TestCommand;

public class ModuleCommandManager extends CommandManager {

    private final ImmutableList<BaseCommand> commands = ImmutableList.of(
            // OTHER
            new TestCommand()
    );

    public void registerAllCommands() {
        commands.forEach(command -> getCommandManager().registerCommand(command));
    }

}
