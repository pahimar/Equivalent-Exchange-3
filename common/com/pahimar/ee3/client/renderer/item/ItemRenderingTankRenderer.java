package com.pahimar.ee3.client.renderer.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelRenderingTank;
import com.pahimar.ee3.lib.Textures;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemRenderingTankRenderer implements IItemRenderer {

    private ModelRenderingTank modelRenderingTank;

    public ItemRenderingTankRenderer() {

        modelRenderingTank = new ModelRenderingTank();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {

        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {

        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {

        switch (type) {
            case ENTITY: {
                renderRenderingTank(-0.5F, -1.2F, 0.5F, 0.75F);
                return;
            }
            case EQUIPPED: {
                renderRenderingTank(-0.2F, -0.85F, 0.8F, 0.75F);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
                renderRenderingTank(-0.2F, -0.50F, 0.8F, 0.75F);
                return;
            }
            case INVENTORY: {
                renderRenderingTank(-1.0F, -2.05F, 0.0F, 0.5F);
                return;
            }
            default:
                return;
        }
    }

    private void renderRenderingTank(float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        // Scale, Translate, Rotate
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_RENDERING_TANK);

        // Render
        modelRenderingTank.render();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

}
