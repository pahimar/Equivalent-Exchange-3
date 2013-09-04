package com.pahimar.ee3.client.renderer.tileentity;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelAludel;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileGlassBell;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * TileEntityAludelRenderer
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class TileEntityAludelRenderer extends TileEntitySpecialRenderer {

    private ModelAludel modelAludel = new ModelAludel();
    private final RenderItem customRenderItem;

    public TileEntityAludelRenderer() {

        customRenderItem = new RenderItem() {

            @Override
            public boolean shouldBob() {

                return false;
            };
        };

        customRenderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

        if (tileEntity instanceof TileAludel) {

            TileAludel tileAludel = (TileAludel) tileEntity;

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            // Scale, Translate, Rotate
            scaleTranslateRotate(x, y, z, tileAludel.getOrientation());

            // Bind texture
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ALUDEL);

            // Render
            modelAludel.render();

            GL11.glPopMatrix();

            /**
             * Render the ghost item inside of the Aludel, slowly spinning
             */
            GL11.glPushMatrix();

            TileEntity tileGlassBell = tileAludel.worldObj.getBlockTileEntity(tileAludel.xCoord, tileAludel.yCoord + 1, tileAludel.zCoord);

            if (tileGlassBell instanceof TileGlassBell) {
                if (tileAludel.getStackInSlot(TileAludel.INPUT_INVENTORY_INDEX) != null) {

                    float scaleFactor = getGhostItemScaleFactor(tileAludel.getStackInSlot(TileAludel.INPUT_INVENTORY_INDEX));
                    float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

                    EntityItem ghostEntityItem = new EntityItem(tileAludel.worldObj);
                    ghostEntityItem.hoverStart = 0.0F;
                    ghostEntityItem.setEntityItemStack(tileAludel.getStackInSlot(TileAludel.INPUT_INVENTORY_INDEX));

                    GL11.glTranslatef((float) x + 0.5F, (float) y + 1.25F, (float) z + 0.5F);
                    GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
                    GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

                    customRenderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);
                }
            }

            GL11.glPopMatrix();

            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

    private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation) {

        if (orientation == ForgeDirection.NORTH) {
            GL11.glTranslated(x + 1, y, z);
            GL11.glRotatef(180F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.EAST) {
            GL11.glTranslated(x + 1, y, z + 1);
            GL11.glRotatef(90F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.SOUTH) {
            GL11.glTranslated(x, y, z + 1);
            GL11.glRotatef(0F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
        else if (orientation == ForgeDirection.WEST) {
            GL11.glTranslated(x, y, z);
            GL11.glRotatef(-90F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);
        }
    }

    private float getGhostItemScaleFactor(ItemStack itemStack) {

        float scaleFactor = 1.0F;

        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemBlock) {
                switch (customRenderItem.getMiniBlockCount(itemStack)) {
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
            else {
                switch (customRenderItem.getMiniItemCount(itemStack)) {
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
