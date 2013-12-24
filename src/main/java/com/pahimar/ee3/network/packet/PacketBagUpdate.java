package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.*;
import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.common.network.Player;

public class PacketBagUpdate extends PacketEE {
    NBTTagCompound newBagData;
    public PacketBagUpdate(NBTTagCompound newBagData)
    {
        super(PacketTypeHandler.BAG_UPDATE, false);
        this.newBagData = newBagData;
    }
    public PacketBagUpdate()
    {
        super(PacketTypeHandler.BAG_UPDATE, false);
    }
    
    public void readData(DataInputStream data) throws IOException
    {
        newBagData = (NBTTagCompound) NBTBase.readNamedTag(data);
    }
    
    public void writeData(DataOutputStream dos) throws IOException
    {
        NBTBase.writeNamedTag(this.newBagData, dos);
    }
    
    public void execute(INetworkManager network, Player p)
    {
        EntityPlayerMP player = (EntityPlayerMP)p;
        // TODO Check to make sure the player hasn't tried to cheat
        player.inventory.getCurrentItem().setTagCompound(newBagData);
    }
}
