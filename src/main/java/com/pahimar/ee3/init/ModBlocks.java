package com.pahimar.ee3.init;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.block.BlockAlchemicalFuel;
import com.pahimar.ee3.block.BlockCalciner;
import com.pahimar.ee3.block.BlockGlassBell;
import com.pahimar.ee3.block.base.BlockBase;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@GameRegistry.ObjectHolder(EquivalentExchange3.MOD_ID)
public class ModBlocks {

    private static final List<BlockBase> BLOCKS = new ArrayList<>();

    public static final BlockBase CALCINER = new BlockCalciner();
    public static final BlockBase ALCHEMICAL_FUEL = new BlockAlchemicalFuel();
    public static final BlockBase GLASS_BELL = new BlockGlassBell();

    private ModBlocks() {}

    public static Collection<BlockBase> getBlocks() {
        return BLOCKS;
    }

    public static void register(BlockBase block) {
        BLOCKS.add(block);
    }
}
