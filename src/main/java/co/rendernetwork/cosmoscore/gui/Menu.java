package co.rendernetwork.cosmoscore.gui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Menu implements InventoryHolder {

    private final Inventory inventory;
    private final Map<Integer, Button> buttons = new HashMap<>();

    public Menu(int size, String title) {
        this.inventory = Bukkit.createInventory(this, size, Component.text(title));
    }

    public void setButton(int slot, Button button) {
        buttons.put(slot, button);
    }

    public Button getButton(int slot) {
        return buttons.get(slot);
    }

    @Override
    public @NotNull Inventory getInventory() {
        for (Map.Entry<Integer, Button> entry : buttons.entrySet())
            inventory.setItem(entry.getKey(), entry.getValue().getItem());
        return inventory;
    }

}
