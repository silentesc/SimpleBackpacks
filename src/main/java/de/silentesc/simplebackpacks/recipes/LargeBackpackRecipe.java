package de.silentesc.simplebackpacks.recipes;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

public class LargeBackpackRecipe {
    public void createLargeBackpackRecipe() {
        // Check URL
        URL textureURL;
        try {
            textureURL = URI.create("https://textures.minecraft.net/texture/a2bb38516b29504186e11559cd5250ae218db4ddd27ae438726c847ce6b3c98").toURL();
        } catch (MalformedURLException e) { e.printStackTrace(); return; }

        // Create backpack item
        ItemStack backpackItem = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta backpackItemMeta = backpackItem.getItemMeta();
        if (backpackItemMeta == null) {
            System.out.println("backpackItemMeta is null");
            return;
        }
        backpackItemMeta.setDisplayName(Main.getInstance().getLargeBackpackDisplayName());
        backpackItemMeta.getPersistentDataContainer().set(Main.getInstance().getBackPackSizeKey(), PersistentDataType.INTEGER, 9*6);

        // Set texture
        SkullMeta skullMeta = (SkullMeta) backpackItemMeta;
        PlayerProfile playerProfile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures playerTextures = playerProfile.getTextures();
        playerTextures.setSkin(textureURL);
        playerProfile.setTextures(playerTextures);
        skullMeta.setOwnerProfile(playerProfile);

        backpackItem.setItemMeta(skullMeta);

        // Create recipe for backpack
        NamespacedKey key = new NamespacedKey(Main.getInstance(), "large_backpack");
        ShapedRecipe backpackRecipe = new ShapedRecipe(key, backpackItem);
        backpackRecipe.shape(
                "III",
                "ICI",
                "III"
        );
        backpackRecipe.setIngredient('I', Material.IRON_INGOT);
        backpackRecipe.setIngredient('C', Material.CHEST);

        Bukkit.addRecipe(backpackRecipe);
    }
}
