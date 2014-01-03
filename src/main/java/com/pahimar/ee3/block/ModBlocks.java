package com.pahimar.ee3.block;

import com.pahimar.ee3.item.ItemAlchemicalFuelBlock;
import com.pahimar.ee3.lib.BlockIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ModBlocks
 *
 * @author pahimar
 */
public class ModBlocks
{
    public static BlockEE alchemicalChest;
    public static BlockEE alchemicalFuel;
    public static BlockEE alchemySquare;
    public static BlockEE aludelBase;
    public static BlockEE calcinator;
    public static BlockEE chalk;
    public static BlockEE glassBell;
    public static BlockEE researchStation;

    public static void init()
    {
        alchemicalChest = new BlockAlchemicalChest(BlockIds.ALCHEMICAL_CHEST);
        alchemicalFuel = new BlockAlchemicalFuel(BlockIds.ALCHEMICAL_FUEL);
        alchemySquare = new BlockAlchemySquare(BlockIds.ALCHEMY_SQUARE);
        aludelBase = new BlockAludelBase(BlockIds.ALUDEL_BASE);
        calcinator = new BlockCalcinator(BlockIds.CALCINATOR);
        chalk = new BlockChalk(BlockIds.CHALK);
        glassBell = new BlockGlassBell(BlockIds.GLASS_BELL);
        researchStation = new BlockResearchStation((BlockIds.RESEARCH_STATION));

        GameRegistry.registerBlock(alchemicalChest, "block." + Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerBlock(alchemicalFuel, ItemAlchemicalFuelBlock.class, "block." + Strings.ALCHEMICAL_FUEL_NAME);
        GameRegistry.registerBlock(alchemySquare, "block." + Strings.ALCHEMY_SQUARE_NAME);
        GameRegistry.registerBlock(aludelBase, "block." + Strings.ALUDEL_NAME);
        GameRegistry.registerBlock(calcinator, "block." + Strings.CALCINATOR_NAME);
        GameRegistry.registerBlock(chalk, "block." + Strings.CHALK_NAME);
        GameRegistry.registerBlock(glassBell, "block." + Strings.GLASS_BELL_NAME);
        GameRegistry.registerBlock(researchStation, "block." + Strings.RESEARCH_STATION_NAME);
    }
}
