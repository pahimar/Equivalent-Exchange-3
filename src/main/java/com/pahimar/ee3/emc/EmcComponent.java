package com.pahimar.ee3.emc;

public class EmcComponent implements Comparable<EmcComponent> {
    
    public final EmcType type;
    public final int weight;

    public EmcComponent(EmcType type, int weight) {

        this.type = type;
        
        if (weight > 0) {
            this.weight = weight;
        }
        else {
            this.weight = -1;
        }
    }
    
    public EmcComponent(EmcType type) {
        
        this(type, 1);
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EmcComponent)) {
            return false;
        }

        EmcComponent emcComponent = (EmcComponent) object;

        return ((this.type == emcComponent.type) && (this.weight == emcComponent.weight));
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("<EMC Type: %s, Weight: %s>", type, weight));

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(EmcComponent emcComponent) {

        if (emcComponent != null) {
            if (this.type == emcComponent.type) {
                return (this.weight - emcComponent.weight);
            }
            else {
                return this.type.compareTo(emcComponent.type);
            }
        }
        else {
            return 1;
        }
    }
}
