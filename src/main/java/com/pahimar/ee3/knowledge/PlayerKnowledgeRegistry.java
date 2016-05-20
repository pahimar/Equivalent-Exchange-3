package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.reference.Comparators;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.util.SerializationHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.io.File;
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
     *  load
     *  save
     */

    public void save() {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            for (String playerName : playerKnowledgeMap.keySet()) {
                SerializationHelper.writeJsonFile(getPlayerKnowledgeFile(playerName), SerializationHelper.GSON.toJson(playerKnowledgeMap.get(playerName)));
            }
        }

        SerializationHelper.writeJsonFile(templatePlayerKnowledgeFile, SerializationHelper.GSON.toJson(templatePlayerKnowledge));
    }

    public void load() {

        // Load template knowledge
    }

    public void loadPlayer(EntityPlayer entityPlayer) {

    }

    public void loadPlayer(File file) {

    }

    private static File getPlayerKnowledgeFile(String playerName) {
        return new File(Files.playerDataDirectory, "knowledge" + File.separator + "transmutation" + File.separator + playerName + ".json");
    }
}
