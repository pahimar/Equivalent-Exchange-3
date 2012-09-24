package ee3.common.core.handlers;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
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
                    // If we still have enough durability to do the transmute, do it
                    if ((currentItemStack.getItemDamage() + ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST) <= currentItemStack.getMaxDamage()) {
                        System.out.println(currentItemStack.getItemDamage());
                    	currentItemStack.damageItem(ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST, player);
                    }
                    // Else capture the destruction of the item, and throw the event
                    else {
                        currentItemStack.damageItem(ConfigurationSettings.MINIUM_STONE_TRANSMUTE_COST, player);
                        MinecraftForge.EVENT_BUS.post(new PlayerDestroyItemEvent(player, currentItemStack));
                        currentItemStack = null;
                    }
                    
                }
            }
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item) {
        // TODO Auto-generated method stub

    }

}
