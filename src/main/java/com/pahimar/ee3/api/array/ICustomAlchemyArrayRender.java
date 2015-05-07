package com.pahimar.ee3.api.array;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public interface ICustomAlchemyArrayRender
{
    @SideOnly(Side.CLIENT)
    public abstract void doCustomRendering(TileEntity tileEntity, double x, double y, double z, int arraySize, ForgeDirection orientation, ForgeDirection rotation, float tick);
}
