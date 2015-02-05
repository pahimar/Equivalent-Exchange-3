package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

// TODO Unload players from disk if they have changes made to their knowledge while they are not online
public class TransmutationKnowledgeRegistry
{
    private static TransmutationKnowledgeRegistry transmutationKnowledgeRegistry = null;
    private static File knowledgeDirectory;
    private static TransmutationKnowledge templateKnowledge;
    private static HashMap<UUID, TransmutationKnowledge> playerKnowledgeMap;

    private TransmutationKnowledgeRegistry()
    {
        knowledgeDirectory = new File(SerializationHelper.getPlayerDataDirectory(), "knowledge" + File.separator + "transmutation");
        knowledgeDirectory.mkdirs();

        loadTemplateKnowledgeFromDisk();

        playerKnowledgeMap = new HashMap<UUID, TransmutationKnowledge>();
    }

    public static TransmutationKnowledgeRegistry getInstance()
    {
        if (transmutationKnowledgeRegistry == null)
        {
            transmutationKnowledgeRegistry = new TransmutationKnowledgeRegistry();
        }

        return transmutationKnowledgeRegistry;
    }

    public TransmutationKnowledge getTemplateKnowledge()
    {
        return templateKnowledge;
    }

    /*****************************************************************************/
    /**               Template Related Transmutation Knowledge                  **/
    /**
     * *************************************************************************
     */
    public Set<ItemStack> getTemplatesKnownTransmutations()
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        return templateKnowledge.getKnownTransmutations();
    }

    public boolean doesTemplateKnow(ItemStack itemStack)
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        return templateKnowledge.isKnown(itemStack);
    }

    public boolean canTemplateLearn(ItemStack itemStack)
    {
        if (AbilityRegistry.getInstance().isLearnable(itemStack))
        {
            if (templateKnowledge == null)
            {
                loadTemplateKnowledgeFromDisk();
            }
            return !templateKnowledge.isKnown(itemStack);
        }

        return false;
    }

    public void teachTemplate(ItemStack itemStack)
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        if (canTemplateLearn(itemStack))
        {
            templateKnowledge.learnTransmutation(itemStack);
        }

        saveTemplateKnowledgeToDisk();
    }

    public void teachTemplateAll()
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        templateKnowledge.setCanTransmuteEverything(true);

        saveTemplateKnowledgeToDisk();
    }

    public void makeTemplateForget(ItemStack itemStack)
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        if (doesTemplateKnow(itemStack))
        {
            templateKnowledge.forgetTransmutation(itemStack);
        }

        saveTemplateKnowledgeToDisk();
    }

    public void makeTemplateForgetAll()
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        templateKnowledge.forgetAllTransmutations();

        saveTemplateKnowledgeToDisk();
    }

    /*****************************************************************************/
    /**                Player Related Transmutation Knowledge                   **/
    /**
     * *************************************************************************
     */
    public Set<ItemStack> getPlayersKnownTransmutations(EntityPlayer entityPlayer)
    {
        loadPlayerFromDiskIfNeeded(entityPlayer);

        return playerKnowledgeMap.get(entityPlayer.getUniqueID()).getKnownTransmutations();
    }

    public boolean doesPlayerKnow(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        loadPlayerFromDiskIfNeeded(entityPlayer);

        return playerKnowledgeMap.get(entityPlayer.getUniqueID()).isKnown(itemStack);
    }

    public boolean canPlayerLearn(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (AbilityRegistry.getInstance().isLearnable(itemStack))
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);
            return !playerKnowledgeMap.get(entityPlayer.getUniqueID()).isKnown(itemStack);
        }

        return false;
    }

    public void teachPlayer(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (canPlayerLearn(entityPlayer, itemStack))
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);
            playerKnowledgeMap.get(entityPlayer.getUniqueID()).learnTransmutation(itemStack);
        }
    }

    public void teachPlayerAll(EntityPlayer entityPlayer)
    {
        loadPlayerFromDiskIfNeeded(entityPlayer);
        playerKnowledgeMap.get(entityPlayer.getUniqueID()).setCanTransmuteEverything(true);
    }

    public void setPlayerCanTransmuteEverything(EntityPlayer entityPlayer, boolean canTransmuteEverything)
    {
        loadPlayerFromDiskIfNeeded(entityPlayer);
        playerKnowledgeMap.get(entityPlayer.getUniqueID()).setCanTransmuteEverything(canTransmuteEverything);
    }

    public void makePlayerForget(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (doesPlayerKnow(entityPlayer, itemStack))
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);
            playerKnowledgeMap.get(entityPlayer.getUniqueID()).forgetTransmutation(itemStack);
        }
    }

    public void makePlayerForgetAll(EntityPlayer entityPlayer)
    {
        loadPlayerFromDiskIfNeeded(entityPlayer);
        playerKnowledgeMap.get(entityPlayer.getUniqueID()).forgetAllTransmutations();
    }

    /*****************************************************************************/
    /**                            Serialization                                **/
    /**
     * *************************************************************************
     */
    public void loadTemplateKnowledgeFromDisk()
    {
        File templateFile = new File(knowledgeDirectory, Files.TEMPLATE_JSON_FILE);

        if (!templateFile.exists())
        {
            templateKnowledge = new TransmutationKnowledge();
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
        }
        else
        {
            templateKnowledge = SerializationHelper.readTransmutationKnowledgeFromFile(knowledgeDirectory, Files.TEMPLATE_JSON_FILE);
        }
    }

    public void saveTemplateKnowledgeToDisk()
    {
        if (templateKnowledge != null)
        {
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
        }
        else
        {
            templateKnowledge = new TransmutationKnowledge();
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
        }
    }

    public void loadPlayerFromDiskIfNeeded(EntityPlayer entityPlayer)
    {
        if (!playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
        {
            TransmutationKnowledge playerTransmutationKnowledge = new TransmutationKnowledge();

            File playerKnowledgeFile = new File(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json");

            if (playerKnowledgeFile.exists() && playerKnowledgeFile.isFile())
            {
                playerTransmutationKnowledge = SerializationHelper.readTransmutationKnowledgeFromFile(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json");
            }

            playerKnowledgeMap.put(entityPlayer.getUniqueID(), playerTransmutationKnowledge);
        }
    }

    public void unloadPlayer(EntityPlayer entityPlayer)
    {
        if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
        {
            savePlayerKnowledgeToDisk(entityPlayer);
            playerKnowledgeMap.remove(entityPlayer.getUniqueID());
        }
    }

    public void savePlayerKnowledgeToDisk(EntityPlayer entityPlayer)
    {
        if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()) && playerKnowledgeMap.get(entityPlayer.getUniqueID()).hasBeenModified())
        {
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json", playerKnowledgeMap.get(entityPlayer.getUniqueID()));
        }
        else
        {
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json", new TransmutationKnowledge());
        }
    }

    public void saveAll()
    {
        saveTemplateKnowledgeToDisk();

        for (UUID playerUUID : playerKnowledgeMap.keySet())
        {
            SerializationHelper.writeTransmutationKnowledgeToFile(knowledgeDirectory, playerUUID.toString() + ".json", playerKnowledgeMap.get(playerUUID));
        }
    }
}
