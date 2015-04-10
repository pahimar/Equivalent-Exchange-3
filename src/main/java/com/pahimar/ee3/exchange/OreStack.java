package com.pahimar.ee3.exchange;

import com.google.common.primitives.Ints;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
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

    private OreStack()
    {
    }

    public OreStack(String oreName)
    {
        this(oreName, 1);
    }

    public OreStack(String oreName, int stackSize)
    {
        this.oreName = oreName;
        this.stackSize = stackSize;
    }

    public static List<OreStack> getOreStacks(ItemStack itemStack)
    {
        List<OreStack> oreList = new ArrayList<OreStack>();
        for (int id : OreDictionary.getOreIDs(itemStack))
        {
            oreList.add(new OreStack(OreDictionary.getOreName(id)));
        }
        return oreList;
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
        if (objectList.size() > 0)
        {
            List<Integer> commonIDs = null;
            for (Object listElement : objectList)
            {
                if (listElement instanceof ItemStack)
                {
                    ItemStack stack = (ItemStack) listElement;

                    if (commonIDs == null)
                    {
                        commonIDs = new ArrayList<Integer>(Ints.asList(OreDictionary.getOreIDs(stack)));
                    }
                    else
                    {
                        commonIDs.retainAll(Ints.asList(OreDictionary.getOreIDs(stack)));
                    }
                }
            }
            if (commonIDs != null && !commonIDs.isEmpty())
            {
                return new OreStack(OreDictionary.getOreName(commonIDs.get(0)));
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

    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setString("oreName", oreName);
        nbtTagCompound.setInteger("stackSize", stackSize);
        return nbtTagCompound;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        this.oreName = nbtTagCompound.getString("oreName");
        this.stackSize = nbtTagCompound.getInteger("stackSize");
    }

    public static OreStack loadOreStackFromNBT(NBTTagCompound nbtTagCompound)
    {
        OreStack oreStack = new OreStack();
        oreStack.readFromNBT(nbtTagCompound);
        return oreStack.oreName != null ? oreStack : null;
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
