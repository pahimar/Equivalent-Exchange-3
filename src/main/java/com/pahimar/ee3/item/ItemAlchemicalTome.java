package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemAlchemicalTome extends ItemEE implements IOwnable
{
    public ItemAlchemicalTome()
    {
        super();
        this.setUnlocalizedName(Names.Items.ALCHEMICAL_TOME);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (!world.isRemote)
        {
            // Set the owner
            if (!ItemHelper.hasOwner(itemStack))
            {
                ItemHelper.setOwner(itemStack, entityPlayer);
            }
        }
        else
        {
            entityPlayer.openGui(EquivalentExchange3.instance, GUIs.ALCHEMICAL_TOME.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }
}
