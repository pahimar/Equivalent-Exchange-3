package com.pahimar.ee3.tileentity;

import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageTileEntityEE;
import com.pahimar.ee3.reference.Names;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.UsernameCache;

import java.util.UUID;

public abstract class TileEntityEE extends TileEntity implements ITickable {

    protected EnumFacing facing;
    protected byte state;
    protected String customName;
    protected UUID ownerUUID;

    public TileEntityEE() {
        facing = EnumFacing.SOUTH;
        state = 0;
        customName = "";
        ownerUUID = null;
    }

    public EnumFacing getFacing() {
        return facing;
    }

    public void setFacing(EnumFacing orientation) {
        this.facing = orientation;
    }

    public void setFacing(int orientation) {
        this.facing = EnumFacing.getFront(orientation);
    }

    public short getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public UUID getOwnerUUID() {
        return ownerUUID;
    }

    public void setOwnerUUID(UUID ownerUUID) {
        this.ownerUUID = ownerUUID;
    }

    public String getOwnerName() {

        if (ownerUUID != null) {
            return UsernameCache.getLastKnownUsername(ownerUUID);
        }

        return "Unknown";
    }

    public void setOwner(EntityPlayer entityPlayer) {
        this.ownerUUID = entityPlayer.getPersistentID();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.FACING)) {
            this.facing = EnumFacing.getFront(nbtTagCompound.getByte(Names.NBT.FACING));
        }

        // Legacy from MC 1.7 and earlier
        if (nbtTagCompound.hasKey(Names.NBT.DIRECTION)) {
            this.facing = EnumFacing.getFront(nbtTagCompound.getByte(Names.NBT.DIRECTION));
        }

        if (nbtTagCompound.hasKey(Names.NBT.STATE)) {
            this.state = nbtTagCompound.getByte(Names.NBT.STATE);
        }

        if (nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME)) {
            this.customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
        }

        if (nbtTagCompound.hasKey(Names.NBT.OWNER_UUID_MOST_SIG) && nbtTagCompound.hasKey(Names.NBT.OWNER_UUID_LEAST_SIG)) {
            this.ownerUUID = new UUID(nbtTagCompound.getLong(Names.NBT.OWNER_UUID_MOST_SIG), nbtTagCompound.getLong(Names.NBT.OWNER_UUID_LEAST_SIG));
        }
    }

    // FIXME This is also not sure to be working
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setByte(Names.NBT.FACING, (byte) facing.ordinal());
        nbtTagCompound.setByte(Names.NBT.STATE, state);

        if (this.hasCustomName()) {
            nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, customName);
        }

        if (this.hasOwner()) {
            nbtTagCompound.setLong(Names.NBT.OWNER_UUID_MOST_SIG, ownerUUID.getMostSignificantBits());
            nbtTagCompound.setLong(Names.NBT.OWNER_UUID_LEAST_SIG, ownerUUID.getLeastSignificantBits());
        }
    }

    public boolean hasCustomName() {
        return customName != null && customName.length() > 0;
    }

    public boolean hasOwner() {
        return ownerUUID != null;
    }

    // FIXME No idea if this is correct
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return (SPacketUpdateTileEntity) Network.INSTANCE.getPacketFrom(new MessageTileEntityEE(this));
    }

    @Override
    public void update() {
        // NOOP
    }
}