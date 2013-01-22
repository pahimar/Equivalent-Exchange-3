package com.pahimar.ee3.core.handlers;

import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.Strings;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;


public class CraftingHandler implements ICraftingHandler {

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {

        for (ItemStack itemStack : player.inventory.mainInventory) {
            if (itemStack != null) {
                if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANS_GUI_OPEN)) {
                    System.out.println("Found a stone with the GUI open");
                }
            }
        }
        
        // TODO Finish this up in the morning
        //for (ItemStack itemStack : craftMatrix)
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {  
    }

}
