package com.pahimar.ee3.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * ItemAlchemicalDust
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
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {

        StringBuilder unlocalizedName = new StringBuilder();
        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);

        unlocalizedName.append("item.");
        unlocalizedName.append(Strings.RESOURCE_PREFIX);
        unlocalizedName.append(Strings.ALCHEMICAL_DUST_NAME);
        unlocalizedName.append(ALCHEMICAL_DUST_NAMES[meta]);

        return unlocalizedName.toString();
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Gets an icon index based on an item's damage value
     */
    public Icon getIconFromDamage(int meta) {

        int j = MathHelper.clamp_int(meta, 0, 5);
        return icons[j];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        icons = new Icon[ALCHEMICAL_DUST_NAMES.length];

        for (int i = 0; i < ALCHEMICAL_DUST_NAMES.length; ++i) {
            icons[i] = iconRegister.registerIcon(Strings.RESOURCE_PREFIX + Strings.ALCHEMICAL_DUST_NAME + ALCHEMICAL_DUST_NAMES[i]);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {

        int meta = MathHelper.clamp_int(stack.getItemDamage(), 0, 5);

        if (meta == 5)
            return true;
        else
            return false;
    }

    @Override
    public String getItemDisplayName(ItemStack itemStack) {

        int meta = MathHelper.clamp_int(itemStack.getItemDamage(), 0, 5);

        switch (meta) {
            case 0:
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
            case 1:
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
            case 2:
                return EnumChatFormatting.GREEN + super.getItemDisplayName(itemStack);
            case 3:
                return EnumChatFormatting.BLUE + super.getItemDisplayName(itemStack);
            case 4:
                return EnumChatFormatting.DARK_PURPLE + super.getItemDisplayName(itemStack);
            case 5:
                return EnumChatFormatting.GOLD + super.getItemDisplayName(itemStack);
            default:
                return EnumChatFormatting.WHITE + super.getItemDisplayName(itemStack);
        }
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)
    public void getSubItems(int id, CreativeTabs creativeTab, List list) {

        for (int meta = 0; meta < 6; ++meta) {
            list.add(new ItemStack(id, 1, meta));
        }
    }
}
