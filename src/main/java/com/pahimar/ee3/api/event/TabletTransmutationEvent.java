package com.pahimar.ee3.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TabletTransmutationEvent extends Event
{
    public final EntityPlayer entityPlayer;
    public final int x;
    public final int y;
    public final int z;
    public final ItemStack itemStack;

    public TabletTransmutationEvent(EntityPlayer entityPlayer, int x, int y, int z, ItemStack itemStack)
    {
        this.entityPlayer = entityPlayer;
        this.x = x;
        this.y = y;
        this.z = z;
        this.itemStack = itemStack;
    }

    @Override
    public boolean isCancelable()
    {
        return true;
    }
}
