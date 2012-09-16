package ee3.common.container;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;

public class ContainerCalcinator extends Container {

    private TileEntityCalcinator calcinator;
    
    public ContainerCalcinator(InventoryPlayer inventoryPlayer, TileEntityCalcinator calcinator) {
        this.calcinator = calcinator;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        // TODO Auto-generated method stub
        return false;
    }

}
