package co.rendernetwork.cosmoscore.gui;

import org.bukkit.entity.Player;

public abstract class BaseMenu {

    public BaseMenu(Player player, int size, String title) {
        Menu menu = new Menu(size, title);
        execute(menu);
        player.openInventory(menu.getInventory());
    }

    public abstract void execute(Menu menu);

}
