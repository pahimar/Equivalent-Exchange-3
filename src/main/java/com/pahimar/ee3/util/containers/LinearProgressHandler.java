package com.pahimar.ee3.util.containers;

import com.pahimar.ee3.util.LogHelper;

import java.util.HashMap;

/**
 * Originated from PR#881
 */
public abstract class LinearProgressHandler<MessageType extends Enum<MessageType>> {

    private Class<MessageType> enumClass;
    private HashMap<Short, ProgressMessage<MessageType>> buffer;

    public LinearProgressHandler(Class<MessageType> enumClass) {

        this.enumClass = enumClass;
        buffer = new HashMap<>();
    }

    public abstract void handle(ProgressMessage<MessageType> message);

    public void handle(int messageId, int data) {

        LogHelper.trace("Got data: {}", data);
        short id = (short)messageId;

        ProgressMessage<MessageType> message;

        if (buffer.containsKey(id)) {

            message = buffer.remove(id);
            message = message.complete(data);
            LogHelper.trace("Completed message: ({}, {}, {})", message.getId(), message.getInt(), message.getFloat());
            handle(message);
            return;
        }

        message = new ProgressMessage<>(enumClass, id, data & 0x0000ffff);

        if (message.incomplete) {
            buffer.put(message.getId(), message);
        }
        else {
            LogHelper.trace("Instant message: ({}, {}, {})", message.getId(), message.getInt(), message.getFloat());
            handle(message);
        }
    }
}
