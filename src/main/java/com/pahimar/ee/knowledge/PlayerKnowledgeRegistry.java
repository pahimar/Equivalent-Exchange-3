package com.pahimar.ee.knowledge;

import com.google.gson.JsonSyntaxException;
import com.pahimar.ee.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee.api.event.PlayerKnowledgeEvent;
import com.pahimar.ee.handler.ConfigurationHandler;
import com.pahimar.ee.reference.Comparators;
import com.pahimar.ee.reference.Files;
import com.pahimar.ee.util.LogHelper;
import com.pahimar.ee.util.SerializationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class PlayerKnowledgeRegistry {

    public static final PlayerKnowledgeRegistry INSTANCE = new PlayerKnowledgeRegistry();

    private final TreeMap<String, PlayerKnowledge> playerKnowledgeMap;
    private final PlayerKnowledge templatePlayerKnowledge;

    public static File templatePlayerKnowledgeFile;

    private PlayerKnowledgeRegistry() {

        playerKnowledgeMap = new TreeMap<>(Comparators.STRING_COMPARATOR);
        templatePlayerKnowledge = new PlayerKnowledge();
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param player
     * @param object
     * @return
     */
    public boolean doesPlayerKnow(EntityPlayer player, Object object) {
        return player != null && doesPlayerKnow(player.getName(), object);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     * @return
     */
    // TODO Remove the object from players knowledge if it is blacklisted
    public boolean doesPlayerKnow(String playerName, Object object) {
        return getPlayerKnowledge(playerName) != null && getPlayerKnowledge(playerName).isKnown(object);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     * @return
     */
    public boolean canPlayerLearn(EntityPlayer entityPlayer, Object object) {

        if (entityPlayer != null) {
            return canPlayerLearn(entityPlayer.getName(), object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     * @return
     */
    public boolean canPlayerLearn(String playerName, Object object) {
        return getPlayerKnowledge(playerName) != null && !getPlayerKnowledge(playerName).isKnown(object) && BlacklistRegistryProxy.isLearnable(object);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     */
    public void teachPlayer(EntityPlayer entityPlayer, Object object) {

        if (entityPlayer != null) {
            teachPlayer(entityPlayer.getName(), object);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     */
    public void teachPlayer(String playerName, Object object) {

        if (getPlayerKnowledge(playerName) != null) {
            if (!MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerLearnEvent(playerName, object))) {
                getPlayerKnowledge(playerName).learn(object);
                save(playerName);
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param objects
     */
    public void teachPlayer(EntityPlayer entityPlayer, Collection<?> objects) {

        if (entityPlayer != null) {
            teachPlayer(entityPlayer.getName(), objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param objects
     */
    public void teachPlayer(String playerName, Collection<?> objects) {

        if (objects != null && getPlayerKnowledge(playerName) != null) {
            PlayerKnowledge playerKnowledge = getPlayerKnowledge(playerName);

            if (!MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerLearnEvent(playerName, objects))) {
                playerKnowledge.learn(objects);
                save(playerName);
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     */
    public void makePlayerForget(EntityPlayer entityPlayer, Object object) {

        if (entityPlayer != null) {
            makePlayerForget(entityPlayer.getName(), object);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     */
    public void makePlayerForget(String playerName, Object object) {

        if (getPlayerKnowledge(playerName) != null) {
            if (!MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerForgetEvent(playerName, object))) {
                getPlayerKnowledge(playerName).forget(object);
                save(playerName);
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param objects
     */
    public void makePlayerForget(EntityPlayer entityPlayer, Collection<?> objects) {

        if (entityPlayer != null) {
            makePlayerForget(entityPlayer.getName(), objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param objects
     */
    public void makePlayerForget(String playerName, Collection<?> objects) {

        if (objects != null && getPlayerKnowledge(playerName) != null) {

            PlayerKnowledge playerKnowledge = getPlayerKnowledge(playerName);

            if (!MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerForgetEvent(playerName, objects))) {
                playerKnowledge.forget(objects);
                save(playerName);
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     */
    public void makePlayerForgetAll(EntityPlayer entityPlayer) {

        if (entityPlayer != null) {
            makePlayerForgetAll(entityPlayer.getName());
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     */
    public void makePlayerForgetAll(String playerName) {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            if (playerName != null && !playerName.isEmpty() && !MinecraftForge.EVENT_BUS.post(new PlayerKnowledgeEvent.PlayerForgetAllEvent(playerName))) {
                playerKnowledgeMap.put(playerName, new PlayerKnowledge());
                save(playerName);
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @return
     */
    public Set<ItemStack> getKnownItemStacks(EntityPlayer entityPlayer) {

        if (entityPlayer != null) {
            return getKnownItemStacks(entityPlayer.getName());
        }

        return new TreeSet<>(Comparators.ID_COMPARATOR);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @return
     */
    public Set<ItemStack> getKnownItemStacks(String playerName) {

        if (getPlayerKnowledge(playerName) != null) {
            return getPlayerKnowledge(playerName).getKnownItemStacks();
        }

        return new TreeSet<>(Comparators.ID_COMPARATOR);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @return
     */
    protected PlayerKnowledge getPlayerKnowledge(EntityPlayer entityPlayer) {

        if (entityPlayer != null) {
            return getPlayerKnowledge(entityPlayer.getName());
        }

        return null;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @return
     */
    private PlayerKnowledge getPlayerKnowledge(String playerName) {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && playerName != null && !playerName.isEmpty()) {
            if (!playerKnowledgeMap.containsKey(playerName)) {
                playerKnowledgeMap.put(playerName, load(getPlayerKnowledgeFile(playerName), false));
            }

            return playerKnowledgeMap.get(playerName);
        }

        return null;
    }

    /**
     * TODO Finish JavaDoc
     */
    public void saveAll() {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {

            // Save the template to file
            // TODO Do not save knowledge that is blacklisted
            SerializationHelper.writeJsonFile(templatePlayerKnowledgeFile, SerializationHelper.GSON.toJson(templatePlayerKnowledge));

            // Save every currently loaded player knowledge to file
            playerKnowledgeMap.keySet().forEach(this::save);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     */
    private void save(String playerName) {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            if (playerName != null && !playerName.isEmpty()) {
                File playerKnowledgeFile = getPlayerKnowledgeFile(playerName);
                if (playerKnowledgeFile != null) {
                    // TODO Do not save knowledge that is currently blacklisted
                    SerializationHelper.writeJsonFile(playerKnowledgeFile, SerializationHelper.GSON.toJson(playerKnowledgeMap.get(playerName)));
                }
            }
        }
    }

    /**
     * TODO Finish JavaDoc
     */
    public void load() {

        // Load template knowledge
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {

            templatePlayerKnowledge.forgetAll();
            // TODO Do not load in knowledge that is blacklisted
            templatePlayerKnowledge.learn(load(templatePlayerKnowledgeFile, true).getKnownItemStacks());

            // Reset the player knowledge map
            playerKnowledgeMap.clear();
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param file
     * @return
     */
    private PlayerKnowledge load(File file, boolean isTemplate) {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && file != null) {
            try {
                String jsonString = SerializationHelper.readJsonFile(file);
                PlayerKnowledge playerKnowledge = SerializationHelper.GSON.fromJson(jsonString, PlayerKnowledge.class);

                if (playerKnowledge != null) {
                    // TODO Remove knowledge that is blacklisted
                    return playerKnowledge;
                }
            }
            catch (JsonSyntaxException e) {
                LogHelper.error("Unable to read player knowledge from file '{}'", file.getAbsoluteFile());
            }
            catch (FileNotFoundException e) {
                LogHelper.warn("Unable to find file '{}'", file.getAbsoluteFile());
            }
        }

        if (ConfigurationHandler.Settings.playerKnowledgeTemplateEnabled && !isTemplate) {
            LogHelper.info("Unable to read player knowledge from {}, initializing a new one with template data", file.getName());
            return new PlayerKnowledge(templatePlayerKnowledge);
        }
        else {
            LogHelper.info("Unable to read player knowledge from {}, initializing a new empty one", file.getName());
            return new PlayerKnowledge();
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @return
     */
    private static File getPlayerKnowledgeFile(String playerName) {

        if (playerName != null && !playerName.isEmpty()) {
            return new File(Files.playerDataDirectory, "knowledge" + File.separator + "transmutation" + File.separator + playerName + ".json");
        }

        return null;
    }
}
