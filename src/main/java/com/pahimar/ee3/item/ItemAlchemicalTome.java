package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.skill.PlayerKnowledge;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
            ItemHelper.setOwner(itemStack, entityPlayer);
        }
        else
        {
            entityPlayer.openGui(EquivalentExchange3.instance, GUIs.ALCHEMICAL_TOME.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return itemStack;
    }

    public static void writePlayerKnowledge(ItemStack itemStack, PlayerKnowledge playerKnowledge)
    {
        if (itemStack.getItem() instanceof ItemAlchemicalTome)
        {
            NBTTagCompound nbtTagCompound;
            if (itemStack.hasTagCompound())
            {
                nbtTagCompound = itemStack.getTagCompound();
            }
            else
            {
                nbtTagCompound = new NBTTagCompound();
            }
            playerKnowledge.writeToNBT(nbtTagCompound);
            itemStack.setTagCompound(nbtTagCompound);
        }
    }

    public static PlayerKnowledge readPlayerKnowledge(ItemStack itemStack)
    {
        if (itemStack.getItem() instanceof ItemAlchemicalTome)
        {
            if (itemStack.hasTagCompound() && NBTHelper.hasTag(itemStack, Names.NBT.ITEM_TRANSMUTATION_KNOWLEDGE))
            {
                PlayerKnowledge playerKnowledge = new PlayerKnowledge();
                playerKnowledge.readFromNBT(itemStack.getTagCompound());
                return playerKnowledge;
            }
            else
            {
                PlayerKnowledge playerKnowledge = new PlayerKnowledge();
                writePlayerKnowledge(itemStack, playerKnowledge);
                return playerKnowledge;
            }
        }

        return null;
    }
}
