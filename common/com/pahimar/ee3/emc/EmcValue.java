package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public final float[] components;

    public EmcValue() {

        this(new float[EmcType.TYPES.length]);
    }

    public EmcValue(int value) {

        this((float) value);
    }

    public EmcValue(float value) {

        this(value, EmcType.DEFAULT);
    }

    public EmcValue(float value, EmcComponent component) {

        this(value, component.type);
    }
    
    public EmcValue(int value, EmcType emcType) {
        this((float) value, emcType);
    }
    
    public EmcValue(float value, EmcType emcType) {

        this.components = new float[EmcType.TYPES.length];
        this.components[emcType.ordinal()] = value;
    }

    public EmcValue(float[] components) {

        this.components = components;
    }

    public EmcValue(int value, List<EmcComponent> componentList) {

        this((float) value, componentList);
    }

    public EmcValue(float value, List<EmcComponent> componentList) {

        this.components = new float[EmcType.TYPES.length];

        List<EmcComponent> collatedComponents = collateComponents(componentList);

        int totalComponents = 0;

        for (EmcComponent component : collatedComponents) {
            if (component.weight > 0) {
                totalComponents += component.weight;
            }
        }

        if (totalComponents > 0) {
            for (EmcComponent component : collatedComponents) {
                if (component.weight > 0) {
                    this.components[component.type.ordinal()] = value * (component.weight * 1F / totalComponents);
                }
            }
        }
        else {
            this.components[EmcType.DEFAULT.ordinal()] = value;
        }
    }

    public float getValue() {

        float sumSubValues = 0;

        for (float subValue : this.components) {
            if (subValue > 0) {
                sumSubValues += subValue;
            }
        }

        return sumSubValues;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EmcValue)) {
            return false;
        }

        return (compareTo((EmcValue) object) == 0);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        
        // TODO Intelligible output
        stringBuilder.append("[");
        
        for (EmcType emcType : EmcType.TYPES) {
            if (components[emcType.ordinal()] > 0) {
                stringBuilder.append(String.format(" %s:%s ", emcType, components[emcType.ordinal()]));
            }
        }
        
        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {

        int hashCode = 1;

        hashCode = 37 * hashCode + Float.floatToIntBits(getValue());
        for (float subValue : components) {
            hashCode = 37 * hashCode + Float.floatToIntBits(subValue);
        }

        return hashCode;
    }

    @Override
    public int compareTo(EmcValue emcValue) {

        if (emcValue instanceof EmcValue) {
            return compareComponents(this.components, emcValue.components);
        }
        else {
            return -1;
        }
    }

    private static List<EmcComponent> collateComponents(List<EmcComponent> uncollatedComponents) {

        Integer[] componentCount = new Integer[EmcType.TYPES.length];

        for (EmcComponent emcComponent : uncollatedComponents) {
            if (componentCount[emcComponent.type.ordinal()] == null) {
                componentCount[emcComponent.type.ordinal()] = new Integer(0);
            }

            if (emcComponent.weight >= 0) {
                componentCount[emcComponent.type.ordinal()] = new Integer(componentCount[emcComponent.type.ordinal()].intValue() + emcComponent.weight);
            }
        }

        List<EmcComponent> collatedComponents = new ArrayList<EmcComponent>();

        for (int i = 0; i < EmcType.TYPES.length; i++) {
            if (componentCount[i] != null) {
                collatedComponents.add(new EmcComponent(EmcType.TYPES[i], componentCount[i].intValue()));
            }
        }

        Collections.sort(collatedComponents);

        return collatedComponents;
    }

    private static int compareComponents(float[] first, float[] second) {

        if (first.length == EmcType.TYPES.length && second.length == EmcType.TYPES.length) {

            for (EmcType emcType : EmcType.TYPES) {
                if (Float.compare(first[emcType.ordinal()], second[emcType.ordinal()]) != 0) {
                    return Float.compare(first[emcType.ordinal()], second[emcType.ordinal()]);
                }
            }

            return 0;
        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}
