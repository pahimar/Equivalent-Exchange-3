package com.pahimar.ee3.reference;

public class Names
{
    public static final class Blocks
    {
        public static final String CHALK = "chalkBlock";
        public static final String ALCHEMICAL_CHEST = "alchemicalChest";
        public static final String ALCHEMICAL_FUEL = "alchemicalFuelBlock";
        public static final String ALUDEL = "aludel";
        public static final String GLASS_BELL = "glassBell";
        public static final String CALCINATOR = "calcinator";
        public static final String RESEARCH_STATION = "researchStation";
        public static final String AUGMENTATION_TABLE = "augmentationTable";
        public static final String TRANSMUTATION_SQUARE = "transmutationSquare";
        public static final String ALCHEMY_ARRAY = "alchemyArray";
        public static final String DUMMY_ARRAY = "dummyArray";
    }

    public static final class Items
    {
        public static final String ALCHEMICAL_BAG = "alchemicalBag";
        public static final String ALCHEMICAL_DUST = "alchemicalDust";
        public static final String[] ALCHEMICAL_DUST_SUBTYPES = {"ash", "verdant", "azure", "minium"};
        public static final String ALCHEMICAL_FUEL = "alchemicalFuel";
        public static final String ALCHEMICAL_COAL = "alchemicalCoal";
        public static final String MOBIUS_FUEL = "mobiusFuel";
        public static final String AETERNALIS_FUEL = "aeternalisFuel";
        public static final String[] ALCHEMICAL_FUEL_SUBTYPES = {ALCHEMICAL_COAL, MOBIUS_FUEL, AETERNALIS_FUEL};
        public static final String CHALK = "chalk";
        public static final String INERT_STONE = "stoneInert";
        public static final String MINIUM_SHARD = "shardMinium";
        public static final String MINIUM_STONE = "stoneMinium";
        public static final String PHILOSOPHERS_STONE = "stonePhilosophers";
        public static final String ALCHEMICAL_UPGRADE = "alchemicalUpgrade";
        public static final String[] ALCHEMICAL_UPGRADE_SUBTYPES = {"verdant", "azure", "minium"};
        public static final String DIVINING_ROD = "diviningRod";
        public static final String ALCHEMICAL_TOME = "alchemicalTome";
        public static final String GUIDE = "guide";
        public static final String MATTER = "matter";
        public static final String[] MATTER_SUBTYPES = {"Proto", "Dark", "Corporeal", "Kinetic", "Temporal", "Essentia", "Amorphous", "Void", "Omni"};
        public static final String GEM = "gem";
        public static final String[] GEM_SUBTYPES = {"Black", "Blue", "Green", "Grey", "Purple", "Red", "Yellow"};
    }

    public static final class Materials
    {
        public static final String DARK_MATTER = "matterDark";
    }

    public static final class Tools
    {
        public static final String DARK_MATTER_SHOVEL = "shovelDarkMatter";
        public static final String DARK_MATTER_PICKAXE = "pickAxeDarkMatter";
        public static final String DARK_MATTER_HAMMER = "hammerDarkMatter";
        public static final String DARK_MATTER_AXE = "axeDarkMatter";
        public static final String DARK_MATTER_HOE = "hoeDarkMatter";
        public static final String DARK_MATTER_FISHING_ROD = "fishingRodDarkMatter";
        public static final String DARK_MATTER_SHEARS = "shearsDarkMatter";
    }

    public static final class Weapons
    {
        public static final String DARK_MATTER_BOW = "bowDarkMatter";
        public static final String DARK_MATTER_ARROW = "arrowDarkMatter";
        public static final String DARK_MATTER_SWORD = "swordDarkMatter";
    }

