package com.pahimar.ee3.waila;

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
                currentTip.set(0, String.format("%s%s", SpecialChars.WHITE, StatCollector.translateToLocal("container.ee3:aludel")));
            }
        }
        else if (accessor.getTileEntity() instanceof TileEntityGlassBell)
        {
            if (accessor.getWorld().getTileEntity(accessor.getPosition().blockX, accessor.getPosition().blockY - 1, accessor.getPosition().blockZ) instanceof TileEntityAludel)
            {
                currentTip.set(0, String.format("%s%s", SpecialChars.WHITE, StatCollector.translateToLocal("container.ee3:aludel")));
            }
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestSmall)
        {
            currentTip.set(0, SpecialChars.WHITE + "Small Alchemical Chest"); // TODO: Localize
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestMedium)
        {
            currentTip.set(0, SpecialChars.WHITE + "Medium Alchemical Chest"); // TODO: Localize
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestLarge)
        {
            currentTip.set(0, SpecialChars.WHITE + "Large Alchemical Chest"); // TODO: Localize
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
    }
}
