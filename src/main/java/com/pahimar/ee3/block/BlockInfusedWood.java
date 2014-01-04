package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.block.material.Material;

public class BlockInfusedWood extends BlockEE
{
    public BlockInfusedWood(int id)
    {
        super(id, Material.wood);
        this.setUnlocalizedName(Strings.INFUSED_WOOD_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setStepSound(soundWoodFootstep);
    }
}
