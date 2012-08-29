package ee3.common.block;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * BlockEE
 * 
 * Parent block class for Equivalent Exchange blocks
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public abstract class BlockEE extends BlockContainer {
	
	public BlockEE(int id, Material material) {
		super(id, material);
	}

}
