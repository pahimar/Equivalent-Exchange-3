package com.pahimar.ee3.core.util;

public class EnergyStack {

    public static final String VANILLA_SMELTING_ENERGY_NAME = "vanillaFuelValueUnits";
    public static final int VANILLA_SMELTING_ENERGY_THRESHOLD = 200;

    public String energyName;
    public int stackSize;

    public EnergyStack(String energyName, int stackSize) {

        this.energyName = energyName;
        this.stackSize = stackSize;
    }

    public EnergyStack() {

        this("", 0);
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

        return (this.stackSize == energyStack.stackSize && this.energyName.equalsIgnoreCase(energyStack.energyName));
    }

}
