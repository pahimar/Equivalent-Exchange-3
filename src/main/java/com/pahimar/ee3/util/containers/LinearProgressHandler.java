package com.pahimar.ee3.util.containers;

import com.pahimar.ee3.util.LogHelper;

import java.util.HashMap;

public abstract class LinearProgressHandler<MessageType extends Enum<MessageType>> {
    private Class<MessageType> enumClass;
    private HashMap<Short, ProgressMessage<MessageType>> buffer;

    public LinearProgressHandler(Class<MessageType> enumClass) {
        this.enumClass = enumClass;
        buffer = new HashMap<Short, ProgressMessage<MessageType>>();
    }

    public abstract void handle(ProgressMessage<MessageType> message);

    public void handle(int messageId, int data) {
        LogHelper.trace(String.format("Got data: %x", data));
        short id = (short)messageId;
        ProgressMessage<MessageType> message;
        if (buffer.containsKey(id)) {
            message = buffer.remove(id);
            message = message.complete(data);
            LogHelper.trace(String.format("Completed message: (%d, %x, %f)", message.getId(), message.getInt(), message.getFloat()));
            handle(message);
            return;
        }
        message = new ProgressMessage<MessageType>(enumClass, id, data & 0x0000ffff);
        if (message.incomplete) {
            buffer.put(message.getId(), message);
        } else {
            LogHelper.trace(String.format("Instant message: (%d, %x, %f)", message.getId(), message.getInt(), message.getFloat()));
            handle(message);
        }
    }
}
