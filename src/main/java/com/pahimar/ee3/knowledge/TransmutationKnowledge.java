package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.INBTTaggable;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class TransmutationKnowledge implements INBTTaggable
{
    private Set<ItemStack> knownTransmutations;

    public TransmutationKnowledge()
    {
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
    }

    public TransmutationKnowledge(Collection<ItemStack> knownTransmutations)
    {
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.knownTransmutations.addAll(knownTransmutations);
    }

    public TransmutationKnowledge(ItemStack... knownTransmutations)
    {
        this(Arrays.asList(knownTransmutations));
    }

    public TransmutationKnowledge(NBTTagCompound nbtTagCompound)
    {
        this.knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.readFromNBT(nbtTagCompound);
    }

    public boolean isKnown(Item item)
    {
        return isKnown(new ItemStack(item));
    }

    public boolean isKnown(Block block)
    {
        return isKnown(new ItemStack(block));
    }

    public boolean isKnown(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;
        return this.knownTransmutations.contains(unitItemStack);
    }

    public Set<ItemStack> getKnownTransmutations()
    {
        return this.knownTransmutations;
    }

    public boolean learnTransmutation(Item item)
    {
        return learnTransmutation(new ItemStack(item));
    }

    public boolean learnTransmutation(Block block)
    {
        return learnTransmutation(new ItemStack(block));
    }

    public boolean learnTransmutation(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (!this.knownTransmutations.contains(unitItemStack))
        {
            return this.knownTransmutations.add(unitItemStack);
        }

        return false;
    }

    public boolean forgetTransmutation(ItemStack itemStack)
    {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (this.knownTransmutations.contains(unitItemStack))
        {
            return this.knownTransmutations.remove(unitItemStack);
        }

        return false;
    }

    public void forgetAllTransmutations()
    {
        this.knownTransmutations.clear();
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE))
        {
            // Read in the ItemStacks in the inventory from NBT
            if (nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE))
            {
                NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE, 10);
                knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
                for (int i = 0; i < tagList.tagCount(); ++i)
                {
                    NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                    ItemStack itemStack = ItemStack.loadItemStackFromNBT(tagCompound);
                    knownTransmutations.add(itemStack);
                }
            } else
            {
                knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
            }
        } else
        {
            knownTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        // Write the ItemStacks in the set to NBT
        NBTTagList tagList = new NBTTagList();
        for (ItemStack itemStack : knownTransmutations)
        {
            NBTTagCompound tagCompound = new NBTTagCompound();
            itemStack.writeToNBT(tagCompound);
            tagList.appendTag(tagCompound);
        }
        nbtTagCompound.setTag(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE, tagList);
    }

    @Override
    public String getTagLabel()
    {
        return "TransmutationKnowledge";
    }

    public static TransmutationKnowledge readTransmutationKnowledgeFromNBT(NBTTagCompound nbtTagCompound)
    {
        TransmutationKnowledge transmutationKnowledge = new TransmutationKnowledge();

        transmutationKnowledge.readFromNBT(nbtTagCompound);

        return transmutationKnowledge;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (ItemStack itemStack : knownTransmutations)
        {
            stringBuilder.append(String.format("%s, ", ItemHelper.toString(itemStack)));
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
