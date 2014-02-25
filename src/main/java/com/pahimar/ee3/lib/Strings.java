package com.pahimar.ee3.lib;

/**
 * Equivalent-Exchange-3
 * <p/>
 * Strings
 *
 * @author pahimar
 */
public class Strings
{
    /* Localization Prefixes */
    public static final String RESOURCE_PREFIX = Reference.MOD_ID.toLowerCase() + ":";

    /* Fingerprint check related constants */
    public static final String NO_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running is a development version of the mod, and as such may be unstable and/or incomplete.";
    public static final String INVALID_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running has been modified from the original, and unpredictable things may happen. Please consider re-downloading the original version of the mod.";

    /* Version check related constants */
    public static final String VERSION_CHECK_INIT_LOG_MESSAGE = "version.ee3:init_log_message";
    public static final String UNINITIALIZED_MESSAGE = "version.ee3:uninitialized";
    public static final String CURRENT_MESSAGE = "version.ee3:current";
    public static final String OUTDATED_MESSAGE = "version.ee3:outdated";
    public static final String GENERAL_ERROR_MESSAGE = "version.ee3:general_error";
    public static final String FINAL_ERROR_MESSAGE = "version.ee3:final_error";
    public static final String MC_VERSION_NOT_FOUND = "version.ee3:mc_version_not_found";

    // Tooltip related localiation keys
    public static final String TOOLTIP_INFUSED_WITH = "tooltip.ee3:infusedPrefix";

    /* NBT related constants */
    public static final String NBT_ITEM_CHARGE_LEVEL_KEY = "itemChargeLevel";
    public static final String NBT_ITEM_MODE_KEY = "itemMode";
    public static final String NBT_ITEM_CRAFTING_GUI_OPEN = "itemCraftingGuiOpen";
    public static final String NBT_ITEM_TRANSMUTATION_GUI_OPEN = "itemTransmutationGuiOpen";
    public static final String NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN = "itemAlchemicalBagGuiOpen";
    public static final String NBT_ITEM_UUID_MOST_SIG = "itemUUIDMostSig";
    public static final String NBT_ITEM_UUID_LEAST_SIG = "itemUUIDLeastSig";
    public static final String NBT_ITEM_DISPLAY = "display";
    public static final String NBT_ITEM_COLOR = "color";
    public static final String NBT_TE_STATE_KEY = "teState";
    public static final String NBT_CUSTOM_NAME = "CustomName";
    public static final String NBT_TE_DIRECTION_KEY = "teDirection";

    /* Block name constants */
    public static final String CALCINATOR_NAME = "calcinator";
    public static final String ALUDEL_NAME = "aludel";
    public static final String ALCHEMICAL_CHEST_NAME = "alchemicalChest";
    public static final String GLASS_BELL_NAME = "glassBell";
    public static final String ALCHEMY_SQUARE_NAME = "alchemySquare";
    public static final String RESEARCH_STATION_NAME = "researchStation";
    public static final String INFUSED_CLOTH_NAME = "infusedCloth";
    public static final String INFUSED_WOOD_NAME = "infusedWood";
    public static final String INFUSED_PLANKS_NAME = "infusedPlanks";

    /* Item name constants */
    public static final String MINIUM_SHARD_NAME = "shardMinium";
    public static final String INERT_STONE_NAME = "stoneInert";
    public static final String MINIUM_STONE_NAME = "stoneMinium";
    public static final String PHILOSOPHERS_STONE_NAME = "stonePhilosophers";
    public static final String ALCHEMICAL_DUST_NAME = "alchemicalDust";
    public static final String[] ALCHEMICAL_DUST_SUBTYPE_NAMES = {"ash", "verdant", "azure", "minium"};
    public static final String ALCHEMICAL_BAG_NAME = "alchemicalBag";
    public static final String CHALK_NAME = "chalk";
    public static final String DIVINING_ROD_NAME = "diviningRod";
    public static final String ALCHEMICAL_COAL_NAME = "alchemicalCoal";
    public static final String MOBIUS_FUEL_NAME = "mobiusFuel";
    public static final String AETERNALIS_FUEL_NAME = "aeternalisFuel";
    public static final String[] ALCHEMICAL_FUEL_SUBTYPE_NAMES = {ALCHEMICAL_COAL_NAME, MOBIUS_FUEL_NAME, AETERNALIS_FUEL_NAME};
    public static final String ALCHEMICAL_FUEL_NAME = "alchemicalFuel";
    public static final String ALCHEMICAL_FUEL_BLOCK_NAME = "alchemicalFuelBlock";

    /* Transmutation cost related constants */
    public static final String TRANSMUTATION_COST = "_cost";
    public static final String TRANSMUTATION_COST_ITEM = "item" + TRANSMUTATION_COST;
    public static final String TRANSMUTATION_COST_BLOCK = "block" + TRANSMUTATION_COST;
    public static final String TRANSMUTATION_COST_MOB = "mob" + TRANSMUTATION_COST;

    /* Container related constants */
    public static final String CONTAINER_CALCINATOR_NAME = "container.ee3:" + CALCINATOR_NAME;
    public static final String CONTAINER_ALUDEL_NAME = "container.ee3:" + ALUDEL_NAME;
    public static final String CONTAINER_ALCHEMICAL_CHEST_NAME = "container.ee3:" + ALCHEMICAL_CHEST_NAME;
    public static final String CONTAINER_ALCHEMICAL_BAG_NAME = "container.ee3:" + ALCHEMICAL_BAG_NAME;
    public static final String CONTAINER_GLASS_BELL_NAME = "container.ee3:" + GLASS_BELL_NAME;
    public static final String CONTAINER_INVENTORY = "container.inventory";
    public static final String CONTAINER_PORTABLE_CRAFTING = "container.crafting";
}
