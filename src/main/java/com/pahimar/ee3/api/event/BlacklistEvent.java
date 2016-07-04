package com.pahimar.ee3.api.event;

import net.minecraftforge.fml.common.eventhandler.Event;

public class BlacklistEvent extends Event {

    private final Object object;

    private BlacklistEvent(Object object) {
        this.object = object;
    }

    public final Object getObject() {
        return object;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    public static final class KnowledgeBlacklistEvent extends BlacklistEvent {

        public KnowledgeBlacklistEvent(Object object) {
            super(object);
        }
    }

    public static final class KnowledgeWhitelistEvent extends BlacklistEvent {

        public KnowledgeWhitelistEvent(Object object) {
            super(object);
        }
    }

    public static final class ExchangeBlacklistEvent extends BlacklistEvent {

        public ExchangeBlacklistEvent(Object object) {
            super(object);
        }
    }

    public static final class ExchangeWhitelistEvent extends BlacklistEvent {

        public ExchangeWhitelistEvent(Object object) {
            super(object);
        }
    }
}
