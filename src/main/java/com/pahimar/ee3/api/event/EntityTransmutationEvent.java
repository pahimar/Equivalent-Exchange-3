package com.pahimar.ee3.api.event;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.entity.EntityEvent;

public class EntityTransmutationEvent extends EntityEvent {

    public final Entity newEntity;

    public EntityTransmutationEvent(Entity originalEntity, Entity newEntity) {
        super(originalEntity);
        this.newEntity = newEntity;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }
}
