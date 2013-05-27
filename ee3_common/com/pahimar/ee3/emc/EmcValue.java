package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Equivalent-Exchange-3
 * 
 * EMCEntry
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EmcValue {

    private float value, recoveryPercentage;
    private List<EmcComponent> emcComponents;

    public EmcValue(float value) {

        this.value = value;
        recoveryPercentage = 1F;
        emcComponents = new ArrayList<EmcComponent>();
    }

    public EmcValue(float value, float recoveryPercentage) {

        this.value = value;
        this.recoveryPercentage = recoveryPercentage;
        emcComponents = new ArrayList<EmcComponent>();
    }
    
    public EmcValue(float value, float recoveryPercentage, List<EmcComponent> emcComponents) {
        
        this.value = value;
        this.recoveryPercentage = recoveryPercentage;
        this.emcComponents = emcComponents;
    }

    public float getValue() {

        return value;
    }

    public float getRecoveryPercentage() {

        return recoveryPercentage;
    }

    public List<EmcComponent> getComponents() {

        return emcComponents;
    }

    public EmcComponent getComponent(EmcType emcType) {
        
        for (EmcComponent emcComponent : emcComponents) {
            if (emcComponent.getEmcType().equals(emcType)) {
                return emcComponent;
            }
        }

        return null;
    }
    
    public boolean containsEmcType(EmcType emcType) {
        
        for (EmcComponent emcComponent : emcComponents) {
            if (emcComponent.getEmcType().equals(emcType)) {
                return true;
            }
        }
        
        return false;
    }

    public void setValue(float cost) {

        this.value = cost;
    }

    public void setRecoveryPercentage(float recoveryPercentage) {

        this.recoveryPercentage = recoveryPercentage;
    }
    
    public void addEmcComponent(EmcComponent emcComponent) {
        
        if (!containsEmcType(emcComponent.getEmcType())) {
            emcComponents.add(emcComponent);
        }
    }
    
    public void addEmcComponent(EmcType emcType, float percentage) {
        
        addEmcComponent(new EmcComponent(emcType, percentage));
    }
    
    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof EmcValue)) {
            return false;
        }
        
        EmcValue emcValue = (EmcValue) object;
        
        if (value == emcValue.value) {
            if (recoveryPercentage == emcValue.recoveryPercentage) {
                return emcComponents.equals(emcValue.getComponents());
            }
        }
        
        return false;
    }

    @Override
    public String toString() {
        
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(String.format("EMC Value: %s, Recovery Percentage: %s, ", value, (recoveryPercentage * 100)));
        
        for (EmcComponent emcComponent : emcComponents) {
            stringBuilder.append(String.format("%s ", emcComponent));
        }
        
        return stringBuilder.toString();
    }
    
    @Override
    public int hashCode() {
        
        int hashCode = 1;
        
        hashCode = 37 * hashCode + Float.floatToIntBits(value);
        hashCode = 37 * hashCode + Float.floatToIntBits(recoveryPercentage);
        hashCode = 37 * hashCode + emcComponents.hashCode();
        
        return hashCode;
    }
}
