package com.pahimar.ee3.skill;

import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.INBTTaggable;
import net.minecraft.nbt.NBTTagCompound;

public class PlayerKnowledge implements INBTTaggable
{
    private ItemTransmutationKnowledge itemTransmutationKnowledge;

    public PlayerKnowledge()
    {
        itemTransmutationKnowledge = new ItemTransmutationKnowledge();
    }

    public PlayerKnowledge(ItemTransmutationKnowledge itemTransmutationKnowledge)
    {
        this.itemTransmutationKnowledge = itemTransmutationKnowledge;
    }

    public ItemTransmutationKnowledge getItemTransmutationKnowledge()
    {
        return itemTransmutationKnowledge;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE))
        {
            // Read in the ItemStacks in the inventory from NBT
            if (nbtTagCompound.hasKey(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE))
            {
                itemTransmutationKnowledge = ItemTransmutationKnowledge.readItemTransmutationKnowledgeFromNBT(nbtTagCompound.getCompoundTag(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE));
            }
            else
            {
                itemTransmutationKnowledge = new ItemTransmutationKnowledge();
            }
        }
        else
        {
            itemTransmutationKnowledge = new ItemTransmutationKnowledge();
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagCompound itemTransmutationCompound = new NBTTagCompound();
        itemTransmutationKnowledge.writeToNBT(itemTransmutationCompound);

        nbtTagCompound.setTag(Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE, itemTransmutationCompound);
    }

    public static PlayerKnowledge readPlayerKnowledgeFromNBT(NBTTagCompound nbtTagCompound)
    {
        PlayerKnowledge playerKnowledge = new PlayerKnowledge();

        playerKnowledge.readFromNBT(nbtTagCompound);

        return playerKnowledge;
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(itemTransmutationKnowledge.toString());

        return stringBuilder.toString();
    }
}
