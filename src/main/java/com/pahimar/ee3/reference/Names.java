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
        public static final String ASH_INFUSED_STONE = "ashInfusedStone";
        public static final String TRANSMUTATION_TABLET = "transmutationTablet";
        public static final String ASH_INFUSED_STONE_SLAB = "ashInfusedStoneSlab";
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
        public static final String LOOT_BALL = "lootBall";
        public static final String MINIUM_SHARD = "shardMinium";
        public static final String MINIUM_STONE = "stoneMinium";
        public static final String PHILOSOPHERS_STONE = "stonePhilosophers";
        public static final String ALCHEMICAL_UPGRADE = "alchemicalUpgrade";
        public static final String[] ALCHEMICAL_UPGRADE_SUBTYPES = {"verdant", "azure", "minium"};
        public static final String DIVINING_ROD = "diviningRod";
        public static final String ALCHENOMICON = "alchenomicon";
        public static final String ALCHEMICAL_TOME = "alchemicalTome";
        public static final String MATTER = "matter";
        public static final String[] MATTER_SUBTYPES = {"Proto", "Dark", "Corporeal", "Kinetic", "Temporal", "Essentia", "Amorphous", "Void", "Omni"};
        public static final String GEM = "gem";
        public static final String[] GEM_SUBTYPES = {"Black", "Blue", "Green", "Grey", "Purple", "Red", "Yellow"};
        public static final String KNOWLEDGE_SCROLL = "knowledgeScroll";
        public static final String POTION_LETHE = "potionLethe";
    }

    public static final class Fluids
    {
        public static final String MILK = "ee3.milk";
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
        public static final String ITEM_TRANSMUTATION_KNOWLEDGE = "transmutationKnowledge";
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
        public static final String ENERGY_VALUE = "energyValue";
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
        public static final String ALCHENOMICON = "container.ee3:" + Items.ALCHENOMICON;
        public static final String TRANSMUTATION_TABLET = "container.ee3:" + Blocks.TRANSMUTATION_TABLET;
    }

    public static final class Keys
    {
        public static final String CATEGORY = "key.categories.ee3";
        public static final String CHARGE = "key.charge";
        public static final String EXTRA = "key.extra";
        public static final String RELEASE = "key.release";
        public static final String TOGGLE = "key.toggle";
    }

    public static final class Commands
    {
        public static final String BASE_COMMAND = Reference.LOWERCASE_MOD_ID;
        public static final String SET_ENERGY_VALUE = "set-energy-value";
        public static final String SET_ENERGY_VALUE_CURRENT_ITEM = "set-energy-value-current-item";
        public static final String SYNC_ENERGY_VALUES = "sync-energy-values";
        public static final String PLAYER_LEARN_EVERYTHING = "player-learn-everything";
        public static final String PLAYER_LEARN_ITEM = "player-learn-item";
        public static final String PLAYER_LEARN_CURRENT_ITEM = "player-learn-current-item";
        public static final String PLAYER_FORGET_EVERYTHING = "player-forget-everything";
        public static final String PLAYER_FORGET_ITEM = "player-forget-item";
        public static final String PLAYER_FORGET_CURRENT_ITEM = "player-forget-current-item";
        public static final String SET_ITEM_LEARNABLE = "set-item-learnable";
        public static final String SET_ITEM_NOT_LEARNABLE = "set-item-not-learnable";
        public static final String SET_ITEM_RECOVERABLE = "set-item-recoverable";
        public static final String SET_ITEM_NOT_RECOVERABLE = "set-item-not-recoverable";
        public static final String RUN_TEST = "run-tests";
        public static final String DEBUG = "debug";
        public static final String ADMIN_PANEL = "admin";
    }

    public static final class AlchemyArrays
    {
        private static final String ALCHEMY_ARRAY_BASE = "arrays.ee3:";
        public static final String ACCELERANT_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "accelerant";
        public static final String COMBUSTION_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "combustion";
        public static final String CONSTRUCTION_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "construction";
        public static final String CONVEYOR_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "conveyor";
        public static final String DESTRUCTION_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "destruction";
        public static final String GELID_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "gelid";
        public static final String PARTHENOGENESIS_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "parthenogenesis";
        public static final String TRANSFIGURATION_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "transfiguration";
        public static final String TRANSMUTATION_ALCHEMY_ARRAY = ALCHEMY_ARRAY_BASE + "transmutation";
    }
}
