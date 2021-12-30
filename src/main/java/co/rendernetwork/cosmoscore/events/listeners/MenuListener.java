package co.rendernetwork.cosmoscore.events.listeners;

import co.rendernetwork.cosmoscore.gui.IMenuAction;
import co.rendernetwork.cosmoscore.gui.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof Menu)) return;
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        event.setCancelled(true);

        Menu menu = (Menu) event.getClickedInventory().getHolder();

        for (IMenuAction action : menu.getButton(event.getRawSlot()).getActions())
            action.execute((Player) event.getWhoClicked());

    }

}
