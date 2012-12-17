package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.item.ITransmutationStone;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.network.PacketTypeHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.LanguageRegistry;

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

		if ((thePlayer.getCurrentEquippedItem() != null) && (thePlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound)) {
		    ((IKeyBound) thePlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(thePlayer, thePlayer.getCurrentEquippedItem(), this.key);
		}
	}
}
