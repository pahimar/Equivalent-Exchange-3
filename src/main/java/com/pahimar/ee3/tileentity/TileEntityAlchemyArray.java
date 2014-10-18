package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.api.Glyph;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageTileEntityAlchemyArray;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityAlchemyArray extends TileEntityEE {
    private AlchemyArray alchemyArray;
    private ForgeDirection rotation;
    private int ticksSinceSync;

    public TileEntityAlchemyArray() {
        super();
        alchemyArray = new AlchemyArray();
        rotation = ForgeDirection.UNKNOWN;
    }

    public AlchemyArray getAlchemyArray() {
        return alchemyArray;
    }

    public boolean addGlyphToAlchemyArray(Glyph glyph) {
        return alchemyArray.addGlyph(glyph);
    }

    public boolean addGlyphToAlchemyArray(Glyph glyph, int size) {
        return addGlyphToAlchemyArray(new Glyph(glyph, size));
    }

    public ForgeDirection getRotation() {
        return rotation;
    }

    public void setRotation(int rotation, int facing) {
        if (this.orientation == ForgeDirection.UP) {
            if ((rotation + facing) % 4 == 0) {
                this.rotation = ForgeDirection.NORTH;
            } else if ((rotation + facing) % 4 == 1) {
                this.rotation = ForgeDirection.EAST;
            } else if ((rotation + facing) % 4 == 2) {
                this.rotation = ForgeDirection.SOUTH;
            } else if ((rotation + facing) % 4 == 3) {
                this.rotation = ForgeDirection.WEST;
            }

        } else if (this.orientation == ForgeDirection.DOWN) {
            if ((rotation + facing) % 4 == 0) {
                this.rotation = ForgeDirection.NORTH;
            } else if ((rotation + facing) % 4 == 1) {
                this.rotation = ForgeDirection.EAST;
            } else if ((rotation + facing) % 4 == 2) {
                this.rotation = ForgeDirection.SOUTH;
            } else if ((rotation + facing) % 4 == 3) {
                this.rotation = ForgeDirection.WEST;
            }
        } else if (this.orientation == ForgeDirection.NORTH) {
            if ((rotation + facing) % 4 == 0) {
                this.rotation = ForgeDirection.UP;
            } else if ((rotation + facing) % 4 == 1) {
                this.rotation = ForgeDirection.EAST;
            } else if ((rotation + facing) % 4 == 2) {
                this.rotation = ForgeDirection.DOWN;
            } else if ((rotation + facing) % 4 == 3) {
                this.rotation = ForgeDirection.WEST;
            }
        } else if (this.orientation == ForgeDirection.SOUTH) {
            if ((rotation + facing) % 4 == 0) {
                this.rotation = ForgeDirection.DOWN;
            } else if ((rotation + facing) % 4 == 1) {
                this.rotation = ForgeDirection.EAST;
            } else if ((rotation + facing) % 4 == 2) {
                this.rotation = ForgeDirection.UP;
            } else if ((rotation + facing) % 4 == 3) {
                this.rotation = ForgeDirection.WEST;
            }
        } else if (this.orientation == ForgeDirection.EAST) {
            if ((rotation + facing) % 4 == 0) {
                this.rotation = ForgeDirection.NORTH;
            } else if ((rotation + facing) % 4 == 1) {
                this.rotation = ForgeDirection.UP;
            } else if ((rotation + facing) % 4 == 2) {
                this.rotation = ForgeDirection.SOUTH;
            } else if ((rotation + facing) % 4 == 3) {
                this.rotation = ForgeDirection.DOWN;
            }
        } else if (this.orientation == ForgeDirection.WEST) {
            if ((rotation + facing) % 4 == 0) {
                this.rotation = ForgeDirection.NORTH;
            } else if ((rotation + facing) % 4 == 1) {
                this.rotation = ForgeDirection.DOWN;
            } else if ((rotation + facing) % 4 == 2) {
                this.rotation = ForgeDirection.SOUTH;
            } else if ((rotation + facing) % 4 == 3) {
                this.rotation = ForgeDirection.UP;
            }
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        if (this.orientation == ForgeDirection.UP || this.orientation == ForgeDirection.DOWN) {
            return AxisAlignedBB.getBoundingBox(xCoord - alchemyArray.getLargestGlyphSize(), yCoord - 1, zCoord - alchemyArray.getLargestGlyphSize(), xCoord + alchemyArray.getLargestGlyphSize(), yCoord + 1, zCoord + alchemyArray.getLargestGlyphSize());
        } else if (this.orientation == ForgeDirection.NORTH || this.orientation == ForgeDirection.SOUTH) {
            return AxisAlignedBB.getBoundingBox(xCoord - alchemyArray.getLargestGlyphSize(), yCoord - alchemyArray.getLargestGlyphSize(), zCoord - 1, xCoord + alchemyArray.getLargestGlyphSize(), yCoord + alchemyArray.getLargestGlyphSize(), zCoord + 1);
        } else if (this.orientation == ForgeDirection.EAST || this.orientation == ForgeDirection.WEST) {
            return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - alchemyArray.getLargestGlyphSize(), zCoord - alchemyArray.getLargestGlyphSize(), xCoord + 1, yCoord + alchemyArray.getLargestGlyphSize(), zCoord + alchemyArray.getLargestGlyphSize());
        }

        return super.getRenderBoundingBox();
    }

    @Override
    public Packet getDescriptionPacket() {
        return PacketHandler.INSTANCE.getPacketFrom(new MessageTileEntityAlchemyArray(this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        NBTTagCompound alchemyArrayTagCompound = nbtTagCompound.getCompoundTag("alchemyArray");
        alchemyArray = AlchemyArray.readAlchemyArrayFromNBT(alchemyArrayTagCompound);

        rotation = ForgeDirection.getOrientation(nbtTagCompound.getInteger("rotation"));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        NBTTagCompound alchemyArrayTagCompound = new NBTTagCompound();
        alchemyArray.writeToNBT(alchemyArrayTagCompound);

        nbtTagCompound.setInteger("rotation", rotation.ordinal());

        nbtTagCompound.setTag("alchemyArray", alchemyArrayTagCompound);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!worldObj.isRemote) {
            if (++ticksSinceSync % 100 == 0) {
                if (!areDummyBlocksValid()) {
                    this.invalidate();
                    worldObj.setBlockToAir(xCoord, yCoord, zCoord);
                }
            }
        }
    }

    private boolean areDummyBlocksValid() {
        boolean validDummyBlocks = true;
        int coordOffset = this.alchemyArray.getLargestGlyphSize() / 2;

        if (this.orientation == ForgeDirection.UP || this.orientation == ForgeDirection.DOWN) {
            for (int i = this.xCoord - coordOffset; i <= this.xCoord + coordOffset; i++) {
                for (int j = this.zCoord - coordOffset; j <= this.zCoord + coordOffset; j++) {
                    if ((i != this.xCoord || j != this.zCoord) && !isValidDummyBlock(i, this.yCoord, j)) {
                        validDummyBlocks = false;
                    }
                }
            }
        } else if (this.orientation == ForgeDirection.NORTH || this.orientation == ForgeDirection.SOUTH) {
            for (int i = this.xCoord - coordOffset; i <= this.xCoord + coordOffset; i++) {
                for (int j = this.yCoord - coordOffset; j <= this.yCoord + coordOffset; j++) {
                    if ((i != this.xCoord || j != this.yCoord) && !isValidDummyBlock(i, j, this.zCoord)) {
                        validDummyBlocks = false;
                    }
                }
            }
        } else if (this.orientation == ForgeDirection.EAST || this.orientation == ForgeDirection.WEST) {
            for (int i = this.yCoord - coordOffset; i <= this.yCoord + coordOffset; i++) {
                for (int j = this.zCoord - coordOffset; j <= this.zCoord + coordOffset; j++) {
                    if ((i != this.yCoord || j != this.zCoord) && !isValidDummyBlock(this.xCoord, i, j)) {
                        validDummyBlocks = false;
                    }
                }
            }
        }

        return validDummyBlocks;
    }

    private boolean isValidDummyBlock(int x, int y, int z) {
        if (!this.worldObj.isRemote) {
            if (this.worldObj.getTileEntity(x, y, z) instanceof TileEntityDummyArray) {
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
