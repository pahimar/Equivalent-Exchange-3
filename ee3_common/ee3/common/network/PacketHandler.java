package ee3.common.network;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

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

	@Override
	public void onPacketData(NetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// TODO Auto-generated method stub
		
	}

}
