package ee3.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.mod_EE3;

public class PedestalPacket extends EEPacket {

	int x, y, z;
	public int itemId;
	public boolean activated;
	
	public PedestalPacket() {
		super(PacketTypeHandler.PEDESTAL, true);
	}
	
	public void setCoords(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void setItem(int itemId) {
		this.itemId = itemId;
	}
	public void setState(boolean activated) {
		this.activated = activated;
	}
	
	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeInt(x);
		data.writeInt(y);
		data.writeInt(z);
		data.writeInt(itemId);
		data.writeBoolean(activated);
	}
	
	public void readData(DataInputStream data) throws IOException {
		this.x = data.readInt();
		this.y = data.readInt();
		this.z = data.readInt();
		this.itemId = data.readInt();
		this.activated = data.readBoolean();
	}
	
	public void execute(NetworkManager network) { 
		mod_EE3.proxy.handlePedestalPacket(x, y, z, itemId, activated);
	}
}
