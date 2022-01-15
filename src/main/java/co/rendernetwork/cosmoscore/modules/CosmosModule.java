package co.rendernetwork.cosmoscore.modules;

public abstract class CosmosModule {

    public ModuleDescription getDescription() {
        return ModuleManager.getModuleDescription(this);
    }

    public abstract void onLoad();
    public abstract void onDrop();

}
