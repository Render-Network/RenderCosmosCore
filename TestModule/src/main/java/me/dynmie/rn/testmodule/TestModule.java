package me.dynmie.rn.testmodule;

import co.rendernetwork.cosmoscore.modules.CosmosModule;
import me.dynmie.rn.testmodule.commands.ModuleCommandManager;

public final class TestModule extends CosmosModule {

    @Override
    public void onLoad() {
        new ModuleCommandManager().registerAllCommands();
    }

    @Override
    public void onDrop() {
        // Do nothing. Yea, I don't know what to add here.
    }

}
