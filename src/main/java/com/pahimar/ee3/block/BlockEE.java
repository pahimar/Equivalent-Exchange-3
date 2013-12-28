package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockEE extends Block
{
    public BlockEE(int id)
    {
        this(id, Material.rock);
    }

    public BlockEE(int id, Material material)
    {
        super(id, material);
        setStepSound(soundStoneFootstep);
        setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        blockIcon = iconRegister.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
