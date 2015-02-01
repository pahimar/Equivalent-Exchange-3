package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Files;
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
        knowledgeDirectory = new File(SerializationHelper.getPlayerDataDirectory(), "knowledge" + File.separator + "transmutation");
        knowledgeDirectory.mkdirs();

        loadTemplateKnowledgeFromDisk();

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
        return templateKnowledge;
    }

    public void loadTemplateKnowledgeFromDisk() {
        File templateFile = new File(knowledgeDirectory, Files.TEMPLATE_JSON_FILE);

        if (!templateFile.exists()) {
            templateKnowledge = new TransmutationKnowledge();
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
        } else {
            templateKnowledge = SerializationHelper.readTransmutationKnowledgeFromFile(knowledgeDirectory, Files.TEMPLATE_JSON_FILE);
        }
    }

    public void saveTemplateKnowledgeToDisk() {
        SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
    }

    public void loadPlayerFromDisk(EntityPlayer entityPlayer)
    {
        TransmutationKnowledge playerTransmutationKnowledge = new TransmutationKnowledge();

        File playerKnowledgeFile = new File(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json");

        if (playerKnowledgeFile.exists() && playerKnowledgeFile.isFile())
        {
            playerTransmutationKnowledge = SerializationHelper.readTransmutationKnowledgeFromFile(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json");
        }

        playerKnowledgeMap.put(entityPlayer.getUniqueID(), playerTransmutationKnowledge);
    }

    public void savePlayerKnowledgeToDisk(EntityPlayer entityPlayer)
    {
        if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID())) {
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json", playerKnowledgeMap.get(entityPlayer.getUniqueID()));
        } else {
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json", new TransmutationKnowledge());
        }
    }

    public void saveAll() {
        saveTemplateKnowledgeToDisk();

        for (UUID playerUUID : playerKnowledgeMap.keySet()) {
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, playerUUID.toString() + ".json", playerKnowledgeMap.get(playerUUID));
        }
    }
}
