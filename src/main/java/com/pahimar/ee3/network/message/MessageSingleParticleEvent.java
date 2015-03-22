package com.pahimar.ee3.network.message;

import com.pahimar.ee3.EquivalentExchange3;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageSingleParticleEvent implements IMessage, IMessageHandler<MessageSingleParticleEvent, IMessage>
{
    private String particleName;
    private double xCoord, yCoord, zCoord;
    private double xVelocity, yVelocity, zVelocity;

    public MessageSingleParticleEvent()
    {

    }

    public MessageSingleParticleEvent(String particleName, double xCoord, double yCoord, double zCoord, double xVelocity, double yVelocity, double zVelocity)
    {
        this.particleName = particleName;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
    }

    @Override
    public void fromBytes(ByteBuf byteBuf)
    {
        int particleNameLength = byteBuf.readInt();
        this.particleName = new String(byteBuf.readBytes(particleNameLength).array());
        this.xCoord = byteBuf.readDouble();
        this.yCoord = byteBuf.readDouble();
        this.zCoord = byteBuf.readDouble();
        this.xVelocity = byteBuf.readDouble();
        this.yVelocity = byteBuf.readDouble();
        this.zVelocity = byteBuf.readDouble();
    }

    @Override
    public void toBytes(ByteBuf byteBuf)
    {
        byteBuf.writeInt(particleName.length());
        byteBuf.writeBytes(particleName.getBytes());
        byteBuf.writeDouble(xCoord);
        byteBuf.writeDouble(yCoord);
        byteBuf.writeDouble(zCoord);
        byteBuf.writeDouble(xVelocity);
        byteBuf.writeDouble(yVelocity);
        byteBuf.writeDouble(zVelocity);
    }

    @Override
    public IMessage onMessage(MessageSingleParticleEvent message, MessageContext ctx)
    {
        EquivalentExchange3.proxy.spawnParticle(message.particleName, message.xCoord, message.yCoord, message.zCoord, message.xVelocity, message.yVelocity, message.zVelocity);
        return null;
    }
}
