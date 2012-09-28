package ee3.common.block;

import ee3.common.EquivalentExchange3;
import ee3.common.lib.GuiIds;
import ee3.common.lib.RenderIds;
import ee3.common.tile.TileCalcinator;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
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
		setCreativeTab(CreativeTabs.tabDecorations);
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
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

	    if (!world.isRemote) {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);
    
            if (tileCalcinator != null) {
                player.openGui(EquivalentExchange3.instance, GuiIds.CALCINATOR, world, x, y, z);
            }
	    }

        return true;
	    
    }

}
