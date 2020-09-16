package me.mrdev.vanish;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class VanishListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Vanish.getVanishList().forEach(VanishedID -> {
            Player Vanished = Bukkit.getPlayer(VanishedID);
            player.hidePlayer(Vanished);
        });
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            /*Or you can do :
            * if(event.getEntityType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER){
            * Rest of the code here
            * }
             */
            Player attacker = (Player)event.getDamager();
            if(Vanish.getVanishList().contains(attacker.getUniqueId())) {
                event.setCancelled(true);
                attacker.sendMessage(ChatColor.RED + "You can't attack players in vanish mode");
            }
        }
    }

}
