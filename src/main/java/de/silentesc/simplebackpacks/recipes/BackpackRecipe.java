package de.silentesc.simplebackpacks.recipes;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class BackpackRecipe {
    public void createBackpackRecipe() {
        // Check URL
        URL textureURL;
        try {
            textureURL = URI.create("https://textures.minecraft.net/texture/a45c7bbcee900802da6b3f8ee538aecf5633beea8301d13f247bd63f75907a58").toURL();
        } catch (MalformedURLException e) { e.printStackTrace(); return; }

        // Create backpack item
        ItemStack backpackItem = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta backpackItemMeta = backpackItem.getItemMeta();
        if (backpackItemMeta == null) {
            System.out.println("backpackItemMeta is null");
            return;
        }
        backpackItemMeta.setDisplayName(Main.getInstance().getBackpackName());
        SkullMeta skullMeta = (SkullMeta) backpackItemMeta;
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures playerTextures = playerProfile.getTextures();
        playerTextures.setSkin(textureURL);
        playerProfile.setTextures(playerTextures);
        skullMeta.setOwnerProfile(playerProfile);
        backpackItem.setItemMeta(skullMeta);

        // Create recipe for backpack
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "backpack");
        ShapedRecipe backpackRecipe = new ShapedRecipe(key, backpackItem);
        backpackRecipe.shape(
                "LLL",
                "LCL",
                "LLL"
        );
        backpackRecipe.setIngredient('L', Material.LEATHER);
        backpackRecipe.setIngredient('C', Material.CHEST);

        Bukkit.addRecipe(backpackRecipe);
    }
}
