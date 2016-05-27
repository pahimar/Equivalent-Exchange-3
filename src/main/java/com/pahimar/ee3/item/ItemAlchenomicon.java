package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.exchange.IEnergyValueProvider;
import com.pahimar.ee3.init.ModItems;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemStackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class ItemAlchenomicon extends ItemEE implements IOwnable, IEnergyValueProvider
{
    public ItemAlchenomicon()
    {
        super();
        this.setUnlocalizedName(Names.Items.ALCHENOMICON);
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
            if (ItemStackUtils.getOwnerUUID(itemStack) == null)
            {
                ItemStackUtils.setOwner(itemStack, entityPlayer);
                entityPlayer.addChatComponentMessage(new ChatComponentTranslation(Messages.OWNER_SET_TO_SELF, new Object[]{itemStack.func_151000_E()}));
            }
            else
            {
                entityPlayer.openGui(EquivalentExchange3.instance, GUIs.ALCHENOMICON.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
            }
        }

        return itemStack;
    }

    @Override
    public EnergyValue getEnergyValue(ItemStack itemStack)
    {
        return EnergyValueRegistryProxy.getEnergyValue(ModItems.alchenomicon, true);
    }
}
