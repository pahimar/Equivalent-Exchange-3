package com.pahimar.ee3.client.renderer.tileentity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.common.ForgeDirection;

import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TileEntityAlchemicalChestRenderer extends TileEntitySpecialRenderer {

    private ModelChest modelChest = new ModelChest();

    public void renderAlchemicalChest(TileAlchemicalChest tileAlchemicalChest, double x, double y, double z, float tick) {

        ForgeDirection direction = null; 

        if (tileAlchemicalChest.getWorldObj() != null)
        {
            direction = ForgeDirection.getOrientation(tileAlchemicalChest.getBlockMetadata());
        }

        ForgeHooksClient.bindTexture(Sprites.MODEL_ALCHEMICAL_CHEST, 0);
        GL11.glPushMatrix();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTranslatef((float)x, (float)y + 1.0F, (float)z + 1.0F);
        GL11.glScalef(1.0F, -1.0F, -1.0F);
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        short angle = 0;

        if (direction != null) {
            if (direction == ForgeDirection.NORTH) {
                angle = 180;
            }
            else if (direction == ForgeDirection.SOUTH) {
                angle = 0;
            }
            else if (direction == ForgeDirection.WEST) {
                angle = 90;
            }
            else if (direction == ForgeDirection.EAST) {
                angle = -90;
            }
        }

        GL11.glRotatef((float)angle, 0.0F, 1.0F, 0.0F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        float adjustedLidAngle = tileAlchemicalChest.prevLidAngle + (tileAlchemicalChest.lidAngle - tileAlchemicalChest.prevLidAngle) * tick;
        adjustedLidAngle = 1.0F - adjustedLidAngle;
        adjustedLidAngle = 1.0F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;
        this.modelChest.chestLid.rotateAngleX = -(adjustedLidAngle * (float)Math.PI / 2.0F);
        this.modelChest.renderAll();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

        renderAlchemicalChest((TileAlchemicalChest) tileEntity, x, y, z, tick);
    }
}
