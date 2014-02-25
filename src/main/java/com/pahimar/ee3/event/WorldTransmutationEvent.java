package com.pahimar.ee3.event;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Equivalent-Exchange-3
 * <p/>
 * WorldTransmutationEvent
 *
 * @author pahimar
 */
public class WorldTransmutationEvent extends ActionEvent
{

    public int targetMeta;
    public Block targetID;

    public WorldTransmutationEvent(byte actionType, ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, boolean hasActionOccured, String data)
    {

        super(actionType, itemStack, player, world, x, y, z, hasActionOccured, data);
        targetID = Block.getBlockFromName(data.substring(0, data.indexOf(":")));
        targetMeta = Integer.parseInt(data.substring(data.indexOf(":") + 1));
        actionResult = ActionResult.DEFAULT;
    }
}
