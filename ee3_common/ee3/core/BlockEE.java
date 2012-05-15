package ee3.core;

import net.minecraft.src.BlockContainer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.forge.ITextureProvider;

public class BlockEE extends BlockContainer implements ITextureProvider {

	protected BlockEE(int i) {
		super(i, Material.rock);
	}

	@Override
	public String getTextureFile() {
		// TODO
		return "";
	}

	@Override
	public TileEntity getBlockEntity() {
		return null;
	}
	
	@Override 
	public TileEntity getBlockEntity(int metadata) {
		// TODO
		return null;
	}
}
