package com.pahimar.ee3.item;

import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.reference.*;
import com.pahimar.ee3.util.NBTHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemDarkMatterShovel extends ItemSpade implements IKeyBound, IChargeable, IModalTool
{
    public ItemDarkMatterShovel()
    {
        super(Material.Tools.DARK_MATTER);
        this.setCreativeTab(CreativeTab.EE3_TAB);
        this.setNoRepair();
        this.setUnlocalizedName(Names.Tools.DARK_MATTER_SHOVEL);
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
            return (double) (this.getMaxChargeLevel() - ((IChargeable) itemStack.getItem()).getChargeLevel(itemStack)) / (double) this.getMaxChargeLevel();
        }

        return 1d;
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
        // TODO
        return null;
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
