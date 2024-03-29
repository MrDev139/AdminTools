package me.mrdev.PlayerLIst;


import me.mrdev.AdminTools;
import me.mrdev.Utils.ItemUtil;
import me.mrdev.Utils.InvUtils;
import me.mrdev.vanish.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class PlayerList implements CommandExecutor {

    public PlayerList(AdminTools plugin) {
        plugin.getCommand("PlayerList").setExecutor(this);
        plugin.getServer().getPluginManager().registerEvents(new PLListener() , plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command displays gui list if you want normal list use /list");
            return false;
        }else {
            Player player = (Player)sender;
            if(!player.hasPermission("AT.List")) {
                player.sendMessage("You would normally have permission , but looks like you don't!");
                return false;
            }else {
                Collection<? extends Player> OnlinePlayers = Bukkit.getOnlinePlayers();
                Inventory inv = Bukkit.createInventory(null , InvUtils.getDivisible(OnlinePlayers.size()), "Online players");
                OnlinePlayers.forEach(p -> {
                    ItemStack item = ItemUtil.createItem(Material.SKULL_ITEM , 1 , ChatColor.AQUA + p.getName());
                    SkullMeta meta = (SkullMeta) item.getItemMeta();
                    ArrayList<String>  lore = new ArrayList<>(Collections.singletonList(ChatColor.GREEN + "Visible"));
                    meta.setOwner(p.getName());
                    if(Vanish.getVanishList().contains(p.getUniqueId())) {
                        lore.set(0 , ChatColor.GREEN + "Vanished");
                    }
                    if (p == player) {
                        lore.set(0 , lore.get(0) + ChatColor.GREEN + "(Its you)");
                    }
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    inv.addItem(item);
                    player.openInventory(inv);
                });
            }
        }
        return true;
    }
}


