package com.pahimar.ee3.core.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalBag;
import com.pahimar.ee3.client.gui.inventory.GuiAlchemicalChest;
import com.pahimar.ee3.client.gui.inventory.GuiCalcinator;
import com.pahimar.ee3.client.gui.inventory.GuiPortableCrafting;
import com.pahimar.ee3.client.gui.inventory.GuiPortableTransmutation;
import com.pahimar.ee3.inventory.ContainerAlchemicalBag;
import com.pahimar.ee3.inventory.ContainerAlchemicalChest;
import com.pahimar.ee3.inventory.ContainerCalcinator;
import com.pahimar.ee3.inventory.ContainerPortableCrafting;
import com.pahimar.ee3.inventory.ContainerPortableTransmutation;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * CommonProxy
 * 
 * The common proxy class between client and server. Client proxy extends this
 * for further client specific functionality
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CommonProxy implements IGuiHandler {

    public void registerKeyBindingHandler() {

    }

    public void registerRenderTickHandler() {

    }

    public void registerDrawBlockHighlightHandler() {

    }

    public void setKeyBinding(String name, int value) {

    }

    public void registerSoundHandler() {

    }

    public void initCustomRarityTypes() {

    }

    public EnumRarity getCustomRarityType(String customRarity) {

        return null;
    }

    public void initRenderingAndTextures() {

    }

    public void initTileEntities() {

        GameRegistry.registerTileEntity(TileCalcinator.class, Strings.TE_CALCINATOR_NAME);
        GameRegistry.registerTileEntity(TileAludel.class, Strings.TE_ALUDEL_NAME);
        GameRegistry.registerTileEntity(TileAlchemicalChest.class, Strings.TE_ALCHEMICAL_CHEST_NAME);
    }

    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit) {
        
    }
    
    public void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {

    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiIds.PORTABLE_CRAFTING) {
            return new ContainerPortableCrafting(player.inventory, world, x, y, z);
        }
        else if (ID == GuiIds.PORTABLE_TRANSMUTATION) {
            return new ContainerPortableTransmutation();
        }
        else if (ID == GuiIds.CALCINATOR) {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);
            return new ContainerCalcinator(player.inventory, tileCalcinator);
        }
        else if (ID == GuiIds.ALCHEMICAL_CHEST) {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);
            return new ContainerAlchemicalChest(player.inventory, tileAlchemicalChest);
        }
        else if (ID == GuiIds.ALCHEMICAL_BAG) {
            // TODO Alchemical Bag inventory work is incomplete
            return new ContainerAlchemicalBag(player.inventory);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        if (ID == GuiIds.PORTABLE_CRAFTING) {
            return new GuiPortableCrafting(player, world, x, y, z);
        }
        else if (ID == GuiIds.PORTABLE_TRANSMUTATION) {
            return new GuiPortableTransmutation(null);
        }
        else if (ID == GuiIds.CALCINATOR) {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);
            return new GuiCalcinator(player.inventory, tileCalcinator);
        }
        else if (ID == GuiIds.ALCHEMICAL_CHEST) {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);
            return new GuiAlchemicalChest(player.inventory, tileAlchemicalChest);
        }
        else if (ID == GuiIds.ALCHEMICAL_BAG) {
            // TODO Alchemical Bag inventory work is incomplete
            return new GuiAlchemicalBag(player.inventory);
        }

        return null;
    }

}
