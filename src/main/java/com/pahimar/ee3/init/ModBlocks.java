package com.pahimar.ee3.init;

import com.pahimar.ee3.block.*;
import com.pahimar.ee3.item.ItemBlockAlchemicalChest;
import com.pahimar.ee3.item.ItemBlockAlchemicalFuel;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
    public static final BlockEE chalkBlock = new BlockChalk();
    public static final BlockEE alchemicalFuelBlock = new BlockAlchemicalFuel();
    public static final BlockEE alchemicalChest = new BlockAlchemicalChest();
    public static final BlockEE aludel = new BlockAludel();
    public static final BlockEE calcinator = new BlockCalcinator();
    public static final BlockEE glassBell = new BlockGlassBell();
    public static final BlockEE researchStation = new BlockResearchStation();
    public static final BlockEE augmentationTable = new BlockAugmentationTable();
    public static final BlockEE ashInfusedStone = new BlockAshInfusedStone();
    public static final BlockEE basicAlchemyArray = new BlockAlchemyArray();
    public static final BlockEE dummyAlchemyArray = new BlockDummyArray();

    public static void init()
    {
        GameRegistry.registerBlock(calcinator, Names.Blocks.CALCINATOR);
        GameRegistry.registerBlock(aludel, Names.Blocks.ALUDEL);
        GameRegistry.registerBlock(glassBell, Names.Blocks.GLASS_BELL);
        GameRegistry.registerBlock(researchStation, Names.Blocks.RESEARCH_STATION);
        GameRegistry.registerBlock(augmentationTable, Names.Blocks.AUGMENTATION_TABLE);
        GameRegistry.registerBlock(alchemicalChest, ItemBlockAlchemicalChest.class, Names.Blocks.ALCHEMICAL_CHEST);
        GameRegistry.registerBlock(chalkBlock, Names.Blocks.CHALK);
        GameRegistry.registerBlock(alchemicalFuelBlock, ItemBlockAlchemicalFuel.class, Names.Blocks.ALCHEMICAL_FUEL);
        GameRegistry.registerBlock(ashInfusedStone, Names.Blocks.ASH_INFUSED_STONE);
        GameRegistry.registerBlock(basicAlchemyArray, Names.Blocks.BASIC_ALCHEMY_ARRAY);
        GameRegistry.registerBlock(dummyAlchemyArray, Names.Blocks.DUMMY_ALCHEMY_ARRAY);
    }
}
