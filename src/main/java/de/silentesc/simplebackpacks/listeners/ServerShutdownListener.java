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
            ItemStack[] itemsInBackpack = openInventory.getTopInventory().getContents();
            ItemStack backpackItem = Main.getInstance().getBackpackUtils().getBackpackItemFromPlayer(player.getUniqueId());

            // Checks
            if (!Main.getInstance().getSmallBackpackDisplayName().equals(openInventory.getTitle()) && !Main.getInstance().getLargeBackpackDisplayName().equals(openInventory.getTitle())) continue;

            // Check and save data
            Main.getInstance().getBackpackUtils().saveBackpackData(player, itemsInBackpack, backpackItem);
        }
    }
}
