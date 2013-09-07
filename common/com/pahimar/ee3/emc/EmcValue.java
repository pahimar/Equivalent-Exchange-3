package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * EMCEntry
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EmcValue implements Comparable<EmcValue> {

    public final float value; 
    public final float recoveryPercent;
    private final List<EmcComponent> components;

    public EmcValue() {

        this(0F, 1F, new ArrayList<EmcComponent>());
    }

    public EmcValue(float value) {

        this(value, 1F, new ArrayList<EmcComponent>());
    }

    public EmcValue(float value, float recoveryPercent) {
    	
        this(value, recoveryPercent, new ArrayList<EmcComponent>());
    }
    
    public EmcValue(float value, List<EmcComponent> components) {

        this(value, 1F, collateComponents(components));
    }

    public EmcValue(float value, float recoveryPercent, List<EmcComponent> components) {

        this.value = value;
        this.recoveryPercent = recoveryPercent;
        this.components = collateComponents(components);
    }

    public List<EmcComponent> getComponents() {
        
    	return components;
    }
    
    public EmcComponent getComponentByType(EmcType type) {

        EmcComponent[] componentArray = (EmcComponent[]) components.toArray();
        Arrays.sort(componentArray);
        return componentArray[type.ordinal()];
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EmcValue)) {
            return false;
        }

        EmcValue emcValue = (EmcValue) object;

        return ((value == emcValue.value) && (recoveryPercent == emcValue.recoveryPercent) && (components.equals(emcValue.getComponents())));
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(String.format("V:%s%sRP:%s%s[", value, Strings.TOKEN_DELIMITER, recoveryPercent, Strings.TOKEN_DELIMITER));
        
        List<EmcComponent> componentArray = this.components;
        Collections.sort(componentArray);
        
        int i = 0;
        
        for (EmcComponent component : componentArray) {
        
        	stringBuilder.append(String.format("%s:%s", component.getType(), component.getRatioWeight()));
        	
        	i++;
            
        	if (i < componentArray.size()) {
            	stringBuilder.append(String.format("%s", Strings.TOKEN_DELIMITER));
            }
        }
        
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {

        int hashCode = 1;

        hashCode = 37 * hashCode + Float.floatToIntBits(value);
        hashCode = 37 * hashCode + Float.floatToIntBits(recoveryPercent);
        hashCode = 37 * hashCode + components.hashCode();

        return hashCode;
    }

	@Override
	public int compareTo(EmcValue emcValue) {
		
		if (Float.compare(this.value, emcValue.value) == 0) {
			if (Float.compare(this.recoveryPercent, emcValue.recoveryPercent) == 0) {
				return (this.components.hashCode() - emcValue.components.hashCode());
			}
			else {
				return Float.compare(this.recoveryPercent, emcValue.recoveryPercent);
			}
		}
		else {
			return Float.compare(this.value, emcValue.value);
		}
	}
	
	private static List<EmcComponent> collateComponents(List<EmcComponent> uncollatedComponents) {
		
		Integer[] componentCount = new Integer[7];
		
		for (EmcComponent emcComponent : uncollatedComponents) {
			if (componentCount[emcComponent.getType().ordinal()] == null) {
				componentCount[emcComponent.getType().ordinal()] = new Integer(0);
			}
			
			componentCount[emcComponent.getType().ordinal()] = new Integer(componentCount[emcComponent.getType().ordinal()].intValue() + emcComponent.getRatioWeight());
		}
		
		List<EmcComponent> collatedComponents = new ArrayList<EmcComponent>();
		
		for (int i = 0; i < 7; i++) {
			if (componentCount[i] != null) {
				collatedComponents.add(new EmcComponent(EmcType.values()[i], componentCount[i].intValue()));
			}
		}
		
		return collatedComponents;
	}
}