    public static final class Armor
    {
        public static final String DARK_MATTER_ARMOR = "armorDarkMatter";
        public static final String CORPOREAL_MATTER_ARMOR = "armorCorporealMatter";
        public static final String KINETIC_MATTER_ARMOR = "armorKineticMatter";
        public static final String TEMPORAL_MATTER_ARMOR = "armorTemporalMatter";
        public static final String ESSENTIA_MATTER_ARMOR = "armorEssentiaMatter";
        public static final String AMORPHOUS_MATTER_ARMOR = "armorAmorphousMatter";
        public static final String VOID_MATTER_ARMOR = "armorVoidMatter";
        public static final String OMNI_MATTER_ARMOR = "armorOmniMatter";
    }

    public static final class NBT
    {
        public static final String ITEMS = "Items";
        public static final String KNOWLEDGE = "Knowledge";
        public static final String CHARGE_LEVEL = "chargeLevel";
        public static final String MODE = "mode";
        public static final String CRAFTING_GUI_OPEN = "craftingGuiOpen";
        public static final String TRANSMUTATION_GUI_OPEN = "transmutationGuiOpen";
        public static final String ALCHEMICAL_BAG_GUI_OPEN = "alchemicalBagGuiOpen";
        public static final String UUID_MOST_SIG = "UUIDMostSig";
        public static final String UUID_LEAST_SIG = "UUIDLeastSig";
        public static final String DISPLAY = "display";
        public static final String COLOR = "color";
        public static final String STATE = "teState";
        public static final String CUSTOM_NAME = "CustomName";
        public static final String DIRECTION = "teDirection";
        public static final String OWNER = "owner";
        public static final String OWNER_UUID_MOST_SIG = "ownerUUIDMostSig";
        public static final String OWNER_UUID_LEAST_SIG = "ownerUUIDLeastSig";
    }

    public static final class Containers
    {
        public static final String VANILLA_INVENTORY = "container.inventory";
        public static final String VANILLA_CRAFTING = "container.crafting";
        public static final String ALCHEMICAL_BAG = "container.ee3:" + Items.ALCHEMICAL_BAG;
        public static final String ALCHEMICAL_CHEST = "container.ee3:" + Blocks.ALCHEMICAL_CHEST;
        public static final String CALCINATOR = "container.ee3:" + Blocks.CALCINATOR;
        public static final String ALUDEL = "container.ee3:" + Blocks.ALUDEL;
        public static final String RESEARCH_STATION = "container.ee3:" + Blocks.RESEARCH_STATION;
        public static final String GLASS_BELL = "container.ee3:" + Blocks.GLASS_BELL;
        public static final String AUGMENTATION_TABLE = "container.ee3:" + Blocks.AUGMENTATION_TABLE;
        public static final String ALCHEMICAL_TOME = "container.ee3:" + Items.ALCHEMICAL_TOME;
        public static final String TRANSMUTATION_SQUARE = "container.ee3:" + Blocks.TRANSMUTATION_SQUARE;
    }

    public static final class Keys
    {
        public static final String CATEGORY = "key.categories.ee3";
        public static final String CHARGE = "key.charge";
        public static final String EXTRA = "key.extra";
        public static final String RELEASE = "key.release";
        public static final String TOGGLE = "key.toggle";
    }

    public static final class Glyphs
    {
        public static final String BASE_CIRCLE = "glyph.ee3:baseCircle";
        public static final String CIRCLE = "glyph.ee3:circle";
        public static final String DIAMOND = "glyph.ee3:diamond";
        public static final String DOT = "glyph.ee3:dot";
        public static final String HEPTAGON = "glyph.ee3:heptagon";
        public static final String HEXAGON = "glyph.ee3:hexagon";
        public static final String INVERTED_TRIANGLE = "glyph.ee3:invertedTriangle";
        public static final String LINE = "glyph.ee3:line";
        public static final String OCTAGON = "glyph.ee3:octagon";
        public static final String PENTAGON = "glyph.ee3:pentagon";
        public static final String SQUARE = "glyph.ee3:square";
        public static final String TRIANGLE = "glyph.ee3:triangle";
    }
}
