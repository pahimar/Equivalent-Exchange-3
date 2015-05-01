package com.pahimar.ee3.exchange;

import com.pahimar.ee3.reference.Comparators;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.*;

public class OreStack implements Comparable<OreStack>
{
    public String oreName;
    public int stackSize;
    public static Comparator<OreStack> comparator = new Comparator<OreStack>()
    {
        @Override
        public int compare(OreStack oreStack1, OreStack oreStack2)
        {
            if (oreStack1 != null && oreStack1.oreName != null)
            {
                if (oreStack2 != null && oreStack2.oreName != null)
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
            Map<String, Integer> oreNameCountMap = new TreeMap<String, Integer>(Comparators.stringComparator);
            for (Object listElement : objectList)
            {
                if (listElement instanceof ItemStack)
                {
                    ItemStack itemStack = (ItemStack) listElement;

                    for (String oreName : CachedOreDictionary.getInstance().getOreNamesForItemStack(itemStack))
                    {
                        if (oreNameCountMap.containsKey(oreName))
                        {
                            oreNameCountMap.put(oreName, oreNameCountMap.get(oreName) + 1);
                        }
                        else
                        {
                            oreNameCountMap.put(oreName, 1);
                        }
                    }
                }
            }

            List<OreStack> candidateOreStacks = new ArrayList<OreStack>();
            for (String oreName : oreNameCountMap.keySet())
            {
                if (oreNameCountMap.get(oreName) == objectList.size())
                {
                    candidateOreStacks.add(new OreStack(oreName));
                }
            }

            if (candidateOreStacks.size() == 1)
            {
                return candidateOreStacks.get(0);
            }

            return null;
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
