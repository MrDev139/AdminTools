package me.mrdev.vanish;


import me.mrdev.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Vanish implements CommandExecutor {

    private static Set<UUID> VanishList = new HashSet<>();
    private AdminTools plugin;

    public Vanish(AdminTools plugin) {
        this.plugin = plugin;
        plugin.getCommand("vanish").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Wanna see the vanish List ? use /v list");
            return false;

        }else {
            Player player = (Player)sender;
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();
            if(args.length == 0) {
                if(!player.hasPermission("AT.vanish") || !player.isOp()) {
                    player.sendMessage("Unknown command. Type ``/help`` for help");
                    return false;

                }else if(!VanishList.contains(player.getUniqueId())) {
                    VanishList.add(player.getUniqueId());
                    players.forEach(p -> p.hidePlayer(player));
                    player.sendMessage(ChatColor.GREEN + "You are now Vanished !");
                    //Did not replace this with a lambda because I wanted to use the cancel method
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (VanishList.contains(player.getUniqueId())) {
                                player.sendTitle(ChatColor.GREEN + "You are currently vanished", ChatColor.YELLOW + "If you want to become visible type /vanish");
                            } else this.cancel();
                        }
                    }.runTaskTimer(plugin ,40 , 40);
                    return true;

                }else {
                    VanishList.remove(player.getUniqueId());
                    players.forEach(p -> p.showPlayer(player));
                    player.sendTitle(ChatColor.GREEN + "You are now Visible", ChatColor.YELLOW + "If you want to vanish again type /vanish");
                    player.sendMessage(ChatColor.GREEN + "You are now Visible !");
                    return true;
                }
            }else if(args.length == 1) {
                if(args[0].equalsIgnoreCase("List")) {
                    VanishList.forEach(vanishedID -> {
                            Player vanishedPLayer = Bukkit.getPlayer(vanishedID);
                            if(vanishedPLayer == null || VanishList.size() <= 0) {
                                player.sendMessage(ChatColor.GREEN + "There is no Vanished Players");
                            }else {
                                String names = vanishedPLayer.getName();
                                player.sendMessage(ChatColor.GREEN + "Vanished Players are\n" + names);
                            }
                    });
                }
                return true;
            }
        }
        return true;
    }

    public static Set<UUID> getVanishList() {
        return VanishList;
    }
}
