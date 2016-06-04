package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.inventory.*;
import com.pahimar.ee3.inventory.*;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.tileentity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {

        BlockPos blockPos = new BlockPos(x, y, z);

        if (id == GUIs.ALCHEMICAL_CHEST.ordinal()) {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(blockPos);
            return new ContainerAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GUIs.GLASS_BELL.ordinal()) {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(blockPos);
            return new ContainerGlassBell(entityPlayer.inventory, tileEntityGlassBell);
        }
        else if (id == GUIs.ALCHEMICAL_BAG.ordinal()) {
            return new ContainerAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItemMainhand()));
        }
        else if (id == GUIs.ALCHENOMICON.ordinal()) {
            return new ContainerAlchenomicon(entityPlayer, entityPlayer.getHeldItemMainhand());
        }
        else if (id == GUIs.CALCINATOR.ordinal()) {
            TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(blockPos);
            return new ContainerCalcinator(entityPlayer.inventory, tileEntityCalcinator);
        }
        else if (id == GUIs.ALUDEL.ordinal()) {
            TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(blockPos);
            return new ContainerAludel(entityPlayer.inventory, tileEntityAludel);
        }
        else if (id == GUIs.RESEARCH_STATION.ordinal()) {
            TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(blockPos);
            return new ContainerResearchStation(entityPlayer.inventory, tileEntityResearchStation);
        }
        else if (id == GUIs.AUGMENTATION_TABLE.ordinal()) {
            TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(blockPos);
            return new ContainerAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);
        }
        else if (id == GUIs.TRANSMUTATION_TABLET.ordinal()) {
            TileEntityTransmutationTablet tileEntityTransmutationTablet = (TileEntityTransmutationTablet) world.getTileEntity(blockPos);
            return new ContainerTransmutationTablet(entityPlayer.inventory, tileEntityTransmutationTablet);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {

        BlockPos blockPos = new BlockPos(x, y, z);

        if (id == GUIs.ALCHEMICAL_CHEST.ordinal()) {
            TileEntityAlchemicalChest tileEntityAlchemicalChest = (TileEntityAlchemicalChest) world.getTileEntity(blockPos);
            return new GuiAlchemicalChest(entityPlayer.inventory, tileEntityAlchemicalChest);
        }
        else if (id == GUIs.GLASS_BELL.ordinal()) {
            TileEntityGlassBell tileEntityGlassBell = (TileEntityGlassBell) world.getTileEntity(blockPos);
            return new GuiGlassBell(entityPlayer.inventory, tileEntityGlassBell);
        }
        else if (id == GUIs.ALCHEMICAL_BAG.ordinal()) {
            return new GuiAlchemicalBag(entityPlayer, new InventoryAlchemicalBag(entityPlayer.getHeldItemMainhand()));
        }
        else if (id == GUIs.ALCHENOMICON.ordinal()) {
            return new GuiAlchenomicon(entityPlayer.inventory, entityPlayer.getHeldItemMainhand());
        }
        else if (id == GUIs.CALCINATOR.ordinal()) {
            TileEntityCalcinator tileEntityCalcinator = (TileEntityCalcinator) world.getTileEntity(blockPos);
            return new GuiCalcinator(entityPlayer.inventory, tileEntityCalcinator);
        }
        else if (id == GUIs.ALUDEL.ordinal()) {
            TileEntityAludel tileEntityAludel = (TileEntityAludel) world.getTileEntity(blockPos);
            return new GuiAludel(entityPlayer.inventory, tileEntityAludel);
        }
        else if (id == GUIs.RESEARCH_STATION.ordinal()) {
            TileEntityResearchStation tileEntityResearchStation = (TileEntityResearchStation) world.getTileEntity(blockPos);
            return new GuiResearchStation(entityPlayer.inventory, tileEntityResearchStation);
        }
        else if (id == GUIs.AUGMENTATION_TABLE.ordinal()) {
            TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) world.getTileEntity(blockPos);
            return new GuiAugmentationTable(entityPlayer.inventory, tileEntityAugmentationTable);
        }
        else if (id == GUIs.TRANSMUTATION_TABLET.ordinal()) {
            TileEntityTransmutationTablet tileEntityTransmutationTablet = (TileEntityTransmutationTablet) world.getTileEntity(blockPos);
            return new GuiTransmutationTablet(entityPlayer.inventory, tileEntityTransmutationTablet);
        }

        return null;
    }
}
