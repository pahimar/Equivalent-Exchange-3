package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.client.renderer.model.ModelResearchStation;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityRendererResearchStation extends TileEntitySpecialRenderer
{
    private final ModelResearchStation modelResearchStation = new ModelResearchStation();
    private final RenderItem customRenderItem;
    
    public TileEntityRendererResearchStation()
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
        if (tileEntity instanceof TileEntityResearchStation)
        {
            TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) tileEntity;

            /**
             * Render the Research Station
             */
            GL11.glPushMatrix();

            // Scale, Translate, Rotate
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glTranslatef((float) x + 0.0F, (float) y + 0.0F, (float) z + 1.0F);

            // Bind texture
            this.bindTexture(Textures.Model.RESEARCH_STATION);

            // Render
            modelResearchStation.render();

            GL11.glPopMatrix();
            
            /**
             * Render the Tome of Alchemical Knowledge
             */
            GL11.glPushMatrix();

            ItemStack alchenomicon = tileEntityResearchStation.getStackInSlot(TileEntityResearchStation.ALCHENOMICON_SLOT_INVENTORY_INDEX);
            if (Minecraft.getMinecraft().gameSettings.fancyGraphics && alchenomicon != null)
            {
                EntityItem ghostEntityItem = new EntityItem(tileEntityResearchStation.getWorldObj());
                ghostEntityItem.hoverStart = 0.0F;
                ghostEntityItem.setEntityItemStack(alchenomicon);
                
                GL11.glTranslated(x + 0.6F, y + 1.015625F, z + 0.35F);
                GL11.glRotatef(90F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45F, 0.0F, 0.0F, 1.0F);

                customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
            }

            GL11.glPopMatrix();
        }
    }
}
