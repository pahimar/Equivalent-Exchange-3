package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.client.renderer.model.ModelTransmutationTablet;
import com.pahimar.ee3.client.util.RenderUtils;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class TileEntityRendererTransmutationTablet extends TileEntitySpecialRenderer
{
    private final ModelTransmutationTablet modelTransmutationTablet = new ModelTransmutationTablet();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityTransmutationTablet)
        {
            TileEntityTransmutationTablet tileEntityTransmutationTablet = (TileEntityTransmutationTablet) tileEntity;

            this.bindTexture(Textures.Model.TRANSMUTATION_TABLET);
            GL11.glPushMatrix();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glTranslatef((float) x, (float) y, (float) z);
            GL11.glTranslatef(0.5F, -0.375F, 0.5F);
            modelTransmutationTablet.renderBase(0.0625f);
            if (tileEntityTransmutationTablet.isStructureValid())
            {
                modelTransmutationTablet.renderTransmutationPad(0.0625f);
            }
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            GL11.glPopMatrix();

            if (tileEntityTransmutationTablet.isStructureValid())
            {
                int rotationAngle = 0;
                if (tileEntityTransmutationTablet.getRotation() == ForgeDirection.NORTH)
                {
                    rotationAngle = 0;
                }
                else if (tileEntityTransmutationTablet.getRotation() == ForgeDirection.EAST)
                {
                    rotationAngle = -90;
                }
                else if (tileEntityTransmutationTablet.getRotation() == ForgeDirection.SOUTH)
                {
                    rotationAngle = 180;
                }
                else if (tileEntityTransmutationTablet.getRotation() == ForgeDirection.WEST)
                {
                    rotationAngle = 90;
                }

                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glPushMatrix();
                GL11.glDepthMask(false);
                GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
                GL11.glPushMatrix();
                GL11.glTranslated(x + 0.5d, y + 0.63d, z + 0.5d);
                GL11.glScalef(2f, 2f, 2f);
                GL11.glRotatef(rotationAngle, tileEntityTransmutationTablet.getOrientation().offsetX, tileEntityTransmutationTablet.getOrientation().offsetY, tileEntityTransmutationTablet.getOrientation().offsetZ);
                GL11.glRotatef(90, -1, 0, 0);
                RenderUtils.renderQuad(Textures.AlchemyArray.TRANSMUTATION_ALCHEMY_ARRAY);
                GL11.glPopMatrix();
                GL11.glDepthMask(true);
                GL11.glPopMatrix();
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
    }
}
