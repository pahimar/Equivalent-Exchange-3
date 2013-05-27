package com.pahimar.ee3.emc;

import java.util.List;

import com.pahimar.ee3.item.CustomStackWrapper;


public class EquivalencyGroup {
    
    private EmcValue emcValue;
    private List<CustomStackWrapper> equivalentItems;
    
    public EquivalencyGroup() {
        
    }
    
    @Override
    // TODO: Finish
    public boolean equals(Object object) {
        
        if (!(object instanceof EquivalencyGroup)) {
            return false;
        }
        
        return false;
    }
    
    @Override
    // TODO: Finish
    public String toString() {

        return "";
    }

    @Override
    // TODO: Finish
    public int hashCode() {
        
        int hashCode = 1;
        
        return hashCode;
    }
}
