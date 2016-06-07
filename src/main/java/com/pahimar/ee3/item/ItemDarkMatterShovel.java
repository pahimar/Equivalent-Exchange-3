package com.pahimar.ee3.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.pahimar.ee3.reference.*;
import com.pahimar.ee3.util.CommonSoundHelper;
import com.pahimar.ee3.util.IChargeable;
import com.pahimar.ee3.util.IKeyBound;
import com.pahimar.ee3.util.NBTUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ItemDarkMatterShovel extends ItemToolModalEE implements IKeyBound, IChargeable
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});

    public ItemDarkMatterShovel()
    {
        super(1.0f, Material.DARK_MATTER_TOOL, blocksEffectiveAgainst);
        this.setUnlocalizedName(Names.Tools.DARK_MATTER_SHOVEL);
    }

    @Override
    public boolean func_150897_b(Block block)
    {
        return block == Blocks.snow_layer || block == Blocks.snow;
    }

    @Override
    public Set<String> getToolClasses(ItemStack itemStack)
    {
        return ImmutableSet.of("shovel");
    }

    @Override
    public short getMaxChargeLevel()
    {
        return 3;
    }

    @Override
    public short getChargeLevel(ItemStack itemStack) {

        if (NBTUtils.getShort(itemStack, Names.NBT.CHARGE_LEVEL) != null) {
            return NBTUtils.getShort(itemStack, Names.NBT.CHARGE_LEVEL);
        }

        return 0;
    }

    @Override
    public void setChargeLevel(ItemStack itemStack, short charge)
    {
        if (charge <= this.getMaxChargeLevel())
        {
            NBTUtils.setShort(itemStack, Names.NBT.CHARGE_LEVEL, charge);
        }
    }

    @Override
    public void increaseChargeLevel(ItemStack itemStack)
    {
        if (getChargeLevel(itemStack) < this.getMaxChargeLevel())
        {
            NBTUtils.setShort(itemStack, Names.NBT.CHARGE_LEVEL, (short) (getChargeLevel(itemStack) + 1));
        }
    }

    @Override
    public void decreaseChargeLevel(ItemStack itemStack)
    {
        if (getChargeLevel(itemStack) > 0)
        {
            NBTUtils.setShort(itemStack, Names.NBT.CHARGE_LEVEL, (short) (getChargeLevel(itemStack) - 1));
        }
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key)
    {
        if (key == Key.CHARGE)
        {
            if (!entityPlayer.isSneaking())
            {
                if (getChargeLevel(itemStack) == this.getMaxChargeLevel())
                {
                    CommonSoundHelper.playSoundAtPlayer(entityPlayer, Sounds.FAIL, 1.5f, 1.5f);
                }
                else
                {
                    increaseChargeLevel(itemStack);
                    CommonSoundHelper.playSoundAtPlayer(entityPlayer, Sounds.CHARGE_UP, 0.5F, 0.5F + 0.5F * (getChargeLevel(itemStack) * 1.0F / this.getMaxChargeLevel()));
                }
            }
            else
            {
                if (getChargeLevel(itemStack) == 0)
                {
                    CommonSoundHelper.playSoundAtPlayer(entityPlayer, Sounds.FAIL, 1.5f, 1.5f);
                }
                else
                {
                    decreaseChargeLevel(itemStack);
                    CommonSoundHelper.playSoundAtPlayer(entityPlayer, Sounds.CHARGE_DOWN, 0.5F, 1.0F - (0.5F - 0.5F * (getChargeLevel(itemStack) * 1.0F / this.getMaxChargeLevel())));
                }
            }
        }
        else if (key == Key.EXTRA)
        {
            CommonSoundHelper.playSoundAtPlayer(entityPlayer, Sounds.TOCK, 0.5f, 1.5F);
            changeToolMode(itemStack);
        }
    }

    @Override
    public List<ToolMode> getAvailableToolModes()
    {
        // TODO
        return Arrays.asList(ToolMode.STANDARD);
    }
}
