package com.pahimar.ee3.registry;

import java.util.List;

public class KnowledgeRegistry
{
    private static KnowledgeRegistry knowledgeRegistry = null;
    private List learnableItems;

    private KnowledgeRegistry()
    {
    }

    public static KnowledgeRegistry getInstance()
    {
        if (knowledgeRegistry == null)
        {
            knowledgeRegistry = new KnowledgeRegistry();
            knowledgeRegistry.init();
        }

        return knowledgeRegistry;
    }

    private void init()
    {

    }
}
