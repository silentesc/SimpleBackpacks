package de.silentesc.simplebackpacks.listeners;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryCloseListener implements Listener {
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        // Checks
        if (!(event.getPlayer() instanceof Player)) return;

        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
        ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
        ItemStack backpackItem;
        ItemStack[] itemsInBackpack = inventory.getContents();

        // Checks
        if (!Main.getInstance().getSmallBackpackDisplayName().equals(event.getView().getTitle()) && !Main.getInstance().getLargeBackpackDisplayName().equals(event.getView().getTitle())) return;
        // !!! Check mainHand first because the game priors it
        if (Main.getInstance().getBackpackUtils().itemIsBackpack(itemInMainHand))
            backpackItem = itemInMainHand;
        else if (Main.getInstance().getBackpackUtils().itemIsBackpack(itemInOffHand))
            backpackItem = itemInOffHand;
        else {
            Main.getInstance().getBackpackUtils().handleBackpackItemNotFound(player, itemsInBackpack);
            return;
        }

        // Check and save backpack data
        Main.getInstance().getBackpackUtils().saveBackpackData(player, itemsInBackpack, backpackItem);
    }
}
