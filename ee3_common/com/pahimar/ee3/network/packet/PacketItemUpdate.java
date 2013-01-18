package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.lib.ItemUpdateTypes;
import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketItemUpdate extends PacketEE {

    public byte slot;
    public byte updateType;

    public PacketItemUpdate() {

        super(PacketTypeHandler.ITEM_UPDATE, false);
    }

    public PacketItemUpdate(byte slot, byte isDestroyed) {

        super(PacketTypeHandler.ITEM_UPDATE, false);
        this.slot = slot;
        this.updateType = isDestroyed;
    }

    public void writeData(DataOutputStream data) throws IOException {

        data.writeByte(slot);
        data.writeByte(updateType);
    }

    public void readData(DataInputStream data) throws IOException {

        this.slot = data.readByte();
        this.updateType = data.readByte();
    }

    public void execute(INetworkManager manager, Player player) {

        EntityPlayer thePlayer = (EntityPlayer) player;
        ItemStack destroyedStack = thePlayer.inventory.getStackInSlot(slot);

        if (updateType == ItemUpdateTypes.DESTROYED) {
            thePlayer.renderBrokenItemStack(destroyedStack);
        }
    }
}
