package ee3.common.block;

import ee3.common.lib.RenderIds;
import ee3.common.tile.TileCalcinator;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

/**
 * BlockCalcinator
 * 
 * Block class for the Calcinator
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockCalcinator extends BlockEE {

	public BlockCalcinator(int id) {
		super(id, Material.rock);
		setHardness(5F);
		setCreativeTab(CreativeTabs.tabDeco);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1) {
		return new TileCalcinator();
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.calcinatorRenderId;
	}
	
	@Override
	public int getBlockTextureFromSide(int par1) {
		return 1;
	}

}
