package co.rendernetwork.cosmoscore.gui;

import org.bukkit.entity.Player;

public abstract class BaseMenu {

    private final Player player;
    private final Menu menu;

    public BaseMenu(Player player, int size, String title) {
        menu = new Menu(size, title);
        this.player = player;
        execute(menu);
    }

    public void open() {
        player.openInventory(menu.getInventory());
    }

    public abstract void execute(Menu menu);

}
