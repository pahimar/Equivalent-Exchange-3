package com.pahimar.ee3.client.gui.inventory;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.item.ItemAlchemicalBag;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.Textures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;

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

    private final int BAG_GUI_X_SIZE = 248, BAG_GUI_Y_SIZE = 186;
    private final int SHARED_GUI_X_SIZE = 256, SHARED_GUI_Y_SIZE = 256;
//    private final int SHARED_GUI_X_SIZE = 270, SHARED_GUI_Y_SIZE = 262;

    private String renderTexture = Textures.GUI_ALCHEMICAL_STORAGE;//Texture to be rendered based on wether the bag is sharing with a chest or not.
    private String containerName = Strings.CONTAINER_ALCHEMICAL_BAG_NAME;
    private int guiSizeX = BAG_GUI_X_SIZE, guiSizeY = BAG_GUI_Y_SIZE;


    public GuiAlchemicalBag(ItemStack stack, EntityPlayer ep) {

        super(new ContainerAlchemicalBag(stack, ep));
//        this.stack = stack;
        boolean shared = ((ItemAlchemicalBag)ModItems.alchemicalBag).isSharing(stack);
        if(shared) {
            System.out.println("Sharing");
            renderTexture = Textures.GUI_SHARED_ALCHEMICAL_STORAGE;
            guiSizeX = SHARED_GUI_X_SIZE;
            guiSizeY = SHARED_GUI_Y_SIZE;
            containerName = "";
        }
        xSize = guiSizeX;
        ySize = guiSizeY;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

        fontRenderer.drawString(StatCollector.translateToLocal(containerName), 8, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 44, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(renderTexture);
//        int xStart = (width - xSize) / 2;
//        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
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
                    if(NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_SHARE))
                        NBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_SHARE);
                }
            }
        }
    }
}
