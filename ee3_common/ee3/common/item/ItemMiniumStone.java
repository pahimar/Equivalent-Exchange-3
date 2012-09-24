package ee3.common.item;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import ee3.common.EquivalentExchange3;
import ee3.common.lib.ConfigurationSettings;
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
        setMaxDamage(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.MAGICAL);
    }
    
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
    	return false;
    }
    
    @Override
    public boolean getShareTag() {
    	return true;
    }
    
    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {
    	itemStack.setItemDamage(itemStack.getItemDamage() + 1);
    	
    	if (itemStack.getItemDamage() < itemStack.getMaxDamage()) {
    		return itemStack;
    	}
    	else {
    		return null;
    	}
    }
    
}
