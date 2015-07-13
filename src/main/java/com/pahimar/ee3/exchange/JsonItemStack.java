package com.pahimar.ee3.exchange;

import com.google.gson.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;

import java.lang.reflect.Type;

public class JsonItemStack
{
    public String itemName;
    public int itemDamage;
    public NBTTagCompound itemNBTTagCompound;

    public JsonItemStack()
    {
        this.itemName = null;
        this.itemDamage = 0;
        this.itemNBTTagCompound = null;
    }

    public JsonItemStack(ItemStack itemStack)
    {
        this.itemName = Item.itemRegistry.getNameForObject(itemStack.getItem());
        this.itemDamage = itemStack.getItemDamage();
        if (itemStack.stackTagCompound != null)
        {
            this.itemNBTTagCompound = itemStack.getTagCompound();
        }
    }

    @Override
    public String toString()
    {
        return String.format("itemName: %s, itemDamage: %s, itemNBTTagCompound: %s", itemName, itemDamage, itemNBTTagCompound);
    }
}
