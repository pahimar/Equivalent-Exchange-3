package com.pahimar.ee3.item;

public class EnergyStack implements Comparable<EnergyStack> {

    public static final String VANILLA_SMELTING_ENERGY_NAME = "vanillaFuelValueUnits";
    public static final int VANILLA_SMELTING_ENERGY_THRESHOLD = 200;

    public String energyName;
    public int stackSize;

    public EnergyStack(String energyName, int stackSize) {

        this.energyName = energyName;
        this.stackSize = stackSize;
    }

    public EnergyStack(String energyName) {

        this(energyName, 1);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("%dxenergyStack.%s", stackSize, energyName));

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EnergyStack)) {
            return false;
        }

        EnergyStack energyStack = (EnergyStack) object;

        if ((this.energyName != null) && (energyStack.energyName != null)) {
            return (stackSize == energyStack.stackSize) && energyName.equalsIgnoreCase(energyStack.energyName);
        }
        else if ((this.energyName == null) && (energyStack.energyName == null)) {
            return (stackSize == energyStack.stackSize);
        }
        else {
            return false;
        }
    }
    
    public static boolean compareEnergyNames(EnergyStack energyStack1, EnergyStack energyStack2) {

        if (energyStack1 != null && energyStack2 != null) {
            if ((energyStack1.energyName != null) && (energyStack2.energyName != null)) {
                return energyStack1.energyName.equalsIgnoreCase(energyStack2.energyName);
            }
        }

        return false;
    }

    @Override
    public int compareTo(EnergyStack energyStack) {

        if (energyStack != null) {
            if ((this.energyName != null) && (energyStack.energyName != null)) {
                if (this.energyName.equalsIgnoreCase(energyStack.energyName)) {
                    return (this.stackSize - energyStack.stackSize);
                }
                else {
                    return this.energyName.compareToIgnoreCase(energyStack.energyName);
                }
            }
            else if ((this.energyName != null) && (energyStack.energyName == null)) {
                return 1;
            }
            else if ((this.energyName == null) && (energyStack.energyName != null)) {
                return -1;
            }
            else {
                return (this.stackSize - energyStack.stackSize);
            }
        }
        else {
            return 1;
        }
    }
}
