package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

/**
 * PacketTileUpdate
 * 
 * Packet specifically for updating/synching tile entities between client and
 * server
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketTileUpdate extends PacketEE {

    public int x, y, z;
    public short state;
    public String owner;
    public String customName;

    public PacketTileUpdate() {

        super(PacketTypeHandler.TILE, true);
    }
    
    public PacketTileUpdate(int x, int y, int z, short state, String owner, String customName) {
    	
    	super(PacketTypeHandler.TILE, true);
    	this.x = x;
    	this.y = y;
    	this.z = z;
    	this.state = state;
    	this.owner = owner;
    	this.customName = customName;
    }

    public void writeData(DataOutputStream data) throws IOException {

        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeShort(state);
        data.writeUTF(owner);
        data.writeUTF(customName);
    }

    public void readData(DataInputStream data) throws IOException {

        this.x = data.readInt();
        this.y = data.readInt();
        this.z = data.readInt();
        this.state = data.readShort();
        this.owner = data.readUTF();
        this.customName = data.readUTF();
    }

    public void execute(INetworkManager manager, Player player) {

        EquivalentExchange3.proxy.handleTileEntityPacket(this.x, this.y, this.z, this.state, this.owner, this.customName);
    }

}
