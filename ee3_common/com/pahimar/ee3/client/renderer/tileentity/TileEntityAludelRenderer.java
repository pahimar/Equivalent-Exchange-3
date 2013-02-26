package com.pahimar.ee3.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.pahimar.ee3.client.model.ModelAludel;
import com.pahimar.ee3.tileentity.TileAludel;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityAludelRenderer extends TileEntitySpecialRenderer {

    static final float scale = (float) (1.0 / 16.0);

    private ModelAludel modelAludel = new ModelAludel(scale);

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

        modelAludel.render((TileAludel) tileEntity, x, y, z);
    }

}
