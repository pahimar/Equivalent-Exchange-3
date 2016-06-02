package com.pahimar.ee3.util;

import com.pahimar.ee3.reference.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class EntityHelper {

    /**
     * TODO Finish JavaDoc
     *
     * @param entity
     * @return
     */
    public static NBTTagCompound getCustomEntityData(Entity entity) {

        if (entity != null && entity.getEntityData().hasKey(Reference.LOWERCASE_MOD_ID) && entity.getEntityData().getTag(Reference.LOWERCASE_MOD_ID) instanceof NBTTagCompound) {
            return entity.getEntityData().getCompoundTag(Reference.LOWERCASE_MOD_ID);
        }

        return new NBTTagCompound();
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entity
     * @param nbtTagCompound
     */
    public static void saveCustomEntityData(Entity entity, NBTTagCompound nbtTagCompound) {

        if (entity != null) {
            entity.getEntityData().setTag(Reference.LOWERCASE_MOD_ID, nbtTagCompound);
        }
    }
}
