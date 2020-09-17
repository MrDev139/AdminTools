package me.mrdev.Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    private static ItemStack itemStack;

    public static ItemStack createItem(Material type, int amount, String displayName, ArrayList<String> lore, short damage, byte data) {
        itemStack = new ItemStack(type, amount, damage, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    public static ItemStack createItem(Material type, int amount) {
        itemStack = new ItemStack(type, amount);
        return itemStack;
    }

    public static ItemStack createItem(Material type, int amount, String displayName, byte data) {
        itemStack = new ItemStack(type, amount, data);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack createItem(Material type, int amount, String displayName) {
        itemStack = new ItemStack(type, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack createItem(Material type, int amount, short damage) {
        itemStack = new ItemStack(type, amount, damage);
        return itemStack;
    }

    public static ItemStack createItem(Material type, int amount, short damage, byte data) {
        itemStack = new ItemStack(type, amount, damage, data);
        return itemStack;
    }

    public static ItemStack createItem(Material type, int amount, String displayName , ArrayList<String> lore) {
        itemStack = new ItemStack(type, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


}

