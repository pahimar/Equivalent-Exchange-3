package com.pahimar.ee3.exchange;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OreStack implements Comparable<OreStack>
{
    public String oreName;
    public int stackSize;
    public static Comparator<OreStack> comparator = new Comparator<OreStack>()
    {

        @Override
        public int compare(OreStack oreStack1, OreStack oreStack2)
        {

            if (oreStack1 != null)
            {
                if (oreStack2 != null)
                {
                    if (oreStack1.oreName.equalsIgnoreCase(oreStack2.oreName))
                    {
                        return oreStack1.stackSize - oreStack2.stackSize;
                    }
                    else
                    {
                        return oreStack1.oreName.compareToIgnoreCase(oreStack2.oreName);
                    }
                }
                else
                {
                    return -1;
                }
            }
            else
            {
                if (oreStack2 != null)
                {
                    return 1;
                }
                else
                {
                    return 0;
                }
            }
        }
    };

    public OreStack(String oreName)
    {
        this(oreName, 1);
    }

    public OreStack(String oreName, int stackSize)
    {
        this.oreName = oreName;
        this.stackSize = stackSize;
    }

    public OreStack(ItemStack itemStack)
    {
        if (itemStack != null && OreDictionary.getOreIDs(itemStack).length > 0)
        {
            this.oreName = OreDictionary.getOreName(OreDictionary.getOreIDs(itemStack)[0]); // TODO Likely not ideal, revisit
            this.stackSize = itemStack.stackSize;
        }
    }

    public static boolean compareOreNames(OreStack oreStack1, OreStack oreStack2)
    {
        if (oreStack1 != null && oreStack2 != null)
        {
            if ((oreStack1.oreName != null) && (oreStack2.oreName != null))
            {
                return oreStack1.oreName.equalsIgnoreCase(oreStack2.oreName);
            }
        }

        return false;
    }

    public static OreStack getOreStackFromList(Object... objects)
    {
        return getOreStackFromList(Arrays.asList(objects));
    }

    public static OreStack getOreStackFromList(List<?> objectList)
    {
        for (Object listElement : objectList)
        {
            if (listElement instanceof ItemStack)
            {
                ItemStack stack = (ItemStack) listElement;

                if (OreDictionary.getOreIDs(stack).length > 0)
                {
                    return new OreStack(stack);
                }
            }
        }

        return null;
    }

    public static int compare(OreStack oreStack1, OreStack oreStack2)
    {
        return comparator.compare(oreStack1, oreStack2);
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof OreStack && (comparator.compare(this, (OreStack) object) == 0);
    }

    @Override
    public String toString()
    {
        return String.format("%sxoreStack.%s", stackSize, oreName);
    }

    @Override
    public int compareTo(OreStack oreStack)
    {
        return comparator.compare(this, oreStack);
    }
}
