package ee3.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import net.minecraft.src.NetworkManager;
import net.minecraftforge.common.ForgeDirection;

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
	
	public void setDirection(ForgeDirection direction) {
		// TODO: Convert from ForgeDirection to the appropriate byte value
	}
	
	public void setDirection(byte direction) {
		this.direction = direction;
	}
	
	public void setState(short state) {
		this.state = state;
	}
	
	public void setPlayerName(String player) {
		this.player = player;
	}
	
	@Override
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

	public void execute(NetworkManager manager, Player player) {
		// TODO: Stuff here
	}
	
}
