package com.pahimar.ee3.emc;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * EMCEntry
 * 
 * Holds the breakdown of how much, and what kinds, of EMC an object has
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EMCEntry {

    private float cost, recoveryPercentage;
    private boolean learnable, recoverable;
    private Map<EMCType, Float> breakdown;

    public EMCEntry(float cost) {

        this.cost = cost;
        recoveryPercentage = 1F;
        learnable = true;
        recoverable = true;
        breakdown = Collections.synchronizedMap(new HashMap<EMCType, Float>());
    }

    public EMCEntry(float cost, float recoveryPercentage, boolean learnable, boolean recoverable) {

        this.cost = cost;
        this.recoveryPercentage = recoveryPercentage;
        this.learnable = learnable;
        this.recoverable = recoverable;
        breakdown = Collections.synchronizedMap(new HashMap<EMCType, Float>());
    }

    public float getCost() {

        return cost;
    }

    public float getRecoveryPercentage() {

        return recoveryPercentage;
    }

    public boolean isLearnable() {

        return learnable;
    }

    public boolean isRecoverable() {

        return recoverable;
    }

    public Map<EMCType, Float> getEMCBreakDown() {

        return breakdown;
    }

    public float getEMCBreakdownByType(EMCType emcType) {

        if (breakdown.containsKey(emcType)) {
            if (breakdown.get(emcType) != null) {
                return breakdown.get(emcType).floatValue();
            }
        }

        return -1F;
    }

    public void setCost(float cost) {

        this.cost = cost;
    }

    public void setRecoveryPercentage(float recoveryPercentage) {

        this.recoveryPercentage = recoveryPercentage;
    }

    public void setLearnable(boolean learnable) {

        this.learnable = learnable;
    }

    public void setRecoverable(boolean recoverable) {

        this.recoverable = recoverable;
    }

    public void addEMCBreakDown(EMCType emcType, Float breakdownPercentage) {

        if (!(breakdown.containsKey(emcType))) {
            breakdown.put(emcType, breakdownPercentage);
        }
    }

    public void setEMCBreakDown(EMCType emcType, Float breakdownPercentage) {

        if (breakdown.containsKey(emcType)) {
            breakdown.put(emcType, breakdownPercentage);
        }
    }

}
