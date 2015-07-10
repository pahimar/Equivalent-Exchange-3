package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.util.LogHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntitySpecialRendererBase extends TileEntitySpecialRenderer
{
    private float lastTickValue = 0f;
    private float totalAdjustedTicks = 0f;

    private float getDeltaTick(float tick)
    {
        float adjTick = tick + (tick < this.lastTickValue ? 1 : 0);
        float delta = adjTick - this.lastTickValue;
        this.lastTickValue = tick;

        return delta;
    }

    private float beginFrame(float dTick)
    {
        float delta = this.getDeltaTick(dTick);
        this.totalAdjustedTicks += delta;

        return this.totalAdjustedTicks;
    }

    @Override
    public final void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float dTick)
    {
        float ticks = this.beginFrame(dTick);
        this.renderTileEntryAt(tileEntity, x, y, z, dTick, ticks);
    }

    protected void renderTileEntryAt(TileEntity tileEntity, double x, double y, double z, float dTick, float totalAdjustedTicks)
    {

    }
}
