package ee3.block;

import java.util.ArrayList;

import ee3.lib.Reference;

import net.minecraft.src.BlockLog;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.ItemStack;

public class BlockCorruptedLog extends BlockLog {
	
	protected BlockCorruptedLog(int id) {
		super(id);
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

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this, 1, 0));
		itemList.add(new ItemStack(this, 1, 1));
		itemList.add(new ItemStack(this, 1, 2));
		itemList.add(new ItemStack(this, 1, 3));
    }
	
}
