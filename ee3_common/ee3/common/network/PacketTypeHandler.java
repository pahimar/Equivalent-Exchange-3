package ee3.common.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import ee3.common.lib.Reference;

import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;

public enum PacketTypeHandler {
	KEY(PacketKeyPressed.class),
	TILE(PacketTileUpdate.class);
	
	private Class<? extends PacketEE> clazz;
	
	PacketTypeHandler(Class<? extends PacketEE> clazz) {
		this.clazz = clazz;
	}
	
	public static PacketEE buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		PacketEE packet = null;
		
		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		packet.readPopulate(dis);
		
		return packet;
	}
	
	public static PacketEE buildPacket(PacketTypeHandler type) {
		PacketEE packet = null;
		
		try {
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		return packet;
	}
	
	public static Packet populatePacket(PacketEE packetEE) {
		byte[] data = packetEE.populate();
		
		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = packetEE.isChunkDataPacket;
		
		return packet250;
	}
}
