package com.pahimar.ee.util;

import com.pahimar.ee.EquivalentExchange;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class EntityUtils {

    /**
     * TODO Finish JavaDoc
     *
     * @param entity
     * @return
     */
    public static NBTTagCompound getCustomEntityData(Entity entity) {

        if (entity != null && entity.getEntityData().hasKey(EquivalentExchange.MOD_ID) && entity.getEntityData().getTag(EquivalentExchange.MOD_ID) instanceof NBTTagCompound) {
            return entity.getEntityData().getCompoundTag(EquivalentExchange.MOD_ID);
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
            entity.getEntityData().setTag(EquivalentExchange.MOD_ID, nbtTagCompound);
        }
    }

    public static EnumFacing getFacingFromEntity(BlockPos blockPos, EntityLivingBase entity) {
        return EnumFacing.getFacingFromVector((float) (entity.posX - blockPos.getX()), (float) (entity.posY - blockPos.getY()), (float) (entity.posZ - blockPos.getZ()));
    }
}
