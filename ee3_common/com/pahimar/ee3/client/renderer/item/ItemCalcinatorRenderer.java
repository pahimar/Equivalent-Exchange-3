package com.pahimar.ee3.client.renderer.item;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelCalcinator;
import com.pahimar.ee3.lib.Textures;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * ItemCalcinatorRenderer
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class ItemCalcinatorRenderer implements IItemRenderer {

    private ModelCalcinator calcinatorModel;

    public ItemCalcinatorRenderer() {

        calcinatorModel = new ModelCalcinator();
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

        float scale;
        switch (type) {
            case ENTITY: {
                scale = 1.0F;
                renderCalcinator(-0.5F * scale, 0.0F * scale, 0.5F * scale, scale);
                break;
            }
            case EQUIPPED: {
                scale = 1.0F;
                renderCalcinator(0.0F * scale, 0.0F * scale, 1.0F * scale, scale);
                break;
            }
            case INVENTORY: {
                scale = 1.0F;
                renderCalcinator(0.0F * scale, -0.1F * scale, 1.0F * scale, scale);
                break;
            }
            default:
                break;
        }

    }

    private void renderCalcinator(float x, float y, float z, float scale) {

        FMLClientHandler.instance().getClient().renderEngine.func_98187_b(Textures.MODEL_CALCINATOR);
        GL11.glPushMatrix(); //start
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glTranslatef(x, y, z); //size
        GL11.glRotatef(-90F, 1F, 0, 0);
        calcinatorModel.render(Tessellator.instance, scale);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix(); //end
    }
}
