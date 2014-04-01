package com.pahimar.ee3.item;

import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;

import java.util.List;

public class ItemAlchemicalInventoryUpgrade extends ItemEE
{
    public ItemAlchemicalInventoryUpgrade()
    {
        super();
        this.setMaxStackSize(64);
        this.setUnlocalizedName(Names.Items.ALCHEMICAL_UPGRADE);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, Names.Items.ALCHEMICAL_UPGRADE);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", Textures.RESOURCE_PREFIX, Names.Items.ALCHEMICAL_UPGRADE, Names.Items.ALCHEMICAL_UPGRADE_SUBTYPES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.ALCHEMICAL_UPGRADE_SUBTYPES.length - 1)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Names.Items.ALCHEMICAL_UPGRADE_SUBTYPES.length; ++meta)
        {
            list.add(new ItemStack(this, 1, meta));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        if (itemStack.getItemDamage() == 0)
        {
            return Integer.parseInt(Colors.DUST_VERDANT, 16);
        }
        else if (itemStack.getItemDamage() == 1)
        {
            return Integer.parseInt(Colors.DUST_AZURE, 16);
        }
        else if (itemStack.getItemDamage() == 2)
        {
            return Integer.parseInt(Colors.DUST_MINIUM, 16);
        }

        return Integer.parseInt(Colors.PURE_WHITE, 16);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean flag)
    {
        list.add(StatCollector.translateToLocal(Messages.UPGRADES_CHESTS));
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        switch (MathHelper.clamp_int(itemStack.getItemDamage(), 0, Names.Items.ALCHEMICAL_UPGRADE_SUBTYPES.length - 1))
        {
            case 0:
            {
                return EnumChatFormatting.GREEN + super.getItemStackDisplayName(itemStack);
            }
            case 1:
            {
                return EnumChatFormatting.BLUE + super.getItemStackDisplayName(itemStack);
            }
            case 2:
            {
                return EnumChatFormatting.RED + super.getItemStackDisplayName(itemStack);
            }
            default:
            {
                return EnumChatFormatting.WHITE + super.getItemStackDisplayName(itemStack);
            }
        }
    }
}
