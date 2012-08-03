package ee3.block;

import java.util.ArrayList;
import java.util.Random;

import ee3.lib.Reference;
import net.minecraft.src.Block;
import net.minecraft.src.BlockFlowing;
import net.minecraft.src.BlockFluid;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.World;

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

	@Override
	public void addCreativeItems(ArrayList itemList) {
		itemList.add(new ItemStack(this));
    }
}
