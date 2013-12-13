package com.pahimar.ee3.network;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.pahimar.ee3.network.packet.PacketEE;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * Equivalent-Exchange-3
 * 
 * PacketHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketHandler implements IPacketHandler {

    /***
     * Handles Packet250CustomPayload packets that are registered to an
     * Equivalent Exchange 3 network channel
     * 
     * @param manager
     *            The NetworkManager associated with the current platform
     *            (client/server)
     * @param packet
     *            The Packet250CustomPayload that was received
     * @param player
     *            The Player associated with the packet
     */
    @Override
    public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {

        // Build a PacketEE object from the data contained within the Packet250CustomPayload packet
        PacketEE packetEE = PacketTypeHandler.buildPacket(packet.data);

        // Execute the appropriate actions based on the PacketEE type
        packetEE.execute(manager, player);
    }

}
