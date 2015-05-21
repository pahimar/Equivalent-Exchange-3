package com.pahimar.ee3.api.event;

import cpw.mods.fml.common.eventhandler.Event;

public class AbilityEvent extends Event
{
    public final Object object;

    public AbilityEvent(Object object)
    {
        this.object = object;
    }

    @Override
    public boolean isCancelable()
    {
        return true;
    }

    public static class SetLearnableEvent extends AbilityEvent
    {
        public SetLearnableEvent(Object object)
        {
            super(object);
        }
    }

    public static class SetNotLearnableEvent extends AbilityEvent
    {
        public SetNotLearnableEvent(Object object)
        {
            super(object);
        }
    }

    public static class SetRecoverableEvent extends AbilityEvent
    {
        public SetRecoverableEvent(Object object)
        {
            super(object);
        }
    }

    public static class SetNotRecoverableEvent extends AbilityEvent
    {
        public SetNotRecoverableEvent(Object object)
        {
            super(object);
        }
    }
}
