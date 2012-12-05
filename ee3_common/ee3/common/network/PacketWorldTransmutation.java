package ee3.common.network;

public class PacketWorldTransmutation extends PacketEE {

    public int originX, originY, originZ;
    public int rangeX, rangeY, rangeZ;
    public int targetID, targetMeta;
    
    public PacketWorldTransmutation() {

        super(PacketTypeHandler.TILE, true);
    }

}
