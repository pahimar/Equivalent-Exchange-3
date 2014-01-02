package com.pahimar.ee3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ITransmutationStone
 *
 * @author pahimar
 */
public interface ITransmutationStone
{
    public abstract void openPortableCraftingGUI(EntityPlayer thePlayer, ItemStack itemStack);

    public abstract void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit);
}
