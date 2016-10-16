package com.pahimar.ee3.block.base;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IBlockEE {

    @SideOnly(Side.CLIENT)
    void initModelsAndVariants();
}
