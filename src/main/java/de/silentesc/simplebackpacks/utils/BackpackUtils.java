package de.silentesc.simplebackpacks.utils;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class BackpackUtils {
    public void saveBackpackData(Player player, ItemStack[] contents, ItemStack backpackItem) {
        // If the backpack in main hand cannot be found drop stuff
        if (backpackItem.getItemMeta() == null || !backpackItem.getItemMeta().getPersistentDataContainer().has(Main.getInstance().getBackPackSizeKey(), PersistentDataType.INTEGER)) {
            // Send error message
            Main.getInstance().getShortMessages()
                    .sendFailMessage(player, "Backpack cannot be found in your main hand, items have been given back into your inventory.");
            // Add items to inventory or drop
            for (ItemStack content : contents) {
                if (content == null) continue;
                HashMap<Integer, ItemStack> notFittingItems = player.getInventory().addItem(content);
                for (ItemStack i : notFittingItems.values())
                    player.getWorld().dropItem(player.getLocation(), i);
            }
            return;
        }

        // Convert all items to a big string
        StringBuilder fullDataString = new StringBuilder();
        for (ItemStack content : contents) {
            ItemStack currentItem = content;
            if (currentItem == null) currentItem = new ItemStack(Material.AIR);
            String itemStr = Main.getInstance().getJavaUtils().itemToString(currentItem);
            fullDataString.append(itemStr).append(";");
        }

        // We checked for that before, here we know 100% the backpack exists
        ItemMeta backpackItemMeta = backpackItem.getItemMeta();
        backpackItemMeta.getPersistentDataContainer().set(Main.getInstance().getBackpackDataKey(), PersistentDataType.STRING, fullDataString.toString());
        backpackItem.setItemMeta(backpackItemMeta);
    }
}
