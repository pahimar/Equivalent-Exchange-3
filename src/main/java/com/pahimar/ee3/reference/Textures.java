package com.pahimar.ee3.reference;

import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.util.ResourceLocation;

public final class Textures
{
    public static final String RESOURCE_PREFIX = Reference.LOWERCASE_MOD_ID + ":";

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
        public static final ResourceLocation TRANSMUTATION_TABLET = ResourceLocationHelper.getResourceLocation(MODEL_TEXTURE_LOCATION + "transmutationTablet.png");
    }

    public static final class Gui
    {
        protected static final String GUI_TEXTURE_LOCATION = "textures/gui/";
        public static final ResourceLocation CALCINATOR = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "calcinator.png");
        public static final ResourceLocation ALUDEL = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "aludel.png");
        public static final ResourceLocation ALCHEMICAL_BAG_SMALL = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "alchemicalBag_small.png");
        public static final ResourceLocation ALCHEMICAL_BAG_MEDIUM = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "alchemicalBag_medium.png");
        public static final ResourceLocation ALCHEMICAL_BAG_LARGE = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "alchemicalBag_large.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_SMALL = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "alchemicalChest_small.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_MEDIUM = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "alchemicalChest_medium.png");
        public static final ResourceLocation ALCHEMICAL_CHEST_LARGE = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "alchemicalChest_large.png");
        public static final ResourceLocation GLASS_BELL = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "glassBell.png");
        public static final ResourceLocation RESEARCH_STATION = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "researchStation.png");
        public static final ResourceLocation RESEARCH_STATION_GYLPH_1 = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "researchStation_Glyph1.png");
        public static final ResourceLocation RESEARCH_STATION_GYLPH_2 = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "researchStation_Glyph2.png");
        public static final ResourceLocation AUGMENTATION_TABLE = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "augmentationTable.png");
        public static final ResourceLocation PORTABLE_CRAFTING = new ResourceLocation("textures/gui/container/crafting_table.png");
        public static final ResourceLocation ALCHEMICAL_TOME = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "alchemicalTome.png");
        public static final ResourceLocation TRANSMUTATION_ARRAY_1 = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "transmutationArray_1.png");
        public static final ResourceLocation TRANSMUTATION_ARRAY_3 = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "transmutationArray_3.png");
        public static final ResourceLocation TRANSMUTATION_ARRAY_5 = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "transmutationArray_5.png");
        public static final ResourceLocation TRANSMUTATION_TABLET = ResourceLocationHelper.getResourceLocation(GUI_TEXTURE_LOCATION + "transmutationTablet.png");

        public static final class Elements
        {
            protected static final String ELEMENT_TEXTURE_LOCATION = GUI_TEXTURE_LOCATION + "elements/";
            public static final ResourceLocation ARROW_LEFT = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "arrowLeft.png");
            public static final ResourceLocation ARROW_RIGHT = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "arrowRight.png");
            public static final ResourceLocation SLIDER_VERTICAL_ENABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "sliderVerticalEnabled.png");
            public static final ResourceLocation SLIDER_VERTICAL_DISABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "sliderVerticalDisabled.png");
            public static final ResourceLocation BUTTON_ENABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonEnabled.png");
            public static final ResourceLocation BUTTON_DISABLED = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonDisabled.png");
            public static final ResourceLocation BUTTON_HOVER = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonHover.png");
            public static final ResourceLocation BUTTON_SORT_OPTION = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonSortOption.png");
            public static final ResourceLocation BUTTON_SORT_ORDER = ResourceLocationHelper.getResourceLocation(ELEMENT_TEXTURE_LOCATION + "buttonSortOrder.png");
        }
    }

    public static final class Effect
    {
        private static final String EFFECTS_LOCATION = "textures/effects/";
        public static final ResourceLocation WORLD_TRANSMUTATION = ResourceLocationHelper.getResourceLocation(EFFECTS_LOCATION + "noise.png");
    }

    public static final class AlchemyArray
    {
        private static final String SYMBOL_TEXTURE_LOCATION = "textures/arrays/";

        public static final ResourceLocation ACCELERANT_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayAccelerant.png");
        public static final ResourceLocation COMBUSTION_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayCombustion.png");
        public static final ResourceLocation CONSTRUCTION_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayConstruction.png");
        public static final ResourceLocation CONVEYOR_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayConveyor.png");
        public static final ResourceLocation DESTRUCTION_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayDestruction.png");
        public static final ResourceLocation GELID_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayGelid.png");
        public static final ResourceLocation PARTHENOGENESIS_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayParthenogenesis.png");
        public static final ResourceLocation TRANSFIGURATION_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayTransfiguration.png");
        public static final ResourceLocation TRANSMUTATION_ALCHEMY_ARRAY = ResourceLocationHelper.getResourceLocation(SYMBOL_TEXTURE_LOCATION + "arrayTransmutation.png");
    }
}
