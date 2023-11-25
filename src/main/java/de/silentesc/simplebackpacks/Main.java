package de.silentesc.simplebackpacks;

import de.silentesc.simplebackpacks.listeners.ServerShutdownListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private Manager manager;

    // Global variables
    private final String prefix;
    private final String smallBackpackDisplayName;
    private final String largeBackpackDisplayName;
    private final NamespacedKey backpackDataKey;
    private final NamespacedKey backPackSizeKey; // Also used to check if item is a backpack

    public Main() {
        instance = this;
        prefix = "§7[§eSimpleBackpacks§7] ";
        smallBackpackDisplayName = "§9Small Backpack";
        largeBackpackDisplayName = "§9Large Backpack";
        backpackDataKey = new NamespacedKey(this, "backpack_data");
        backPackSizeKey = new NamespacedKey(this, "backpack_size");
    }

    @Override
    public void onEnable() {
        // Constructor calls initialize() and register()
        manager = new Manager();
    }

    @Override
    public void onDisable() {
        // Close backpack inventories when the server is shut down
        new ServerShutdownListener().onShutdown();
    }

    // Getter
    public static Main getInstance() {
        return instance;
    }

    public Manager getManager() {
        return manager;
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
}
