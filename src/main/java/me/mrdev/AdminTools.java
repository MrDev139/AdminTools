package me.mrdev;

import me.mrdev.PlayerLIst.PlayerList;
import me.mrdev.vanish.Vanish;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminTools extends JavaPlugin {

    @Override
    public void onEnable() { //This project is trash and part of the past
        long time = System.currentTimeMillis();
        new Vanish(this);
        new PlayerList(this);
        time = System.currentTimeMillis() - time;
        getLogger().info("The plugin took " + time + "ms to load a 2 cmds and 2 listeners");
    }


    @Override
    public void onDisable() {
        getLogger().info("Just remove me already please");
    }
}
