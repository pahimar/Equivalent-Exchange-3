package com.pahimar.ee3.emc;

public enum EmcType {
    OMNI, CORPOREAL, KINETIC, TEMPORAL, ESSENTIA, AMORPHOUS, VOID;
    
    public static final EmcType[] TYPES = EmcType.values();
    
    public static final EmcType DEFAULT = EmcType.OMNI;
}
