package ee3.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.forge.packets.ForgePacket;

/**
 * TODO Class Description 
 * @author pahimar, cpw
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class EEPacket {

	protected PacketTypeHandler packetType;
	protected boolean isChunkDataPacket;
	
	public EEPacket(PacketTypeHandler type, boolean isChunkDataPacket) {
		this.packetType = type;
		this.isChunkDataPacket = isChunkDataPacket;
	}
	
	public byte[] populate() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		try {
			dos.writeByte(packetType.ordinal());
			this.writeData(dos);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return bos.toByteArray(); 
	}
	
	public void readPopulate(DataInputStream data) {
		try {
			this.readData(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void execute(NetworkManager network) { }
	public void writeData(DataOutputStream data) throws IOException { }	
	public void readData(DataInputStream data) throws IOException { }	
	public void setKey(int key) { }
	public void setCoords(int x, int y, int z) { }	
	public void setOrientation(byte direction) { }
	public void setPlayerName(String player) { }
	public void setItem(int itemId) { }
	public void setState(boolean activated) { }
}
