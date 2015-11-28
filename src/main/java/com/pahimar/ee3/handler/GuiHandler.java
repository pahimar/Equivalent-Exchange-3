package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.*;
import com.pahimar.ee3.inventory.*;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GUIs.ALCHEMICAL_CHEST.ordinal())
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new ContainerAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GUIs.GLASS_BELL.ordinal())
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new ContainerGlassBell(entityPlayer.inventory, tileEntityGlassBell);
        }
        else if (id == GUIs.ALCHEMICAL_BAG.ordinal())
        {
            return new ContainerAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItem()));
        }
        else if (id == GUIs.ALCHENOMICON.ordinal())
        {
            return new ContainerAlchenomicon(entityPlayer, entityPlayer.getHeldItem());
        }
        else if (id == GUIs.CALCINATOR.ordinal())
        {
            TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
            return new ContainerCalcinator(entityPlayer.inventory, tileEntityCalcinator);
        }
        else if (id == GUIs.ALUDEL.ordinal())
        {
            TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
            return new ContainerAludel(entityPlayer.inventory, tileEntityAludel);
        }
        else if (id == GUIs.RESEARCH_STATION.ordinal())
        {
            TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(x, y, z);
            return new ContainerResearchStation(entityPlayer.inventory, tileEntityResearchStation);
        }
        else if (id == GUIs.AUGMENTATION_TABLE.ordinal())
        {
            TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(x, y, z);
            return new ContainerAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);
        }
        else if (id == GUIs.TRANSMUTATION_TABLET.ordinal())
        {
            TileEntityTransmutationTablet tileEntityTransmutationTablet = (TileEntityTransmutationTablet) world.getTileEntity(x, y, z);
            return new ContainerTransmutationTablet(entityPlayer.inventory, tileEntityTransmutationTablet);
        }
        else if (id == GUIs.TRANSMUTATION_ARRAY.ordinal())
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(x, y, z);
            return new ContainerTransmutationArray(entityPlayer.inventory, tileEntityAlchemyArray);
        }
        else if (id == GUIs.SYMBOL_SELECTION.ordinal())
        {
            return new ContainerSymbolSelection();
        }
        else if (id == GUIs.ADMIN_PANEL.ordinal())
        {
            return new ContainerAdminPanel(entityPlayer.inventory);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GUIs.ALCHEMICAL_CHEST.ordinal())
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new GuiAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GUIs.GLASS_BELL.ordinal())
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new GuiGlassBell(entityPlayer.inventory, tileEntityGlassBell);
        }
        else if (id == GUIs.ALCHEMICAL_BAG.ordinal())
        {
            return new GuiAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItem()));
        }
        else if (id == GUIs.ALCHENOMICON.ordinal())
        {
            return new GuiAlchenomicon(entityPlayer.inventory, entityPlayer.getHeldItem());
        }
        else if (id == GUIs.CALCINATOR.ordinal())
        {
            TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
            return new GuiCalcinator(entityPlayer.inventory, tileEntityCalcinator);
        }
        else if (id == GUIs.ALUDEL.ordinal())
        {
            TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
            return new GuiAludel(entityPlayer.inventory, tileEntityAludel);
        }
        else if (id == GUIs.RESEARCH_STATION.ordinal())
        {
            TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(x, y, z);
            return new GuiResearchStation(entityPlayer.inventory, tileEntityResearchStation);
        }
        else if (id == GUIs.AUGMENTATION_TABLE.ordinal())
        {
            TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(x, y, z);
            return new GuiAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);
        }
        else if (id == GUIs.TRANSMUTATION_TABLET.ordinal())
        {
            TileEntityTransmutationTablet tileEntityTransmutationTablet = (TileEntityTransmutationTablet) world.getTileEntity(x, y, z);
            return new GuiTransmutationTablet(entityPlayer.inventory, tileEntityTransmutationTablet);
        }
        else if (id == GUIs.TRANSMUTATION_ARRAY.ordinal())
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(x, y, z);
            return new GuiTransmutationArray(entityPlayer.inventory, tileEntityAlchemyArray);
        }
        else if (id == GUIs.SYMBOL_SELECTION.ordinal())
        {
            return new GuiSymbolSelection();
        }
        else if (id == GUIs.ADMIN_PANEL.ordinal())
        {
            return new GuiAdminPanel(entityPlayer.inventory);
        }

        return null;
    }
}
