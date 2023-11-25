package de.silentesc.simplebackpacks;

import de.silentesc.simplebackpacks.listeners.CraftItemListener;
import de.silentesc.simplebackpacks.listeners.InventoryClickListener;
import de.silentesc.simplebackpacks.listeners.InventoryCloseListener;
import de.silentesc.simplebackpacks.listeners.PlayerInteractListener;
import de.silentesc.simplebackpacks.recipes.BackpackRecipe;
import de.silentesc.simplebackpacks.utils.BackpackUtils;
import de.silentesc.simplebackpacks.utils.JavaUtils;
import de.silentesc.simplebackpacks.utils.ShortMessages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Manager {
    /*
     * Manager for instances, register commands etc.
     * Used for example to init everything at the plugin start like prefix, utils and so on
     * You can get all utils from this class
     */

    // Util instances
    private JavaUtils javaUtils;
    private ShortMessages shortMessages;
    private BackpackUtils backpackUtils;
    // Recipes
    private BackpackRecipe backpackRecipe;

    public Manager() {
        initialize();
        register();
    }

    // Init all classes like utils etc.
    private void initialize() {
        javaUtils = new JavaUtils();
        backpackRecipe = new BackpackRecipe();
        shortMessages = new ShortMessages();
        backpackUtils = new BackpackUtils();
    }

    // Register Commands, TabCompleter and Listeners
    private void register() {
        // Listeners
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new CraftItemListener(), Main.getInstance());
        pluginManager.registerEvents(new PlayerInteractListener(), Main.getInstance());
        pluginManager.registerEvents(new InventoryCloseListener(), Main.getInstance());
        pluginManager.registerEvents(new InventoryClickListener(), Main.getInstance());
        // Recipes
        backpackRecipe.createBackpackRecipe();
    }

    // Getter
    public JavaUtils getJavaUtils() {
        return javaUtils;
    }

    public ShortMessages getShortMessages() {
        return shortMessages;
    }

    public BackpackUtils getBackpackUtils() {
        return backpackUtils;
    }
}
