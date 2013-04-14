package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.core.helper.LocalizationHelper;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.item.ItemAlchemicalBag;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * GuiAlchemicalBag
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class GuiAlchemicalBag extends GuiContainer {
    
    private ItemAlchemicalBag itemABag;

    public GuiAlchemicalBag(InventoryPlayer inventoryPlayer, ItemAlchemicalBag alchemicalBag) {

        super(new ContainerAlchemicalBag(inventoryPlayer));
        itemABag = alchemicalBag;
        xSize = 248;
        ySize = 186;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        fontRenderer.drawString(StatCollector.translateToLocal(itemABag.hasCustomName() ? itemABag.getCustomName() : itemABag.getCustomName()), 8, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 44, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(Textures.GUI_ALCHEMICAL_STORAGE);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed() {

        super.onGuiClosed();

        if (mc.thePlayer != null) {
            for (ItemStack itemStack : mc.thePlayer.inventory.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
                    }
                }
            }
        }
    }
}
