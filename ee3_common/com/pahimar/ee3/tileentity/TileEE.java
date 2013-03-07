package com.pahimar.ee3.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.tileentity.TileEntity;

import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketTileUpdate;

/**
 * TileEE
 * 
 * General tile entity for EE3 related tile entities
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
    	
    	this.state = 0;
    	this.owner = "";
    	this.customName = "";
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
        
        return ((owner != null) && (owner.length() > 0));
    }

    public void setOwner(String owner) {

        this.owner = owner;
    }
    
    public boolean hasCustomName() {
        
        return ((this.customName != null) && (this.customName.length() > 0));
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

    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);
        
        if (nbtTagCompound.hasKey(Strings.NBT_TE_STATE_KEY)) {
            this.state = nbtTagCompound.getShort(Strings.NBT_TE_STATE_KEY);
        }
        
        if (nbtTagCompound.hasKey(Strings.NBT_TE_OWNER_KEY)) {
            this.owner = nbtTagCompound.getString(Strings.NBT_TE_OWNER_KEY);
        }
        
        if (nbtTagCompound.hasKey(Strings.NBT_TE_CUSTOM_NAME)) {
            this.customName = nbtTagCompound.getString(Strings.NBT_TE_CUSTOM_NAME);
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {

        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setShort(Strings.NBT_TE_STATE_KEY, this.state);
        
        if (hasOwner()) {
            nbtTagCompound.setString(Strings.NBT_TE_OWNER_KEY, this.owner);
        }
        
        if (this.hasCustomName()) {
            nbtTagCompound.setString(Strings.NBT_TE_CUSTOM_NAME, this.customName);
        }
    }
    
    @Override
    public Packet getDescriptionPacket() {

    	return PacketTypeHandler.populatePacket(new PacketTileUpdate(this.xCoord, this.yCoord, this.zCoord, this.state, this.owner, this.customName));
    }

}
