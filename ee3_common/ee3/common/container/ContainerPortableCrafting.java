package ee3.common.container;

import net.minecraft.src.ContainerWorkbench;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.World;

/**
 * ContainerPortableCrafting
 * 
 * Container class for the portable crafting interface
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ContainerPortableCrafting extends ContainerWorkbench {

    public ContainerPortableCrafting(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        super(inventoryPlayer, world, x, y, z);
    }

    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        return true;
    }
    
    @Override
    public void onCraftGuiClosed(EntityPlayer player) {
    	super.onCraftGuiClosed(player);
    }

}
