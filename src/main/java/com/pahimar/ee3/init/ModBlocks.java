package com.pahimar.ee3.init;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.block.BlockCalciner;
import com.pahimar.ee3.block.base.BlockEE;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@GameRegistry.ObjectHolder(EquivalentExchange3.MOD_ID)
public class ModBlocks {

    private static final List<BlockEE> BLOCKS = new ArrayList<>();

    public static final BlockEE calciner = new BlockCalciner();

    private ModBlocks() {}

    public static Collection<BlockEE> getBlocks() {
        return BLOCKS;
    }

    public static void register(BlockEE block) {
        BLOCKS.add(block);
    }
}
