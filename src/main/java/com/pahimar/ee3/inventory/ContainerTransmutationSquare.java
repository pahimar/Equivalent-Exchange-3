package com.pahimar.ee3.inventory;

import com.pahimar.ee3.tileentity.TileEntityTransmutationSquare;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTransmutationSquare extends ContainerEE
{
    private TileEntityTransmutationSquare tileEntityTransmutationSquare;

    public ContainerTransmutationSquare(InventoryPlayer inventoryPlayer, TileEntityTransmutationSquare tileEntityTransmutationSquare)
    {
        this.tileEntityTransmutationSquare = tileEntityTransmutationSquare;
    }
}
