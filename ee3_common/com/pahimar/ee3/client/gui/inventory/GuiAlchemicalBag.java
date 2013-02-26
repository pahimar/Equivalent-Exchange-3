package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.lib.Strings;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAlchemicalBag extends GuiContainer {
    
    public GuiAlchemicalBag(InventoryPlayer inventoryPlayer) {

        super(new ContainerAlchemicalBag(inventoryPlayer));
        this.xSize = 248;
        this.ySize = 186;
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {
        
        this.fontRenderer.drawString(LanguageRegistry.instance().getStringLocalization(Strings.GUI_ALCHEMICAL_BAG_NAME), 8, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 44, this.ySize - 96 + 2, 4210752);
    }
    
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        int backgroundTexture = this.mc.renderEngine.getTexture(Sprites.GUI_ALCHEMICAL_STORAGE);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(backgroundTexture);
        int xStart = (this.width - this.xSize) / 2;
        int yStart = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, this.xSize, this.ySize);
    }

    public void onGuiClosed() {

        super.onGuiClosed();

        if (this.mc.thePlayer != null) {
            for (ItemStack itemStack : this.mc.thePlayer.inventory.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMY_BAG_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMY_BAG_GUI_OPEN);
                    }
                }
            }
        }
    }
}
