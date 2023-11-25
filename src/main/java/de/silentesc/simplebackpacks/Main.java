package de.silentesc.simplebackpacks;

import de.silentesc.simplebackpacks.listeners.*;
import de.silentesc.simplebackpacks.recipes.LargeBackpackRecipe;
import de.silentesc.simplebackpacks.recipes.SmallBackpackRecipe;
import de.silentesc.simplebackpacks.utils.BackpackUtils;
import de.silentesc.simplebackpacks.utils.JavaUtils;
import de.silentesc.simplebackpacks.utils.ShortMessages;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;

    // Global variables
    private String prefix;
    private String smallBackpackDisplayName;
    private String largeBackpackDisplayName;
    private NamespacedKey backpackDataKey;
    private NamespacedKey backPackSizeKey; // Also used to check if item is a backpack

    // Util instances
    private JavaUtils javaUtils;
    private ShortMessages shortMessages;
    private BackpackUtils backpackUtils;

    // Recipes
    private SmallBackpackRecipe smallBackpackRecipe;
    private LargeBackpackRecipe largeBackpackRecipe;

    public Main() {
        instance = this;
    }

    @Override
    public void onEnable() {
        initialize();
        register();
    }

    @Override
    public void onDisable() {
        // Close backpack inventories when the server is shut down
        new ServerShutdownListener().onShutdown();
    }

    // Init all classes like utils etc.
    private void initialize() {
        prefix = "§7[§eSimpleBackpacks§7] ";
        smallBackpackDisplayName = "§9Small Backpack";
        largeBackpackDisplayName = "§9Large Backpack";
        backpackDataKey = new NamespacedKey(this, "backpack_data");
        backPackSizeKey = new NamespacedKey(this, "backpack_size");

        javaUtils = new JavaUtils();
        smallBackpackRecipe = new SmallBackpackRecipe();
        largeBackpackRecipe = new LargeBackpackRecipe();
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
        smallBackpackRecipe.createSmallBackpackRecipe();
        largeBackpackRecipe.createLargeBackpackRecipe();
    }

    // Getter
    public static Main getInstance() {
        return instance;
    }
    public String getPrefix() {
        return prefix;
    }
    public String getSmallBackpackDisplayName() {
        return smallBackpackDisplayName;
    }
    public String getLargeBackpackDisplayName() {
        return largeBackpackDisplayName;
    }
    public NamespacedKey getBackpackDataKey() {
        return backpackDataKey;
    }
    public NamespacedKey getBackPackSizeKey() {
        return backPackSizeKey;
    }
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
