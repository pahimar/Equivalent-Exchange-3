package com.pahimar.ee3.util;

import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageSoundEvent;
import com.pahimar.ee3.reference.Sounds;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;

public class CommonSoundHelper
{
    public static void playSoundAt(EntityPlayer entityPlayer, String soundName, float volume, float pitch)
    {
        playSoundAt(entityPlayer, soundName, volume, pitch, 32d);
    }

    public static void playSoundAt(EntityPlayer entityPlayer, String soundName, float volume, float pitch, double range)
    {
        PacketHandler.INSTANCE.sendToAllAround(new MessageSoundEvent(entityPlayer, soundName, volume, pitch), new NetworkRegistry.TargetPoint(entityPlayer.worldObj.provider.dimensionId, entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, range));
    }

    public static void playChalkSoundAt(EntityPlayer entityPlayer)
    {
        playSoundAt(entityPlayer, Sounds.Chalk.getRandomChalkSound(), 1f, 1f);
    }
}
