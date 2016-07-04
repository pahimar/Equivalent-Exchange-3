package com.pahimar.ee3.api.exchange;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public final class EnergyValue implements Comparable<EnergyValue> {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###,###,###,###.###");
    private float energyValue;

    public EnergyValue()
    {
        this(0);
    }

    public EnergyValue(Number energyValue) {
        this.energyValue = energyValue.floatValue();
    }

    public float getValue() {
        return this.energyValue;
    }

    public ITextComponent getTextComponent() {
        return new TextComponentString("" + this.getValue());
    }

    @Override
    public String toString() {
        return DECIMAL_FORMAT.format(energyValue);
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof EnergyValue && (compareTo((EnergyValue) object) == 0);
    }

    @Override
    public int compareTo(EnergyValue energyValue) {

        if (energyValue != null) {
            return Float.compare(this.energyValue, energyValue.getValue());
        }
        else {
            return -1;
        }
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setFloat("energyValue", energyValue);
        return nbtTagCompound;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        if (nbtTagCompound.hasKey("energyValue")) {
            this.energyValue = nbtTagCompound.getFloat("energyValue");
        }
    }

    public static NBTTagCompound writeEnergyValueToNBT(EnergyValue energyValue) {

        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        energyValue.writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static EnergyValue loadEnergyValueFromNBT(NBTTagCompound nbtTagCompound) {

        if (nbtTagCompound.hasKey("energyValue")) {
            float energyValue = nbtTagCompound.getFloat("energyValue");
            return new EnergyValue(energyValue);
        }

        return null;
    }

    public static EnergyValue factor(EnergyValue energyValue, Number factor) {

        if ((Float.compare(factor.floatValue(), 0f) != 0) && (energyValue != null)) {
            return new EnergyValue(new BigDecimal(energyValue.getValue() * 1f / factor.floatValue()).setScale(3, BigDecimal.ROUND_HALF_EVEN).floatValue());
        }
        else {
            return null;
        }
    }
}
