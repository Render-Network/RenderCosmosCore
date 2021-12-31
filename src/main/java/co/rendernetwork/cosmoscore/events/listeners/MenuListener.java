package co.rendernetwork.cosmoscore.events.listeners;

import co.rendernetwork.cosmoscore.gui.Button;
import co.rendernetwork.cosmoscore.gui.IMenuAction;
import co.rendernetwork.cosmoscore.gui.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getClickedInventory() == null) return;
        if (!(event.getClickedInventory().getHolder() instanceof Menu)) return;

        event.setCancelled(true);
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;

        Menu menu = (Menu) event.getClickedInventory().getHolder();
        Button button = menu.getButton(event.getRawSlot());
        if (button == null) return;

        for (IMenuAction action : button.getActions())
            action.execute((Player) event.getWhoClicked());

    }

    @EventHandler
    public void onDrag(InventoryDragEvent event) {
        if (event.getInventory().getHolder() instanceof Menu) event.setCancelled(true);
    }

    @EventHandler
    public void onMove(InventoryMoveItemEvent event) {
        if (event.getSource().getHolder() instanceof Menu) event.setCancelled(true);
        if (event.getInitiator().getHolder() instanceof Menu) event.setCancelled(true);
        if (event.getDestination().getHolder() instanceof Menu) event.setCancelled(true);
    }

}
