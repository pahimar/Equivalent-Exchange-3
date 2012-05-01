package net.minecraft.src.ee3.core;

public class PStoneRecipes {

	public void init() {
		// X dirt == X cobblestone == X stone == X sand
		// X gravel == X sandstone
		// X rose == X dandelion == X indigo flower (RP2)
		// X sapling == X birch sapling == X pine sapling == X jungle sapling
		// X wood == X birch wood == X pine wood == X jungle wood
		// X wood plank == X birch wood plank == X pine wood plank == X jungle wood plank
		// X wool == X wool (next meta colour)
		/* Cobblestone to diamond recipe path */
		// X cobblestone == X/4 gravel
		// X gravel == X/8 wood
		// X wood == X/2 obsidian
		// X obsidian == X/4 iron ingot
		// X iron ingot == X/8 gold ingot
		// X gold ingot == X/4 diamond
		/* Therefore, 8192 cobblestone == 2048 gravel == 256 wood == 128 obsidian == 32 iron ingot == 4 gold ingot == 1 diamond */
	}
}
