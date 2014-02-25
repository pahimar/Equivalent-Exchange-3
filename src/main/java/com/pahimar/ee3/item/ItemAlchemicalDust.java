package com.pahimar.ee3.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemAlchemicalDust
 *
 * @author pahimar
 */
public class ItemAlchemicalDust extends ItemEE
{
    public ItemAlchemicalDust()
    {
        super();
        this.setHasSubtypes(true);
        maxStackSize = 64;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass)
    {
        if (itemStack.getItemDamage() == 0)
        {
            return Integer.parseInt(Colours.DUST_ASH, 16);
        }
        else if (itemStack.getItemDamage() == 1)
        {
            return Integer.parseInt(Colours.DUST_VERDANT, 16);
        }
        else if (itemStack.getItemDamage() == 2)
        {
            return Integer.parseInt(Colours.DUST_AZURE, 16);
        }
        else if (itemStack.getItemDamage() == 3)
        {
            return Integer.parseInt(Colours.DUST_MINIUM, 16);
        }

        return Integer.parseInt(Colours.PURE_WHITE, 16);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_DUST_NAME);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_DUST_NAME, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1)]);
    }

    @Override
    public String getItemStackDisplayName(ItemStack itemStack)
    {
        switch (MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1))
        {
            case 0:
            {
                return EnumChatFormatting.GRAY + super.getItemStackDisplayName(itemStack);
            }
            case 1:
            {
                return EnumChatFormatting.GREEN + super.getItemStackDisplayName(itemStack);
            }
            case 2:
            {
                return EnumChatFormatting.BLUE + super.getItemStackDisplayName(itemStack);
            }
            case 3:
            {
                return EnumChatFormatting.RED + super.getItemStackDisplayName(itemStack);
            }
            default:
            {
                return EnumChatFormatting.WHITE + super.getItemStackDisplayName(itemStack);
            }
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }

    public static List<ItemStack> getAlchemicalDusts()
    {
        List<ItemStack> alchemicalDustStacks = new ArrayList<ItemStack>();

        for (int meta = 0; meta < Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length; meta++)
        {
            alchemicalDustStacks.add(new ItemStack(ModItems.alchemicalDust, 1, meta));
        }

        return alchemicalDustStacks;
    }
}
