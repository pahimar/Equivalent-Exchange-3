package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.inventory.InventoryAlchemicalBag;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

/**
 * Equivalent-Exchange-3
 * <p/>
 * GuiAlchemicalBag
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class GuiAlchemicalBag extends GuiContainer
{

    public GuiAlchemicalBag(InventoryPlayer inventoryPlayer, InventoryAlchemicalBag inventoryAlchemicalBag)
    {
        super(new ContainerAlchemicalBag(inventoryPlayer, inventoryAlchemicalBag));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();

        if (mc.thePlayer != null)
        {
            for (ItemStack itemStack : mc.thePlayer.inventory.mainInventory)
            {
                if (itemStack != null)
                {
                    if (ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN))
                    {
                        ItemStackNBTHelper.removeTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN);
                    }
                }
            }
        }
    }
}
