package com.pahimar.ee3.lib;

import com.pahimar.ee3.helper.ResourceLocationHelper;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

/**
 * Equivalent-Exchange-3
 * <p/>
 * Sprites
 *
 * @author pahimar
 */
public class Textures
{

    // Base file paths
    public static final String MODEL_TEXTURE_LOCATION = "textures/models/";
    public static final String ARMOR_SHEET_LOCATION = "textures/armor/";
    public static final String GUI_SHEET_LOCATION = "textures/gui/";
    public static final String EFFECTS_LOCATION = "textures/effects/";

    // Item/Block sprite sheets
    public static final ResourceLocation VANILLA_BLOCK_TEXTURE_SHEET = TextureMap.locationBlocksTexture;
    public static final ResourceLocation VANILLA_ITEM_TEXTURE_SHEET = TextureMap.locationItemsTexture;

    // Armor sprite sheets

    // GUI textures
    public static final ResourceLocation GUI_CALCINATOR = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "calcinator.png");
    public static final ResourceLocation GUI_ALUDEL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "aludel.png");
    public static final ResourceLocation GUI_ALCHEMICAL_STORAGE_SMALL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalStorage_Small.png");
    public static final ResourceLocation GUI_ALCHEMICAL_STORAGE_MEDIUM = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalStorage_Medium.png");
    public static final ResourceLocation GUI_ALCHEMICAL_STORAGE_LARGE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalStorage_Large.png");
    public static final ResourceLocation GUI_PORTABLE_CRAFTING = new ResourceLocation("textures/gui/container/crafting_table.png");
    public static final ResourceLocation GUI_GLASS_BELL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "glassBell.png");

    // Model textures
    public static final ResourceLocation MODEL_CALCINATOR = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "calcinator.png");
    public static final ResourceLocation MODEL_ALUDEL = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "aludel.png");
    public static final ResourceLocation MODEL_ALCHEMICAL_CHEST_SMALL = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "alchemicalChest_Small.png");
    public static final ResourceLocation MODEL_ALCHEMICAL_CHEST_MEDIUM = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "alchemicalChest_Medium.png");
    public static final ResourceLocation MODEL_ALCHEMICAL_CHEST_LARGE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "alchemicalChest_Large.png");
    public static final ResourceLocation MODEL_GLASS_BELL = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "aludel.png");
    public static final ResourceLocation MODEL_RESEARCH_STATION = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "researchStation.png");

    // Effect textures
    public static final ResourceLocation EFFECT_WORLD_TRANSMUTATION = ResourceLocationHelper.getResourceLocation(EFFECTS_LOCATION + "noise.png");
}
