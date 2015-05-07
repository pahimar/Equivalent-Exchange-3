package com.pahimar.ee3.array;

import com.pahimar.ee3.api.array.AlchemyArray;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Particles;
import com.pahimar.ee3.reference.Sounds;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityTransmutationTablet;
import com.pahimar.ee3.util.CommonParticleHelper;
import com.pahimar.ee3.util.CommonSoundHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Random;

public class TransmutationAlchemyArray extends AlchemyArray implements IInventory
{
    private ItemStack[] inventory = new ItemStack[25];

    public TransmutationAlchemyArray()
    {
        super(Textures.AlchemyArray.TRANSMUTATION_ALCHEMY_ARRAY, Names.AlchemyArrays.TRANSMUTATION_ALCHEMY_ARRAY);
    }

    @Override
    public void onArrayActivated(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        // TODO Come back to this later to resolve inventory issues
        //        if (!entityPlayer.isSneaking())
        //        {
        //            entityPlayer.openGui(EquivalentExchange3.instance, GUIs.TRANSMUTATION_ARRAY.ordinal(), world, arrayX, arrayY, arrayZ);
        //            return;
        //        }

        if (!world.isRemote && entityPlayer.isSneaking())
        {
            boolean successFlag = false;

            if (world.getTileEntity(arrayX, arrayY, arrayZ) instanceof TileEntityAlchemyArray)
            {
                TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) world.getTileEntity(arrayX, arrayY, arrayZ);

                // First, see if we can make a Transmutation Tablet
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

                    ejectInventory(world, arrayX, arrayY, arrayZ);

                    successFlag = true;
                }

                if (successFlag)
                {
                    CommonSoundHelper.playSoundAtLocation(world.provider.dimensionId, arrayX, arrayY, arrayZ, Sounds.TRANSMUTE, 1f, 1f);

                    if (tileEntityAlchemyArray.getSize() == 1)
                    {
                        CommonParticleHelper.spawnParticleAtLocation(Particles.LARGE_SMOKE, world.provider.dimensionId, arrayX + 0.5d, arrayY, arrayZ + 0.5d, 0d, 0.1d, 0d);
                    }
                    else if (tileEntityAlchemyArray.getSize() == 2)
                    {
                        for (int i = -1; i <= 1; i++)
                        {
                            for (int j = -1; j <= 1; j++)
                            {
                                CommonParticleHelper.spawnParticleAtLocation(Particles.LARGE_SMOKE, world.provider.dimensionId, arrayX + i + 0.5d, arrayY, arrayZ + j + 0.5d, 0d, 0.1d, 0d);
                            }
                        }
                    }
                    else if (tileEntityAlchemyArray.getSize() == 3)
                    {
                        for (int i = -2; i <= 2; i++)
                        {
                            for (int j = -2; j <= 2; j++)
                            {
                                CommonParticleHelper.spawnParticleAtLocation(Particles.LARGE_SMOKE, world.provider.dimensionId, arrayX + i + 0.5d, arrayY, arrayZ + j + 0.5d, 0d, 0.1d, 0d);
                            }
                        }
                    }
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

    @Override
    public int getSizeInventory()
    {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        if (slotIndex < getSizeInventory())
        {
            return inventory[slotIndex];
        }

        return null;
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            if (itemStack.stackSize <= decrementAmount)
            {
                setInventorySlotContents(slotIndex, null);
            }
            else
            {
                itemStack = itemStack.splitStack(decrementAmount);
                if (itemStack.stackSize == 0)
                {
                    setInventorySlotContents(slotIndex, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        ItemStack itemStack = getStackInSlot(slotIndex);
        if (itemStack != null)
        {
            setInventorySlotContents(slotIndex, null);
        }
        return itemStack;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        if (slotIndex < getSizeInventory())
        {
            inventory[slotIndex] = itemStack;
            if (itemStack != null && itemStack.stackSize > getInventoryStackLimit())
            {
                itemStack.stackSize = getInventoryStackLimit();
            }
        }
    }

    @Override
    public String getInventoryName()
    {
        return Names.AlchemyArrays.TRANSMUTATION_ALCHEMY_ARRAY;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public void markDirty()
    {
        // NOOP
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return true;
    }

    @Override
    public void openInventory()
    {
        // NOOP
    }

    @Override
    public void closeInventory()
    {
        // NOOP
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        if (slotIndex < getSizeInventory())
        {
            return itemStack.getItem() instanceof ItemBlock;
        }

        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList(Names.NBT.ITEMS, 10);
        inventory = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < inventory.length)
            {
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (int currentIndex = 0; currentIndex < getSizeInventory(); ++currentIndex)
        {
            if (getStackInSlot(currentIndex) != null)
            {
                NBTTagCompound tagCompound = new NBTTagCompound();
                tagCompound.setByte("Slot", (byte) currentIndex);
                getStackInSlot(currentIndex).writeToNBT(tagCompound);
                tagList.appendTag(tagCompound);
            }
        }
        nbtTagCompound.setTag(Names.NBT.ITEMS, tagList);
    }

    protected void ejectInventory(World world, int x, int y, int z)
    {
        for (int i = 0; i < getSizeInventory(); i++)
        {
            ItemStack itemStack = getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0)
            {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, itemStack.copy());

                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
