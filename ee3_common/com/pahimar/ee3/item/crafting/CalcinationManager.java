package com.pahimar.ee3.item.crafting;


public class CalcinationManager {

    private static final CalcinationManager calcinationBase = new CalcinationManager();
    
    public static final CalcinationManager calcination() {
        
        return calcinationBase;
    }
}
