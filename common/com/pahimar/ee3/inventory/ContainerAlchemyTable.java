package com.pahimar.ee3.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

import com.pahimar.ee3.tileentity.TileAlchemyTable;

public class ContainerAlchemyTable extends Container {

    private TileAlchemyTable tileAlchemyTable;

    public ContainerAlchemyTable(InventoryPlayer inventoryPlayer, TileAlchemyTable tileAlchemyTable) {

        this.tileAlchemyTable = tileAlchemyTable;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {

        // TODO Auto-generated method stub
        return false;
    }

}
