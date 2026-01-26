package me.neyucity.project.neyumenu;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private static final Map<String, Map<UUID, Long>> cooldowns = new HashMap<>();

    public static boolean isOnCooldown(Player player, String key, long cooldown) {
        cooldowns.putIfAbsent(key, new HashMap<>());

        Long last = cooldowns.get(key).get(player.getUniqueId());
        if (last == null) return false;

        return System.currentTimeMillis() - last < cooldown;
    }

    public static long getRemaining(Player player, String key, long cooldown) {
        Long last = cooldowns.get(key).get(player.getUniqueId());
        if (last == null) return 0;

        return Math.max(0, (cooldown - (System.currentTimeMillis() - last)) / 1000);
    }

    public static void setCooldown(Player player, String key) {
        cooldowns.get(key).put(player.getUniqueId(), System.currentTimeMillis());
    }
}
