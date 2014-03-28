package com.pahimar.ee3.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ActionEvent
 *
 * @author pahimar
 */
public class ActionEvent extends Event
{

    public final byte actionType;
    public final EntityPlayer player;
    public final World world;
    public final int x, y, z;
    public final boolean hasActionOccured;
    public final String data;
    public ItemStack itemStack;
    public ActionResult actionResult;

    public ActionEvent(byte actionType, ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, boolean hasActionOccured, String data)
    {

        this.actionType = actionType;
        this.itemStack = itemStack;
        this.player = player;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.hasActionOccured = hasActionOccured;
        this.data = data;
    }

    public enum ActionResult
    {
        SUCCESS, DEFAULT, FAILURE
    }
}
