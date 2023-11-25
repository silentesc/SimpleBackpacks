package de.silentesc.simplebackpacks.listeners;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();

        // Checks
        if (clickedItem == null) return;
        if (!Main.getInstance().getBackpackName().equals(event.getView().getTitle())) return;
        if (clickedItem.getItemMeta() == null || !Main.getInstance().getBackpackName().equals(clickedItem.getItemMeta().getDisplayName())) return;

        // Cancel event
        event.setCancelled(true);
    }
}
