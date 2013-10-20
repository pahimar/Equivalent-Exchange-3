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

    public final float recoveryPercent;
    public final float[] components;

    public EmcValue() {

        this(1F, new float[EmcType.TYPES.length]);
    }

    public EmcValue(int value) {

        this((float) value);
    }

    public EmcValue(float value) {

        this(value, 1F);
    }

    public EmcValue(float value, EmcComponent component) {

        this(value, 1F, component);
    }

    public EmcValue(float value, float recoveryPercent, EmcComponent component) {

        this.recoveryPercent = recoveryPercent;
        this.components = new float[EmcType.TYPES.length];
        this.components[component.type.ordinal()] = value;
    }

    public EmcValue(int value, float recoveryPercent) {

        this((float) value, recoveryPercent);
    }

    public EmcValue(float value, float recoveryPercent) {

        this.recoveryPercent = recoveryPercent;
        this.components = new float[EmcType.TYPES.length];
        this.components[EmcType.DEFAULT.ordinal()] = value;
    }

    public EmcValue(float[] components) {

        this(1F, components);
    }

    public EmcValue(float recoveryPercent, float[] components) {

        this.recoveryPercent = recoveryPercent;
        this.components = components;
    }

    public EmcValue(int value, List<EmcComponent> componentList) {

        this((float) value, componentList);
    }

    public EmcValue(float value, List<EmcComponent> componentList) {

        this(value, 1F, componentList);
    }

    public EmcValue(float value, float recoveryPercent, List<EmcComponent> componentList) {

        this.recoveryPercent = recoveryPercent;
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
        stringBuilder.append(String.format("%s @ %s", components.toString(), recoveryPercent));

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {

        int hashCode = 1;

        hashCode = 37 * hashCode + Float.floatToIntBits(getValue());
        hashCode = 37 * hashCode + Float.floatToIntBits(recoveryPercent);
        for (float subValue : components) {
            hashCode = 37 * hashCode + Float.floatToIntBits(subValue);
        }

        return hashCode;
    }

    @Override
    public int compareTo(EmcValue emcValue) {

        if (emcValue instanceof EmcValue) {
            if (Float.compare(this.getValue(), emcValue.getValue()) == 0) {
                if (Float.compare(this.recoveryPercent, emcValue.recoveryPercent) == 0) {
                    return compareComponents(this.components, emcValue.components);
                }
                else {
                    return Float.compare(this.recoveryPercent, emcValue.recoveryPercent);
                }
            }
            else {
                return Float.compare(this.getValue(), emcValue.getValue());
            }
        }
        else {
            return 1;
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
