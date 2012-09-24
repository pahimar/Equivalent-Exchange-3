package ee3.common.item;

import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.CustomItemRarity;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;

/**
 * ItemPhilosopherStone
 * 
 * The Philosophers Stone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemPhilosopherStone extends ItemEE {

    public ItemPhilosopherStone(int id) {
        super(id);
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.RARE);
    }
    
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
    	return false;
    }
    
    @Override
    public boolean getShareTag() {
    	return true;
    }

}
