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

import com.pahimar.ee3.client.model.ModelGlassBell;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileGlassBell;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * TileEntityGlassBellRenderer
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class TileEntityGlassBellRenderer extends TileEntitySpecialRenderer {

    private ModelGlassBell modelGlassBell = new ModelGlassBell();
    private final RenderItem customRenderItem;

    public TileEntityGlassBellRenderer() {

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

        if (tileEntity instanceof TileGlassBell) {
            TileGlassBell tileGlassBell = (TileGlassBell) tileEntity;

            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);

            /**
             * Render the Glass Bell
             */
            GL11.glPushMatrix();

            // Scale, Translate, Rotate
            renderGlassBellByOrientation(x, y, z, tileGlassBell.getOrientation());

            // Bind texture
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_GLASS_BELL);

            modelGlassBell.render();

            GL11.glPopMatrix();

            /**
             * Render the ghost item inside of the Glass Bell, slowly spinning
             */
            GL11.glPushMatrix();

            if (tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX) != null) {

                float scaleFactor = getGhostItemScaleFactor(tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX));
                float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);

                EntityItem ghostEntityItem = new EntityItem(tileGlassBell.worldObj);
                ghostEntityItem.hoverStart = 0.0F;
                ghostEntityItem.setEntityItemStack(tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX));

                translateGhostItemByOrientation(ghostEntityItem.getEntityItem(), x, y, z, tileGlassBell.getOrientation());
                GL11.glScalef(scaleFactor, scaleFactor, scaleFactor);
                GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);

                customRenderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);
            }

            GL11.glPopMatrix();

            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_LIGHTING);
        }
    }

    private void renderGlassBellByOrientation(double x, double y, double z, ForgeDirection forgeDirection) {

        switch (forgeDirection) {
            case DOWN: {
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glTranslatef((float) x + 0.0F, (float) y + 2.0F, (float) z + 0.0F);
                GL11.glRotatef(90F, 1F, 0F, 0F);
                return;
            }
            case UP: {
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glTranslatef((float) x + 0.0F, (float) y + -1.0F, (float) z + 1.0F);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                return;
            }
            case NORTH: {
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glTranslatef((float) x + 1.0F, (float) y + 0.0F, (float) z + 2.0F);
                GL11.glRotatef(180F, 0F, 1F, 0F);
                return;
            }
            case SOUTH: {
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glTranslatef((float) x + 0.0F, (float) y + 0.0F, (float) z + -1.0F);
                return;
            }
            case EAST: {
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glTranslatef((float) x + -1.0F, (float) y + 1.0F, (float) z + 1.0F);
                GL11.glRotatef(-90F, 0F, 0F, 1F);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                return;
            }
            case WEST: {
                GL11.glScalef(1.0F, 1.0F, 1.0F);
                GL11.glTranslatef((float) x + 2.0F, (float) y + 0.0F, (float) z + 1.0F);
                GL11.glRotatef(90F, 0F, 0F, 1F);
                GL11.glRotatef(-90F, 1F, 0F, 0F);
                return;
            }
            case UNKNOWN: {
                return;
            }
            default: {
                return;
            }
        }
    }

    private void translateGhostItemByOrientation(ItemStack ghostItemStack, double x, double y, double z, ForgeDirection forgeDirection) {

        if (ghostItemStack != null) {
            if (ghostItemStack.getItem() instanceof ItemBlock) {
                switch (forgeDirection) {
                    case DOWN: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.7F, (float) z + 0.5F);
                        return;
                    }
                    case UP: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.25F, (float) z + 0.5F);
                        return;
                    }
                    case NORTH: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.7F);
                        return;
                    }
                    case SOUTH: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.3F);
                        return;
                    }
                    case EAST: {
                        GL11.glTranslatef((float) x + 0.3F, (float) y + 0.5F, (float) z + 0.5F);
                        return;
                    }
                    case WEST: {
                        GL11.glTranslatef((float) x + 0.70F, (float) y + 0.5F, (float) z + 0.5F);
                        return;
                    }
                    case UNKNOWN: {
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            else {
                switch (forgeDirection) {
                    case DOWN: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.6F, (float) z + 0.5F);
                        return;
                    }
                    case UP: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.20F, (float) z + 0.5F);
                        return;
                    }
                    case NORTH: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.4F, (float) z + 0.7F);
                        return;
                    }
                    case SOUTH: {
                        GL11.glTranslatef((float) x + 0.5F, (float) y + 0.4F, (float) z + 0.3F);
                        return;
                    }
                    case EAST: {
                        GL11.glTranslatef((float) x + 0.3F, (float) y + 0.4F, (float) z + 0.5F);
                        return;
                    }
                    case WEST: {
                        GL11.glTranslatef((float) x + 0.70F, (float) y + 0.4F, (float) z + 0.5F);
                        return;
                    }
                    case UNKNOWN: {
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
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
