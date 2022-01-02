package co.rendernetwork.cosmoscore.gui.menus;

import co.rendernetwork.cosmoscore.gui.BaseMenu;
import co.rendernetwork.cosmoscore.gui.Button;
import co.rendernetwork.cosmoscore.gui.Menu;
import co.rendernetwork.cosmoscore.utils.ColorUtil;
import co.rendernetwork.cosmoscore.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SystemMenu extends BaseMenu {

    public SystemMenu(Player player) {
        super(player, 9 * 2, ColorUtil.format("&lCore Settings"));
    }

    @Override
    public void execute(Menu menu) {

        menu.setButton(0, new Button(new ItemBuilder(Material.OAK_LOG).setDisplayName(ColorUtil.format("&c&lReload Config")).addLoreLine(ColorUtil.format("&7Reloads the config.")).build())
                .addAction(player -> player.performCommand("system reload"))
                .setClickClose());
        menu.setButton(1, new Button(new ItemBuilder(Material.OAK_LEAVES).setDisplayName(ColorUtil.format("&c&lReload Player Data")).addLoreLine(ColorUtil.format("&7Reloads player data for all players.")).build())
                .addAction(player -> player.performCommand("system reload playerdata"))
                .setClickClose());

    }
}
