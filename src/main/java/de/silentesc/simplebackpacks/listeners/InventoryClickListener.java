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
        int hotbarSlot = event.getHotbarButton();

        // Return if event not in backpack
        if (!Main.getInstance().getSmallBackpackDisplayName().equals(event.getView().getTitle()) &&
                !Main.getInstance().getLargeBackpackDisplayName().equals(event.getView().getTitle()))
            return;

        // Is not hotkey click
        if (hotbarSlot == -1) {
            if (clickedItem == null) return;
            if (clickedItem.getItemMeta() == null || !clickedItem.getItemMeta().getPersistentDataContainer().has(Main.getInstance().getBackPackSizeKey(), PersistentDataType.INTEGER)) return;
            event.setCancelled(true);
        }
        // Is hotkey click
        else {
            ItemStack itemInHotbar = event.getWhoClicked().getInventory().getItem(hotbarSlot);
            if (Main.getInstance().getBackpackUtils().itemIsBackpack(itemInHotbar) || Main.getInstance().getBackpackUtils().itemIsBackpack(clickedItem)) {
                event.setCancelled(true);
            }
        }
    }
}
