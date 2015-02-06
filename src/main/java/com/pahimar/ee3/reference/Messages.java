package com.pahimar.ee3.reference;

public final class Messages
{
    public static final String UPGRADES_CHESTS = "tooltip.ee3:upgradesPrefix";
    public static final String NO_OWNER = "tooltip.ee3:none";

    /* Fingerprint check related constants */
    public static final String NO_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running is a development version of the mod, and as such may be unstable and/or incomplete.";
    public static final String INVALID_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running has been modified from the original, and unpredictable things may happen. Please consider re-downloading the original version of the mod.";

    public static final class Commands
    {
        private static final String COMMAND_PREFIX = "command.ee3.";

        public static final String BASE_COMMAND_USAGE = COMMAND_PREFIX + "usage";

        public static final String PLAYER_NOT_FOUND_ERROR = COMMAND_PREFIX + "player-not-found.error";
        public static final String INVALID_NBT_TAG_ERROR = COMMAND_PREFIX + "invalid-nbt-tag.error";

        public static final String PLAYER_LEARN_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_EVERYTHING + ".usage";
        public static final String PLAYER_LEARN_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_EVERYTHING + ".success";

        public static final String PLAYER_LEARN_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_ITEM + ".usage";
        public static final String PLAYER_LEARN_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_LEARN_ITEM + ".success";

        public static final String PLAYER_FORGET_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_EVERYTHING + ".usage";
        public static final String PLAYER_FORGET_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_EVERYTHING + ".success";

        public static final String PLAYER_FORGET_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_ITEM + ".usage";
        public static final String PLAYER_FORGET_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.PLAYER_FORGET_ITEM + ".success";

        public static final String TEMPLATE_LEARN_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_EVERYTHING + ".usage";
        public static final String TEMPLATE_LEARN_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_EVERYTHING + ".success";

        public static final String TEMPLATE_LEARN_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_ITEM + ".usage";
        public static final String TEMPLATE_LEARN_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_LEARN_ITEM + ".success";

        public static final String TEMPLATE_FORGET_EVERYTHING_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_EVERYTHING + ".usage";
        public static final String TEMPLATE_FORGET_EVERYTHING_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_EVERYTHING + ".success";

        public static final String TEMPLATE_FORGET_ITEM_USAGE = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_ITEM + ".usage";
        public static final String TEMPLATE_FORGET_ITEM_SUCCESS = COMMAND_PREFIX + Names.Commands.TEMPLATE_FORGET_ITEM + ".success";

    }

    public static final class Configuration
    {
        public static final String GENERAL_SYNC_THRESHOLD = "sync.threshold";
        public static final String GENERAL_SYNC_THRESHOLD_LABEL = "general.sync.threshold.label";
        public static final String GENERAL_SYNC_THRESHOLD_COMMENT = "general.sync.threshold.comment";

        public static final String SOUND_MODE = "soundMode";
        public static final String SOUND_MODE_LABEL = "general.sound.soundMode.label";
        public static final String SOUND_MODE_COMMENT = "general.sound.soundMode.comment";
    }
}
