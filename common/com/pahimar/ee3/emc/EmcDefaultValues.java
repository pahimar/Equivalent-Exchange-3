package com.pahimar.ee3.emc;

import java.util.HashMap;

import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcDefaultValues {

    // Unit components for the various EmcTypes
    public static final EmcComponent OMNI_UNIT_COMPONENT = new EmcComponent(EmcType.OMNI);
    public static final EmcComponent CORPOREAL_UNIT_COMPONENT = new EmcComponent(EmcType.CORPOREAL);
    public static final EmcComponent KINETIC_UNIT_COMPONENT = new EmcComponent(EmcType.KINETIC);
    public static final EmcComponent TEMPORAL_UNIT_COMPONENT = new EmcComponent(EmcType.TEMPORAL);
    public static final EmcComponent ESSENTIA_UNIT_COMPONENT = new EmcComponent(EmcType.ESSENTIA);
    public static final EmcComponent AMORPHOUS_UNIT_COMPONENT = new EmcComponent(EmcType.AMORPHOUS);
    public static final EmcComponent VOID_UNIT_COMPONENT = new EmcComponent(EmcType.VOID);
    
    private static HashMap<CustomWrappedStack, EmcValue> defaultEmcValues = new HashMap<CustomWrappedStack, EmcValue>();

    public static void init() {

    }
}
