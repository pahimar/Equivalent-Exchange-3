package com.pahimar.ee3.lib;

public class InterModComms {

    // Interacting with the Recipe Registry
    public static final String ADD_RECIPE = "add-recipe";

    // Interacting with the EMC BlackList
    public static final String ADD_BLACKLIST_ENTRY = "add-blacklist-entry";
    public static final String REMOVE_BLACKLIST_ENTRY = "remove-blacklist-entry";

    // Interacting with the EMC value mappings
    public static final String ASSIGN_EMC_VALUE_PRE = "assign-emc-value-pre";
    public static final String ASSIGN_EMC_VALUE_POST = "assign-emc-value-post";
    
    public static final String STACK_VALUE_MAPPING_TAG_NAME = "stackValueMap";
    public static final String COUNT_TAG_NAME = "count";
    public static final String STACK_VALUE_MAPPING_TEMPLATE = "stackValueMapping_%s";
    public static final String STACK_TAG_NAME = "stack";
    public static final String EMC_VALUE_TAG_NAME = "emcValue";
    public static final String EMC_VALUE_COMPONENT_ORDINAL_TEMPLATE = "componentOrdinal_%s";
}
