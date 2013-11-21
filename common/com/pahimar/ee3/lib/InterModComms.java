package com.pahimar.ee3.lib;

public class InterModComms {

    // Interacting with the Recipe Registry
    public static final String RECIPE_ADD = "recipe-add";

    // Interacting with the EMC BlackList
    public static final String BLACKLIST_ADD_ENTRY = "blacklist-add-entry";
    public static final String BLACKLIST_REMOVE_ENTRY = "blacklist-remove-entry";

    // Interacting with the EMC value mappings
    public static final String EMC_ASSIGN_VALUE_PRE = "emc-assign-value-pre";
    public static final String EMC_ASSIGN_VALUE_POST = "emc-assign-value-post";
    public static final String EMC_HAS_VALUE = "emc-has-value";
    public static final String EMC_RETURN_HAS_VALUE = "emc-return-has-value";
    public static final String EMC_GET_VALUE = "emc-get-value";
    public static final String EMC_RETURN_GET_VALUE = "emc-return-get-value";
}
