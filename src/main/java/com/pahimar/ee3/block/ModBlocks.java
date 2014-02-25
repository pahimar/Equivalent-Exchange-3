package com.pahimar.ee3.block;

import com.pahimar.ee3.item.*;
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
    public static BlockEE infusedCloth;
    public static BlockEE infusedWood;
    public static BlockEE infusedPlanks;

    public static void init()
    {
        alchemicalChest = new BlockAlchemicalChest();
        alchemicalFuel = new BlockAlchemicalFuel();
        alchemySquare = new BlockAlchemySquare();
        aludelBase = new BlockAludelBase();
        calcinator = new BlockCalcinator();
        chalk = new BlockChalk();
        glassBell = new BlockGlassBell();
        researchStation = new BlockResearchStation();
        infusedWood = new BlockInfusedWood();
        infusedCloth = new BlockInfusedCloth();
        infusedPlanks = new BlockInfusedPlanks();

        GameRegistry.registerBlock(alchemicalChest, ItemBlockAlchemicalChest.class, "block." + Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerBlock(alchemicalFuel, ItemBlockAlchemicalFuel.class, "block." + Strings.ALCHEMICAL_FUEL_NAME);
        GameRegistry.registerBlock(alchemySquare, "block." + Strings.ALCHEMY_SQUARE_NAME);
        GameRegistry.registerBlock(aludelBase, "block." + Strings.ALUDEL_NAME);
        GameRegistry.registerBlock(calcinator, "block." + Strings.CALCINATOR_NAME);
        GameRegistry.registerBlock(chalk, "block." + Strings.CHALK_NAME);
        GameRegistry.registerBlock(glassBell, "block." + Strings.GLASS_BELL_NAME);
        GameRegistry.registerBlock(researchStation, "block." + Strings.RESEARCH_STATION_NAME);
        GameRegistry.registerBlock(infusedCloth, ItemBlockInfusedCloth.class, "block." + Strings.INFUSED_CLOTH_NAME);
        GameRegistry.registerBlock(infusedWood, ItemBlockInfusedWood.class, "block." + Strings.INFUSED_WOOD_NAME);
        GameRegistry.registerBlock(infusedPlanks, ItemBlockInfusedPlanks.class, "block." + Strings.INFUSED_PLANKS_NAME);
    }
}
