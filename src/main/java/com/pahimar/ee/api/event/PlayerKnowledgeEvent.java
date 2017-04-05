package com.pahimar.ee.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PlayerKnowledgeEvent extends Event {

    private final String playerName;
    private final Object[] objects;

    private PlayerKnowledgeEvent(EntityPlayer entityPlayer, Object... objects) {

        if (entityPlayer != null) {
            playerName = entityPlayer.getName();
        }
        else {
            playerName = null;
        }

        this.objects = objects;
    }

    private PlayerKnowledgeEvent(String playerName, Object... objects) {
        this.playerName = playerName;
        this.objects = objects;
    }

    public final String getPlayerName() {
        return playerName;
    }

    public final Object[] getObjects() {
        return objects;
    }

    @Override
    public boolean isCancelable() {
        return true;
    }

    public static final class PlayerLearnEvent extends PlayerKnowledgeEvent {

        public PlayerLearnEvent(EntityPlayer entityPlayer, Object... objects) {
            super(entityPlayer, objects);
        }

        public PlayerLearnEvent(String playerName, Object... objects) {
            super(playerName, objects);
        }
    }

    public static final class PlayerForgetEvent extends PlayerKnowledgeEvent {

        public PlayerForgetEvent(EntityPlayer entityPlayer, Object... objects) {
            super(entityPlayer, objects);
        }

        public PlayerForgetEvent(String playerName, Object... objects) {
            super(playerName, objects);
        }
    }

    public static final class PlayerForgetAllEvent extends PlayerKnowledgeEvent {

        public PlayerForgetAllEvent(EntityPlayer entityPlayer) {
            super(entityPlayer, null);
        }

        public PlayerForgetAllEvent(String playerName) {
            super(playerName, null);
        }
    }
}
