package com.pahimar.ee3.api.event;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.BlockEvent;

public class WorldTransmutationEvent extends BlockEvent
{
    public final ForgeDirection sideHit;
    public final EntityLiving entityLiving;
    public final ItemStack heldItemStack;

    public WorldTransmutationEvent(int x, int y, int z, World world, Block block, int blockMetaData, ForgeDirection sideHit, EntityLiving entityLiving)
    {
        super(x, y, z, world, block, blockMetaData);
        this.sideHit = sideHit;
        this.entityLiving = entityLiving;
        this.heldItemStack = entityLiving.getHeldItem();
    }

    @Override
    public boolean isCancelable()
    {
        return true;
    }
}
