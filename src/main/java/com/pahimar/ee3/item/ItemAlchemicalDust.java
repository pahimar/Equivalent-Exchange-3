package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemAlchemicalDust
 *
 * @author pahimar
 */
public class ItemAlchemicalDust extends ItemEE
{
    public ItemAlchemicalDust(int id)
    {
        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_DUST_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
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
        else if (itemStack.getItemDamage() == 4)
        {
            return Integer.parseInt(Colours.DUST_IRIDESCENT, 16);
        }

        return Integer.parseInt(Colours.PURE_WHITE, 16);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_DUST_NAME, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack, int renderPass)
    {
        return MathHelper.clamp_int(stack.getItemDamage(), 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1) == (Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1);
    }

    @Override
    public String getItemDisplayName(ItemStack itemStack)
    {
        switch (MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1))
        {
            case 0:
            {
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
            }
            case 1:
            {
                return EnumChatFormatting.GREEN + super.getItemDisplayName(itemStack);
            }
            case 2:
            {
                return EnumChatFormatting.BLUE + super.getItemDisplayName(itemStack);
            }
            case 3:
            {
                return EnumChatFormatting.RED + super.getItemDisplayName(itemStack);
            }
            case 4:
            {
                return EnumChatFormatting.GOLD + super.getItemDisplayName(itemStack);
            }
            default:
            {
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
            }
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }

    @Override
    public List<ItemStack> getSubTypes()
    {
        List<ItemStack> alchemicalDustStacks = new ArrayList<ItemStack>();

        for (int meta = 0; meta < Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length; meta++)
        {
            alchemicalDustStacks.add(new ItemStack(ModItems.alchemicalDust, 1, meta));
        }

        return alchemicalDustStacks;
    }
}
