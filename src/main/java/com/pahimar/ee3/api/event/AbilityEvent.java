package com.pahimar.ee3.api.event;

import cpw.mods.fml.common.eventhandler.Event;

/**
 * @deprecated See {@link BlacklistEvent}
 */
@Deprecated
public class AbilityEvent extends Event {

    public final Object object;

    /**
     * @deprecated See {@link BlacklistEvent}
     */
    @Deprecated
    public AbilityEvent(Object object) {
        this.object = object;
    }
    @Override
    public boolean isCancelable()
    {
        return true;
    }

    /**
     * @deprecated See {@link com.pahimar.ee3.api.event.BlacklistEvent.KnowledgeWhitelistEvent}
     */
    public static class SetLearnableEvent extends AbilityEvent
    {
        public SetLearnableEvent(Object object)
        {
            super(object);
        }
    }

    /**
     * @deprecated See {@link com.pahimar.ee3.api.event.BlacklistEvent.KnowledgeBlacklistEvent}
     */
    public static class SetNotLearnableEvent extends AbilityEvent
    {
        public SetNotLearnableEvent(Object object)
        {
            super(object);
        }
    }

    /**
     * @deprecated See {@link com.pahimar.ee3.api.event.BlacklistEvent.ExchangeWhitelistEvent}
     */
    public static class SetRecoverableEvent extends AbilityEvent
    {
        public SetRecoverableEvent(Object object)
        {
            super(object);
        }
    }

    /**
     * @deprecated See {@link com.pahimar.ee3.api.event.BlacklistEvent.ExchangeBlacklistEvent}
     */
    public static class SetNotRecoverableEvent extends AbilityEvent
    {
        public SetNotRecoverableEvent(Object object)
        {
            super(object);
        }
    }
}
