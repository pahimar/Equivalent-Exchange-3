package com.pahimar.ee3.array;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Sounds;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TransmutationAlchemyArray extends AlchemyArray
{
    public TransmutationAlchemyArray()
    {
        super(Textures.AlchemyArray.TRANSMUTATION_ALCHEMY_ARRAY, Names.AlchemyArrays.TRANSMUTATION_ALCHEMY_ARRAY);
    }

    @Override
    public void onArrayActivated(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            if (world.getTileEntity(arrayX, arrayY, arrayZ) instanceof TileEntityAlchemyArray)
            {
                TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(arrayX, arrayY, arrayZ);
                if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.UP && tileEntityAlchemyArray.getSize() == 2 && areBlocksValidForTransmutationTablet(world, arrayX, arrayY, arrayZ))
                {
                    world.setBlock(arrayX - 1, arrayY - 1, arrayZ - 1, ModBlocks.ashInfusedStoneSlab, 1, 3);
                    world.setBlock(arrayX, arrayY - 1, arrayZ - 1, ModBlocks.ashInfusedStoneSlab, 2, 3);
                    world.setBlock(arrayX + 1, arrayY - 1, arrayZ - 1, ModBlocks.ashInfusedStoneSlab, 3, 3);

                    world.setBlock(arrayX - 1, arrayY - 1, arrayZ, ModBlocks.ashInfusedStoneSlab, 4, 3);
                    world.setBlock(arrayX, arrayY - 1, arrayZ, ModBlocks.transmutationTablet, 0, 3);
                    world.setBlock(arrayX + 1, arrayY - 1, arrayZ, ModBlocks.ashInfusedStoneSlab, 5, 3);

                    world.setBlock(arrayX - 1, arrayY - 1, arrayZ + 1, ModBlocks.ashInfusedStoneSlab, 6, 3);
                    world.setBlock(arrayX, arrayY - 1, arrayZ + 1, ModBlocks.ashInfusedStoneSlab, 7, 3);
                    world.setBlock(arrayX + 1, arrayY - 1, arrayZ + 1, ModBlocks.ashInfusedStoneSlab, 8, 3);

                    if (world.getTileEntity(arrayX, arrayY - 1, arrayZ) instanceof TileEntityTransmutationTablet)
                    {
                        ((TileEntityTransmutationTablet) world.getTileEntity(arrayX, arrayY - 1, arrayZ)).setOrientation(tileEntityAlchemyArray.getOrientation());
                    }

                    EquivalentExchange3.proxy.playSound(Sounds.TRANSMUTE, arrayX, arrayY, arrayZ, 1f, 1f);
                }
            }
        }
    }

    private boolean areBlocksValidForTransmutationTablet(World world, int arrayX, int arrayY, int arrayZ)
    {
        boolean areBlocksValid = true;

        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if (world.getBlock(arrayX + i, arrayY - 1, arrayZ + j) != ModBlocks.ashInfusedStone)
                {
                    areBlocksValid = false;
                }
            }
        }

        return areBlocksValid;
    }
}
