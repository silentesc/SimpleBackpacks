package de.silentesc.simplebackpacks.listeners;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class ServerShutdownListener {
    public void onShutdown() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            InventoryView openInventory = player.getOpenInventory();
            ItemStack itemInMainHand = player.getInventory().getItemInMainHand();
            ItemStack itemInOffHand = player.getInventory().getItemInOffHand();
            ItemStack backpackItem;
            ItemStack[] itemsInBackpack = openInventory.getTopInventory().getContents();

            // Checks
            if (!Main.getInstance().getSmallBackpackDisplayName().equals(openInventory.getTitle()) && !Main.getInstance().getLargeBackpackDisplayName().equals(openInventory.getTitle())) continue;
            // !!! Check mainHand first because the game priors it
            if (Main.getInstance().getBackpackUtils().itemIsBackpack(itemInMainHand))
                backpackItem = itemInMainHand;
            else if (Main.getInstance().getBackpackUtils().itemIsBackpack(itemInOffHand))
                backpackItem = itemInOffHand;
            else {
                Main.getInstance().getBackpackUtils().handleBackpackItemNotFound(player, itemsInBackpack);
                return;
            }

            // Check and save data
            Main.getInstance().getBackpackUtils().saveBackpackData(player, itemsInBackpack, backpackItem);
        }
    }
}
