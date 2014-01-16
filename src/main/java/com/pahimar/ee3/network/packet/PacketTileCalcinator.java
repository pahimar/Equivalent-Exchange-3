package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.network.PacketTypeHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.network.INetworkManager;
import net.minecraftforge.common.ForgeDirection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketTileCalcinator extends PacketEE
{
    public int x, y, z;
    public byte orientation;
    public byte state;
    public String customName;
    public byte leftStackSize, leftStackMeta, rightStackSize, rightStackMeta;

    public PacketTileCalcinator()
    {
        super(PacketTypeHandler.TILE_CALCINATOR, true);
    }

    public PacketTileCalcinator(int x, int y, int z, ForgeDirection orientation, byte state, String customName, byte leftStackSize, byte leftStackMeta, byte rightStackSize, byte rightStackMeta)
    {
        super(PacketTypeHandler.TILE_CALCINATOR, true);
        this.x = x;
        this.y = y;
        this.z = z;
        this.orientation = (byte) orientation.ordinal();
        this.state = state;
        this.customName = customName;
        this.leftStackSize = leftStackSize;
        this.leftStackMeta = leftStackMeta;
        this.rightStackSize = rightStackSize;
        this.rightStackMeta = rightStackMeta;
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeInt(x);
        data.writeInt(y);
        data.writeInt(z);
        data.writeByte(orientation);
        data.writeByte(state);
        data.writeUTF(customName);
        data.writeByte(leftStackSize);
        data.writeByte(leftStackMeta);
        data.writeByte(rightStackSize);
        data.writeByte(rightStackMeta);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        x = data.readInt();
        y = data.readInt();
        z = data.readInt();
        orientation = data.readByte();
        state = data.readByte();
        customName = data.readUTF();
        leftStackSize = data.readByte();
        leftStackMeta = data.readByte();
        rightStackSize = data.readByte();
        rightStackMeta = data.readByte();
    }

    @Override
    public void execute(INetworkManager manager, Player player)
    {
        EquivalentExchange3.proxy.handleTileCalcinatorPacket(x, y, z, ForgeDirection.getOrientation(orientation), state, customName, leftStackSize, leftStackMeta, rightStackSize, rightStackMeta);
    }
}
