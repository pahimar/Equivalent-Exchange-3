package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.client.renderer.model.ModelAludel;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAludel;
import com.pahimar.ee3.tileentity.TileEntityGlassBell;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityAludelRenderer extends TileEntitySpecialRenderer
{
    private final ModelAludel modelAludel = new ModelAludel();
    private final RenderItem customRenderItem;

    public TileEntityAludelRenderer()
    {
        customRenderItem = new RenderItem()
        {
            @Override
            public boolean shouldBob()
            {
                return false;
            }
        };

        customRenderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityAludel)
        {
            TileEntityAludel tileEntityAludel = (TileEntityAludel) tileEntity;

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            // Scale, Translate, Rotate
            scaleTranslateRotate(x, y, z, tileEntityAludel.getOrientation());

            // Bind texture
            this.bindTexture(Textures.MODEL_ALUDEL);

            // Render
            modelAludel.render();

            GL11.glPopMatrix();

            /**
             * Render the ghost item inside of the Aludel, slowly spinning
             */
            GL11.glPushMatrix();

            TileEntity tileGlassBell = tileEntityAludel.getWorldObj().getTileEntity(tileEntityAludel.xCoord, tileEntityAludel.yCoord + 1, tileEntityAludel.zCoord);

            if (tileGlassBell instanceof TileEntityGlassBell)
            {
                if (tileEntityAludel.outputItemStack != null)
                {
                    float scaleFactor = getGhostItemScaleFactor(tileEntityAludel.outputItemStack);
                    float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

                    EntityItem ghostEntityItem = new EntityItem(tileEntityAludel.getWorldObj());
                    ghostEntityItem.hoverStart = 0.0F;
                    ghostEntityItem.setEntityItemStack(tileEntityAludel.outputItemStack);

                    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.25F, (float) z + 0.5F);
                    GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
                    GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

                    customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
                }
            }

            GL11.glPopMatrix();

            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

    private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation)
    {
        if (orientation == ForgeDirection.NORTH)
        {
            GL11.glTranslated(x + 1, y, z);
            GL11.glRotatef(180F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.EAST)
        {
            GL11.glTranslated(x + 1, y, z + 1);
            GL11.glRotatef(90F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.SOUTH)
        {
            GL11.glTranslated(x, y, z + 1);
            GL11.glRotatef(0F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.WEST)
        {
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(-90F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
    }

    private float getGhostItemScaleFactor(ItemStack itemStack)
    {
        float scaleFactor = 1.0F;

        if (itemStack != null)
        {
            byte numBlocks = 1;
            if (itemStack.stackSize > 1)
            {
                numBlocks = 2;
            }
            else if (itemStack.stackSize > 5)
            {
                numBlocks = 3;
            }
            else if (itemStack.stackSize > 20)
            {
                numBlocks = 4;
            }
            else if (itemStack.stackSize > 40)
            {
                numBlocks = 5;
            }

            if (itemStack.getItem() instanceof ItemBlock)
            {
                switch (numBlocks)
                {
                    case 1:
                        return 0.90F;
                    case 2:
                        return 0.90F;
                    case 3:
                        return 0.90F;
                    case 4:
                        return 0.90F;
                    case 5:
                        return 0.80F;
                    default:
                        return 0.90F;
                }
            }
            else
            {
                switch (numBlocks)
                {
                    case 1:
                        return 0.65F;
                    case 2:
                        return 0.65F;
                    case 3:
                        return 0.65F;
                    case 4:
                        return 0.65F;
                    default:
                        return 0.65F;
                }
            }
        }

        return scaleFactor;
    }
}
