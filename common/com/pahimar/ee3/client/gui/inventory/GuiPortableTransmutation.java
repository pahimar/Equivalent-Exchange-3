package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.nbt.NBTHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * GuiPortableTransmutation
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class GuiPortableTransmutation extends GuiContainer {

    public GuiPortableTransmutation(Container par1Container) {

        super(par1Container);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.GUI_PORTABLE_TRANSMUTATION);
        int var5 = (width - xSize) / 2;
        int var6 = (height - ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed() {

        super.onGuiClosed();

        if (mc.thePlayer != null) {
            for (ItemStack itemStack : mc.thePlayer.inventory.mainInventory) {
                if (itemStack != null) {
                    if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN)) {
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN);
                    }
                }
            }
        }
    }
}
