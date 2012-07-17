package ee3.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ee3.mod_EE3;

import net.minecraft.src.NetworkManager;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TileEntityPacket extends EEPacket {

	public int x, y, z;
	public byte direction;
	String player;
	
	public TileEntityPacket() {
		super(PacketTypeHandler.TILE, true);
	}
	
	public void setCoords(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setOrientation(byte direction) {
		this.direction = direction;
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
		data.writeUTF(player);
	}
	
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.direction = data.readByte();
		this.player = data.readUTF();
	}
	
	public void execute(NetworkManager network) {
		mod_EE3.proxy.handleTEPacket(x, y, z, direction, player);
	}
}
