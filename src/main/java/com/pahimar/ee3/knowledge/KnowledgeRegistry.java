package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.util.SerializationHelper;

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
}
