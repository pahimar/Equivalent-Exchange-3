package com.pahimar.ee3.reference;

public final class Messages
{
    public static final String OWNER_SET_TO_SELF = "misc.ee3:owner-set-to-self";
    public static final String ENERGY_VALUE = "misc.ee3:energy-value";

    /* Fingerprint check related constants */
    public static final String NO_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running is a development version of the mod, and as such may be unstable and/or incomplete.";
    public static final String INVALID_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running has been modified from the original, and unpredictable things may happen. Please consider re-downloading the original version of the mod.";

    public static final class Gui
    {
        private static final String GUI_PREFIX = "container.ee3:";

        public static final String NO_KNOWN_TRANSMUTATIONS = GUI_PREFIX + "alchemicalTome.noTransmutationsKnown";
    }

    public static final class Tooltips
    {
        private static final String TOOLTIP_PREFIX = "tooltip.ee3:";

        public static final String UPGRADES_CHESTS = TOOLTIP_PREFIX + "upgradesPrefix";
        public static final String ITEM_BELONGS_TO = TOOLTIP_PREFIX + "belongsTo";
        public static final String ITEM_BELONGS_TO_NO_ONE = TOOLTIP_PREFIX + "belongsToNoOne";
        public static final String SMALL = TOOLTIP_PREFIX + "small";
        public static final String MEDIUM = TOOLTIP_PREFIX + "medium";
        public static final String LARGE = TOOLTIP_PREFIX + "large";
        public static final String SORT_BY_DISPLAY_NAME = TOOLTIP_PREFIX + "sortByDisplayName";
        public static final String SORT_BY_ENERGY_VALUE = TOOLTIP_PREFIX + "sortByEnergyValue";
        public static final String SORT_BY_ID = TOOLTIP_PREFIX + "sortByID";
        public static final String SORT_ASCENDING = TOOLTIP_PREFIX + "sortAscending";
        public static final String SORT_DESCENDING = TOOLTIP_PREFIX + "sortDescending";
    }

    public static final class Commands
    {
        private static final String COMMAND_PREFIX = "commands.ee3.";

        public static final String BASE_COMMAND_USAGE = COMMAND_PREFIX + "usage";

        public static final String PLAYER_NOT_FOUND_ERROR = COMMAND_PREFIX + "player-not-found.error";
        public static final String INVALID_NBT_TAG_ERROR = COMMAND_PREFIX + "invalid-nbt-tag.error";
        public static final String NO_ITEM = COMMAND_PREFIX + "no-item.error";

        public static final String SET_ENERGY_VALUE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE + ".usage";
        public static final String SET_ENERGY_VALUE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE + ".success";

        public static final String SET_ENERGY_VALUE_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE_CURRENT_ITEM + ".usage";
        public static final String SET_ENERGY_VALUE_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ENERGY_VALUE_CURRENT_ITEM + ".success";

        public static final String SYNC_ENERGY_VALUES_USAGE = COMMAND_PREFIX + Names.Commands.SYNC_ENERGY_VALUES + ".usage";
        public static final String SYNC_ENERGY_VALUES_SUCCESS = COMMAND_PREFIX + Names.Commands.SYNC_ENERGY_VALUES + ".success";
        public static final String SYNC_ENERGY_VALUES_DENIED = COMMAND_PREFIX + Names.Commands.SYNC_ENERGY_VALUES + ".denied";

        public static final String PLAYER_LEARN_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_EVERYTHING + ".usage";
        public static final String PLAYER_LEARN_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_EVERYTHING + ".success";

        public static final String PLAYER_LEARN_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_ITEM + ".usage";
        public static final String PLAYER_LEARN_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_ITEM + ".success";

        public static final String PLAYER_LEARN_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_CURRENT_ITEM + ".usage";
        public static final String PLAYER_LEARN_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_CURRENT_ITEM + ".success";

        public static final String PLAYER_FORGET_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_EVERYTHING + ".usage";
        public static final String PLAYER_FORGET_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_EVERYTHING + ".success";

        public static final String PLAYER_FORGET_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_ITEM + ".usage";
        public static final String PLAYER_FORGET_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_ITEM + ".success";

        public static final String PLAYER_FORGET_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_CURRENT_ITEM + ".usage";
        public static final String PLAYER_FORGET_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_CURRENT_ITEM + ".success";

