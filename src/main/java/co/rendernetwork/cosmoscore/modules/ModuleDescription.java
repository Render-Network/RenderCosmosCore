package co.rendernetwork.cosmoscore.modules;

import java.util.UUID;

public class ModuleDescription {

    private final String name;
    private final String identifier;
    private final String[] authors;
    private final String description;
    private final String version;
    private final String main;
    private boolean activated;

    public ModuleDescription(String name, String identifier, String[] authors, String description, String version, String main) {
        this.name = name;
        this.identifier = identifier;
        this.authors = authors;
        this.description = description;
        this.version = version;
        this.main = main;
        activated = true;
    }

    public void setActivated(boolean state) { activated = state; }

    public String getName() { return name; }
    public String getIdentifier() { return identifier; }
    public String[] getAuthors() { return authors; }
    public String getDescription() { return description; }
    public String getVersion() { return version; }
    public String getMain() { return main; }
    public boolean isActivated() { return activated; }
}
