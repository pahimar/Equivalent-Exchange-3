package ee3.block;

import ee3.lib.Reference;
import net.minecraft.src.BlockSapling;
import net.minecraft.src.IBlockAccess;

public class BlockCorruptedSapling extends BlockSapling {

	protected BlockCorruptedSapling(int par1) {
		super(par1, 15);
	}

	public int getBlockColor() {
        return Integer.parseInt(Reference.hexRedWaterCorruptionOverlayColor, 16);
    }

    /**
     * Returns the color this block should be rendered. Used by leaves.
     */
    public int getRenderColor(int par1) {
		return Integer.parseInt(Reference.hexRedWaterCorruptionOverlayColor, 16);
    }

    /**
     * Returns a integer with hex for 0xrrggbb with this color multiplied against the blocks color. Note only called
     * when first determining what to render.
     */
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
		return Integer.parseInt(Reference.hexRedWaterCorruptionOverlayColor, 16);
    }
	
}
