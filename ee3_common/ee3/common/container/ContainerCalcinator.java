package ee3.common.container;

import ee3.common.tile.TileCalcinator;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;

public class ContainerCalcinator extends Container {

    private TileCalcinator calcinator;
    
    public ContainerCalcinator(InventoryPlayer inventoryPlayer, TileCalcinator calcinator) {
        this.calcinator = calcinator;
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer var1) {
        // TODO Auto-generated method stub
        return false;
    }

}
