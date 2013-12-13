package com.pahimar.ee3.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Equivalent-Exchange-3
 * 
 * ITransmutationStone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public interface ITransmutationStone {

    public abstract void openPortableCraftingGUI(EntityPlayer thePlayer, ItemStack itemStack);

    public abstract void openPortableTransmutationGUI(EntityPlayer thePlayer, ItemStack itemStack);

    public abstract void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit);

}
