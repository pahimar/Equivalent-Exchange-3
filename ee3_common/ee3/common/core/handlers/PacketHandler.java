package ee3.common.core.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import ee3.common.network.PacketEE;
import ee3.common.network.PacketTypeHandler;

/**
 * PacketHandler
 * 
 * Handles the dispatch and receipt of packets for the mod
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketHandler implements IPacketHandler {

	/***
	 * Handles Packet250CustomPayload packets that are registered to an Equivalent Exchange 3 network channel
	 * @param manager The NetworkManager associated with the current platform (client/server)
	 * @param packet The Packet250CustomPayload that was received
	 * @param player The Player associated with the packet
	 */
    @Override
    public void onPacketData(NetworkManager manager, Packet250CustomPayload packet, Player player) {
    	// Build a PacketEE object from the data contained within the Packet250CustomPayload packet
    	PacketEE packetEE = PacketTypeHandler.buildPacket(packet.data);
    	
    	// Execute the appropriate actions based on the PacketEE type
    	packetEE.execute(manager, player);
    }

}
