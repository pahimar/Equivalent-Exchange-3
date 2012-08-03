package ee3.block;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.forge.ITextureProvider;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class BlockEE extends BlockContainer {

	protected BlockEE(int i) {
		super(i, Material.rock);
	}
	
	@Override
	public TileEntity getBlockEntity() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getBlockName() {
		return "Test";
	}
}
