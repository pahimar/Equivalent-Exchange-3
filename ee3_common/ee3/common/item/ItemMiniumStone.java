package ee3.common.item;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import ee3.common.EquivalentExchange3;
import ee3.common.core.helper.TransmutationHelper;
import ee3.common.lib.Colours;
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
    	
		return itemStack;
    }
    
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int l, float f1, float f2, float f3) {
		boolean result = TransmutationHelper.transmuteInWorld(world, entityPlayer, itemStack, x, y, z);
		
		if (result) {
			itemStack.damageItem(1, entityPlayer);
		}
		
		return result;
    }
	
	@SideOnly(Side.CLIENT)
	@Override
    public int func_82790_a(ItemStack par1ItemStack, int par2)
    {
        return Integer.parseInt(Colours.PURE_RED, 16);
    }
	
	@SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return false;
    }
    
}
