package com.pahimar.ee3.emc;

public class EmcComponent implements Comparable<EmcComponent> {

    private final EmcType type;
    private final int ratioWeight;

    public EmcComponent(EmcType emcType, int ratioWeight) {

        this.type = emcType;
        this.ratioWeight = ratioWeight;
    }

    public EmcType getType() {

        return type;
    }

    public int getRatioWeight() {

        return ratioWeight;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EmcComponent)) {
            return false;
        }

        EmcComponent emcComponent = (EmcComponent) object;

        return ((this.type == emcComponent.type) && (this.ratioWeight == emcComponent.ratioWeight));
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("<EMC Type: %s, Ratio Weight: %s>", type, ratioWeight));

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(EmcComponent emcComponent) {

        if (emcComponent != null) {
            if (this.type == emcComponent.type) {
                return (this.ratioWeight - emcComponent.ratioWeight);
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
