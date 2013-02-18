package com.pahimar.ee3.client.renderer;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelAludel;
import com.pahimar.ee3.lib.Sprites;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;


public class ItemAludelRenderer implements IItemRenderer {
    
    private ModelAludel aludelModel;
    
    public ItemAludelRenderer() {
        
        aludelModel = new ModelAludel(1 / 16F);
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
                renderAludel(-0.5F, 0F, -0.5F);
                break;
            }
            case EQUIPPED: {
                renderAludel(0F, 0.4F, 0F);
                break;
            }
            case INVENTORY: {
                renderAludel(1F, 0.65F, 1F);
                break;
            }
            default:
                break;
        }
    }
    
    private void renderAludel(float x, float y, float z) {

        Tessellator tesselator = Tessellator.instance;
        ForgeHooksClient.bindTexture(Sprites.MODEL_ALUDEL, 0);
        GL11.glPushMatrix(); //start
        GL11.glTranslatef(x, y, z); //size
        aludelModel.render(0.0625F);
        GL11.glPopMatrix(); //end
    }
}
