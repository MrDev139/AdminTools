package me.mrdev;

import me.mrdev.vanish.Vanish;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdminTools extends JavaPlugin {

    @Override
    public void onEnable() {
        new Vanish(this);
    }


    @Override
    public void onDisable() {

    }
}
