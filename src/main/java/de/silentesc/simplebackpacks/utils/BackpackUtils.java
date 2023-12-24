package de.silentesc.simplebackpacks.utils;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class BackpackUtils {
    private final HashMap<UUID, ItemStack> openBackpacks = new HashMap<>();

    public void addOpenBackpack(Player player, ItemStack backpackItem) {
        UUID playerUUID = player.getUniqueId();
        if (openBackpacks.containsKey(playerUUID)) {
            System.out.printf("%s opened a backpack but is still in the openBackpacks HashMap%n", playerUUID);
            openBackpacks.remove(playerUUID);
        }
        openBackpacks.put(playerUUID, backpackItem);
    }

    public void removeOpenBackpack(UUID playerUUID) {
        openBackpacks.remove(playerUUID);
    }

    public ItemStack getBackpackItemFromPlayer(UUID playerUUID) {
        return openBackpacks.get(playerUUID);
    }

    public boolean itemIsBackpack(ItemStack backpackItem) {
        return backpackItem.getItemMeta() != null && backpackItem.getItemMeta().getPersistentDataContainer().has(Main.getInstance().getBackPackSizeKey(), PersistentDataType.INTEGER);
    }

    public void handleBackpackItemNotFound(Player player, ItemStack[] contents) {
        // Send error message
        Main.getInstance().getShortMessages()
                .sendFailMessage(player, "Backpack cannot be found, items have been given back into your inventory.");
        // Add items to inventory or drop
        for (ItemStack content : contents) {
            if (content == null) continue;
            HashMap<Integer, ItemStack> notFittingItems = player.getInventory().addItem(content);
            for (ItemStack i : notFittingItems.values())
                player.getWorld().dropItem(player.getLocation(), i);
        }
    }

    public void saveBackpackData(Player player, ItemStack[] contents, ItemStack backpackItem) {
        // Remove backpack from open hashmap
        Main.getInstance().getBackpackUtils().removeOpenBackpack(player.getUniqueId());

        // If the backpack in main hand cannot be found drop stuff
        if (backpackItem.getItemMeta() == null || !itemIsBackpack(backpackItem)) {
            handleBackpackItemNotFound(player, contents);
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
