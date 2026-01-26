package me.neyucity.project.neyumenu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NeyuMenu extends JavaPlugin {

    private MenuListener menuListener;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        menuListener = new MenuListener(this);
        Bukkit.getPluginManager().registerEvents(menuListener, this);

        getLogger().info("NeyuMenu enabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("neyumenu")) return false;

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {

            if (!sender.hasPermission("neyumenu.admin")) {
                sender.sendMessage(ChatColor.RED + "Bạn không có quyền!");
                return true;
            }

            reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "✔ Đã reload config NeyuMenu!");

            for (Player p : Bukkit.getOnlinePlayers()) {
                menuListener.giveMenuItemsPublic(p);
            }

            return true;
        }

        sender.sendMessage(ChatColor.YELLOW + "Usage: /neyumenu reload");
        return true;
    }
}
