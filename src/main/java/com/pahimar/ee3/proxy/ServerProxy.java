package com.pahimar.ee3.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ServerProxy
 *
 * @author pahimar
 */
public class ServerProxy extends CommonProxy
{
    public void registerKeyBindingHandler()
    {
        // NOOP
    }

    public void registerRenderTickHandler()
    {
        // NOOP
    }

    public void registerDrawBlockHighlightHandler()
    {
        // NOOP
    }

    public void registerSoundHandler()
    {
        // NOOP
    }

    public void initRenderingAndTextures()
    {
        // NOOP
    }

    @Override
    public void registerItemTooltipHandler()
    {
        // NOOP
    }

    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit)
    {
        // NOOP
    }

    public void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data)
    {
        // NOOP
    }

    public void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName)
    {
        // NOOP
    }

    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, String itemName, int metaData, int stackSize, int color)
    {
        // NOOP
    }

    public void handleTileCalcinatorPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, byte leftStackSize, byte leftStackMeta, byte rightStackSize, byte rightStackMeta)
    {
        // NOOP
    }

	@Override
	public void setKeyBinding(String name, int value, String Category) {
		// TODO Auto-generated method stub
		
	}
}
