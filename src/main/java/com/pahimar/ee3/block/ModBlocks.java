package com.pahimar.ee3.block;

import com.pahimar.ee3.item.ItemAlchemicalFuelBlock;
import com.pahimar.ee3.item.ItemInfusedClothBlock;
import com.pahimar.ee3.item.ItemInfusedOreBlock;
import com.pahimar.ee3.item.ItemInfusedStoneBlock;
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
    public static BlockEE infusedStone;
    public static BlockEE infusedCloth;
    public static BlockEE infusedWood;
    public static BlockEE infusedOre;

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
        infusedStone = new BlockInfusedStone(BlockIds.INFUSED_STONE);
        infusedWood = new BlockInfusedWood(BlockIds.INFUSED_WOOD);
        infusedCloth = new BlockInfusedCloth(BlockIds.INFUSED_CLOTH);
        infusedOre = new BlockInfusedOre(BlockIds.INFUSED_ORE);

        GameRegistry.registerBlock(alchemicalChest, "block." + Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerBlock(alchemicalFuel, ItemAlchemicalFuelBlock.class, "block." + Strings.ALCHEMICAL_FUEL_NAME);
        GameRegistry.registerBlock(alchemySquare, "block." + Strings.ALCHEMY_SQUARE_NAME);
        GameRegistry.registerBlock(aludelBase, "block." + Strings.ALUDEL_NAME);
        GameRegistry.registerBlock(calcinator, "block." + Strings.CALCINATOR_NAME);
        GameRegistry.registerBlock(chalk, "block." + Strings.CHALK_NAME);
        GameRegistry.registerBlock(glassBell, "block." + Strings.GLASS_BELL_NAME);
        GameRegistry.registerBlock(researchStation, "block." + Strings.RESEARCH_STATION_NAME);
        GameRegistry.registerBlock(infusedCloth, ItemInfusedClothBlock.class, "block." + Strings.INFUSED_CLOTH_NAME);
        GameRegistry.registerBlock(infusedOre, ItemInfusedOreBlock.class, "block." + Strings.INFUSED_ORE_NAME);
        GameRegistry.registerBlock(infusedStone, ItemInfusedStoneBlock.class, "block." + Strings.INFUSED_STONE_NAME);
        GameRegistry.registerBlock(infusedWood, "block." + Strings.INFUSED_WOOD_NAME);
    }
}
