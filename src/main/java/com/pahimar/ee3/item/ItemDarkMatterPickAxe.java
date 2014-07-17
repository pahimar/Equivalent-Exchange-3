package com.pahimar.ee3.item;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.reference.*;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ItemDarkMatterPickAxe extends ItemToolEE implements IKeyBound, IChargeable, IModalTool
{
    private static final Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{Blocks.cobblestone, Blocks.double_stone_slab, Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore, Blocks.rail, Blocks.detector_rail, Blocks.golden_rail, Blocks.activator_rail});

    public ItemDarkMatterPickAxe()
    {
        super(2f, Material.Tools.DARK_MATTER, blocksEffectiveAgainst);
        this.setCreativeTab(CreativeTab.EE3_TAB);
        this.setNoRepair();
        this.setUnlocalizedName(Names.Tools.DARK_MATTER_PICKAXE);
    }

    @Override
    public boolean func_150897_b(Block block)
    {
        return block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == net.minecraft.block.material.Material.rock ? true : (block.getMaterial() == net.minecraft.block.material.Material.iron ? true : block.getMaterial() == net.minecraft.block.material.Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 1) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2) : this.toolMaterial.getHarvestLevel() >= 2);
    }

    @Override
    public float func_150893_a(ItemStack itemStack, Block block)
    {
        return block.getMaterial() != net.minecraft.block.material.Material.iron && block.getMaterial() != net.minecraft.block.material.Material.anvil && block.getMaterial() != net.minecraft.block.material.Material.rock ? super.func_150893_a(itemStack, block) : this.efficiencyOnProperMaterial;
    }

    @Override
    public Set<String> getToolClasses(ItemStack itemStack)
    {
        return ImmutableSet.of("pickaxe");
    }

    @Override
    public float getDigSpeed(ItemStack itemStack, Block block, int meta)
    {
        if ((ForgeHooks.isToolEffective(itemStack, block, meta) || block == Blocks.obsidian || block == Blocks.redstone_ore || block == Blocks.lit_redstone_ore) && (itemStack.getItem() instanceof IChargeable))
        {
            return super.getDigSpeed(itemStack, block, meta) + (((IChargeable) itemStack.getItem()).getChargeLevel(itemStack) * 12f);
        }

        return super.getDigSpeed(itemStack, block, meta);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            // TODO
            LogHelper.info("Right click with the Dark Matter Pickaxe");
        }

        return false;
    }

    @Override
    public short getMaxChargeLevel()
    {
        return 3;
    }

    @Override
    public short getChargeLevel(ItemStack itemStack)
    {
        return NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL);
    }

    @Override
    public void setChargeLevel(ItemStack itemStack, short chargeLevel)
    {
        if (chargeLevel <= this.getMaxChargeLevel())
        {
            NBTHelper.setShort(itemStack, Names.NBT.CHARGE_LEVEL, chargeLevel);
        }
    }

    @Override
    public void increaseChargeLevel(ItemStack itemStack)
    {
        if (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) < this.getMaxChargeLevel())
        {
            NBTHelper.setShort(itemStack, Names.NBT.CHARGE_LEVEL, (short) (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) + 1));
        }
    }

    @Override
    public void decreaseChargeLevel(ItemStack itemStack)
    {
        if (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) > 0)
        {
            NBTHelper.setShort(itemStack, Names.NBT.CHARGE_LEVEL, (short) (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) - 1));
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
                    entityPlayer.worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Sounds.FAIL, 1.5f, 1.5f);
                }
                else
                {
                    increaseChargeLevel(itemStack);
                    entityPlayer.worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Sounds.CHARGE_UP, 0.5F, 0.5F + 0.5F * (getChargeLevel(itemStack) * 1.0F / this.getMaxChargeLevel()));
                }
            }
            else
            {
                if (getChargeLevel(itemStack) == 0)
                {
                    entityPlayer.worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Sounds.FAIL, 1.5f, 1.5f);
                }
                else
                {
                    decreaseChargeLevel(itemStack);
                    entityPlayer.worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Sounds.CHARGE_DOWN, 0.5F, 1.0F - (0.5F - 0.5F * (getChargeLevel(itemStack) * 1.0F / this.getMaxChargeLevel())));
                }
            }
        }
        else if (key == Key.EXTRA)
        {
            changeToolMode(itemStack);
        }
    }

    @Override
    public List<ToolMode> getAvailableToolModes()
    {
        return Arrays.asList(ToolMode.STANDARD, ToolMode.WIDE, ToolMode.TALL);
    }

    @Override
    public ToolMode getCurrentToolMode(ItemStack itemStack)
    {
        if (NBTHelper.getShort(itemStack, Names.NBT.MODE) < ToolMode.TYPES.length)
        {
            return ToolMode.TYPES[NBTHelper.getShort(itemStack, Names.NBT.MODE)];
        }

        return null;
    }

    @Override
    public void setToolMode(ItemStack itemStack, ToolMode toolMode)
    {
        NBTHelper.setShort(itemStack, Names.NBT.MODE, (short) toolMode.ordinal());
    }

    @Override
    public void changeToolMode(ItemStack itemStack)
    {
        ToolMode currentToolMode = getCurrentToolMode(itemStack);

        if (getAvailableToolModes().contains(currentToolMode))
        {
            if (getAvailableToolModes().indexOf(currentToolMode) == getAvailableToolModes().size() - 1)
            {
                setToolMode(itemStack, getAvailableToolModes().get(0));
            }
            else
            {
                setToolMode(itemStack, getAvailableToolModes().get(getAvailableToolModes().indexOf(currentToolMode) + 1));
            }
        }
    }
}
