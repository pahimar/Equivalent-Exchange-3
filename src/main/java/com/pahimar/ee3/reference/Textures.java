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
        public static final ResourceLocation ALCHEMICAL_TOME = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "alchemicalTome.png");
        public static final ResourceLocation TRANSMUTATION_SQUARE = ResourceLocationHelper.getResourceLocation(GUI_SHEET_LOCATION + "transmutationSquare.png");
    }

    public static final class Effect
    {
        private static final String EFFECTS_LOCATION = "textures/effects/";
        public static final ResourceLocation WORLD_TRANSMUTATION = ResourceLocationHelper.getResourceLocation(EFFECTS_LOCATION + "noise.png");
    }

    public static final class Glyph
    {
        private static final String SYMBOL_TEXTURE_LOCATION = "textures/glyphs/";

        public static final ResourceLocation BASE_CIRCLE = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transBaseCircle.png");
        public static final ResourceLocation DOT = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transDot.png");
        public static final ResourceLocation LINE = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transLine.png");
        public static final ResourceLocation CIRCLE = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transCircle.png");
        public static final ResourceLocation TRIANGLE = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transTriangle.png");
        public static final ResourceLocation SQUARE = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transSquare.png");
        public static final ResourceLocation DIAMOND = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transDiamond.png");
        public static final ResourceLocation PENTAGON = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transPentagon.png");
        public static final ResourceLocation HEXAGON = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transHexagon.png");
        public static final ResourceLocation HEPTAGON = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transHeptagon.png");
        public static final ResourceLocation OCTAGON = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "transOctagon.png");
    }
}
