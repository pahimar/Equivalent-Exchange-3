package com.pahimar.ee3.tileentity;

import com.google.common.collect.ImmutableSortedSet;
import com.pahimar.ee3.api.Glyph;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityAlchemyArray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class TileEntityAlchemyArray extends TileEntityEE
{
    private SortedSet<Glyph> glyphs;
    private int largestGlyphSize;
    private ForgeDirection rotation;
    private int ticksSinceSync;

    public TileEntityAlchemyArray()
    {
        super();
        glyphs = new TreeSet<Glyph>();
        largestGlyphSize = 0;
        rotation = ForgeDirection.UNKNOWN;
    }

    public Set<Glyph> getGlyphs()
    {
        return ImmutableSortedSet.copyOf(glyphs);
    }

    public boolean addGlyphToAlchemyArray(Glyph glyph)
    {
        if (!glyphs.contains(glyph))
        {
            if (glyph.getSize() > largestGlyphSize)
            {
                largestGlyphSize = glyph.getSize();
            }

            return glyphs.add(glyph);
        }

        return false;
    }

    public boolean addGlyphToAlchemyArray(Glyph glyph, int size)
    {
        return addGlyphToAlchemyArray(new Glyph(glyph, size));
    }

    public ForgeDirection getRotation()
    {
        return rotation;
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

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        if (this.orientation == ForgeDirection.UP || this.orientation == ForgeDirection.DOWN)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - largestGlyphSize, yCoord - 1, zCoord - largestGlyphSize, xCoord + largestGlyphSize, yCoord + 1, zCoord + largestGlyphSize);
        }
        else if (this.orientation == ForgeDirection.NORTH || this.orientation == ForgeDirection.SOUTH)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - largestGlyphSize, yCoord - largestGlyphSize, zCoord - 1, xCoord + largestGlyphSize, yCoord + largestGlyphSize, zCoord + 1);
        }
        else if (this.orientation == ForgeDirection.EAST || this.orientation == ForgeDirection.WEST)
        {
            return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - largestGlyphSize, zCoord - largestGlyphSize, xCoord + 1, yCoord + largestGlyphSize, zCoord + largestGlyphSize);
        }

        return super.getRenderBoundingBox();
    }

    @Override
    public Packet getDescriptionPacket()
    {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityAlchemyArray(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);

        largestGlyphSize = 0;
        rotation = ForgeDirection.getOrientation(nbtTagCompound.getInteger("rotation"));

        // Read in the ItemStacks in the inventory from NBT
        NBTTagList tagList = nbtTagCompound.getTagList("glyphs", 10);
        glyphs = new TreeSet<Glyph>();
        for (int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            Glyph glyph = Glyph.readGlyphFromNBT(tagCompound);
            glyphs.add(glyph);

            if (glyph.getSize() > largestGlyphSize)
            {
                largestGlyphSize = glyph.getSize();
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("rotation", rotation.ordinal());

        // Write the ItemStacks in the inventory to NBT
        NBTTagList tagList = new NBTTagList();
        for (Glyph glyph : glyphs)
        {
            NBTTagCompound tagCompound = new NBTTagCompound();
            glyph.writeToNBT(tagCompound);
            tagList.appendTag(tagCompound);
        }
        nbtTagCompound.setTag("glyphs", tagList);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            if (++ticksSinceSync % 100 == 0)
            {
                if (!areDummyBlocksValid())
                {
                    this.invalidate();
                    worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                }
            }
        }
    }

    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        // TODO: Perform the action for the registered alchemy array
    }

    private boolean areDummyBlocksValid()
    {
        boolean validDummyBlocks = true;
        int coordOffset = this.largestGlyphSize / 2;

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
}
