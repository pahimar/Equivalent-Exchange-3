package ee3.common.item;

import java.util.List;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.MathHelper;
import net.minecraft.src.StringTranslate;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.CustomItemRarity;
import ee3.common.lib.Strings;

/**
 * ItemAlchemyDust
 * 
 * General class for Alchemy related dusts
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemAlchemyDust extends ItemEE {

    public static final String[] alchemyDustNames = new String[] { "ash", "minium", "verdant", "azure", "amaranthine", "iridescent" };

    public ItemAlchemyDust(int id) {

        super(id);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setIconCoord(0, 3);
        this.setItemName(Strings.ALCHEMY_DUST_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }

    @SideOnly(Side.CLIENT)
    public int getIconFromDamage(int meta) {

        int i = MathHelper.clamp_int(meta, 0, 11);
        return (this.iconIndex + i);
    }

    public String getItemNameIS(ItemStack stack) {

        int meta = MathHelper.clamp_int(stack.getItemDamage(), 0, 5);
        return super.getItemName() + "." + alchemyDustNames[meta];
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
