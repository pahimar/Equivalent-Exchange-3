package com.pahimar.ee3.reference;

import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public final class Textures
{
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    public static final class Armor
    {
        private static final String ARMOR_SHEET_LOCATION = "textures/armor/";
    }

    public static final class Model
    {
        private static final String MODEL_TEXTURE_LOCATION = "textures/models/";
        public static final ResourceLocation CALCINATOR_IDLE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "calcinator_idle.png");
        public static final ResourceLocation CALCINATOR_ACTIVE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "calcinator_active.png");
        public static final ResourceLocation ALUDEL = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "aludel.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_SMALL = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "alchemicalChest_small.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_MEDIUM = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "alchemicalChest_medium.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_LARGE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "alchemicalChest_large.png");
        public static final ResourceLocation GLASS_BELL = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "aludel.png");
        public static final ResourceLocation RESEARCH_STATION = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "researchStation.png");
        public static final ResourceLocation AUGMENTATION_TABLE = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "augmentationTable.png");
    }

    public static final class Gui
    {
        private static final String GUI_SHEET_LOCATION = "textures/gui/";
        public static final ResourceLocation CALCINATOR = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "calcinator.png");
        public static final ResourceLocation ALUDEL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "aludel.png");
        public static final ResourceLocation ALCHEMICAL_BAG_SMALL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalBag_small.png");
        public static final ResourceLocation ALCHEMICAL_BAG_MEDIUM = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalBag_medium.png");
        public static final ResourceLocation ALCHEMICAL_BAG_LARGE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalBag_large.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_SMALL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalChest_small.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_MEDIUM = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalChest_medium.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_LARGE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalChest_large.png");
        public static final ResourceLocation GLASS_BELL = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "glassBell.png");
        public static final ResourceLocation RESEARCH_STATION = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "researchStation.png");
        public static final ResourceLocation AUGMENTATION_TABLE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "augmentationTable.png");
        public static final ResourceLocation PORTABLE_CRAFTING = new ResourceLocation("textures/gui/container/crafting_table.png");
    }

    public static final class Effect
    {
        private static final String EFFECTS_LOCATION = "textures/effects/";
        public static final ResourceLocation WORLD_TRANSMUTATION = ResourceLocationHelper.getResourceLocation(EFFECTS_LOCATION + "noise.png");
    }
}
