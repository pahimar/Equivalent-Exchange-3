package com.pahimar.ee3.network.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.lib.Commands;
import com.pahimar.ee3.network.PacketTypeHandler;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.Player;

public class PacketSoundEvent extends PacketEE {

    public String playerName;
    public String soundName;
    public double x, y, z;
    public float volume, pitch;

    public PacketSoundEvent() {

        super(PacketTypeHandler.SOUND_EVENT, false);
    }

    public PacketSoundEvent(String playerName, String soundName, double x, double y, double z, float volume, float pitch) {

        super(PacketTypeHandler.SOUND_EVENT, false);
        this.playerName = playerName;
        this.soundName = soundName;
        this.x = x;
        this.y = y;
        this.z = z;
        this.volume = volume;
        this.pitch = pitch;
    }

    public void writeData(DataOutputStream data) throws IOException {

        data.writeUTF(playerName);
        data.writeUTF(soundName);
        data.writeDouble(x);
        data.writeDouble(y);
        data.writeDouble(z);
        data.writeFloat(volume);
        data.writeFloat(pitch);
    }

    public void readData(DataInputStream data) throws IOException {

        this.playerName = data.readUTF();
        this.soundName = data.readUTF();
        this.x = data.readDouble();
        this.y = data.readDouble();
        this.z = data.readDouble();
        this.volume = data.readFloat();
        this.pitch = data.readFloat();
    }

    public void execute(INetworkManager manager, Player player) {

        EntityPlayer thePlayer = (EntityPlayer) player;

        if (ConfigurationSettings.ENABLE_SOUNDS.equalsIgnoreCase(Commands.ALL)) {
            FMLClientHandler.instance().getClient().sndManager.playSound(soundName, (float) x, (float) y, (float) z, volume, pitch);
        }
        else if (ConfigurationSettings.ENABLE_SOUNDS.equalsIgnoreCase(Commands.SELF)) {
            if (thePlayer.username.equalsIgnoreCase(playerName)) {
                FMLClientHandler.instance().getClient().sndManager.playSound(soundName, (float) x, (float) y, (float) z, volume, pitch);
            }
        }
    }
}
