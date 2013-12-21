package com.pahimar.ee3.proxy;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;
import com.pahimar.ee3.tileentity.TileGlassBell;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ServerProxy
 *
 * @author pahimar
 */
public class ServerProxy implements IProxy
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

    public void setKeyBinding(String name, int value)
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

    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileCalcinator.class, Strings.TE_CALCINATOR_NAME);
        GameRegistry.registerTileEntity(TileAludel.class, Strings.TE_ALUDEL_NAME);
        GameRegistry.registerTileEntity(TileAlchemicalChest.class, Strings.TE_ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerTileEntity(TileGlassBell.class, Strings.TE_GLASS_BELL_NAME);
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

    public void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize, int color)
    {
        // NOOP
    }
}
