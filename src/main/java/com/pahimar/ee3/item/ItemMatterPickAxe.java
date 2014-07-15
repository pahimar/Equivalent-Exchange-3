package com.pahimar.ee3.item;

import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.reference.*;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.Arrays;
import java.util.List;

public class ItemMatterPickAxe extends ItemPickaxe implements IKeyBound, IChargeable, IModalTool
{
    private short maxChargeLevel;

    public ItemMatterPickAxe(ToolMaterial toolMaterial, int maxChargeLevel)
    {
        super(toolMaterial);
        this.maxChargeLevel = (short) maxChargeLevel;
        this.setCreativeTab(CreativeTab.EE3_TAB);
        this.setNoRepair();
        this.setUnlocalizedName(Names.Tools.DARK_MATTER_PICKAXE);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf(".") + 1));
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public boolean showDurabilityBar(ItemStack itemStack)
    {
        return ((IChargeable) itemStack.getItem()).getChargeLevel(itemStack) > 0;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof IChargeable)
        {
            return (double) (this.maxChargeLevel - ((IChargeable) itemStack.getItem()).getChargeLevel(itemStack)) / (double) this.maxChargeLevel;
        }

        return 1d;
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
            LogHelper.info("Right click with the Dark Matter Pickaxe");
        }

        return false;
    }

    @Override
    public short getChargeLevel(ItemStack itemStack)
    {
        return NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL);
    }

    @Override
    public void setChargeLevel(ItemStack itemStack, short chargeLevel)
    {
        if (chargeLevel <= maxChargeLevel)
        {
            NBTHelper.setShort(itemStack, Names.NBT.CHARGE_LEVEL, chargeLevel);
        }
    }

    @Override
    public void increaseChargeLevel(ItemStack itemStack)
    {
        if (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) < maxChargeLevel)
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
                if (getChargeLevel(itemStack) == maxChargeLevel)
                {
                    entityPlayer.worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Sounds.FAIL, 1.5f, 1.5f);
                }
                else
                {
                    increaseChargeLevel(itemStack);
                    entityPlayer.worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Sounds.CHARGE_UP, 0.5F, 0.5F + 0.5F * (getChargeLevel(itemStack) * 1.0F / maxChargeLevel));
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
                    entityPlayer.worldObj.playSoundEffect(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, Sounds.CHARGE_DOWN, 0.5F, 1.0F - (0.5F - 0.5F * (getChargeLevel(itemStack) * 1.0F / maxChargeLevel)));
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
        return Arrays.asList(ToolMode.SINGLE, ToolMode.WIDE, ToolMode.TALL);
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
