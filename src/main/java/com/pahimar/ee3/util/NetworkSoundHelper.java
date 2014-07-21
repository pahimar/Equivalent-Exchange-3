package com.pahimar.ee3.util;

import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSoundEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;

public class NetworkSoundHelper
{
    public static void playSoundAt(EntityPlayer entityPlayer, String soundName, float volume, float pitch)
    {
        playSoundAt(entityPlayer, soundName, volume, pitch, 32d);
    }

    public static void playSoundAt(EntityPlayer entityPlayer, String soundName, float volume, float pitch, double range)
    {
        PacketHandler.INSTANCE.sendToAllAround(new MessageSoundEvent(entityPlayer, soundName, volume, pitch), new NetworkRegistry.TargetPoint(entityPlayer.worldObj.provider.dimensionId, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, range));
    }
}
