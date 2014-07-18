package com.pahimar.ee3.reference;

public final class Messages
{
    public static final String UPGRADES_CHESTS = "tooltip.ee3:upgradesPrefix";
    public static final String NO_OWNER = "tooltip.ee3:none";

    /* Fingerprint check related constants */
    public static final String NO_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running is a development version of the mod, and as such may be unstable and/or incomplete.";
    public static final String INVALID_FINGERPRINT_MESSAGE = "The copy of Equivalent Exchange 3 that you are running has been modified from the original, and unpredictable things may happen. Please consider re-downloading the original version of the mod.";

    public static final class Configuration
    {
        //        Settings.TRANSMUTATION_KNOWLEDGE_MODE = configuration.get("general.transmutation", "mode", 0, "comment", 0, 2).setLanguageKey("general.transmutation.knowledge.mode").getInt();
        public static final String CATEGORY_TRANSMUTATION = "general.transmutation";
        public static final String TRANSMUTATION_KNOWLEDGE_MODE = "knowledgeMode";
        public static final String TRANSMUTATION_KNOWLEDGE_MODE_LABEL = "general.transmutation.knowledge.mode";
        public static final String TRANSMUTATION_KNOWLEDGE_MODE_COMMENT = "general.transmutation.knowledge.comment";
        public static final int TRANSMUTATION_KNOWLEDGE_MODE_DEFAULT = 0;
        public static final int TRANSMUTATION_KNOWLEDGE_MODE_MIN = 0;
        public static final int TRANSMUTATION_KNOWLEDGE_MODE_MAX = 2;

    }
}
