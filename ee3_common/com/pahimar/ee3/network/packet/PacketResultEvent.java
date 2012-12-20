package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketResultEvent extends PacketEE {

    public boolean shouldChangeNextBlock;
    public boolean shouldDestroyCurrentlyHeldItem;

    public PacketResultEvent() {

        super(PacketTypeHandler.RESULT_EVENT, false);
    }

    public PacketResultEvent(boolean shouldChangeNextBlock, boolean shouldDestroyCurrentlyHeldItem) {

        super(PacketTypeHandler.RESULT_EVENT, false);
        this.shouldChangeNextBlock = shouldChangeNextBlock;
        this.shouldDestroyCurrentlyHeldItem = shouldDestroyCurrentlyHeldItem;
    }

    public void writeData(DataOutputStream data) throws IOException {

        data.writeBoolean(shouldChangeNextBlock);
        data.writeBoolean(shouldDestroyCurrentlyHeldItem);
    }

    public void readData(DataInputStream data) throws IOException {

        this.shouldChangeNextBlock = data.readBoolean();
        this.shouldDestroyCurrentlyHeldItem = data.readBoolean();
    }

    public void execute(INetworkManager manager, Player player) {

        System.out.println(shouldChangeNextBlock);
        if (shouldChangeNextBlock) {
            System.out.format("previous %d:%d, current %d:%d, target %d:%d\n", TransmutationHelper.previousBlockStack.itemID, TransmutationHelper.previousBlockStack.getItemDamage(), TransmutationHelper.currentBlockStack.itemID, TransmutationHelper.currentBlockStack.getItemDamage(), TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage());
            TransmutationHelper.targetBlockStack = TransmutationHelper.getNextBlock(TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage(), true);
            System.out.format("previous %d:%d, current %d:%d, target %d:%d\n", TransmutationHelper.previousBlockStack.itemID, TransmutationHelper.previousBlockStack.getItemDamage(), TransmutationHelper.currentBlockStack.itemID, TransmutationHelper.currentBlockStack.getItemDamage(), TransmutationHelper.targetBlockStack.itemID, TransmutationHelper.targetBlockStack.getItemDamage());
        }
    }
}
