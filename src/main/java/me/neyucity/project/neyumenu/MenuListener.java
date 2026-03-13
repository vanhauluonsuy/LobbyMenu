package me.neyucity.project.neyumenu;

import org.bukkit.*;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.stream.Collectors;

public class MenuListener implements Listener {

    private final NeyuMenu plugin;

    public MenuListener(NeyuMenu plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> giveMenuItems(e.getPlayer()), 10L);
    }

    public void giveMenuItemsPublic(Player p) {
        giveMenuItems(p);
    }

    private void giveMenuItems(Player p) {
        ConfigurationSection sec = plugin.getConfig().getConfigurationSection("items");
        if (sec == null) return;

        for (String key : sec.getKeys(false)) {
            ConfigurationSection item = sec.getConfigurationSection(key);

            int slot = item.getInt("slot");
            Material mat = Material.valueOf(item.getString("material"));

            ItemStack stack = new ItemStack(mat);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(color(item.getString("name")));
            meta.setLore(color(item.getStringList("lore")));
            stack.setItemMeta(meta);

            ItemStack current = p.getInventory().getItem(slot);
            if (current == null || !current.isSimilar(stack)) {
                p.getInventory().setItem(slot, stack);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!e.hasItem()) return;

        Player p = e.getPlayer();

        for (String key : plugin.getConfig().getConfigurationSection("items").getKeys(false)) {

            ConfigurationSection item = plugin.getConfig().getConfigurationSection("items." + key);
            Material mat = Material.valueOf(item.getString("material"));

            if (e.getItem().getType() != mat) continue;

            e.setCancelled(true);

            long cooldown = item.getLong("cooldown") * 1000;
            if (CooldownManager.isOnCooldown(p, key, cooldown)) {
                long left = CooldownManager.getRemaining(p, key, cooldown);
                p.sendMessage(ChatColor.RED + "Vui lòng đợi " + left + "s!");
                return;
            }

            p.playSound(p.getLocation(), Sound.valueOf(item.getString("sound")), 1f, 1f);
            p.spawnParticle(Particle.valueOf(item.getString("particle")), p.getLocation(), 20);

            for (String cmd : item.getStringList("commands")) {
                p.performCommand(cmd.replace("%player%", p.getName()));
            }

            CooldownManager.setCooldown(p, key);
        }
    }

    
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if (isMenuItem(e.getItemDrop().getItemStack())) e.setCancelled(true);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getCurrentItem() != null && isMenuItem(e.getCurrentItem())) {
            e.setCancelled(true);
        }
    }

    private boolean isMenuItem(ItemStack item) {
        for (String key : plugin.getConfig().getConfigurationSection("items").getKeys(false)) {
            Material mat = Material.valueOf(plugin.getConfig().getString("items." + key + ".material"));
            if (item.getType() == mat) return true;
        }
        return false;
    }

    private String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    private List<String> color(List<String> list) {
        return list.stream().map(this::color).collect(Collectors.toList());
    }
}
