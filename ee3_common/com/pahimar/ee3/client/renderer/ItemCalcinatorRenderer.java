package com.pahimar.ee3.client.renderer;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.client.model.ModelCalcinator;
import com.pahimar.ee3.lib.Sprites;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * RenderItemCalcinator
 * 
 * Renders the Calcinator in game as an item (in hand, on the ground, and in
 * inventory)
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class ItemCalcinatorRenderer implements IItemRenderer {

    private ModelCalcinator calcinatorModel;

    public ItemCalcinatorRenderer() {

        calcinatorModel = new ModelCalcinator(1 / 16F);
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
                renderCalcinator(-0.5F, 0F, -0.5F);
                break;
            }
            case EQUIPPED: {
                renderCalcinator(0F, 0.4F, 0F);
                break;
            }
            case INVENTORY: {
                renderCalcinator(1F, 0.65F, 1F);
                break;
            }
            default:
                break;
        }

    }

    private void renderCalcinator(float x, float y, float z) {

        Tessellator tesselator = Tessellator.instance;
        ForgeHooksClient.bindTexture(Sprites.MODEL_CALCINATOR, 0);
        GL11.glPushMatrix(); //start
        GL11.glTranslatef(x, y, z); //size
        calcinatorModel.render(0.0625F);
        GL11.glPopMatrix(); //end
    }

}
