package ee3.common.core.handlers;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import cpw.mods.fml.common.ICraftingHandler;
import ee3.common.item.ModItems;
import ee3.common.lib.ConfigurationSettings;
import ee3.common.lib.Reference;

/**
 * CraftingHandler
 * 
 * Used to execute our custom code when things are crafted/smelted
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CraftingHandler implements ICraftingHandler {

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix) {
        ItemStack currentItemStack;
        for (int i = 0; i < craftMatrix.getSizeInventory(); i++) {
            currentItemStack = craftMatrix.getStackInSlot(i);
            if (currentItemStack != null) {
                if (currentItemStack.itemID == ModItems.miniumStone.shiftedIndex) {
                    
                    // Capture the destruction of the Minium Stone when used the final time
                    currentItemStack.damageItem(ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST, player);
                    currentItemStack.stackSize++;
                    
                } else if (currentItemStack.itemID == ModItems.philStone.shiftedIndex) {
                    currentItemStack.stackSize++;
                }
            }
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {
        // TODO Auto-generated method stub

    }

}
