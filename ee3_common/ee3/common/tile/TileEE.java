package ee3.common.tile;

import ee3.common.lib.Reference;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;

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
	
	private byte direction;
	private short state;
	private String owner;

	public byte getDirection() {
		return direction;
	}
	
	public void setDirection(byte direction) {
		this.direction = direction;
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
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public boolean isUseableByPlayer(EntityPlayer player) {
		return owner.equals(player.username);
	}
	
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        
        direction = nbtTagCompound.getByte(Reference.TE_GEN_DIRECTION_NBT_TAG_LABEL);
        state = nbtTagCompound.getShort(Reference.TE_GEN_STATE_NBT_TAG_LABEL);
        owner = nbtTagCompound.getString(Reference.TE_GEN_OWNER_NBT_TAG_LABEL);
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        
        nbtTagCompound.setByte(Reference.TE_GEN_DIRECTION_NBT_TAG_LABEL, direction);
        nbtTagCompound.setShort(Reference.TE_GEN_STATE_NBT_TAG_LABEL, state);
        if(owner != null && owner != "") {
        	nbtTagCompound.setString(Reference.TE_GEN_OWNER_NBT_TAG_LABEL, owner);
        }
    }
    
}
