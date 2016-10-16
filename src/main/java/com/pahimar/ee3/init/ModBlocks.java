package com.pahimar.ee3.init;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.block.BlockAlchemicalFuel;
import com.pahimar.ee3.block.BlockCalciner;
import com.pahimar.ee3.block.BlockGlassBell;
import com.pahimar.ee3.block.base.IBlockEE;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@GameRegistry.ObjectHolder(EquivalentExchange3.MOD_ID)
public class ModBlocks {

    private static final List<Block> BLOCKS = new ArrayList<>();

    public static final Block CALCINER = new BlockCalciner();
    public static final Block ALCHEMICAL_FUEL = new BlockAlchemicalFuel();
    public static final Block GLASS_BELL = new BlockGlassBell();

    private ModBlocks() {}

    public static Collection<Block> getBlocks() {
        return BLOCKS;
    }

    public static void register(Block block) {
        if (block instanceof IBlockEE) {
            BLOCKS.add(block);
        }
    }
}
