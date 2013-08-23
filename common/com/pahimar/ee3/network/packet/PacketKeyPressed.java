package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

/**
 * Equivalent-Exchange-3
 * 
 * PacketKeyPressed
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

    @Override
    public void readData(DataInputStream data) throws IOException {

        key = data.readUTF();
    }

    @Override
    public void execute(INetworkManager manager, Player player) {

        EntityPlayer thePlayer = (EntityPlayer) player;

        if (thePlayer.getCurrentEquippedItem() != null && thePlayer.getCurrentEquippedItem().getItem() instanceof IKeyBound) {
            ((IKeyBound) thePlayer.getCurrentEquippedItem().getItem()).doKeyBindingAction(thePlayer, thePlayer.getCurrentEquippedItem(), key);
        }
    }
}
