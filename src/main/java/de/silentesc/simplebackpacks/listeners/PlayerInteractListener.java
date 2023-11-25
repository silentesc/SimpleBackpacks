package de.silentesc.simplebackpacks.listeners;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PlayerInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        final int backpackSize = 9*3;
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        ItemMeta itemMeta;

        // Checks
        if (item == null) return;
        itemMeta = item.getItemMeta();
        if (itemMeta == null) return;
        if (!Main.getInstance().getBackpackName().equals(itemMeta.getDisplayName())) return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        // Cancel event
        event.setCancelled(true);

        // Get data
        ItemStack[] itemsInBackpack = new ItemStack[backpackSize];
        String fullDataString = itemMeta.getPersistentDataContainer().get(Main.getInstance().getBackpackDataKey(), PersistentDataType.STRING);
        if (fullDataString != null) {
            for (int i = 0; i < backpackSize; i++) {
                String itemStr = fullDataString.split(";")[i];
                itemsInBackpack[i] = Main.getInstance().getManager().getJavaUtils().stringToItem(itemStr);
            }
        }

        // Create inventory
        Inventory inventory = Bukkit.createInventory(player, backpackSize, Main.getInstance().getBackpackName());
        inventory.setContents(itemsInBackpack);
        player.openInventory(inventory);
    }
}
