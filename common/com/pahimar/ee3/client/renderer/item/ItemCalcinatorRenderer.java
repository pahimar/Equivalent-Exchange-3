package com.pahimar.ee3.client.renderer.item;

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

    private ModelCalcinator modelCalcinator;

    public ItemCalcinatorRenderer() {

        modelCalcinator = new ModelCalcinator();
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
                renderCalcinator(-0.5F, 0.0F, 0.5F, 1.0F);
                return;
            }
            case EQUIPPED: {
                renderCalcinator(0.0F, 0.0F, 1.0F, 1.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON: {
                renderCalcinator(0.0F, 0.0F, 1.0F, 1.0F);
                return;
            }
            case INVENTORY: {
                renderCalcinator(0.0F, -0.1F, 1.0F, 1.0F);
                return;
            }
            default:
                return;
        }
    }

    private void renderCalcinator(float x, float y, float z, float scale) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        // Scale, Translate, Rotate
        GL11.glScalef(scale, scale, scale);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(-90F, 1F, 0, 0);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.func_110577_a(Textures.MODEL_CALCINATOR);

        // Render
        modelCalcinator.render();
        this.renderChain();
        

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    private void renderChain(){
    	GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_CULL_FACE);
		GL11.glBegin(GL11.GL_TRIANGLES);{
			//face1 quad1
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.347500, 0.613300, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.387700, 0.653500, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.356900, 0.685500, 0.397000);
			//face2 quad1
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.356900, 0.685500, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.316600, 0.645300, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.347500, 0.613300, 0.294500);
			//face3 quad2
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.613300, 0.652500, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.653500, 0.612300, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.685500, 0.643100, 0.397000);
			//face4 quad2
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.685500, 0.643100, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.645300, 0.683400, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.613300, 0.652500, 0.294500);
			//face5 quad3
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.386700, 0.347500, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.346500, 0.387700, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.314500, 0.356900, 0.397000);
			//face6 quad3
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.314500, 0.356900, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.354700, 0.316600, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.386700, 0.347500, 0.294500);
			//face7 quad4
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.652500, 0.386700, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.125000);
			GL11.glVertex3d(0.612300, 0.346500, 0.294500);
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.643100, 0.314500, 0.397000);
			//face8 quad4
			GL11.glTexCoord2d(0.468800, 1-0.296900);
			GL11.glVertex3d(0.643100, 0.314500, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.296900);
			GL11.glVertex3d(0.683400, 0.354700, 0.397000);
			GL11.glTexCoord2d(0.390600, 1-0.125000);
			GL11.glVertex3d(0.652500, 0.386700, 0.294500);
		}
		GL11.glEnd();
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glPopMatrix();
    }
}
