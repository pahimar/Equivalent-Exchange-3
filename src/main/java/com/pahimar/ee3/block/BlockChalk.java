package com.pahimar.ee3.block;

import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Strings;
import net.minecraft.block.material.Material;

import java.util.Random;

public class BlockChalk extends BlockEE
{
    public BlockChalk(int id)
    {
        super(id, Material.clay);
        setHardness(0.6F);
        setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.CHALK_NAME);
        setStepSound(soundGravelFootstep);
    }

    @Override
    public int idDropped(int par1, Random random, int par2)
    {
        return ModItems.chalk.itemID;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return (random.nextInt(4) + 1);
    }
}
