package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.*;
import com.pahimar.ee3.inventory.*;
import com.pahimar.ee3.reference.GuiId;
import com.pahimar.ee3.tileentity.*;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GuiId.ALCHEMICAL_CHEST.ordinal())
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new ContainerAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GuiId.GLASS_BELL.ordinal())
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new ContainerGlassBell(entityPlayer.inventory, tileEntityGlassBell);
        }
        else if (id == GuiId.ALCHEMICAL_BAG.ordinal())
        {
            return new ContainerAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItem()));
        }
        else if (id == GuiId.ALCHEMICAL_TOME.ordinal())
        {
            return new ContainerAlchemicalTome(new InventoryAlchemicalTome(entityPlayer.getHeldItem()));
        }
        else if (id == GuiId.CALCINATOR.ordinal())
        {
            TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
            return new ContainerCalcinator(entityPlayer.inventory, tileEntityCalcinator);
        }
        else if (id == GuiId.ALUDEL.ordinal())
        {
            TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
            return new ContainerAludel(entityPlayer.inventory, tileEntityAludel);
        }
        else if (id == GuiId.RESEARCH_STATION.ordinal())
        {
            TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(x, y, z);
            return new ContainerResearchStation(entityPlayer.inventory, tileEntityResearchStation);
        }
        else if (id == GuiId.AUGMENTATION_TABLE.ordinal())
        {
            TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(x, y, z);
            return new ContainerAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z)
    {
        if (id == GuiId.ALCHEMICAL_CHEST.ordinal())
        {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(x, y, z);
            return new GuiAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GuiId.GLASS_BELL.ordinal())
        {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(x, y, z);
            return new GuiGlassBell(entityPlayer.inventory, tileEntityGlassBell);
        }
        else if (id == GuiId.ALCHEMICAL_BAG.ordinal())
        {
            return new GuiAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItem()));
        }
        else if (id == GuiId.ALCHEMICAL_TOME.ordinal())
        {
            return new GuiAlchemicalTome(new InventoryAlchemicalTome(entityPlayer.getHeldItem()));
        }
        else if (id == GuiId.CALCINATOR.ordinal())
        {
            TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(x, y, z);
            return new GuiCalcinator(entityPlayer.inventory, tileEntityCalcinator);
        }
        else if (id == GuiId.ALUDEL.ordinal())
        {
            TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(x, y, z);
            return new GuiAludel(entityPlayer.inventory, tileEntityAludel);
        }
        else if (id == GuiId.RESEARCH_STATION.ordinal())
        {
            TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(x, y, z);
            return new GuiResearchStation(entityPlayer.inventory, tileEntityResearchStation);
        }
        else if (id == GuiId.AUGMENTATION_TABLE.ordinal())
        {
            TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(x, y, z);
            return new GuiAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);
        }

        return null;
    }
}
