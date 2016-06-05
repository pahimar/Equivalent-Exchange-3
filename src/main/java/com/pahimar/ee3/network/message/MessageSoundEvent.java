package com.pahimar.ee3.network.message;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.handler.ConfigurationHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

public class MessageSoundEvent implements IMessage {

    private long mostSigUUID, leastSigUUID;
    private String soundName;
    private float xCoord, yCoord, zCoord;
    private float volume, pitch;

    public MessageSoundEvent() {
    }

    public MessageSoundEvent(EntityPlayer entityPlayer, String soundName, float volume, float pitch) {
        this.mostSigUUID = entityPlayer.getUniqueID().getMostSignificantBits();
        this.leastSigUUID = entityPlayer.getUniqueID().getLeastSignificantBits();
        this.soundName = soundName;
        this.xCoord = (float) entityPlayer.posX;
        this.yCoord = (float) entityPlayer.posY;
        this.zCoord = (float) entityPlayer.posZ;
        this.volume = volume;
        this.pitch = pitch;
    }

    public MessageSoundEvent(String soundName, float xCoord, float yCoord, float zCoord, float volume, float pitch) {
        this.mostSigUUID = 0;
        this.leastSigUUID = 0;
        this.soundName = soundName;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.volume = volume;
        this.pitch = pitch;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        this.mostSigUUID = byteBuf.readLong();
        this.leastSigUUID = byteBuf.readLong();
        int soundNameLength = byteBuf.readInt();
        this.soundName = new String(byteBuf.readBytes(soundNameLength).array());
        this.xCoord = byteBuf.readFloat();
        this.yCoord = byteBuf.readFloat();
        this.zCoord = byteBuf.readFloat();
        this.volume = byteBuf.readFloat();
        this.pitch = byteBuf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeLong(mostSigUUID);
        byteBuf.writeLong(leastSigUUID);
        byteBuf.writeInt(soundName.length());
        byteBuf.writeBytes(soundName.getBytes());
        byteBuf.writeFloat(xCoord);
        byteBuf.writeFloat(yCoord);
        byteBuf.writeFloat(zCoord);
        byteBuf.writeFloat(volume);
        byteBuf.writeFloat(pitch);
    }

    public static class MessageHandler implements IMessageHandler<MessageSoundEvent, IMessage> {

        @Override
        public IMessage onMessage(MessageSoundEvent event, MessageContext context) {

            UUID originUUID = new UUID(event.mostSigUUID, event.leastSigUUID);

            if (ConfigurationHandler.Settings.soundMode.equalsIgnoreCase("All")) {
                EquivalentExchange3.proxy.playSound(event.soundName, event.xCoord, event.yCoord, event.zCoord, event.volume, event.pitch);
            }
            else if (ConfigurationHandler.Settings.soundMode.equalsIgnoreCase("Self")) {
                if (FMLClientHandler.instance().getClient().thePlayer.getUniqueID().equals(originUUID)) {
                    EquivalentExchange3.proxy.playSound(event.soundName, event.xCoord, event.yCoord, event.zCoord, event.volume, event.pitch);
                }
            }

            return null;
        }
    }
}
