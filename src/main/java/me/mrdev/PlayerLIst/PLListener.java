package me.mrdev.PlayerLIst;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PLListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equals("Online players")) {
            if(event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                ItemStack head = event.getCurrentItem();
                if(head.hasItemMeta()) {
                    Bukkit.getOnlinePlayers().stream()
                            .filter(p -> p.getName().equals(head.getItemMeta().getDisplayName()))
                            .findAny().ifPresent(p -> player.teleport(p.getLocation()));
                }
                event.setCancelled(true);
            }
        }
    }

}
