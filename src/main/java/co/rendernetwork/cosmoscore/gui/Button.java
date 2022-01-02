package co.rendernetwork.cosmoscore.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Button {

    private final ItemStack item;
    private final List<MenuAction> actions = new ArrayList<>();

    public Button(ItemStack item) {
        this.item = item;
    }

    public Button addAction(MenuAction clickAction) {
        actions.add(clickAction);
        return this;
    }

    public Button setClickClose() {
        actions.add(HumanEntity::closeInventory);
        return this;
    }

    public List<MenuAction> getActions() {
        return actions;
    }

    public ItemStack getItem() {
        return item;
    }

}
