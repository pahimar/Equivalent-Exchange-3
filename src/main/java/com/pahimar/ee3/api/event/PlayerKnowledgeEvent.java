package com.pahimar.ee3.api.event;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class PlayerKnowledgeEvent extends Event
{
    public final UUID playerUUID;

    private PlayerKnowledgeEvent(EntityPlayer entityPlayer)
    {
        this(entityPlayer.getUniqueID());
    }

    private PlayerKnowledgeEvent(UUID playerUUID)
    {
        this.playerUUID = playerUUID;
    }

    @Override
    public boolean isCancelable()
    {
        return true;
    }

    public static class PlayerLearnKnowledgeEvent extends PlayerKnowledgeEvent
    {
        public final ItemStack itemStack;

        public PlayerLearnKnowledgeEvent(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super(entityPlayer);
            this.itemStack = itemStack;
        }

        public PlayerLearnKnowledgeEvent(UUID playerUUID, ItemStack itemStack)
        {
            super(playerUUID);
            this.itemStack = itemStack;
        }
    }

    public static class PlayerForgetKnowledgeEvent extends PlayerKnowledgeEvent
    {
        public final ItemStack itemStack;

        public PlayerForgetKnowledgeEvent(EntityPlayer entityPlayer, ItemStack itemStack)
        {
            super(entityPlayer);
            this.itemStack = itemStack;
        }

        public PlayerForgetKnowledgeEvent(UUID playerUUID, ItemStack itemStack)
        {
            super(playerUUID);
            this.itemStack = itemStack;
        }
    }

    public static class PlayerForgetAllKnowledgeEvent extends PlayerKnowledgeEvent
    {
        public PlayerForgetAllKnowledgeEvent(EntityPlayer entityPlayer)
        {
            super(entityPlayer);
        }

        public PlayerForgetAllKnowledgeEvent(UUID playerUUID)
        {
            super(playerUUID);
        }
    }
}
