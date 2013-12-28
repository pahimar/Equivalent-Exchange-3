package com.pahimar.ee3.block;

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

    /* Mod block instances */
    public static BlockEE alchemicalCoal;
    public static BlockEE mobiusFuel;
    public static BlockEE aeternalisFuel;

    public static BlockContainerEE calcinator;
    public static BlockContainerEE aludelBase;
    public static BlockContainerEE alchemicalChest;
    public static BlockContainerEE glassBell;

    public static void init()
    {
        alchemicalCoal = new BlockAlchemicalCoal(BlockIds.ALCHEMICAL_COAL);
        mobiusFuel = new BlockMobiusFuel(BlockIds.MOBIUS_FUEL);
        aeternalisFuel = new BlockAeternalisFuel(BlockIds.AETERNALIS_FUEL);

        calcinator = new BlockCalcinator(BlockIds.CALCINATOR);
        aludelBase = new BlockAludelBase(BlockIds.ALUDEL_BASE);
        alchemicalChest = new BlockAlchemicalChest(BlockIds.ALCHEMICAL_CHEST);
        glassBell = new BlockGlassBell(BlockIds.GLASS_BELL);

        GameRegistry.registerBlock(alchemicalCoal, "block." + Strings.ALCHEMICAL_COAL_NAME);
        GameRegistry.registerBlock(mobiusFuel, "block." + Strings.MOBIUS_FUEL_NAME);
        GameRegistry.registerBlock(aeternalisFuel, "block." + Strings.AETERNALIS_FUEL_NAME);
        GameRegistry.registerBlock(calcinator, "block." + Strings.CALCINATOR_NAME);
        GameRegistry.registerBlock(aludelBase, "block." + Strings.ALUDEL_NAME);
        GameRegistry.registerBlock(alchemicalChest, "block." + Strings.ALCHEMICAL_CHEST_NAME);
        GameRegistry.registerBlock(glassBell, "block." + Strings.GLASS_BELL_NAME);
    }
}
