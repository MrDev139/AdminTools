package me.mrdev;

import me.mrdev.PlayerLIst.PlayerList;
import me.mrdev.vanish.Vanish;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminTools extends JavaPlugin {

    @Override
    public void onEnable() {
        new Vanish(this);
        new PlayerList(this);
    }


    @Override
    public void onDisable() {

    }
}
