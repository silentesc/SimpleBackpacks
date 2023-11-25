package de.silentesc.simplebackpacks.utils;

import de.silentesc.simplebackpacks.Main;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ShortMessages {
    public void sendFailMessage(Player player, String message) {
        player.sendMessage(Main.getInstance().getPrefix() + "§c" + message);
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1f, 1f);
    }
}
