package me.mrdev.PlayerLIst;


import com.avaje.ebean.validation.NotNull;
import me.mrdev.AdminTools;
import me.mrdev.Utils.ItemUtil;
import me.mrdev.vanish.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class PlayerList implements CommandExecutor {

    public PlayerList(AdminTools plugin) {
        plugin.getCommand("PlayerList").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("This command displays gui list if you want normal list use /list");
            return false;
        }else {
            Player player = (Player)sender;
            if(!player.hasPermission("AT.List")) {
                player.sendMessage("Oops , You don't have permission for it , but you still can use /list");
                return false;
            }else {
                Collection<? extends Player> OnlinePlayers = Bukkit.getOnlinePlayers();
                Inventory inv = Bukkit.createInventory(null , Math.max(OnlinePlayers.size(), 9), "Online players");
                OnlinePlayers.forEach(p -> {
                    UUID ID = p.getUniqueId();
                    String display = ChatColor.AQUA + p.getName();
                    ItemStack item = ItemUtil.createItem(Material.SKULL_ITEM , 1 , display);
                    ArrayList<String> lore;
                    SkullMeta meta;
                    if(Vanish.getVanishList().contains(ID)) {
                        lore = new ArrayList<>(Collections.singletonList(ChatColor.GREEN + "Vanished"));
                        meta = (SkullMeta) item.getItemMeta();
                        meta.setOwner(p.getName());
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        inv.addItem(item);
                    }else {
                        lore = new ArrayList<>(Collections.singletonList(ChatColor.GREEN + "Visible"));
                        meta = (SkullMeta) item.getItemMeta();
                        meta.setOwner(p.getName());
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        inv.addItem(item);
                    }
                    player.openInventory(inv);
                });
            }
        }
        return true;
    }
}