        public static final String TEMPLATE_LEARN_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_EVERYTHING + ".usage";
        public static final String TEMPLATE_LEARN_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_EVERYTHING + ".success";

        public static final String TEMPLATE_LEARN_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_ITEM + ".usage";
        public static final String TEMPLATE_LEARN_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_ITEM + ".success";

        public static final String TEMPLATE_LEARN_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_CURRENT_ITEM + ".usage";
        public static final String TEMPLATE_LEARN_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_CURRENT_ITEM + ".success";

        public static final String TEMPLATE_FORGET_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_EVERYTHING + ".usage";
        public static final String TEMPLATE_FORGET_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_EVERYTHING + ".success";

        public static final String TEMPLATE_FORGET_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_ITEM + ".usage";
        public static final String TEMPLATE_FORGET_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_ITEM + ".success";

        public static final String TEMPLATE_FORGET_CURRENT_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_CURRENT_ITEM + ".usage";
        public static final String TEMPLATE_FORGET_CURRENT_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_CURRENT_ITEM + ".success";

        public static final String SET_ITEM_LEARNABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_LEARNABLE + ".usage";
        public static final String SET_ITEM_LEARNABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_LEARNABLE + ".success";

        public static final String SET_ITEM_NOT_LEARNABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_LEARNABLE + ".usage";
        public static final String SET_ITEM_NOT_LEARNABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_LEARNABLE + ".success";

        public static final String SET_ITEM_RECOVERABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_RECOVERABLE + ".usage";
        public static final String SET_ITEM_RECOVERABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_RECOVERABLE + ".success";

        public static final String SET_ITEM_NOT_RECOVERABLE_USAGE = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_RECOVERABLE + ".usage";
        public static final String SET_ITEM_NOT_RECOVERABLE_SUCCESS = COMMAND_PREFIX + Names.Commands.SET_ITEM_NOT_RECOVERABLE + ".success";

        public static final String RUN_TEST_USAGE = COMMAND_PREFIX + Names.Commands.RUN_TEST + ".usage";
        public static final String RUN_TESTS_SUCCESS = COMMAND_PREFIX + Names.Commands.RUN_TEST + ".success";
        public static final String RUN_TESTS_NOT_FOUND = COMMAND_PREFIX + Names.Commands.RUN_TEST + ".notfound";
    }

    public static final class Configuration
    {
        public static final String GENERAL_SYNC_THRESHOLD = "sync.threshold";
        public static final String GENERAL_SYNC_THRESHOLD_LABEL = "general.sync.threshold.label";
        public static final String GENERAL_SYNC_THRESHOLD_COMMENT = "general.sync.threshold.comment";

        public static final String ITEMS_MINIUM_STONE_DURABILITY = "miniumStone.durability";
        public static final String ITEMS_MINIUM_STONE_DURABILITY_LABEL = "item.miniumStone.durability.label";
        public static final String ITEMS_MINIUM_STONE_DURABILITY_COMMENT = "item.miniumStone.durability.comment";

        public static final String SOUND_MODE = "soundMode";
        public static final String SOUND_MODE_LABEL = "general.sound.soundMode.label";
        public static final String SOUND_MODE_COMMENT = "general.sound.soundMode.comment";

        public static final String ABILITIES_ONLY_LOAD_FILE = "abilities.onlyLoadFile";
        public static final String ABILITIES_ONLY_LOAD_FILE_LABEL = "general.abilities.onlyLoadFile.label";
        public static final String ABILITIES_ONLY_LOAD_FILE_COMMENT = "general.abilities.onlyLoadFile.comment";

        public static final String REGENERATE_ENERGYVALUES_WHEN = "energyvalues.regenerateEnergyValuesWhen";
        public static final String REGENERATE_ENERGYVALUES_WHEN_LABEL = "general.energyvalues.regenerateEnergyValuesWhen.label";
        public static final String REGENERATE_ENERGYVALUES_WHEN_COMMENT = "general.energyvalues.regenerateEnergyValuesWhen.comment";

        public static final String LOG_TRACE_TO_INFO = "debug.logTraceToInfo";
        public static final String LOG_TRACE_TO_INFO_LABEL = "debug.logTraceToInfo.label";
        public static final String LOG_TRACE_TO_INFO_COMMENT = "debug.logTraceToInfo.comment";
    }
}
