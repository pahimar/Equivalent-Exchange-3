package com.pahimar.ee3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * ITransmutationStone
 * 
 * Transmutation Stone interface, for use amongst all different kinds of
 * transmutation stones
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public interface ITransmutationStone {

    public abstract void openPortableCrafting(EntityPlayer thePlayer);

    public abstract void transmuteBlocks(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit);

}
