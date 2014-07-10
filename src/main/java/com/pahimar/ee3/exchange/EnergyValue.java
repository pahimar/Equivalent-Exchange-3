package com.pahimar.ee3.exchange;

public class EnergyValue implements Comparable<EnergyValue>
{
    private final float energyValue;
    private final EnergyType energyType;

    public EnergyValue(int energyValue)
    {
        this((float) energyValue);
    }

    public EnergyValue(float energyValue)
    {
        this(energyValue, EnergyType.DEFAULT);
    }

    public EnergyValue(float energyValue, EnergyType energyType)
    {
        this.energyValue = energyValue;
        this.energyType = energyType;
    }

    public EnergyValue(int energyValue, EnergyType energyType)
    {
        this((float) energyValue, energyType);
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof EnergyValue && (compareTo((EnergyValue) object) == 0);
    }

    @Override
    public String toString()
    {
        return String.format(" %s@%s ", energyValue, energyType);
    }

    @Override
    public int compareTo(EnergyValue energyValue)
    {
        if (energyValue != null)
        {
            if (this.energyType == energyValue.getEnergyType())
            {
                return Float.compare(this.energyValue, energyValue.getEnergyValue());
            }
            else
            {
                return (this.energyType.ordinal() - energyValue.getEnergyType().ordinal());
            }
        }
        else
        {
            return -1;
        }
    }

    public EnergyType getEnergyType()
    {
        return this.energyType;
    }

    public float getEnergyValue()
    {
        return this.energyValue;
    }

    public static enum EnergyType
    {
        OMNI, CORPOREAL, KINETIC, TEMPORAL, ESSENTIA, AMORPHOUS, VOID;

        public static final EnergyType DEFAULT = EnergyType.CORPOREAL;
    }
}
