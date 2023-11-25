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
        ItemStack itemInPlayerHand = player.getInventory().getItemInMainHand();
        ItemStack[] itemsInBackpack;

        // Checks
        if (!Main.getInstance().getSmallBackpackDisplayName().equals(event.getView().getTitle()) && !Main.getInstance().getLargeBackpackDisplayName().equals(event.getView().getTitle())) return;

        itemsInBackpack =  inventory.getContents();

        // Check and save backpack data
        Main.getInstance().getBackpackUtils().saveBackpackData(player, itemsInBackpack, itemInPlayerHand);
    }
}
