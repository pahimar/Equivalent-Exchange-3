package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.ToolMode;
import com.pahimar.ee3.util.IChargeable;
import com.pahimar.ee3.util.IKeyBound;
import com.pahimar.ee3.util.IModalTool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemDarkMatterHammer extends ItemEE implements IKeyBound, IChargeable, IModalTool
{
    public ItemDarkMatterHammer()
    {
        super();
        this.setUnlocalizedName(Names.Tools.DARK_MATTER_HAMMER);
    }

    @Override
    public short getMaxChargeLevel()
    {
        return 0;
    }

    @Override
    public short getChargeLevel(ItemStack itemStack)
    {
        return 0;
    }

    @Override
    public void setChargeLevel(ItemStack itemStack, short chargeLevel)
    {

    }

    @Override
    public void increaseChargeLevel(ItemStack itemStack)
    {

    }

    @Override
    public void decreaseChargeLevel(ItemStack itemStack)
    {

    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key)
    {

    }

    @Override
    public List<ToolMode> getAvailableToolModes()
    {
        return null;
    }

    @Override
    public ToolMode getCurrentToolMode(ItemStack itemStack)
    {
        return null;
    }

    @Override
    public void setToolMode(ItemStack itemStack, ToolMode toolMode)
    {

    }

    @Override
    public void changeToolMode(ItemStack itemStack)
    {

    }
}
