package ee3.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.EquivalentExchange3;
import ee3.common.item.ITransmutationStone;
import ee3.common.lib.GuiIds;
import ee3.common.lib.ItemIds;
import ee3.common.lib.Reference;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.INetworkManager;
import net.minecraft.src.Packet250CustomPayload;

/**
 * PacketKeyPressed
 * 
 * Packet specifically for notifying the server of client key pressed events
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class PacketKeyPressed extends PacketEE {

	public String key;
	
	public PacketKeyPressed() {
		super(PacketTypeHandler.KEY, false);
	}
	
	public PacketKeyPressed(String key) {
		super(PacketTypeHandler.KEY, false);
		this.key = key;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(key);
	}
	
	public void readData(DataInputStream data) throws IOException {
		this.key = data.readUTF();
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void execute(INetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;

		if ((this.key.equals(Reference.KEYBINDING_EXTRA)) && (thePlayer.getCurrentEquippedItem().getItem() instanceof ITransmutationStone)) {
			thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_CRAFTING, thePlayer.worldObj, (int)thePlayer.posX, (int)thePlayer.posY, (int)thePlayer.posZ);
		}
	}
}
