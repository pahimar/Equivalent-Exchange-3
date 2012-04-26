package net.minecraft.src.ee3.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet1Login;
import net.minecraft.src.mod_EE3;
import net.minecraft.src.forge.IConnectionHandler;
import net.minecraft.src.forge.IPacketHandler;
import net.minecraft.src.forge.MessageManager;

public class PacketHandler implements IPacketHandler, IConnectionHandler {

	@Override
	public void onConnect(NetworkManager network) {
	}

	@Override
	//Called when a connection is established 
	public void onLogin(NetworkManager network, Packet1Login login) {
		MessageManager.getInstance().registerChannel(network, this, mod_EE3.CHANNEL_NAME);
	}

	@Override
	//Called when a connection drops
	public void onDisconnect(NetworkManager network, String message, Object[] args) {
		MessageManager.getInstance().removeConnection(network);
	}
	
	@Override
	// Called when a Packet is received
	public void onPacketData(NetworkManager network, String channel, byte[] data) {
		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
		EEPacket packet = PacketTypeHandler.buildPacket(data);
		packet.execute(network);
	}
	
	// Called when a Packet is requested to be sent
	public static Packet getPacketForSending(EEPacket packet) {
		return PacketTypeHandler.populatePacket(packet);
	}
	
	public static EEPacket getPacket(PacketTypeHandler type) {
		EEPacket pkt = PacketTypeHandler.buildPacket(type);
		return pkt;
	}
}
