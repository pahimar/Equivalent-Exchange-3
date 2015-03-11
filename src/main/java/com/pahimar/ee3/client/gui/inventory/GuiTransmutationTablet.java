package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;

@SideOnly(Side.CLIENT)
public class GuiTransmutationTablet extends GuiBase
{
    public GuiTransmutationTablet(InventoryPlayer inventoryPlayer)
    {
        super(new ContainerTransmutationTablet(inventoryPlayer), Textures.Gui.TRANSMUTATION_TABLET);
        xSize = 256;
        ySize = 256;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        this.drawTitle = false;
        this.drawInventory = false;
    }
}
