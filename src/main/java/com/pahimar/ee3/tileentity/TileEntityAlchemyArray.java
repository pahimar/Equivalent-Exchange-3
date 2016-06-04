package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.api.array.AlchemyArray;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageTileEntityAlchemyArray;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAlchemyArray extends TileEntityEE implements ISidedInventory
{
    private AlchemyArray alchemyArray;
    private ForgeDirection rotation;
    private int size;
    private int tickCount;

    public TileEntityAlchemyArray()
    {
        super();
        rotation = ForgeDirection.UNKNOWN;
        size = 0;
        alchemyArray = null;
    }

    public AlchemyArray getAlchemyArray()
    {
        return alchemyArray;
    }

    public void setAlchemyArray(AlchemyArray alchemyArray)
    {
        setAlchemyArray(alchemyArray, 1);
    }

    public void setAlchemyArray(AlchemyArray alchemyArray, int size)
    {
        this.alchemyArray = alchemyArray;
        this.size = size;
    }

    public ForgeDirection getRotation()
    {
        return rotation;
    }

    public void setRotation(ForgeDirection rotation)
    {
        this.rotation = rotation;
    }

    public void setRotation(int rotation, int facing)
    {
        if (this.orientation == ForgeDirection.UP)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }

        }
        else if (this.orientation == ForgeDirection.DOWN)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }
        }
        else if (this.orientation == ForgeDirection.NORTH)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.UP;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.DOWN;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }
        }
        else if (this.orientation == ForgeDirection.SOUTH)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.DOWN;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.EAST;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.UP;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.WEST;
            }
        }
        else if (this.orientation == ForgeDirection.EAST)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.UP;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.DOWN;
            }
        }
        else if (this.orientation == ForgeDirection.WEST)
        {
            if ((rotation + facing) % 4 == 0)
            {
                this.rotation = ForgeDirection.NORTH;
            }
            else if ((rotation + facing) % 4 == 1)
            {
                this.rotation = ForgeDirection.DOWN;
            }
            else if ((rotation + facing) % 4 == 2)
            {
                this.rotation = ForgeDirection.SOUTH;
            }
            else if ((rotation + facing) % 4 == 3)
            {
                this.rotation = ForgeDirection.UP;
            }
        }
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        if (this.orientation == ForgeDirection.UP || this.orientation == ForgeDirection.DOWN)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - size, yCoord - 1, zCoord - size, xCoord + size, yCoord + 1, zCoord + size);
        }
        else if (this.orientation == ForgeDirection.NORTH || this.orientation == ForgeDirection.SOUTH)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - size, yCoord - size, zCoord - 1, xCoord + size, yCoord + size, zCoord + 1);
        }
        else if (this.orientation == ForgeDirection.EAST || this.orientation == ForgeDirection.WEST)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - size, zCoord - size, xCoord + 1, yCoord + size, zCoord + size);
        }

        return super.getRenderBoundingBox();
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            ++tickCount;
            if (tickCount % 100 == 0)
            {
                if (!areDummyBlocksValid())
                {
                    this.invalidate();
                    worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                }
            }

            onUpdate(worldObj, xCoord, yCoord, zCoord, tickCount);
        }
    }

    public int getLightLevel()
    {
        if (alchemyArray != null)
        {
            return alchemyArray.getLightLevel();
        }

        return 0;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        onBlockPlacedBy(world, x, y, z, this.xCoord, this.yCoord, this.zCoord, entityLiving, itemStack);
    }

    public void onBlockPlacedBy(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onArrayPlacedBy(world, eventX, eventY, eventZ, arrayX, arrayY, arrayZ, entityLiving, itemStack);
        }
    }

    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        onBlockActivated(world, x, y, z, this.xCoord, this.yCoord, this.zCoord, entityPlayer, sideHit, hitX, hitY, hitZ);
    }

    public void onBlockActivated(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onArrayActivated(world, eventX, eventY, eventZ, arrayX, arrayY, arrayZ, entityPlayer, sideHit, hitX, hitY, hitZ);
        }
    }

    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityPlayer)
    {
        onBlockClicked(world, x, y, z, this.xCoord, this.yCoord, this.zCoord, entityPlayer);
    }

    public void onBlockClicked(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onArrayClicked(world, eventX, eventY, eventZ, arrayX, arrayY, arrayZ, entityPlayer);
        }
    }

    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
        onBlockDestroyedByExplosion(world, x, y, z, this.xCoord, this.yCoord, this.zCoord, explosion);
    }

    public void onBlockDestroyedByExplosion(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Explosion explosion)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onArrayDestroyedByExplosion(world, eventX, eventY, eventZ, arrayX, arrayY, arrayZ, explosion);
        }
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metaData)
    {
        onBlockDestroyedByPlayer(world, x, y, z, this.xCoord, this.yCoord, this.zCoord, metaData);
    }

    public void onBlockDestroyedByPlayer(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, int metaData)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onArrayDestroyedByPlayer(world, eventX, eventY, eventZ, arrayX, arrayY, arrayZ, metaData);
        }
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        onEntityCollidedWithBlock(world, x, y, z, this.xCoord, this.yCoord, this.zCoord, entity);
    }

    public void onEntityCollidedWithBlock(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Entity entity)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onEntityCollidedWithArray(world, eventX, eventY, eventZ, arrayX, arrayY, arrayZ, entity);
        }
    }

    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        onFallenUpon(world, x, y, z, this.xCoord, this.yCoord, this.zCoord, entity, fallDistance);
    }

    public void onFallenUpon(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Entity entity, float fallDistance)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onArrayFallenUpon(world, eventX, eventY, eventZ, arrayX, arrayY, arrayZ, entity, fallDistance);
        }
    }

    public void onUpdate(World world, int x, int y, int z, int tickCount)
    {
        if (alchemyArray != null)
        {
            alchemyArray.onUpdate(world, x, y, z, tickCount);
        }
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return Network.INSTANCE.getPacketFrom(new MessageTileEntityAlchemyArray(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        rotation = ForgeDirection.getOrientation(nbtTagCompound.getInteger("rotation"));
        size = nbtTagCompound.getInteger("size");
        NBTTagCompound alchemyArrayTagCompound = nbtTagCompound.getCompoundTag("alchemyArray");

        if (!alchemyArrayTagCompound.hasNoTags())
        {
            alchemyArray = AlchemyArray.readArrayFromNBT(alchemyArrayTagCompound);
            try
            {
                Class clazz = Class.forName(alchemyArray.getClassName(), true, Loader.instance().getModClassLoader());
                alchemyArray = (AlchemyArray) clazz.getConstructor().newInstance();
                alchemyArray.readFromNBT(alchemyArrayTagCompound);
            }
            catch (Exception e)
            {
                this.invalidate();
                if (worldObj != null)
                {
                    worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                }
            }
        }
        else
        {
            this.invalidate();
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("rotation", rotation.ordinal());
        nbtTagCompound.setInteger("size", size);
        NBTTagCompound alchemyArrayTagCompound = new NBTTagCompound();
        if (alchemyArray != null)
        {
            alchemyArray.writeToNBT(alchemyArrayTagCompound);
        }
        nbtTagCompound.setTag("alchemyArray", alchemyArrayTagCompound);
    }

    private boolean areDummyBlocksValid()
    {
        boolean validDummyBlocks = true;
        int coordOffset = this.size / 2;

        if (this.orientation == ForgeDirection.UP || this.orientation == ForgeDirection.DOWN)
        {
            for (int i = this.xCoord - coordOffset; i <= this.xCoord + coordOffset; i++)
            {
                for (int j = this.zCoord - coordOffset; j <= this.zCoord + coordOffset; j++)
                {
                    if ((i != this.xCoord || j != this.zCoord) && !isValidDummyBlock(i, this.yCoord, j))
                    {
                        validDummyBlocks = false;
                    }
                }
            }
        }
        else if (this.orientation == ForgeDirection.NORTH || this.orientation == ForgeDirection.SOUTH)
        {
            for (int i = this.xCoord - coordOffset; i <= this.xCoord + coordOffset; i++)
            {
                for (int j = this.yCoord - coordOffset; j <= this.yCoord + coordOffset; j++)
                {
                    if ((i != this.xCoord || j != this.yCoord) && !isValidDummyBlock(i, j, this.zCoord))
                    {
                        validDummyBlocks = false;
                    }
                }
            }
        }
        else if (this.orientation == ForgeDirection.EAST || this.orientation == ForgeDirection.WEST)
        {
            for (int i = this.yCoord - coordOffset; i <= this.yCoord + coordOffset; i++)
            {
                for (int j = this.zCoord - coordOffset; j <= this.zCoord + coordOffset; j++)
                {
                    if ((i != this.yCoord || j != this.zCoord) && !isValidDummyBlock(this.xCoord, i, j))
                    {
                        validDummyBlocks = false;
                    }
                }
            }
        }

        return validDummyBlocks;
    }

    private boolean isValidDummyBlock(int x, int y, int z)
    {
        if (!this.worldObj.isRemote)
        {
            if (this.worldObj.getTileEntity(x, y, z) instanceof TileEntityDummyArray)
            {
                TileEntityDummyArray tileEntityDummyArray = (TileEntityDummyArray) this.worldObj.getTileEntity(x, y, z);

                return tileEntityDummyArray.getOrientation() == this.orientation &&
                        tileEntityDummyArray.getTrueXCoord() == this.xCoord &&
                        tileEntityDummyArray.getTrueYCoord() == this.yCoord &&
                        tileEntityDummyArray.getTrueZCoord() == this.zCoord;
            }
        }

        return false;
    }

    @Override
    public int getSizeInventory()
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).getSizeInventory();
        }

        return 0;
    }

    @Override
    public ItemStack getStackInSlot(int slotIndex)
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).getStackInSlot(slotIndex);
        }

        return null;
    }

    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount)
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).decrStackSize(slotIndex, decrementAmount);
        }

        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slotIndex)
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).getStackInSlotOnClosing(slotIndex);
        }

        return null;
    }

    @Override
    public void setInventorySlotContents(int slotIndex, ItemStack itemStack)
    {
        if (alchemyArray instanceof IInventory)
        {
            ((IInventory) alchemyArray).setInventorySlotContents(slotIndex, itemStack);
        }
    }

    @Override
    public String getInventoryName()
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).getInventoryName();
        }

        return null;
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).hasCustomInventoryName();
        }

        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).getInventoryStackLimit();
        }

        return 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).isUseableByPlayer(entityplayer);
        }

        return false;
    }

    @Override
    public void openInventory()
    {
        if (alchemyArray instanceof IInventory)
        {
            ((IInventory) alchemyArray).openInventory();
        }
    }

    @Override
    public void closeInventory()
    {
        if (alchemyArray instanceof IInventory)
        {
            ((IInventory) alchemyArray).closeInventory();
        }
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack)
    {
        if (alchemyArray instanceof IInventory)
        {
            return ((IInventory) alchemyArray).isItemValidForSlot(slotIndex, itemStack);
        }

        return false;
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int slotIndex)
    {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int slotIndex, ItemStack itemStack, int side)
    {
        return false;
    }

    @Override
    public boolean canExtractItem(int slotIndex, ItemStack itemStack, int side)
    {
        return false;
    }
}
