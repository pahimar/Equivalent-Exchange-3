package com.pahimar.ee3.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public interface IProxy
{
    public abstract void registerKeyBindingHandler();

    public abstract void registerRenderTickHandler();

    public abstract void registerDrawBlockHighlightHandler();

    public abstract void setKeyBinding(String name, int value);

    public abstract void registerSoundHandler();

    public abstract void initRenderingAndTextures();

    public abstract void registerTileEntities();

    public abstract void registerItemTooltipHandler();

    public abstract void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data);

    public abstract void handleTileEntityPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName);

    public abstract void handleTileWithItemPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, int itemID, int metaData, int stackSize, int color);

    public abstract void handleTileCalcinatorPacket(int x, int y, int z, ForgeDirection orientation, byte state, String customName, byte leftStackSize, byte leftStackMeta, byte rightStackSize, byte rightStackMeta);

    public abstract void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit);
}
