package com.pahimar.ee3.helper;

import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.Comparator;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemDropHelper
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class ItemHelper
{

    private static double rand;

    /**
     * Compares two ItemStacks for equality, testing itemID, metaData,
     * stackSize, and their NBTTagCompounds (if they are present)
     *
     * @param first  The first ItemStack being tested for equality
     * @param second The second ItemStack being tested for equality
     * @return true if the two ItemStacks are equivalent, false otherwise
     */
    public static boolean equals(ItemStack first, ItemStack second)
    {

        return (comparator.compare(first, second) == 0);
    }

    public static int compare(ItemStack itemStack1, ItemStack itemStack2)
    {
        return comparator.compare(itemStack1, itemStack2);
    }

    public static String toString(ItemStack itemStack)
    {

        if (itemStack != null)
        {
            return String.format("%sxitemStack[%s:%s:%s:%s]", itemStack.stackSize, itemStack.itemID, itemStack.getItemDamage(), itemStack.getUnlocalizedName(), itemStack.getItem().getClass().getCanonicalName());
        }

        return "itemStack[null]";
    }

    public static boolean hasColor(ItemStack itemStack)
    {

        return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey(Strings.NBT_ITEM_DISPLAY) && itemStack.getTagCompound().getCompoundTag(Strings.NBT_ITEM_DISPLAY).hasKey(Strings.NBT_ITEM_COLOR);
    }

    public static int getColor(ItemStack itemStack)
    {

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null)
        {
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        }
        else
        {

            NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);
            return displayTagCompound == null ? Integer.parseInt(Colours.PURE_WHITE, 16) : displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR) ? displayTagCompound.getInteger(Strings.NBT_ITEM_COLOR) : Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    public static void setColor(ItemStack itemStack, int color)
    {

        if (itemStack != null)
        {

            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound == null)
            {

                nbtTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(nbtTagCompound);
            }

            NBTTagCompound colourTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

            if (!nbtTagCompound.hasKey(Strings.NBT_ITEM_DISPLAY))
            {
                nbtTagCompound.setCompoundTag(Strings.NBT_ITEM_DISPLAY, colourTagCompound);
            }

            colourTagCompound.setInteger(Strings.NBT_ITEM_COLOR, color);
        }
    }

    public static void dropMiniumShard(EntityPlayer player, EntityLivingBase entity)
    {

        if (GeneralHelper.isHostileEntity(entity))
        {
            rand = Math.random();

            if (rand < 0.15d)
            {
                entity.dropItem(ModItems.miniumShard.itemID, 1);
            }
        }
    }

    public static Comparator<ItemStack> comparator = new Comparator<ItemStack>()
    {

        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {

            if (itemStack1 != null && itemStack2 != null)
            {
                // Sort on itemID
                if (itemStack1.itemID == itemStack2.itemID)
                {

                    // Then sort on meta
                    if (itemStack1.getItemDamage() == itemStack2.getItemDamage())
                    {

                        // Then sort on NBT
                        if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                        {

                            // Then sort on stack size
                            if (itemStack1.getTagCompound().equals(itemStack2.getTagCompound()))
                            {
                                return (itemStack1.stackSize - itemStack2.stackSize);
                            }
                            else
                            {
                                return (itemStack1.getTagCompound().hashCode() - itemStack2.getTagCompound().hashCode());
                            }
                        }
                        else if (!(itemStack1.hasTagCompound()) && itemStack2.hasTagCompound())
                        {
                            return -1;
                        }
                        else if (itemStack1.hasTagCompound() && !(itemStack2.hasTagCompound()))
                        {
                            return 1;
                        }
                        else
                        {
                            return (itemStack1.stackSize - itemStack2.stackSize);
                        }
                    }
                    else
                    {
                        return (itemStack1.getItemDamage() - itemStack2.getItemDamage());
                    }
                }
                else
                {
                    return (itemStack1.itemID - itemStack2.itemID);
                }
            }
            else if (itemStack1 != null && itemStack2 == null)
            {
                return -1;
            }
            else if (itemStack1 == null && itemStack2 != null)
            {
                return 1;
            }
            else
            {
                return 0;
            }

        }

    };
}
