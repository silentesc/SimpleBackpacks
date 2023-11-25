package de.silentesc.simplebackpacks.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class JavaUtils {
    public int randomNumber(int min, int max) {
        return ((int)(Math.random()*(max-min)) + min);
    }

    public String itemToString(ItemStack itemStack) {
        YamlConfiguration config = new YamlConfiguration();
        config.set("i", itemStack);
        return config.saveToString();
    }

    public ItemStack stringToItem(String str) {
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.loadFromString(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return config.getItemStack("i");
    }
}
