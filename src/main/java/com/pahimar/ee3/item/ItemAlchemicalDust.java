package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
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
    public static final EmcValue[] DEFAULT_EMC_VALUES = {
            new EmcValue(1),
            new EmcValue(64),
            new EmcValue(2048),
            new EmcValue(8192),
            new EmcValue(73728),
            new EmcValue(4718592)
    };

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemAlchemicalDust(int id)
    {
        super(id);
        this.setHasSubtypes(true);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_DUST_NAME, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int meta)
    {
        return icons[MathHelper.clamp_int(meta, 0, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
        icons = new Icon[Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length];

        for (int i = 0; i < Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES.length; ++i)
        {
            icons[i] = iconRegister.registerIcon(String.format("%s%s.%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_DUST_NAME, Strings.ALCHEMICAL_DUST_SUBTYPE_NAMES[i]));
        }
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
