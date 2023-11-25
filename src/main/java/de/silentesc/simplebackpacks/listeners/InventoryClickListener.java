package de.silentesc.simplebackpacks.listeners;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();

        // Checks
        if (clickedItem == null) return;
        if (!Main.getInstance().getSmallBackpackDisplayName().equals(event.getView().getTitle()) && !Main.getInstance().getLargeBackpackDisplayName().equals(event.getView().getTitle())) return;
        if (clickedItem.getItemMeta() == null || !clickedItem.getItemMeta().getPersistentDataContainer().has(Main.getInstance().getBackPackSizeKey(), PersistentDataType.INTEGER)) return;

        // Cancel event
        event.setCancelled(true);
    }
}
