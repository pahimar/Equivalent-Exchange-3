package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.ItemHelper;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class TransmutationKnowledgeRegistry
{
    private static TransmutationKnowledgeRegistry transmutationKnowledgeRegistry = null;
    private static File playerKnowledgeDirectory;
    private static File dataKnowledgeDirectory;
    private static TransmutationKnowledge templateKnowledge;
    private static HashMap<UUID, TransmutationKnowledge> playerKnowledgeMap;

    private TransmutationKnowledgeRegistry()
    {
        playerKnowledgeDirectory = new File(SerializationHelper.getPlayerDataDirectory(), "knowledge" + File.separator + "transmutation");
        playerKnowledgeDirectory.mkdirs();

        dataKnowledgeDirectory = new File(SerializationHelper.getDataDirectory(), "knowledge" + File.separator + "transmutation");
        dataKnowledgeDirectory.mkdirs();

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

    public Set<ItemStack> getTemplatesKnownTransmutationsFilteredStartsWith(String filterString)
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        return ItemHelper.filterByNameStartsWith(templateKnowledge.getKnownTransmutations(), filterString);
    }

    public Set<ItemStack> getTemplatesKnownTransmutationsFilteredContains(String filterString)
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        return ItemHelper.filterByNameContains(templateKnowledge.getKnownTransmutations(), filterString);
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

    public void teachTemplateEverything()
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

    public void makeTemplateForgetEverything()
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
        if (entityPlayer != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);

            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                return playerKnowledgeMap.get(entityPlayer.getUniqueID()).getKnownTransmutations();
            }
        }

        return null;
    }

    public Set<ItemStack> getPlayersKnownTransmutationsFilteredStartsWith(EntityPlayer entityPlayer, String startsWith)
    {
        if (entityPlayer != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);

            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                return playerKnowledgeMap.get(entityPlayer.getUniqueID()).filterByNameStartsWith(startsWith);
            }
        }

        return null;
    }

    public Set<ItemStack> getPlayersKnownTransmutationsFilteredContains(EntityPlayer entityPlayer, String contains)
    {
        if (entityPlayer != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);

            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                return playerKnowledgeMap.get(entityPlayer.getUniqueID()).filterByNameContains(contains);
            }
        }

        return null;
    }

    public boolean doesPlayerKnow(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (entityPlayer != null && itemStack != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);

            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                return playerKnowledgeMap.get(entityPlayer.getUniqueID()).isKnown(itemStack);
            }
        }

        return false;
    }

    public boolean canPlayerLearn(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (entityPlayer != null && itemStack != null)
        {
            if (AbilityRegistry.getInstance().isLearnable(itemStack))
            {
                loadPlayerFromDiskIfNeeded(entityPlayer);

                if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
                {
                    return !playerKnowledgeMap.get(entityPlayer.getUniqueID()).isKnown(itemStack);
                }
            }
        }

        return false;
    }

    public void teachPlayer(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (entityPlayer != null && itemStack != null && canPlayerLearn(entityPlayer, itemStack))
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);
            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                playerKnowledgeMap.get(entityPlayer.getUniqueID()).learnTransmutation(itemStack);
            }
        }
    }

    public void teachPlayerEverything(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);

            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                playerKnowledgeMap.get(entityPlayer.getUniqueID()).setCanTransmuteEverything(true);
            }
        }
    }

    public void setPlayerCanTransmuteEverything(EntityPlayer entityPlayer, boolean canTransmuteEverything)
    {
        if (entityPlayer != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);
            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                playerKnowledgeMap.get(entityPlayer.getUniqueID()).setCanTransmuteEverything(canTransmuteEverything);
            }
        }
    }

    public void makePlayerForget(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (entityPlayer != null && itemStack != null && doesPlayerKnow(entityPlayer, itemStack))
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);
            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                playerKnowledgeMap.get(entityPlayer.getUniqueID()).forgetTransmutation(itemStack);
            }
        }
    }

    public void makePlayerForgetEverything(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer);
            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                playerKnowledgeMap.get(entityPlayer.getUniqueID()).forgetAllTransmutations();
            }
        }
    }

    /*****************************************************************************/
    /**                            Serialization                                **/
    /**
     * *************************************************************************
     */
    public void loadTemplateKnowledgeFromDisk()
    {
        if (dataKnowledgeDirectory != null)
        {
            File templateFile = new File(dataKnowledgeDirectory, Files.TEMPLATE_JSON_FILE);

            if (!templateFile.exists())
            {
                templateKnowledge = new TransmutationKnowledge();
                SerializationHelper.writeTransmutationKnowledgeToFile(dataKnowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
            }
            else
            {
                templateKnowledge = SerializationHelper.readTransmutationKnowledgeFromFile(dataKnowledgeDirectory, Files.TEMPLATE_JSON_FILE);
            }
        }
        else
        {
            templateKnowledge = new TransmutationKnowledge();
        }
    }

    public void saveTemplateKnowledgeToDisk()
    {
        if (dataKnowledgeDirectory != null)
        {
            if (templateKnowledge != null)
            {
                SerializationHelper.writeTransmutationKnowledgeToFile(dataKnowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
            }
            else
            {
                templateKnowledge = new TransmutationKnowledge();
                SerializationHelper.writeTransmutationKnowledgeToFile(dataKnowledgeDirectory, Files.TEMPLATE_JSON_FILE, templateKnowledge);
            }
        }
    }

    public void loadPlayerFromDiskIfNeeded(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null && entityPlayer.getUniqueID() != null && playerKnowledgeDirectory != null)
        {
            if (!playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                TransmutationKnowledge playerTransmutationKnowledge = new TransmutationKnowledge();

                File playerKnowledgeFile = new File(playerKnowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json");

                if (playerKnowledgeFile.exists() && playerKnowledgeFile.isFile())
                {
                    playerTransmutationKnowledge = SerializationHelper.readTransmutationKnowledgeFromFile(playerKnowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json");
                }

                playerKnowledgeMap.put(entityPlayer.getUniqueID(), playerTransmutationKnowledge);
            }
        }
    }

    public void unloadPlayer(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null && entityPlayer.getUniqueID() != null)
        {
            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()))
            {
                savePlayerKnowledgeToDisk(entityPlayer);
                playerKnowledgeMap.remove(entityPlayer.getUniqueID());
            }
        }
    }

    public void savePlayerKnowledgeToDisk(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null && entityPlayer.getUniqueID() != null && playerKnowledgeDirectory != null)
        {
            if (playerKnowledgeMap.containsKey(entityPlayer.getUniqueID()) && playerKnowledgeMap.get(entityPlayer.getUniqueID()) != null && playerKnowledgeMap.get(entityPlayer.getUniqueID()).hasBeenModified())
            {
                SerializationHelper.writeTransmutationKnowledgeToFile(playerKnowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json", playerKnowledgeMap.get(entityPlayer.getUniqueID()));
            }
            else
            {
                loadPlayerFromDiskIfNeeded(entityPlayer);
                SerializationHelper.writeTransmutationKnowledgeToFile(playerKnowledgeDirectory, entityPlayer.getUniqueID().toString() + ".json", new TransmutationKnowledge());
            }
        }
    }

    public void saveAll()
    {
        if (dataKnowledgeDirectory != null)
        {
            saveTemplateKnowledgeToDisk();
        }

        if (playerKnowledgeDirectory != null)
        {
            for (UUID playerUUID : playerKnowledgeMap.keySet())
            {
                SerializationHelper.writeTransmutationKnowledgeToFile(playerKnowledgeDirectory, playerUUID.toString() + ".json", playerKnowledgeMap.get(playerUUID));
            }
        }
    }
}
