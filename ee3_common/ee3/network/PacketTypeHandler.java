package ee3.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import ee3.lib.Reference;

import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;


public enum PacketTypeHandler {
	KEY(KeyPressedPacket.class),
	TILE(TileEntityPacket.class),
	PEDESTAL(PedestalPacket.class);
	
	private Class<? extends EEPacket> clazz;

	PacketTypeHandler(Class<? extends EEPacket> clazz) {
		this.clazz=clazz;
	}
	
	// Called from PacketHandler.onPacketData
	public static EEPacket buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		EEPacket pkt = null;
		try {
			pkt = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		pkt.readPopulate(dis);
		return pkt;
	}
	
	// Called from code to get a packet of a specific type
	public static EEPacket buildPacket(PacketTypeHandler type) {
		EEPacket pkt = null;
		try {
			pkt = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pkt;
	}

	public static Packet populatePacket(EEPacket packet) {
		byte[] data = packet.populate();
		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = Reference.CHANNEL_NAME;
		pkt.data = data;
		pkt.length = data.length;
		pkt.isChunkDataPacket = packet.isChunkDataPacket;
		return pkt;
	}
}
