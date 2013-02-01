package com.pahimar.ee3.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.common.ICraftingHandler;

public class CraftingHandler implements ICraftingHandler {

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {

        ItemStack openStone = null;
        
        for (ItemStack itemStack : player.inventory.mainInventory) {
            if (itemStack != null) {
                if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN)) {
                    openStone = itemStack;
                }
            }
        }

        ItemStack itemStack = null;
        if (openStone != null) {
            for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
                itemStack = craftMatrix.getStackInSlot(i);
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN)) {
                        openStone = itemStack;
                    }
                }
            }
        }
        
        if (openStone != null) {
            openStone.damageItem(ConfigurationSettings.TRANSMUTE_COST_ITEM, player);
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {

    }

}
