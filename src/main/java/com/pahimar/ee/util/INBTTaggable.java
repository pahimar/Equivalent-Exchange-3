package com.pahimar.ee.util;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTTaggable {

    void readFromNBT(NBTTagCompound nbtTagCompound);

    void writeToNBT(NBTTagCompound nbtTagCompound);

    String getTagLabel();
}
