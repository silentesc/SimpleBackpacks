package de.silentesc.simplebackpacks.listeners;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class CraftItemListener implements Listener {
    @EventHandler
    public void onItemCraft(CraftItemEvent event) {
        ItemStack item = event.getInventory().getResult();

        // Checks
        if (item == null) return;
        if (item.getItemMeta() == null || !Main.getInstance().getBackpackName().equals(item.getItemMeta().getDisplayName())) return;

        // Cancel if amount is more than 1 and shift click
        if (event.isShiftClick()) {
            boolean maxOneAmount = false;
            for (ItemStack i : event.getInventory().getMatrix()) {
                if (i.getAmount() <= 1) {
                    maxOneAmount = true;
                    break;
                }
            }
            if (!maxOneAmount) {
                event.setCancelled(true);
                return;
            }
        }

        // Generate random unique string
        int randomNumber = Main.getInstance().getManager().getJavaUtils().randomNumber(100000000, 999999999);
        long timestamp = System.currentTimeMillis();
        String uniqueString = String.format("%d.%d", randomNumber, timestamp);

        // Assign string to item
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "unique_string");
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.getPersistentDataContainer().set(key, PersistentDataType.STRING, uniqueString);
        item.setItemMeta(itemMeta);
    }
}
