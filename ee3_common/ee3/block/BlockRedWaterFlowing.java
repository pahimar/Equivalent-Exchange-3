package ee3.block;

import ee3.lib.Reference;
import net.minecraft.src.BlockFlowing;
import net.minecraft.src.BlockFluid;
import net.minecraft.src.Material;

public class BlockRedWaterFlowing extends BlockFlowing {
	
	protected BlockRedWaterFlowing(int id, Material material) {
		super(id, material);
		this.blockHardness = 100F;
		this.setLightOpacity(3);
		this.setBlockName("redWaterFlowing");
	}

	@Override
	public String getTextureFile() {
		return Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET;
	}
	
}
