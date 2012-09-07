package ee3.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.lib.BlockIds;
import net.minecraft.src.Block;

public class ModBlocks {
    
    /* Block name constants */
    public static final String CALCINATOR_NAME = "calcinator";
	
    /* Mod block instances */
	public static Block calcinator;

	public static void init() {

		calcinator = new BlockCalcinator(BlockIds.CALCINATOR).setBlockName(CALCINATOR_NAME);
		
		LanguageRegistry.addName(calcinator, "Calcinator");
		
		GameRegistry.registerBlock(calcinator);
		
	}

}
