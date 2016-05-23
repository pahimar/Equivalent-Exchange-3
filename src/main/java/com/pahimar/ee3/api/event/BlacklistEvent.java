package com.pahimar.ee3.api.event;

import cpw.mods.fml.common.eventhandler.Event;

public class BlacklistEvent extends Event {

    public final Object object;

    public BlacklistEvent(Object object) {
        this.object = object;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    public static class KnowledgeBlacklistEvent extends BlacklistEvent {

        public KnowledgeBlacklistEvent(Object object) {
            super(object);
        }
    }

    public static class KnowledgeWhitelistEvent extends BlacklistEvent {

        public KnowledgeWhitelistEvent(Object object) {
            super(object);
        }
    }

    public static class ExchangeBlacklistEvent extends BlacklistEvent {

        public ExchangeBlacklistEvent(Object object) {
            super(object);
        }
    }

    public static class ExchangeWhitelistEvent extends BlacklistEvent {

        public ExchangeWhitelistEvent(Object object) {
            super(object);
        }
    }
}
