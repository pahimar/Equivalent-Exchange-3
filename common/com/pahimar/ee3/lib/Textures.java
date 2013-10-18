package com.pahimar.ee3.lib;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

import com.pahimar.ee3.core.helper.ResourceLocationHelper;

/**
 * Equivalent-Exchange-3
 * 
 * Sprites
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Textures {

    // Base file paths
    public static final String MODEL_SHEET_LOCATION = "textures/models/";
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
    public static final ResourceLocation GUI_ALCHEMICAL_STORAGE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalStorage.png");
    public static final ResourceLocation GUI_SHARED_ALCHEMICAL_STORAGE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "sharedAlchemicalStorage.png");
    public static final ResourceLocation GUI_PORTABLE_CRAFTING = new ResourceLocation("textures/gui/container/crafting_table.png");
    public static final ResourceLocation GUI_PORTABLE_TRANSMUTATION = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "portableTransmutation.png");
    public static final ResourceLocation GUI_GLASS_BELL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "glassBell.png");
    public static final ResourceLocation GUI_ALCHEMY_TABLE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemyTable.png");
    public static final ResourceLocation GUI_RENDERING_TANK = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "renderingTank.png");

    // Model textures
    public static final ResourceLocation MODEL_CALCINATOR = ResourceLocationHelper.getResourceLocation(MODEL_SHEET_LOCATION + "calcinator.png");
    public static final ResourceLocation MODEL_ALUDEL = ResourceLocationHelper.getResourceLocation(MODEL_SHEET_LOCATION + "aludel.png");
    public static final ResourceLocation MODEL_ALCHEMICAL_CHEST = ResourceLocationHelper.getResourceLocation(MODEL_SHEET_LOCATION + "alchemicalChest.png");
    public static final ResourceLocation MODEL_GLASS_BELL = ResourceLocationHelper.getResourceLocation(MODEL_SHEET_LOCATION + "aludel.png");
    public static final ResourceLocation MODEL_ALCHEMY_TABLE = ResourceLocationHelper.getResourceLocation(MODEL_SHEET_LOCATION + "alchemyTable.png");
    public static final ResourceLocation MODEL_RENDERING_TANK = ResourceLocationHelper.getResourceLocation(MODEL_SHEET_LOCATION + "renderingTank.png");

    // Effect textures
    public static final ResourceLocation EFFECT_WORLD_TRANSMUTATION = ResourceLocationHelper.getResourceLocation(EFFECTS_LOCATION + "noise.png");

}
