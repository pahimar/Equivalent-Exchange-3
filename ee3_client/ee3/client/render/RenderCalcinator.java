package ee3.client.render;

import org.lwjgl.opengl.GL11;

import ee3.common.tile.TileCalcinator;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

/**
 * RenderCalcinator
 * 
 * Renders the Calcinator in game as a TESR (Tile Entity Special Render)
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RenderCalcinator extends TileEntitySpecialRenderer {

    static final float scale = (float) (1.0 / 16.0);
    
	private ModelCalcinator modelCalcinator = new ModelCalcinator(scale);
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
	    modelCalcinator.render((TileCalcinator)tileEntity, x, y, z);
	}

}
