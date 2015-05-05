package com.pahimar.ee3.reference;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Comparator;

public class Comparators
{
    public static Comparator<String> stringComparator = new Comparator<String>()
    {
        @Override
        public int compare(String string1, String string2)
        {
            return string1.compareToIgnoreCase(string2);
        }
    };

    public static Comparator<ItemStack> idComparator = new Comparator<ItemStack>()
    {
        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {
            if (itemStack1 != null && itemStack2 != null)
            {
                // Sort on itemID
                if (Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem()) == 0)
                {
                    // Sort on item
                    if (itemStack1.getItem() == itemStack2.getItem())
                    {
                        // Then sort on meta
                        if ((itemStack1.getItemDamage() == itemStack2.getItemDamage()) || itemStack1.getItemDamage() == OreDictionary.WILDCARD_VALUE || itemStack2.getItemDamage() == OreDictionary.WILDCARD_VALUE)
                        {
                            // Then sort on NBT
                            if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound())
                            {
                                // Then sort on stack size
                                if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2))
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
                        return itemStack1.getItem().getUnlocalizedName(itemStack1).compareToIgnoreCase(itemStack2.getItem().getUnlocalizedName(itemStack2));
                    }
                }
                else
                {
                    return Item.getIdFromItem(itemStack1.getItem()) - Item.getIdFromItem(itemStack2.getItem());
                }
            }
            else if (itemStack1 != null)
            {
                return -1;
            }
            else if (itemStack2 != null)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    };

    public static Comparator<ItemStack> reverseIdComparator = new Comparator<ItemStack>()
    {
        @Override
        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {
            return idComparator.compare(itemStack1, itemStack2) * -1;
        }
    };

    public static Comparator<ItemStack> displayNameComparator = new Comparator<ItemStack>()
    {
        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {
            if (itemStack1 != null && itemStack2 != null)
            {
                if (itemStack1.getDisplayName().equalsIgnoreCase(itemStack2.getDisplayName()))
                {
                    return idComparator.compare(itemStack1, itemStack2);
                }
                else
                {
                    return itemStack1.getDisplayName().compareToIgnoreCase(itemStack2.getDisplayName());
                }
            }
            else if (itemStack1 != null)
            {
                return -1;
            }
            else if (itemStack2 != null)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    };

    public static Comparator<ItemStack> reverseDisplayNameComparator = new Comparator<ItemStack>()
    {
        @Override
        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {
            return displayNameComparator.compare(itemStack1, itemStack2) * -1;
        }
    };

    public static Comparator<ItemStack> energyValueItemStackComparator = new Comparator<ItemStack>()
    {
        @Override
        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {
            if (itemStack1 != null && itemStack2 != null)
            {
                if (EnergyValueRegistry.getInstance().hasEnergyValue(itemStack1) && EnergyValueRegistry.getInstance().hasEnergyValue(itemStack2))
                {
                    return Float.compare(EnergyValueRegistry.getInstance().getEnergyValue(itemStack1).getValue(), EnergyValueRegistry.getInstance().getEnergyValue(itemStack2).getValue());
                }
                else
                {
                    return idComparator.compare(itemStack1, itemStack2);
                }
            }
            else if (itemStack1 != null)
            {
                return -1;
            }
            else if (itemStack2 != null)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    };

    public static Comparator<ItemStack> reverseEnergyValueComparator = new Comparator<ItemStack>()
    {
        @Override
        public int compare(ItemStack itemStack1, ItemStack itemStack2)
        {
            return energyValueItemStackComparator.compare(itemStack1, itemStack2) * -1;
        }
    };
}
