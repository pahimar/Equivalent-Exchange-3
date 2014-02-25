package com.pahimar.ee3.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Strings;

public class BlockChalk extends BlockEE
{
    public BlockChalk()
    {
        super(Material.clay);
        this.setHardness(0.6F);
        this.setBlockName(Strings.CHALK_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setStepSound(soundTypeStone);
    }

    @Override
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return ModItems.chalk;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return (random.nextInt(4) + 1);
    }
}
