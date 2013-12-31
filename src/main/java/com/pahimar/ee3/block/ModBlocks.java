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
    public static BlockEE alchemicalFuel;
    public static BlockEE chalk;
    public static BlockEE transmutationSquare;

    public static BlockContainerEE calcinator;
    public static BlockContainerEE aludelBase;
    public static BlockContainerEE alchemicalChest;
    public static BlockContainerEE glassBell;

    public static void init()
    {
        chalk = new BlockChalk(BlockIds.CHALK);
        alchemicalFuel = new BlockAlchemicalFuel(BlockIds.ALCHEMICAL_FUEL);
        transmutationSquare = new BlockTransmutationSquare(BlockIds.TRANSMUTATION_SQUARE);

        calcinator = new BlockCalcinator(BlockIds.CALCINATOR);
        aludelBase = new BlockAludelBase(BlockIds.ALUDEL_BASE);
        alchemicalChest = new BlockAlchemicalChest(BlockIds.ALCHEMICAL_CHEST);
        glassBell = new BlockGlassBell(BlockIds.GLASS_BELL);

        GameRegistry.registerBlock(alchemicalFuel, ItemAlchemicalFuelBlock.class, "block." + Strings.ALCHEMICAL_FUEL_NAME);
        GameRegistry.registerBlock(chalk, "block." + Strings.CHALK_NAME);
        GameRegistry.registerBlock(calcinator, "block." + Strings.CALCINATOR_NAME);
        GameRegistry.registerBlock(aludelBase, "block." + Strings.ALUDEL_NAME);
        GameRegistry.registerBlock(alchemicalChest, "block." + Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerBlock(glassBell, "block." + Strings.GLASS_BELL_NAME);
        GameRegistry.registerBlock(transmutationSquare, "block." + Strings.TRANSMUTATION_SQUARE_NAME);
    }
}
