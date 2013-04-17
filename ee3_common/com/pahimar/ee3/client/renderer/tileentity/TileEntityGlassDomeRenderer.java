package com.pahimar.ee3.client.renderer.tileentity;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelGlassDome;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileGlassDome;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * TileEntityCalcinatorRenderer
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class TileEntityGlassDomeRenderer extends TileEntitySpecialRenderer {

    private ModelGlassDome modelGlassDome = new ModelGlassDome();
    private final RenderItem customRenderItem;
    private EntityItem ghostEntityItem;
    
    public TileEntityGlassDomeRenderer() {
        
        ghostEntityItem = null;
        
        customRenderItem = new RenderItem() {
            public boolean shouldBob() {
                return false;
            };
        };
        
        customRenderItem.setRenderManager(RenderManager.instance);
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {

        if (tileEntity instanceof TileGlassDome) {
            TileGlassDome tileGlassDome = (TileGlassDome) tileEntity;
            
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_CULL_FACE);
            
            GL11.glPushMatrix();

            // Scale, Translate, Rotate
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x + 0.5F, (float) y + (-1.0F), (float) z + 1.2F);
            GL11.glRotatef(45F, 0F, 1F, 0F);
            GL11.glRotatef(-90F, 1F, 0F, 0F);

            // Bind texture
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_ALUDEL);

            // Render
            modelGlassDome.render();
            
            GL11.glPopMatrix();
            
            GL11.glPushMatrix();
            for (int i = 0; i < tileGlassDome.getSizeInventory(); i++) {
                
                GL11.glTranslatef((float) x + 0.5F, (float) y + 0.1F, (float) z + 0.5F);
                GL11.glScalef(0.5F, 0.5F, 0.5F);
                
                if (ghostEntityItem == null) {
                    ghostEntityItem = new EntityItem(tileGlassDome.worldObj, tileGlassDome.xCoord, tileGlassDome.yCoord, tileGlassDome.zCoord);
                }
                
                if (tileGlassDome.getStackInSlot(i) != null) {
                    
                    ghostEntityItem.setEntityItemStack(tileGlassDome.getStackInSlot(i));
                    
                    if (ghostEntityItem.getEntityItem() != null) {
                        ghostEntityItem.onUpdate();
                        customRenderItem.doRenderItem(ghostEntityItem, 0, 0, 0, 0, 0);
                    }
                }
                
            }
            GL11.glPopMatrix();

            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glEnable(GL11.GL_LIGHTING);
            
        }
    }
}
