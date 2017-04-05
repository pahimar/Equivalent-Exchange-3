package com.pahimar.ee.network.message;

import com.pahimar.ee.EquivalentExchange;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSingleParticleEvent implements IMessage {

    private EnumParticleTypes particleType;
    private double xCoord, yCoord, zCoord, xVelocity, yVelocity, zVelocity;

    public MessageSingleParticleEvent() {
    }

    public MessageSingleParticleEvent(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity) {

        this.particleType = particleType;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {

        int particleNameLength = byteBuf.readInt();
        if (particleNameLength > 0) {
            this.particleType = EnumParticleTypes.getByName(new String(byteBuf.readBytes(particleNameLength).array()));
        }
        this.xCoord = byteBuf.readDouble();
        this.yCoord = byteBuf.readDouble();
        this.zCoord = byteBuf.readDouble();
        this.xVelocity = byteBuf.readDouble();
        this.yVelocity = byteBuf.readDouble();
        this.zVelocity = byteBuf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {

        if (particleType != null) {
            byteBuf.writeInt(particleType.getParticleName().length());
            byteBuf.writeBytes(particleType.getParticleName().getBytes());
        }
        else {
            byteBuf.writeInt(0);
        }
        byteBuf.writeDouble(xCoord);
        byteBuf.writeDouble(yCoord);
        byteBuf.writeDouble(zCoord);
        byteBuf.writeDouble(xVelocity);
        byteBuf.writeDouble(yVelocity);
        byteBuf.writeDouble(zVelocity);
    }

    public static class MessageHandler implements IMessageHandler<MessageSingleParticleEvent, IMessage> {

        @Override
        public IMessage onMessage(MessageSingleParticleEvent message, MessageContext ctx) {
            EquivalentExchange.proxy.spawnParticle(message.particleType, message.xCoord, message.yCoord, message.zCoord, message.xVelocity, message.yVelocity, message.zVelocity);
            return null;
        }
    }
}
