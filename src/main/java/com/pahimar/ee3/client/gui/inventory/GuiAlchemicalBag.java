package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.inventory.InventoryAlchemicalBag;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.lib.Textures;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketBagUpdate;
import com.pahimar.ee3.network.packet.PacketEE;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

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
    ItemStack bag;
    InventoryAlchemicalBag inv;

    public GuiAlchemicalBag(InventoryPlayer inventoryPlayer, ItemStack bag, int slot)
    {
        super(new ContainerAlchemicalBag(inventoryPlayer, new InventoryAlchemicalBag(bag, slot)));
        this.inv = ((ContainerAlchemicalBag)this.inventorySlots).inv;
        this.bag = this.inv.bag;
        xSize = 248;
        ySize = 186;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {

        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_ALCHEMICAL_BAG_NAME), 8, 6, 4210752);
        fontRenderer.drawString(StatCollector.translateToLocal(Strings.CONTAINER_INVENTORY), 44, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        // this.mc.getTextureManager().bindTexture(...)
        this.mc.getTextureManager().bindTexture(Textures.GUI_ALCHEMICAL_STORAGE);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void onGuiClosed()
    {

        super.onGuiClosed();
        
        if (this.bag.stackTagCompound == null)
        {
            this.bag.setTagCompound(new NBTTagCompound());
        }
        this.inv.writeToNBT(this.bag.getTagCompound());
        
        PacketEE packet = new PacketBagUpdate(this.bag.getTagCompound());
        PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(packet));

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
