package com.pahimar.ee3.item;

import com.pahimar.ee3.api.array.AlchemyArray;
import com.pahimar.ee3.array.AlchemyArrayRegistry;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageChalkSettings;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.util.EntityUtils;
import com.pahimar.ee3.util.IKeyBound;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemChalk extends ItemEE implements IKeyBound
{
    public ItemChalk()
    {
        super();
        this.setUnlocalizedName(Names.Items.CHALK);
        this.setMaxDamage(80);
        this.canRepair = true;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (!world.isRemote)
        {
            if (side == 0)
            {
                --y;
            }
            else if (side == 1)
            {
                ++y;
            }
            else if (side == 2)
            {
                --z;
            }
            else if (side == 3)
            {
                ++z;
            }
            else if (side == 4)
            {
                --x;
            }
            else if (side == 5)
            {
                ++x;
            }

            if (canPlaceAlchemyArray(itemStack, entityPlayer, world, x, y, z, side))
            {
                placeAlchemyArray(itemStack, entityPlayer, world, x, y, z, side);
                return true;
            }
        }

        return false;
    }

    private boolean canPlaceAlchemyArray(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side)
    {
        if (!world.isRemote)
        {
            NBTTagCompound playerCustomData = EntityUtils.getCustomEntityData(entityPlayer);
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(playerCustomData);
            AlchemyArray alchemyArray = AlchemyArrayRegistry.getInstance().getAlchemyArray(chalkSettings.getIndex());

            int coordOffset = chalkSettings.getSize() - 1;
            ForgeDirection orientation = ForgeDirection.getOrientation(side);
            boolean canPlaceAlchemyArray = ModBlocks.alchemyArray.canPlaceBlockOnSide(world, x, y, z, side);

            int chargeLevel = ((chalkSettings.getSize() - 1) * 2) + 1;

            if (itemStack.getItemDamage() == itemStack.getMaxDamage() && (chargeLevel * chargeLevel) * alchemyArray.getChalkCostPerBlock() == 1)
            {
                canPlaceAlchemyArray = true;
            }
            else if (itemStack.getMaxDamage() - itemStack.getItemDamage() + 1 < (chargeLevel * chargeLevel) * alchemyArray.getChalkCostPerBlock())
            {
                canPlaceAlchemyArray = false;
            }

            if (canPlaceAlchemyArray)
            {
                if (orientation == ForgeDirection.UP || orientation == ForgeDirection.DOWN)
                {
                    for (int i = x - coordOffset; i <= x + coordOffset; i++)
                    {
                        for (int j = z - coordOffset; j <= z + coordOffset; j++)
                        {
                            if ((i != x || j != z) && (!ModBlocks.dummyArray.canPlaceBlockOnSide(world, i, y, j, side) || world.getTileEntity(i, y, j) instanceof TileEntityDummyArray))
                            {
                                canPlaceAlchemyArray = false;
                            }
                        }
                    }
                }
                else if (orientation == ForgeDirection.NORTH || orientation == ForgeDirection.SOUTH)
                {
                    for (int i = x - coordOffset; i <= x + coordOffset; i++)
                    {
                        for (int j = y - coordOffset; j <= y + coordOffset; j++)
                        {
                            if ((i != x || j != y) && (!ModBlocks.dummyArray.canPlaceBlockOnSide(world, i, j, z, side) || world.getTileEntity(i, j, z) instanceof TileEntityDummyArray))
                            {
                                canPlaceAlchemyArray = false;
                            }
                        }
                    }
                }
                else if (orientation == ForgeDirection.EAST || orientation == ForgeDirection.WEST)
                {
                    for (int i = y - coordOffset; i <= y + coordOffset; i++)
                    {
                        for (int j = z - coordOffset; j <= z + coordOffset; j++)
                        {
                            if ((i != y || j != z) && (!ModBlocks.dummyArray.canPlaceBlockOnSide(world, x, i, j, side) || world.getTileEntity(x, i, j) instanceof TileEntityDummyArray))
                            {
                                canPlaceAlchemyArray = false;
                            }
                        }
                    }
                }
            }

            return canPlaceAlchemyArray;
        }

        return true;
    }

    private void placeAlchemyArray(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side)
    {
        if (!world.isRemote)
        {
            NBTTagCompound playerCustomData = EntityUtils.getCustomEntityData(entityPlayer);
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(playerCustomData);
            int coordOffset = chalkSettings.getSize() - 1;
            ForgeDirection orientation = ForgeDirection.getOrientation(side);
            boolean canPlaceAlchemyArray = ModBlocks.alchemyArray.canPlaceBlockOnSide(world, x, y, z, side);

            placeBlockAt(entityPlayer, itemStack, world, x, y, z, ModBlocks.alchemyArray, side);

            if (canPlaceAlchemyArray)
            {
                if (orientation == ForgeDirection.UP || orientation == ForgeDirection.DOWN)
                {
                    for (int i = x - coordOffset; i <= x + coordOffset; i++)
                    {
                        for (int j = z - coordOffset; j <= z + coordOffset; j++)
                        {
                            if (i != x || j != z)
                            {
                                placeBlockAt(entityPlayer, itemStack, world, i, y, j, ModBlocks.dummyArray, side);
                                if (world.getTileEntity(i, y, j) instanceof TileEntityDummyArray)
                                {
                                    ((TileEntityDummyArray) world.getTileEntity(i, y, j)).setTrueCoords(x, y, z);
                                }
                            }
                        }
                    }
                }
                else if (orientation == ForgeDirection.NORTH || orientation == ForgeDirection.SOUTH)
                {
                    for (int i = x - coordOffset; i <= x + coordOffset; i++)
                    {
                        for (int j = y - coordOffset; j <= y + coordOffset; j++)
                        {
                            if (i != x || j != y)
                            {
                                placeBlockAt(entityPlayer, itemStack, world, i, j, z, ModBlocks.dummyArray, side);
                                {
                                    ((TileEntityDummyArray) world.getTileEntity(i, j, z)).setTrueCoords(x, y, z);
                                }
                            }
                        }
                    }
                }
                else if (orientation == ForgeDirection.EAST || orientation == ForgeDirection.WEST)
                {
                    for (int i = y - coordOffset; i <= y + coordOffset; i++)
                    {
                        for (int j = z - coordOffset; j <= z + coordOffset; j++)
                        {
                            if (i != y || j != z)
                            {
                                placeBlockAt(entityPlayer, itemStack, world, x, i, j, ModBlocks.dummyArray, side);
                                {
                                    ((TileEntityDummyArray) world.getTileEntity(x, i, j)).setTrueCoords(x, y, z);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Called to actually place the block, after the location is determined and all permission checks have been made.
     *
     * @param itemStack    The item stack that was used to place the block. This can be changed inside the method.
     * @param entityPlayer The entityPlayer who is placing the block. Can be null if the block is not being placed by a entityPlayer.
     */
    private boolean placeBlockAt(EntityPlayer entityPlayer, ItemStack itemStack, World world, int x, int y, int z, Block block, int metadata)
    {
        if (!world.isRemote)
        {
            if (!world.setBlock(x, y, z, block, metadata, 3))
            {
                return false;
            }

            if (world.getBlock(x, y, z) == block)
            {
                NBTTagCompound playerCustomData = EntityUtils.getCustomEntityData(entityPlayer);
                ChalkSettings chalkSettings = new ChalkSettings();
                chalkSettings.readFromNBT(playerCustomData);
                AlchemyArray alchemyArray = AlchemyArrayRegistry.getInstance().getAlchemyArray(chalkSettings.getIndex());

                if (alchemyArray != null)
                {
                    itemStack.damageItem(alchemyArray.getChalkCostPerBlock(), entityPlayer);
                    block.onBlockPlacedBy(world, x, y, z, entityPlayer, itemStack);
                    block.onPostBlockPlaced(world, x, y, z, metadata);
                }
            }
        }

        return true;
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key)
    {
        if (key != Key.UNKNOWN)
        {
            NBTTagCompound playerCustomData = EntityUtils.getCustomEntityData(entityPlayer);
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(playerCustomData);

            if (key == Key.CHARGE)
            {
                if (!entityPlayer.isSneaking())
                {
                    chalkSettings.incrementSize();
                }
                else
                {
                    chalkSettings.decrementSize();
                }
            }
            else if (key == Key.TOGGLE)
            {
                if (!entityPlayer.isSneaking())
                {
                    chalkSettings.incrementIndex();
                }
                else
                {
                    chalkSettings.decrementIndex();
                }
            }
            else if (key == Key.RELEASE)
            {
                if (!entityPlayer.isSneaking())
                {
                    chalkSettings.rotateClockwise();
                }
                else
                {
                    chalkSettings.rotateCounterClockwise();
                }
            }

            chalkSettings.writeToNBT(playerCustomData);
            EntityUtils.saveCustomEntityData(entityPlayer, playerCustomData);
            Network.INSTANCE.sendTo(new MessageChalkSettings(chalkSettings), (EntityPlayerMP) entityPlayer);
        }
    }
}