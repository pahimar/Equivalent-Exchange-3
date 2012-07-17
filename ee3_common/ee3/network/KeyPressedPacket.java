package ee3.network;

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
public class KeyPressedPacket extends EEPacket {

	public int key;

	public KeyPressedPacket() {
		super(PacketTypeHandler.KEY, false);
	}
	
	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(key);
	}
	
	public void readData(DataInputStream data) throws IOException {
		this.key = data.readInt();
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	// Stub
	public void execute(NetworkManager network) {
		mod_EE3.proxy.handleControl(network, key);
	}
}
