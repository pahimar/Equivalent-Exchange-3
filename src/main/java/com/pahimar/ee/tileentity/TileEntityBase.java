package com.pahimar.ee.tileentity;

import com.pahimar.ee.reference.Names;
import com.sun.istack.internal.Nullable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

import java.util.UUID;

public abstract class TileEntityBase extends TileEntity implements ITickable {

    protected String customName;
    protected UUID owner;

    public TileEntityBase() {
        customName = "";
        owner = null;
    }

    public boolean hasCustomName() {
        return customName != null && !customName.isEmpty();
    }

    @Nullable
    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    @Nullable
    public UUID getOwner() {
        return owner;
    }

    public void setOwner(UUID owner) {
        this.owner = owner;
    }

    public void setOwner(EntityPlayer entityPlayer) {
        if (entityPlayer != null) {
            setOwner(entityPlayer.getUniqueID());
        }
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return !isInvalid() && playerIn.getDistanceSq(pos.add(0.5D, 0.5D, 0.5D)) <= 64D;
    }

    @Override
    public void update() {
        // NO-OP
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Names.NBT.CUSTOM_NAME)) {
            this.customName = nbtTagCompound.getString(Names.NBT.CUSTOM_NAME);
        }

        if (nbtTagCompound.hasUniqueId(Names.NBT.OWNER)) {
            this.owner = nbtTagCompound.getUniqueId(Names.NBT.OWNER);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        if (this.hasCustomName()) {
            nbtTagCompound.setString(Names.NBT.CUSTOM_NAME, customName);
        }

        if (this.hasOwner()) {
            nbtTagCompound.setUniqueId(Names.NBT.OWNER, owner);
        }

        return nbtTagCompound;
    }
}