package com.pahimar.ee3.emc;


public class EmcComponent {
        
    private final EmcType emcType;
    private final float percentage;
    
    public EmcComponent(EmcType emcType, float percentage) {
        
        this.emcType = emcType;
        this.percentage = percentage;
    }
    
    public EmcType getEmcType() {
        
        return emcType;
    }
    
    public float getPercentage() {
        
        return percentage;
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof EmcComponent)) {
            
            return false;
        }
        
        EmcComponent emcBreakDown = (EmcComponent) object;
        
        return ((this.emcType == emcBreakDown.emcType) && (this.percentage == emcBreakDown.percentage));
    }
    
    @Override
    public String toString() {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(String.format("<EMC Type: %s, Percentage: %s>", emcType, (percentage * 100)));
        
        return stringBuilder.toString();
    }
}
