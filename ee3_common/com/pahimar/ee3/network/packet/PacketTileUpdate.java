package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;
import net.minecraftforge.common.ForgeDirection;

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
    public byte direction;
    public short state;
    public String player;

    public PacketTileUpdate() {

        super(PacketTypeHandler.TILE, true);
    }

    public void setCoords(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void writeData(DataOutputStream data) throws IOException {

        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeByte(direction);
        data.writeShort(state);
        data.writeUTF(player);
    }

    public void readData(DataInputStream data) throws IOException {

        this.x = data.readInt();
        this.y = data.readInt();
        this.z = data.readInt();
        this.direction = data.readByte();
        this.state = data.readShort();
        this.player = data.readUTF();
    }

    public void execute(INetworkManager manager, Player player) {

        // TODO: Stuff here
    }

}
