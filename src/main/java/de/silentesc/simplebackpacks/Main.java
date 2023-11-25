package de.silentesc.simplebackpacks;

import de.silentesc.simplebackpacks.listeners.ServerShutdownListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private Manager manager;

    // Global variables
    private final String prefix;
    private final String backpackName;
    private final NamespacedKey backpackDataKey;

    public Main() {
        instance = this;
        prefix = "§7[§eSimpleBackpacks§7] ";
        backpackName = "§9Backpack";
        backpackDataKey = new NamespacedKey(this, "backpack_data");
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

    public String getBackpackName() {
        return backpackName;
    }

    public NamespacedKey getBackpackDataKey() {
        return backpackDataKey;
    }
}
