package com.pahimar.ee3.util;

import com.pahimar.ee3.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class EntityHelper
{
    public static NBTTagCompound getCustomEntityData(Entity entity)
    {
        if (entity != null && entity.getEntityData().hasKey(Reference.MOD_ID.toLowerCase()) && entity.getEntityData().getTag(Reference.MOD_ID.toLowerCase()) instanceof NBTTagCompound)
        {
            return entity.getEntityData().getCompoundTag(Reference.MOD_ID.toLowerCase());
        }

        return null;
    }

    public static void saveCustomEntityData(Entity entity, NBTTagCompound nbtTagCompound)
    {
        if (entity != null)
        {
            entity.getEntityData().setTag(Reference.MOD_ID.toLowerCase(), nbtTagCompound);
        }
    }
}
