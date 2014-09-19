package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.IKeyBound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemChalk extends ItemEE implements IKeyBound
{
    public ItemChalk()
    {
        super();
        this.setMaxStackSize(64);
        this.setUnlocalizedName(Names.Items.CHALK);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        return itemStack;
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key)
    {
        if (key == Key.TOGGLE)
        {
            entityPlayer.openGui(EquivalentExchange3.instance, GUIs.SYMBOL_SELECTION.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }
    }
}