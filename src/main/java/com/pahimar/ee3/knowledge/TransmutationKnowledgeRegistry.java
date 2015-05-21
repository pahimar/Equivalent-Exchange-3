package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.api.event.PlayerKnowledgeEvent;
import com.pahimar.ee3.api.event.TemplateKnowledgeEvent;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.FilterUtils;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

import java.io.File;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class TransmutationKnowledgeRegistry
{
    private static TransmutationKnowledgeRegistry transmutationKnowledgeRegistry = null;
    private static File playerKnowledgeDirectory, dataKnowledgeDirectory;
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

    /* Template Related Transmutation Knowledge */
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

        return FilterUtils.filterByNameStartsWith(templateKnowledge.getKnownTransmutations(), filterString);
    }

    public Set<ItemStack> getTemplatesKnownTransmutationsFilteredContains(String filterString)
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        return FilterUtils.filterByNameContains(templateKnowledge.getKnownTransmutations(), filterString);
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

        if (canTemplateLearn(itemStack) && !MinecraftForge.EVENT_BUS.post(new TemplateKnowledgeEvent.TemplateLearnKnowledgeEvent(itemStack)))
        {
            templateKnowledge.learnTransmutation(itemStack);
            saveTemplateKnowledgeToDisk();
        }
    }

    public void makeTemplateForget(ItemStack itemStack)
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        if (doesTemplateKnow(itemStack) && !MinecraftForge.EVENT_BUS.post(new TemplateKnowledgeEvent.TemplateForgetKnowledgeEvent(itemStack)))
        {
            templateKnowledge.forgetTransmutation(itemStack);
            saveTemplateKnowledgeToDisk();
        }
    }

    public void makeTemplateForgetEverything()
    {
        if (templateKnowledge == null)
        {
            loadTemplateKnowledgeFromDisk();
        }

        if (!MinecraftForge.EVENT_BUS.post(new TemplateKnowledgeEvent.TemplateForgetAllKnowledgeEvent()))
        {
            templateKnowledge.forgetAllTransmutations();
            saveTemplateKnowledgeToDisk();
        }
    }

    /* Player Related Transmutation Knowledge */
    public Set<ItemStack> getPlayersKnownTransmutations(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null)
        {
            return getPlayersKnownTransmutations(entityPlayer.getUniqueID());
        }

        return null;
    }

    public Set<ItemStack> getPlayersKnownTransmutations(UUID playerUUID)
    {
        if (playerUUID != null)
        {
            loadPlayerFromDiskIfNeeded(playerUUID);

            if (playerKnowledgeMap.containsKey(playerUUID))
            {
                return playerKnowledgeMap.get(playerUUID).getKnownTransmutations();
            }
        }

        return null;
    }

    public Set<ItemStack> getPlayersKnownTransmutationsFilteredStartsWith(EntityPlayer entityPlayer, String startsWith)
    {
        if (entityPlayer != null)
        {
            return getPlayersKnownTransmutationsFilteredStartsWith(entityPlayer.getUniqueID(), startsWith);
        }

        return null;
    }

    public Set<ItemStack> getPlayersKnownTransmutationsFilteredStartsWith(UUID playerUUID, String startsWith)
    {
        if (playerUUID != null)
        {
            loadPlayerFromDiskIfNeeded(playerUUID);

            if (playerKnowledgeMap.containsKey(playerUUID))
            {
                return playerKnowledgeMap.get(playerUUID).filterByNameStartsWith(startsWith);
            }
        }

        return null;
    }

    public Set<ItemStack> getPlayersKnownTransmutationsFilteredContains(EntityPlayer entityPlayer, String contains)
    {
        if (entityPlayer != null)
        {
            return getPlayersKnownTransmutationsFilteredContains(entityPlayer.getUniqueID(), contains);
        }

        return null;
    }

    public Set<ItemStack> getPlayersKnownTransmutationsFilteredContains(UUID playerUUID, String contains)
    {
        if (playerUUID != null)
        {
            loadPlayerFromDiskIfNeeded(playerUUID);

            if (playerKnowledgeMap.containsKey(playerUUID))
            {
                return playerKnowledgeMap.get(playerUUID).filterByNameContains(contains);
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

    public boolean doesPlayerKnow(UUID playerUUID, ItemStack itemStack)
    {
        if (playerUUID != null && itemStack != null)
        {
            loadPlayerFromDiskIfNeeded(playerUUID);

            if (playerKnowledgeMap.containsKey(playerUUID))
            {
                return playerKnowledgeMap.get(playerUUID).isKnown(itemStack);
            }
        }

        return false;
    }

    public boolean canPlayerLearn(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (entityPlayer != null)
        {
            return canPlayerLearn(entityPlayer.getUniqueID(), itemStack);
        }

        return false;
    }

    public boolean canPlayerLearn(UUID playerUUID, ItemStack itemStack)
    {
        if (playerUUID != null && itemStack != null)
        {
            if (AbilityRegistry.getInstance().isLearnable(itemStack))
            {
                loadPlayerFromDiskIfNeeded(playerUUID);

                if (playerKnowledgeMap.containsKey(playerUUID))
                {
                    return !playerKnowledgeMap.get(playerUUID).isKnown(itemStack);
                }
            }
        }

        return false;
    }

    public void teachPlayer(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (entityPlayer != null)
        {
            teachPlayer(entityPlayer.getUniqueID(), itemStack);
        }
    }

    public void teachPlayer(UUID playerUUID, ItemStack itemStack)
    {
        if (playerUUID != null && itemStack != null && canPlayerLearn(playerUUID, itemStack))
        {
            loadPlayerFromDiskIfNeeded(playerUUID);
            if (playerKnowledgeMap.containsKey(playerUUID) && !MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerLearnKnowledgeEvent(playerUUID, itemStack)))
            {
                playerKnowledgeMap.get(playerUUID).learnTransmutation(itemStack);
                savePlayerKnowledgeToDisk(playerUUID);
            }
        }
    }

    public void makePlayerForget(EntityPlayer entityPlayer, ItemStack itemStack)
    {
        if (entityPlayer != null)
        {
            makePlayerForget(entityPlayer.getUniqueID(), itemStack);
        }
    }

    public void makePlayerForget(UUID playerUUID, ItemStack itemStack)
    {
        if (playerUUID != null && itemStack != null && doesPlayerKnow(playerUUID, itemStack))
        {
            loadPlayerFromDiskIfNeeded(playerUUID);
            if (playerKnowledgeMap.containsKey(playerUUID) && !MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerForgetKnowledgeEvent(playerUUID, itemStack)))
            {
                playerKnowledgeMap.get(playerUUID).forgetTransmutation(itemStack);
                savePlayerKnowledgeToDisk(playerUUID);
            }
        }
    }

    public void makePlayerForgetEverything(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null)
        {
            makePlayerForgetEverything(entityPlayer.getUniqueID());
        }
    }

    public void makePlayerForgetEverything(UUID playerUUID)
    {
        if (playerUUID != null)
        {
            loadPlayerFromDiskIfNeeded(playerUUID);
            if (playerKnowledgeMap.containsKey(playerUUID) && !MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerForgetAllKnowledgeEvent(playerUUID)))
            {
                playerKnowledgeMap.get(playerUUID).forgetAllTransmutations();
                savePlayerKnowledgeToDisk(playerUUID);
            }
        }
    }

    /* Serialization */
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
        if (entityPlayer != null && entityPlayer.getUniqueID() != null)
        {
            loadPlayerFromDiskIfNeeded(entityPlayer.getUniqueID());
        }
    }

    public void loadPlayerFromDiskIfNeeded(UUID playerUUID)
    {
        if (playerUUID != null && playerKnowledgeDirectory != null && !playerKnowledgeMap.containsKey(playerUUID))
        {
            TransmutationKnowledge playerTransmutationKnowledge = new TransmutationKnowledge();

            File playerKnowledgeFile = new File(playerKnowledgeDirectory, playerUUID.toString() + ".json");

            if (playerKnowledgeFile.exists() && playerKnowledgeFile.isFile())
            {
                playerTransmutationKnowledge = SerializationHelper.readTransmutationKnowledgeFromFile(playerKnowledgeDirectory, playerUUID.toString() + ".json");
            }

            playerKnowledgeMap.put(playerUUID, playerTransmutationKnowledge);
        }
    }

    public void unloadPlayer(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null)
        {
            unloadPlayer(entityPlayer.getUniqueID());
        }
    }

    public void unloadPlayer(UUID playerUUID)
    {
        if (playerUUID != null)
        {
            if (playerKnowledgeMap.containsKey(playerUUID))
            {
                savePlayerKnowledgeToDisk(playerUUID);
                playerKnowledgeMap.remove(playerUUID);
            }
        }
    }

    public void savePlayerKnowledgeToDisk(EntityPlayer entityPlayer)
    {
        if (entityPlayer != null && entityPlayer.getUniqueID() != null)
        {
            savePlayerKnowledgeToDisk(entityPlayer.getUniqueID());
        }
    }

    public void savePlayerKnowledgeToDisk(UUID playerUUID)
    {
        if (playerUUID != null && playerKnowledgeDirectory != null)
        {
            if (playerKnowledgeMap.containsKey(playerUUID) && playerKnowledgeMap.get(playerUUID) != null)
            {
                if (playerKnowledgeMap.get(playerUUID).hasBeenModified())
                {
                    SerializationHelper.writeTransmutationKnowledgeToFile(playerKnowledgeDirectory, playerUUID.toString() + ".json", playerKnowledgeMap.get(playerUUID));
                }
            }
            else
            {
                loadPlayerFromDiskIfNeeded(playerUUID);
                SerializationHelper.writeTransmutationKnowledgeToFile(playerKnowledgeDirectory, playerUUID.toString() + ".json", playerKnowledgeMap.get(playerUUID));
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

    public void clear()
    {
        saveAll();
        this.transmutationKnowledgeRegistry = null;
    }
}
