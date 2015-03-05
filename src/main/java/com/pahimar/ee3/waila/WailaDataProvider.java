package com.pahimar.ee3.waila;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.*;
import mcp.mobius.waila.api.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.util.List;

public class WailaDataProvider implements IWailaDataProvider
{
    @Override
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return null;
    }

    @Override
    public List<String> getWailaHead(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        if (accessor.getTileEntity() instanceof TileEntityAludel)
        {
            if (accessor.getWorld().getTileEntity(accessor.getPosition().blockX, accessor.getPosition().blockY + 1, accessor.getPosition().blockZ) instanceof TileEntityGlassBell)
            {
                currentTip.set(0, String.format("%s%s", SpecialChars.WHITE, StatCollector.translateToLocal(Names.Containers.ALUDEL)));
            }
        }
        else if (accessor.getTileEntity() instanceof TileEntityGlassBell)
        {
            if (accessor.getWorld().getTileEntity(accessor.getPosition().blockX, accessor.getPosition().blockY - 1, accessor.getPosition().blockZ) instanceof TileEntityAludel)
            {
                currentTip.set(0, String.format("%s%s", SpecialChars.WHITE, StatCollector.translateToLocal(Names.Containers.ALUDEL)));
            }
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestSmall)
        {
            currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Messages.SMALL) + " " + StatCollector.translateToLocal(Names.Blocks.ALCHEMICAL_CHEST));
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestMedium)
        {
            currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Messages.MEDIUM) + " " + StatCollector.translateToLocal(Names.Blocks.ALCHEMICAL_CHEST));
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestLarge)
        {
            currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Messages.LARGE) + " " + StatCollector.translateToLocal(Names.Blocks.ALCHEMICAL_CHEST));
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemyArray)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) accessor.getTileEntity();

            if (tileEntityAlchemyArray.getAlchemyArray() != null)
            {
                currentTip.set(0, SpecialChars.WHITE + tileEntityAlchemyArray.getAlchemyArray().getDisplayName());
            }
            else
            {
                currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Names.Blocks.ALCHEMY_ARRAY));
            }
        }
        else if (accessor.getTileEntity() instanceof TileEntityDummyArray)
        {
            TileEntityDummyArray tileEntityDummyArray = (TileEntityDummyArray) accessor.getTileEntity();
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) accessor.getWorld().getTileEntity(tileEntityDummyArray.getTrueXCoord(), tileEntityDummyArray.getTrueYCoord(), tileEntityDummyArray.getTrueZCoord());

            if (tileEntityAlchemyArray.getAlchemyArray() != null)
            {
                currentTip.set(0, SpecialChars.WHITE + tileEntityAlchemyArray.getAlchemyArray().getDisplayName());
            }
            else
            {
                currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Names.Blocks.DUMMY_ARRAY));
            }
        }

        return currentTip;
    }

    @Override
    public List<String> getWailaBody(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currentTip;
    }

    @Override
    public List<String> getWailaTail(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config)
    {
        return currentTip;
    }

    public static void callbackRegister(IWailaRegistrar registrar)
    {
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityAludel.class);
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityGlassBell.class);
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityAlchemicalChestSmall.class);
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityAlchemicalChestMedium.class);
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityAlchemicalChestLarge.class);
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityAlchemyArray.class);
        registrar.registerHeadProvider(new WailaDataProvider(), TileEntityDummyArray.class);
    }
}
