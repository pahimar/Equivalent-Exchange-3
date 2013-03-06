package com.pahimar.ee3.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.CustomItemRarity;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ItemAlchemyDust
 * 
 * General class for Alchemy related dusts
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemAlchemicalDust extends ItemEE {

    private static final String[] ALCHEMICAL_DUST_NAMES = new String[] { "Ash", "Minium", "Verdant", "Azure", "Amaranthine", "Iridescent" };

    @SideOnly(Side.CLIENT)
    private Icon[] icons;

    public ItemAlchemicalDust(int id) {

        super(id);
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Strings.ALCHEMICAL_DUST_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);
        return (super.getUnlocalizedName() + ALCHEMICAL_DUST_NAMES[meta]);
    }

    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int meta) {

        int j = MathHelper.clamp_int(meta, 0, 5);
        return this.icons[j];
    }

    @SideOnly(Side.CLIENT)
    public void func_94581_a(IconRegister iconRegister) {

        this.icons = new Icon[ALCHEMICAL_DUST_NAMES.length];

        for (int i = 0; i < ALCHEMICAL_DUST_NAMES.length; ++i) {
            this.icons[i] = iconRegister.func_94245_a(Reference.MOD_ID.toLowerCase() + ":" + Strings.ALCHEMICAL_DUST_NAME + ALCHEMICAL_DUST_NAMES[i]);
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        int meta = MathHelper.clamp_int(stack.getItemDamage(), 0, 5);

        if (meta == 5) {
            return true;
        }
        else {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {

        int meta = MathHelper.clamp_int(stack.getItemDamage(), 0, 11);

        switch (meta) {
            case 0:
                return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.NORMAL);
            case 1:
                return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.NORMAL);
            case 2:
                return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.UNCOMMON);
            case 3:
                return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.MAGICAL);
            case 4:
                return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.EPIC);
            case 5:
                return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.LEGENDARY);
            default:
                return EnumRarity.common;
        }

    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List list) {

        for (int meta = 0; meta < 6; ++meta) {
            list.add(new ItemStack(id, 1, meta));
        }
    }
}
