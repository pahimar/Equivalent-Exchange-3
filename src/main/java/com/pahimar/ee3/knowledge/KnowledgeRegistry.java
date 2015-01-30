package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.entity.player.EntityPlayer;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public class KnowledgeRegistry
{
    private static KnowledgeRegistry knowledgeRegistry = null;
    private static File knowledgeDirectory;
    private static TransmutationKnowledge templateKnowledge;
    private static HashMap<UUID, TransmutationKnowledge> playerKnowledgeMap;

    private KnowledgeRegistry()
    {
        knowledgeDirectory = new File(SerializationHelper.getPlayerDataDirectory(), "knowledge");
        knowledgeDirectory.mkdirs();

        playerKnowledgeMap = new HashMap<UUID, TransmutationKnowledge>();
    }

    public static KnowledgeRegistry getInstance()
    {
        if (knowledgeRegistry == null)
        {
            knowledgeRegistry = new KnowledgeRegistry();
        }

        return knowledgeRegistry;
    }

    public TransmutationKnowledge getTemplateKnowledge()
    {
        if (templateKnowledge == null)
        {

        }

        return templateKnowledge;
    }

    public void loadPlayerFromDisk(EntityPlayer entityPlayer)
    {
        TransmutationKnowledge playerTransmutationKnowledge = new TransmutationKnowledge();
        LogHelper.error(entityPlayer.getUniqueID());
        File playerKnowledgeFile = new File(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".transmutation");

        LogHelper.info(playerKnowledgeFile.getAbsolutePath());

        if (playerKnowledgeFile.exists() && playerKnowledgeFile.isFile())
        {
            playerTransmutationKnowledge = TransmutationKnowledge.readTransmutationKnowledgeFromNBT(SerializationHelper.readNBTFromFile(playerKnowledgeFile));
        }

        playerKnowledgeMap.put(entityPlayer.getUniqueID(), playerTransmutationKnowledge);
    }

    public void savePlayerKnowledgeToDisk(EntityPlayer entityPlayer)
    {
        SerializationHelper.writeNBTToFile(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json", playerKnowledgeMap.get(entityPlayer.getUniqueID()));
    }
}
