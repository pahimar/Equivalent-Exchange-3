package com.pahimar.ee3.block.base;

import net.minecraft.block.material.Material;

public abstract class BlockEnumEE extends BlockEE {

    protected final String[] VARIANTS;

    public BlockEnumEE(String name, String ... variants) {
        this(name, Material.ROCK, variants);
    }

    public BlockEnumEE(String name, Material material, String ... variants) {
        super(name, material);

        if (variants.length > 0) {
            VARIANTS = variants;
        }
        else {
            VARIANTS = new String[0];
        }
    }
}
