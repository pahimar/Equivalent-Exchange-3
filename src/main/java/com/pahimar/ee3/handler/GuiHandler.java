package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.*;
import com.pahimar.ee3.inventory.*;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.tileentity.*;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        final GUIs guiId = getGuiId(id);
        if (guiId == null) return null;

        switch(guiId)
        {
            case ALCHEMICAL_CHEST:
                TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
                return new ContainerAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);

            case GLASS_BELL:
                TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
                return new ContainerGlassBell(entityPlayer.inventory, tileEntityGlassBell);

            case ALCHEMICAL_BAG:
                return new ContainerAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItem()));

            case ALCHEMICAL_TOME:
//              return new ContainerAlchemicalTome(new InventoryAlchemicalTome(entityPlayer.getHeldItem()));
                return new ContainerAlchemicalTome(entityPlayer.inventory);

            case CALCINATOR:
                TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
                return new ContainerCalcinator(entityPlayer.inventory, tileEntityCalcinator);

            case ALUDEL:
                TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
                return new ContainerAludel(entityPlayer.inventory, tileEntityAludel);

            case RESEARCH_STATION:
                TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(x, y, z);
                return new ContainerResearchStation(entityPlayer.inventory, tileEntityResearchStation);

            case AUGMENTATION_TABLE:
                TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(x, y, z);
                return new ContainerAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);

            case SYMBOL_SELECTION:
                return new ContainerSymbolSelection();

            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        final GUIs guiId = getGuiId(id);
        if (guiId == null) return null;

        switch(guiId)
        {
            case ALCHEMICAL_CHEST:
            {
                TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
                return new GuiAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);
            }
            case GLASS_BELL:
            {
                TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
                return new GuiGlassBell(entityPlayer.inventory, tileEntityGlassBell);
            }
            case ALCHEMICAL_BAG:
            {
                return new GuiAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItem()));
            }
            case ALCHEMICAL_TOME:
            {
//              return new GuiAlchemicalTome(new InventoryAlchemicalTome(entityPlayer.getHeldItem()));
                return new GuiAlchemicalTome(entityPlayer);
            }
            case CALCINATOR:
            {
                TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
                return new GuiCalcinator(entityPlayer.inventory, tileEntityCalcinator);
            }
            case ALUDEL:
            {
                TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
                return new GuiAludel(entityPlayer.inventory, tileEntityAludel);
            }
            case RESEARCH_STATION:
            {
                TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(x, y, z);
                return new GuiResearchStation(entityPlayer.inventory, tileEntityResearchStation);
            }
            case AUGMENTATION_TABLE:
            {
                TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(x, y, z);
                return new GuiAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);
            }
            case SYMBOL_SELECTION:
            {
                return new GuiSymbolSelection();
            }
            default:
            return null;
        }
    }

    private static GUIs getGuiId(int id) {
        try {
            return GUIs.VALUES[id];
        } catch (ArrayIndexOutOfBoundsException e) {
            LogHelper.warn("Invalid GUI id");
            return null;
        }
    }
}
