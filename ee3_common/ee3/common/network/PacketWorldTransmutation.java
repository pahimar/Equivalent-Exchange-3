package ee3.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.INetworkManager;
import cpw.mods.fml.common.network.Player;

public class PacketWorldTransmutation extends PacketEE {

    public int originX, originY, originZ;
    public byte rangeX, rangeY, rangeZ;
    public int targetID, targetMeta;
    // TODO: Decide if this will be a general "action" packet or specific
    
    public PacketWorldTransmutation() {

        super(PacketTypeHandler.TILE, false);
    }
    
    public void setOrigin(int originX, int originY, int originZ) {
        this.originX = originX;
        this.originY = originY;
        this.originZ = originZ;
    }
    
    public void setRange(byte rangeX, byte rangeY, byte rangeZ) {
        this.rangeX = rangeX;
        this.rangeY = rangeY;
        this.rangeZ = rangeZ;
    }
    
    public void setTransmutationTarget(int targetID, int targetMeta) {
        this.targetID = targetID;
        this.targetMeta = targetMeta;
    }
    
    public void writeData(DataOutputStream data) throws IOException {
        data.writeInt(originX);
        data.writeInt(originY);
        data.writeInt(originZ);
        data.writeByte(rangeX);
        data.writeByte(rangeY);
        data.writeByte(rangeZ);
        data.writeInt(targetID);
        data.writeInt(targetMeta);
    }
    
    public void readData(DataInputStream data) throws IOException {
        this.originX = data.readInt();
        this.originY = data.readInt();
        this.originZ = data.readInt();
        this.rangeX = data.readByte();
        this.rangeY = data.readByte();
        this.rangeZ = data.readByte();
        this.targetID = data.readInt();
        this.targetMeta = data.readInt();
    }
    
    public void execute(INetworkManager manager, Player player) {
     // TODO: Stuff here
    }

}
