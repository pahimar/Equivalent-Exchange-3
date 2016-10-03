package com.pahimar.ee3.block.base;

import net.minecraft.block.material.Material;

public abstract class BlockEnumEE extends BlockEE {
    public BlockEnumEE(String name) {
        super(name);
    }

    public BlockEnumEE(String name, Material material) {
        super(name, material);
    }
}
