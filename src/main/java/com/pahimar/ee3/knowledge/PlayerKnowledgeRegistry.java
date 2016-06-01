package com.pahimar.ee3.knowledge;

import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.handler.ConfigurationHandler;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.LogHelper;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

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

    public static final Marker PLAYER_KNOWLEDGE_MARKER = MarkerManager.getMarker("EE3_PLAYER_KNOWLEDGE", LogHelper.MOD_MARKER);
    public static final Marker PLAYER_LEARN_KNOWLEDGE = MarkerManager.getMarker("EE3_PLAYER_TEACH_KNOWLEDGE", PLAYER_KNOWLEDGE_MARKER);
    public static final Marker PLAYER_FORGET_KNOWLEDGE_MARKER = MarkerManager.getMarker("EE3_PLAYER_FORGET_KNOWLEDGE", PLAYER_KNOWLEDGE_MARKER);
    public static final Marker PLAYER_FORGET_ALL_KNOWLEDGE_MARKER = MarkerManager.getMarker("EE3_PLAYER_FORGET_ALL_KNOWLEDGE", PLAYER_FORGET_KNOWLEDGE_MARKER);

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
        return player != null && doesPlayerKnow(player.getDisplayName(), object);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param object
     * @return
     */
    public boolean doesPlayerKnow(String playerName, Object object) {

        if (getPlayerKnowledge(playerName) != null) {
            return getPlayerKnowledge(playerName).isKnown(object);
        }

        return false;
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
            return canPlayerLearn(entityPlayer.getDisplayName(), object);
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

        if (getPlayerKnowledge(playerName) != null) {
            return !getPlayerKnowledge(playerName).isKnown(object) && BlacklistRegistryProxy.isLearnable(object);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @param object
     */
    public void teachPlayer(EntityPlayer entityPlayer, Object object) {

        if (entityPlayer != null) {
            teachPlayer(entityPlayer.getDisplayName(), object);
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
            getPlayerKnowledge(playerName).learn(object);
            LogHelper.trace(PLAYER_LEARN_KNOWLEDGE, "Player {} learned {}", playerName, object);
            save(playerName);
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
            teachPlayer(entityPlayer.getDisplayName(), objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param objects
     */
    public void teachPlayer(String playerName, Collection<?> objects) {

        if (objects != null) {

            PlayerKnowledge playerKnowledge = getPlayerKnowledge(playerName);

            if (playerKnowledge != null) {
                for (Object object : objects){
                    getPlayerKnowledge(playerName).learn(object);
                    LogHelper.trace(PLAYER_LEARN_KNOWLEDGE, "Player {} learned {}", playerName, object);
                }
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
            makePlayerForget(entityPlayer.getDisplayName(), object);
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
            getPlayerKnowledge(playerName).forget(object);
            LogHelper.trace(PLAYER_FORGET_KNOWLEDGE_MARKER, "Player {} forgot {}", playerName, object);
            save(playerName);
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
            makePlayerForget(entityPlayer.getDisplayName(), objects);
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @param objects
     */
    public void makePlayerForget(String playerName, Collection<?> objects) {

        if (objects != null) {

            PlayerKnowledge playerKnowledge = getPlayerKnowledge(playerName);

            if (playerKnowledge != null) {
                for (Object object : objects) {
                    getPlayerKnowledge(playerName).forget(object);
                    LogHelper.trace(PLAYER_FORGET_KNOWLEDGE_MARKER, "Player {} forgot {}", playerName, object);
                }
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
            makePlayerForgetAll(entityPlayer.getDisplayName());
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     */
    public void makePlayerForgetAll(String playerName) {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            if (playerName != null && !playerName.isEmpty()) {
                playerKnowledgeMap.put(playerName, new PlayerKnowledge());
                LogHelper.trace(PLAYER_FORGET_ALL_KNOWLEDGE_MARKER, "Player {} forget everything", playerName);
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
            return getKnownItemStacks(entityPlayer.getDisplayName());
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
            return getPlayerKnowledge(entityPlayer.getDisplayName());
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
            SerializationHelper.writeJsonFile(templatePlayerKnowledgeFile, SerializationHelper.GSON.toJson(templatePlayerKnowledge));

            // Save every currently loaded player knowledge to file
            for (String playerName : playerKnowledgeMap.keySet()) {
                save(playerName);
            }
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
