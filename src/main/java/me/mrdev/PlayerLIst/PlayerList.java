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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

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
            Collection<? extends Player> OnlinePlayers = Bukkit.getOnlinePlayers();
            if(!player.hasPermission("AT.List")) {
                player.sendMessage("Oops , You don't have permission for it , but you still can use /list");
                return false;
            }else {
                Inventory inv = Bukkit.createInventory(null , OnlinePlayers.size() , "Online players");
                OnlinePlayers.forEach(onlinePlayer -> {
                    UUID ID = onlinePlayer.getUniqueId();
                    String names = ChatColor.AQUA + onlinePlayer.getName();
                    ArrayList<String> lore = new ArrayList<>();
                    if(Vanish.getVanishList().contains(ID)) {
                        lore.add(ChatColor.GREEN + "Vanished");
                        inv.addItem(ItemUtil.createItem(Material.SKULL , 1 , names , lore));
                    }else {
                        lore.add(ChatColor.GREEN + "Visible");
                        inv.addItem(ItemUtil.createItem(Material.SKULL , 1 , names , lore));
                    }
                    player.openInventory(inv);
                });
            }
        }
        return true;
    }
}


