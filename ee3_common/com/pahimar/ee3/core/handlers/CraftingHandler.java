package com.pahimar.ee3.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.nbt.NBTHelper;

import cpw.mods.fml.common.ICraftingHandler;

/**
 * Equivalent-Exchange-3
 * 
 * CraftingHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CraftingHandler implements ICraftingHandler {

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {

        if (player.worldObj.isRemote) {
            doPortableCrafting(player, craftMatrix);
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {

    }

    /***
     * Check to see if the crafting is occurring from the portable crafting
     * interface. If so, do durability damage to the appropriate transmutation
     * stone that was used for portable crafting.
     * 
     * @param player
     *            The player that is completing the crafting
     * @param craftMatrix
     *            The contents of the crafting matrix
     */
    private void doPortableCrafting(EntityPlayer player, IInventory craftMatrix) {

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
}
