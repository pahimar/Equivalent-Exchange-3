package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;

import com.pahimar.ee3.inventory.ContainerAlchemyTable;
import com.pahimar.ee3.tileentity.TileAlchemyTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAlchemyTable extends GuiContainer {

    private TileAlchemyTable tileAlchemyTable;

    public GuiAlchemyTable(InventoryPlayer inventoryPlayer, TileAlchemyTable tileAlchemyTable) {

        super(new ContainerAlchemyTable(inventoryPlayer, tileAlchemyTable));
        this.tileAlchemyTable = tileAlchemyTable;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        // TODO Auto-generated method stub

    }

}
