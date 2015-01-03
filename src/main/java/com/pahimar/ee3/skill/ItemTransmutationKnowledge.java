package com.pahimar.ee3.skill;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.INBTTaggable;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class ItemTransmutationKnowledge implements INBTTaggable {
    private Set<ItemStack> knownItemTransmutations;

    public ItemTransmutationKnowledge() {
        this.knownItemTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
    }

    public ItemTransmutationKnowledge(Collection<ItemStack> knownItemTransmutations) {
        this.knownItemTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.knownItemTransmutations.addAll(knownItemTransmutations);
    }

    public ItemTransmutationKnowledge(ItemStack... knownItemTransmutations) {
        this(Arrays.asList(knownItemTransmutations));
    }

    public ItemTransmutationKnowledge(NBTTagCompound nbtTagCompound) {
        this.knownItemTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        this.readFromNBT(nbtTagCompound);
    }

    public boolean isItemStackKnown(ItemStack itemStack) {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;
        return this.knownItemTransmutations.contains(unitItemStack);
    }

    public Set<ItemStack> getKnownItemTransmutations() {
        return this.knownItemTransmutations;
    }

    public boolean learnItemStack(ItemStack itemStack) {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (!this.knownItemTransmutations.contains(unitItemStack)) {
            return this.knownItemTransmutations.add(unitItemStack);
        }

        return false;
    }

    public boolean forgetItemStack(ItemStack itemStack) {
        ItemStack unitItemStack = itemStack.copy();
        unitItemStack.stackSize = 1;

        if (this.knownItemTransmutations.contains(unitItemStack)) {
            return this.knownItemTransmutations.remove(unitItemStack);
        }

        return false;
    }

    public void resetKnownItemStacks() {
        this.knownItemTransmutations.clear();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        if (nbtTagCompound != null && nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE)) {
            // Read in the ItemStacks in the inventory from NBT
            if (nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE)) {
                NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE, 10);
                knownItemTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
                for (int i = 0; i < tagList.tagCount(); ++i) {
                    NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
                    ItemStack itemStack = ItemStack.loadItemStackFromNBT(tagCompound);
                    knownItemTransmutations.add(itemStack);
                }
            } else {
                knownItemTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
            }
        } else {
            knownItemTransmutations = new TreeSet<ItemStack>(ItemHelper.comparator);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        // Write the ItemStacks in the set to NBT
        NBTTagList tagList = new NBTTagList();
        for (ItemStack itemStack : knownItemTransmutations) {
            NBTTagCompound tagCompound = new NBTTagCompound();
            itemStack.writeToNBT(tagCompound);
            tagList.appendTag(tagCompound);
        }
        nbtTagCompound.setTag(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE, tagList);
    }

    public static ItemTransmutationKnowledge readItemTransmutationKnowledgeFromNBT(NBTTagCompound nbtTagCompound) {
        ItemTransmutationKnowledge itemTransmutationKnowledge = new ItemTransmutationKnowledge();

        itemTransmutationKnowledge.readFromNBT(nbtTagCompound);

        return itemTransmutationKnowledge;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        for (ItemStack itemStack : knownItemTransmutations) {
            stringBuilder.append(String.format("%s, ", ItemHelper.toString(itemStack)));
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
