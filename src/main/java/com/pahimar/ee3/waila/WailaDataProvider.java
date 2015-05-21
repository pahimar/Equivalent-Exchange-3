package com.pahimar.ee3.waila;

import com.pahimar.ee3.block.BlockAshInfusedStoneSlab;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.tileentity.*;
import mcp.mobius.waila.api.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

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
            currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Messages.Tooltips.SMALL) + " " + StatCollector.translateToLocal(Names.Blocks.ALCHEMICAL_CHEST));
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestMedium)
        {
            currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Messages.Tooltips.MEDIUM) + " " + StatCollector.translateToLocal(Names.Blocks.ALCHEMICAL_CHEST));
        }
        else if (accessor.getTileEntity() instanceof TileEntityAlchemicalChestLarge)
        {
            currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Messages.Tooltips.LARGE) + " " + StatCollector.translateToLocal(Names.Blocks.ALCHEMICAL_CHEST));
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

            if (tileEntityAlchemyArray != null && tileEntityAlchemyArray.getAlchemyArray() != null && tileEntityAlchemyArray.getAlchemyArray().getDisplayName() != null)
            {
                currentTip.set(0, SpecialChars.WHITE + tileEntityAlchemyArray.getAlchemyArray().getDisplayName());
            }
            else
            {
                currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(Names.Blocks.DUMMY_ARRAY));
            }
        }
        else if (accessor.getBlock() instanceof BlockAshInfusedStoneSlab)
        {
            int metaData = accessor.getMetadata();
            int x = accessor.getPosition().blockX;
            int y = accessor.getPosition().blockY;
            int z = accessor.getPosition().blockZ;
            String unLocalizedBlockName = accessor.getWorld().getBlock(x, y, z).getUnlocalizedName() + ".name";

            if (metaData == 1)
            {
                x++;
                z++;
            }
            else if (metaData == 2)
            {
                z++;
            }
            else if (metaData == 3)
            {
                x--;
                z++;
            }
            else if (metaData == 4)
            {
                x++;
            }
            else if (metaData == 5)
            {
                x--;
            }
            else if (metaData == 6)
            {
                x++;
                z--;
            }
            else if (metaData == 7)
            {
                z--;
            }
            else if (metaData == 8)
            {
                x--;
                z--;
            }

            if (metaData != 0 && accessor.getWorld().getTileEntity(x, y, z) instanceof TileEntityTransmutationTablet)
            {
                currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(accessor.getWorld().getBlock(x, y, z).getUnlocalizedName() + ".name"));
            }
            else
            {
                currentTip.set(0, SpecialChars.WHITE + StatCollector.translateToLocal(unLocalizedBlockName));
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

    @Override
    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z)
    {
        return null;
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
        registrar.registerHeadProvider(new WailaDataProvider(), BlockAshInfusedStoneSlab.class);
    }
}
