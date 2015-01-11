package com.pahimar.ee3.knowledge;

import net.minecraft.nbt.NBTTagCompound;

public abstract class PlayerKnowledge
{
    public abstract String getKnowledgeLabel();

    public abstract void readFromNBT(NBTTagCompound nbtTagCompound);

    public abstract void writeToNBT(NBTTagCompound nbtTagCompound);
}
