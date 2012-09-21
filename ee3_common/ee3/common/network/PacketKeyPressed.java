package ee3.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import net.minecraft.src.NetworkManager;

public class PacketKeyPressed extends PacketEE {

	public int key;
	
	public PacketKeyPressed() {
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
	
	public void execute(NetworkManager manager, Player player) {
		// TODO: Stuff here
	}
}
