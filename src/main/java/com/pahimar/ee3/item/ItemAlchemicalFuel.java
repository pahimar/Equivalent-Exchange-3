package com.pahimar.ee3.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;

import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAlchemicalFuel extends ItemEE
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icons;

    public ItemAlchemicalFuel()
    {
        super();
        this.setHasSubtypes(true);
        this.maxStackSize = 64;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s.%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_FUEL_NAME, Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES[MathHelper.clamp_int(itemStack.getItemDamage(), 0, Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length - 1)]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta)
    {
        return icons[MathHelper.clamp_int(meta, 0, Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length - 1)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icons = new IIcon[Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length];

        for (int i = 0; i < Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length; ++i)
        {
            icons[i] = iconRegister.registerIcon(String.format("%s%s.%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_FUEL_NAME, Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES[i]));
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item id, CreativeTabs creativeTab, List list)
    {
        for (int meta = 0; meta < Strings.ALCHEMICAL_FUEL_SUBTYPE_NAMES.length; ++meta)
        {
            list.add(new ItemStack(id, 1, meta));
        }
    }
}
