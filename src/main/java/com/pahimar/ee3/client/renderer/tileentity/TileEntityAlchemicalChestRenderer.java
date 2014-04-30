package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAlchemicalChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityAlchemicalChestRenderer extends TileEntitySpecialRenderer
{
    private final ModelChest modelChest = new ModelChest();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityAlchemicalChest)
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) tileEntity;
            ForgeDirection direction = null;

            if (tileEntityAlchemicalChest.getWorldObj() != null)
            {
                direction = tileEntityAlchemicalChest.getOrientation();
            }

            if (tileEntityAlchemicalChest.getState() == 0)
            {
                this.bindTexture(Textures.MODEL_ALCHEMICAL_CHEST_SMALL);
            }
            else if (tileEntityAlchemicalChest.getState() == 1)
            {
                this.bindTexture(Textures.MODEL_ALCHEMICAL_CHEST_MEDIUM);
            }
            else if (tileEntityAlchemicalChest.getState() == 2)
            {
                this.bindTexture(Textures.MODEL_ALCHEMICAL_CHEST_LARGE);
            }

            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x, (float) y + 1.0F, (float) z + 1.0F);
            GL11.glScalef(1.0F, -1.0F, -1.0F);
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            short angle = 0;

            if (direction != null)
            {
                if (direction == ForgeDirection.NORTH)
                {
                    angle = 180;
                }
                else if (direction == ForgeDirection.SOUTH)
                {
                    angle = 0;
                }
                else if (direction == ForgeDirection.WEST)
                {
                    angle = 90;
                }
                else if (direction == ForgeDirection.EAST)
                {
                    angle = -90;
                }
            }

            GL11.glRotatef(angle, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            float adjustedLidAngle = tileEntityAlchemicalChest.prevLidAngle + (tileEntityAlchemicalChest.lidAngle - tileEntityAlchemicalChest.prevLidAngle) * tick;
            adjustedLidAngle = 1.0F - adjustedLidAngle;
            adjustedLidAngle = 1.0F - adjustedLidAngle * adjustedLidAngle * adjustedLidAngle;
            modelChest.chestLid.rotateAngleX = -(adjustedLidAngle * (float) Math.PI / 2.0F);
            modelChest.renderAll();
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
