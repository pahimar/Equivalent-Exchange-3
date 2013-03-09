package com.pahimar.ee3.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketTileUpdate;

/**
 * Equivalent-Exchange-3
 * 
 * TileEE
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TileEE extends TileEntity {

    private short state;
    private String owner;
    private String customName;

    public TileEE() {

        state = 0;
        owner = "";
        customName = "";
    }

    public short getState() {

        return state;
    }

    public void setState(short state) {

        this.state = state;
    }

    public String getOwner() {

        return owner;
    }

    public boolean hasOwner() {

        return owner != null && owner.length() > 0;
    }

    public void setOwner(String owner) {

        this.owner = owner;
    }

    public boolean hasCustomName() {

        return customName != null && customName.length() > 0;
    }

    public String getCustomName() {

        return customName;
    }

    public void setCustomName(String customName) {

        this.customName = customName;
    }

    public boolean isUseableByPlayer(EntityPlayer player) {

        return owner.equals(player.username);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey(Strings.NBT_TE_STATE_KEY)) {
            state = nbtTagCompound.getShort(Strings.NBT_TE_STATE_KEY);
        }

        if (nbtTagCompound.hasKey(Strings.NBT_TE_OWNER_KEY)) {
            owner = nbtTagCompound.getString(Strings.NBT_TE_OWNER_KEY);
        }

        if (nbtTagCompound.hasKey(Strings.NBT_TE_CUSTOM_NAME)) {
            customName = nbtTagCompound.getString(Strings.NBT_TE_CUSTOM_NAME);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setShort(Strings.NBT_TE_STATE_KEY, state);

        if (hasOwner()) {
            nbtTagCompound.setString(Strings.NBT_TE_OWNER_KEY, owner);
        }

        if (this.hasCustomName()) {
            nbtTagCompound.setString(Strings.NBT_TE_CUSTOM_NAME, customName);
        }
    }

    @Override
    public Packet getDescriptionPacket() {

        return PacketTypeHandler.populatePacket(new PacketTileUpdate(xCoord, yCoord, zCoord, state, owner, customName));
    }

}
