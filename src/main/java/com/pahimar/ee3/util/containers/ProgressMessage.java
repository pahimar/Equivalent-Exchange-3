package com.pahimar.ee3.util.containers;

import com.pahimar.ee3.util.LogHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;

import java.util.List;

/**
 * Originated from PR#881
 */
public class ProgressMessage<MessageType extends Enum<MessageType>> {

    Class<MessageType> enumClass;
    boolean incomplete;
    short messageId;
    int data;

    public ProgressMessage(Class<MessageType> enumClass, short messageId, int data) {

        this.enumClass = enumClass;
        this.incomplete = isInverted(messageId);
        this.messageId = incomplete ? invert(messageId) : messageId;
        this.data = data;
    }

    public ProgressMessage(Class<MessageType> enumClass, MessageType messageType, int data) {
        this(enumClass, (short)messageType.ordinal(), data);
    }

    public ProgressMessage(Class<MessageType> enumClass, MessageType messageType, float data) {
        this(enumClass, (short)messageType.ordinal(), Float.floatToIntBits(data));
    }

    public ProgressMessage<MessageType> complete(int data) {
        return new ProgressMessage<>(enumClass, messageId, this.data | (data << 16));
    }

    public short getId() {
        return messageId;
    }

    public MessageType getType() {
        return enumClass.getEnumConstants()[messageId];
    }

    public int getInt() {
        return data;
    }

    public short getShort() {
        return (short) data;
    }

    public float getFloat() {
        return Float.intBitsToFloat(data);
    }

    private boolean isInverted(short messageId) {
        return messageId > Short.MAX_VALUE/2;
    }

    private short invert(short messageId) {
        return (short)(Short.MAX_VALUE - messageId);
    }

    public void send(Container container, List crafters) {

        LogHelper.trace("Sending int: {}", data);
        sendAtom(container, crafters, invert(messageId), (short)(data & 0xffff));
        if ((data >>> 16) != 0) {
            sendAtom(container, crafters, messageId, (short)(data >>> 16));
        }
    }

    private void sendAtom(Container container, List crafters, short messageId, short body) {

        LogHelper.trace("Sending atom: {}", body);
        List<ICrafting> crafterList = (List<ICrafting>) crafters;
        for (ICrafting crafter: crafterList) {
            crafter.sendProgressBarUpdate(container, messageId, body);
        }
    }
}
