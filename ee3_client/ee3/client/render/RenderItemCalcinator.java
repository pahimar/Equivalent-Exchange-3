package ee3.client.render;

import org.lwjgl.opengl.GL11;

import ee3.common.lib.Reference;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Tessellator;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

/**
 * RenderItemCalcinator
 * 
 * Renders the Calcinator in game as an item (in hand, on the ground, and in inventory)
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RenderItemCalcinator implements IItemRenderer {

	private ModelCalcinator calcinatorModel;
	
	public RenderItemCalcinator() {
		calcinatorModel = new ModelCalcinator(1/16F);
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
			case ENTITY: renderCalcinator(-0.5F, 0F, -0.5F); break;
			case EQUIPPED: renderCalcinator(0F, 0.4F, 0F); break;
			case INVENTORY: renderCalcinator(1F, 0.65F, 1F); break;
			default: break;
		}
		
	}
	
	private void renderCalcinator(float x, float y, float z)  {
		Tessellator tesselator = Tessellator.instance;
		ForgeHooksClient.bindTexture(Reference.SPRITE_SHEET_LOCATION + "calcinator.png", 0);
		GL11.glPushMatrix(); //start
			GL11.glTranslatef(x, y, z); //size
	        calcinatorModel.render(0.0625F);
	    GL11.glPopMatrix(); //end
	}

}
