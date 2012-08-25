package ee3.common.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.CustomItemRarity;

/**
 * ItemMiniumStone
 * 
 * The "lesser" or "imperfect" Philosophers Stone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemMiniumStone extends ItemEE {

    public ItemMiniumStone(int id) {
        super(id);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.MAGICAL);
    }
    
}
