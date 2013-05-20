package com.pahimar.ee3.core.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * ItemDropHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemHelper {

    private static double rand;
    
    public static boolean compare(ItemStack first, ItemStack second) {
        
        if ((first != null) && (second != null)) {
            if (first.itemID == second.itemID) {
                if (first.getItemDamage() == second.getItemDamage()) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ArrayList collateStacks(List unCollatedStacks) {
        ArrayList collatedStacks = new ArrayList();
        
        for (int i = 0; i < unCollatedStacks.size(); i++) {
            
            if (collatedStacks.size() == 0) {
                collatedStacks.add(unCollatedStacks.get(i));
            }
            else {
                boolean found = false;
                
                for (int j = 0; j < collatedStacks.size(); j++) {
                    if ((unCollatedStacks.get(i) instanceof ItemStack) && (collatedStacks.get(j) instanceof ItemStack)) {
                        ItemStack unCollatedStack = (ItemStack) unCollatedStacks.get(i);
                        ItemStack collatedStack = (ItemStack) collatedStacks.get(j);
                        
                        if (compare(unCollatedStack, collatedStack)) {
                            ((ItemStack) collatedStacks.get(j)).stackSize += 1;
                            found = true;
                        }
                    }
                    else if ((unCollatedStacks.get(i) instanceof OreStack) && (collatedStacks.get(j) instanceof OreStack)) {
                        OreStack unCollatedStack = (OreStack) unCollatedStacks.get(i);
                        OreStack collatedStack = (OreStack) collatedStacks.get(j);
                        
                        if (OreStack.compareStacks(unCollatedStack, collatedStack)) {
                            ((OreStack) collatedStacks.get(j)).stackSize += 1;
                            found = true;
                        }
                    }
                }
                
                if (!found) {
                    collatedStacks.add(unCollatedStacks.get(i));
                }
            }
        }
        
        return collatedStacks;
    }

    public static boolean hasColor(ItemStack itemStack) {

        return !itemStack.hasTagCompound() ? false : !itemStack.getTagCompound().hasKey(Strings.NBT_ITEM_DISPLAY) ? false : itemStack.getTagCompound().getCompoundTag(Strings.NBT_ITEM_DISPLAY).hasKey(Strings.NBT_ITEM_COLOR);
    }

    public static int getColor(ItemStack itemStack) {

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null)
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        else {

            NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);
            return displayTagCompound == null ? Integer.parseInt(Colours.PURE_WHITE, 16) : displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR) ? displayTagCompound.getInteger(Strings.NBT_ITEM_COLOR) : Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    public static void setColor(ItemStack itemStack, int color) {

        if (itemStack != null) {

            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound == null) {

                nbtTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(nbtTagCompound);
            }

            NBTTagCompound colourTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

            if (!nbtTagCompound.hasKey(Strings.NBT_ITEM_DISPLAY)) {
                nbtTagCompound.setCompoundTag(Strings.NBT_ITEM_DISPLAY, colourTagCompound);
            }

            colourTagCompound.setInteger(Strings.NBT_ITEM_COLOR, color);
        }
    }

    public static void dropMiniumShard(EntityPlayer player, EntityLiving entity) {

        if (GeneralHelper.isHostileEntity(entity)) {
            rand = Math.random();

            if (rand < 0.15d) {
                entity.dropItem(ModItems.miniumShard.itemID, 1);
            }
        }
    }

}
