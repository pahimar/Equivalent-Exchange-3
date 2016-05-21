package com.pahimar.ee3.knowledge;

import com.google.gson.JsonSyntaxException;
import com.pahimar.ee3.handler.ConfigurationHandler;
import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.TreeMap;

public class PlayerKnowledgeRegistry {

    public static final PlayerKnowledgeRegistry INSTANCE = new PlayerKnowledgeRegistry();

    private final TreeMap<String, PlayerKnowledge> playerKnowledgeMap;
    private final PlayerKnowledge templatePlayerKnowledge;

    private static final String TEMPLATE_PLAYER_KNOWLEDGE_FILEPATH = "knowledge" + File.separator + "transmutation" + Files.TEMPLATE_JSON_FILENAME;
    public static File templatePlayerKnowledgeFile;

    private PlayerKnowledgeRegistry() {

        playerKnowledgeMap = new TreeMap<>(Comparators.STRING_COMPARATOR);
        templatePlayerKnowledge = new PlayerKnowledge();

        // Init some data for testing
        PlayerKnowledge playerKnowledge = new PlayerKnowledge();
        playerKnowledge.learn(new ItemStack(Items.apple));
        playerKnowledge.learn(new ItemStack(Items.arrow));
        playerKnowledge.learn(new ItemStack(Items.baked_potato));
        playerKnowledge.learn(new ItemStack(Items.blaze_powder));
        playerKnowledge.learn(new ItemStack(Items.blaze_rod));
        playerKnowledge.learn(new ItemStack(Items.chainmail_boots));
        playerKnowledge.learn(new ItemStack(Items.carrot));
        playerKnowledge.learn(new ItemStack(Items.carrot_on_a_stick));
        playerKnowledge.learn(new ItemStack(Items.chainmail_boots));
        playerKnowledgeMap.put("pahimar", playerKnowledge);
    }

    /**
     *
     *  doesKnow
     *  canLearn
     *  teach
     *  makeForget
     *  makeForgetAll
     */

    /**
     * TODO Finish JavaDoc
     *
     * @param entityPlayer
     * @return
     */
    public PlayerKnowledge getPlayerKnowledge(EntityPlayer entityPlayer) {

        if (entityPlayer != null) {
            return getPlayerKnowledge(entityPlayer.getDisplayName());
        }
        else {
            throw new IllegalArgumentException("EntityPlayer must be non-null");
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param playerName
     * @return
     */
    public PlayerKnowledge getPlayerKnowledge(String playerName) {

        // TODO Logging
        if (playerName != null && !playerName.isEmpty()) {
            if (!playerKnowledgeMap.containsKey(playerName)) {
                playerKnowledgeMap.put(playerName, load(getPlayerKnowledgeFile(playerName)));
            }

            return playerKnowledgeMap.get(playerName);
        }
        else {
            throw new IllegalArgumentException("Invalid player name - must not be null and must be longer than 0 characters");
        }
    }

    /**
     * TODO Finish JavaDoc
     */
    public void save() {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {

            // Save the template to file
            SerializationHelper.writeJsonFile(templatePlayerKnowledgeFile, SerializationHelper.GSON.toJson(templatePlayerKnowledge));

            // Save every currently loaded player knowledge to file
            for (String playerName : playerKnowledgeMap.keySet()) {
                if (playerName != null && !playerName.isEmpty()) {
                    File playerKnowledgeFile = getPlayerKnowledgeFile(playerName);
                    if (playerKnowledgeFile != null) {
                        SerializationHelper.writeJsonFile(playerKnowledgeFile, SerializationHelper.GSON.toJson(playerKnowledgeMap.get(playerName)));
                    }
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
            // FIXME Priority Number 2
        }
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param file
     * @return
     */
    private PlayerKnowledge load(File file) {

        if (file != null) {
            try {
                String jsonString = SerializationHelper.readJsonFile(file);
                PlayerKnowledge playerKnowledge = SerializationHelper.GSON.fromJson(jsonString, PlayerKnowledge.class);

                if (playerKnowledge != null) {
                   return playerKnowledge;
                }
            }
            catch (JsonSyntaxException | FileNotFoundException e) {
            }
        }

        if (ConfigurationHandler.Settings.playerKnowledgeTemplateEnabled) {
            return new PlayerKnowledge(templatePlayerKnowledge);
        }
        else {
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
        else {
            throw new IllegalArgumentException("Invalid player name - must not be null and must be longer than 0 characters");
        }
    }
}
