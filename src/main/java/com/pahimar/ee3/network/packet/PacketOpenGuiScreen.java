package com.pahimar.ee3.network.packet;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.network.PacketTypeHandler;
import cpw.mods.fml.common.network.Player;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketOpenGuiScreen extends PacketEE
{
    public byte guiId;

    public PacketOpenGuiScreen()
    {
        super(PacketTypeHandler.OPEN_GUI_SCREEN, false);
    }

    public PacketOpenGuiScreen(byte guiId)
    {
        super(PacketTypeHandler.OPEN_GUI_SCREEN, false);
        this.guiId = guiId;
    }

    @Override
    public void writeData(DataOutputStream data) throws IOException
    {
        data.writeByte(guiId);
    }

    @Override
    public void readData(DataInputStream data) throws IOException
    {
        guiId = data.readByte();
    }

    @Override
    public void execute(INetworkManager manager, Player player)
    {
        EntityPlayer thePlayer = (EntityPlayer) player;
        thePlayer.openGui(EquivalentExchange3.instance, guiId, thePlayer.getEntityWorld(), (int)thePlayer.posX, (int)thePlayer.posY, (int)thePlayer.posZ);
    }
}
