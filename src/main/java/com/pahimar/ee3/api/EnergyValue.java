package com.pahimar.ee3.api;

import net.minecraft.nbt.NBTTagCompound;

public final class EnergyValue implements Comparable<EnergyValue>
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

    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setFloat("energyValue", energyValue);
        nbtTagCompound.setInteger("energyType", energyType.ordinal());
        return nbtTagCompound;
    }

    public static NBTTagCompound writeEnergyValueToNBT(EnergyValue energyValue)
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        energyValue.writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    public static EnergyValue loadEnergyValueFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound.hasKey("energyValue") && nbtTagCompound.hasKey("energyType"))
        {
            float energyValue = nbtTagCompound.getFloat("energyValue");
            EnergyType energyType = EnergyType.getEnergyTypeFromOrdinal(nbtTagCompound.getInteger("energyType"));

            return new EnergyValue(energyValue, energyType);
        }

        return null;
    }

    public static enum EnergyType
    {
        UNKNOWN, CORPOREAL, KINETIC, TEMPORAL, ESSENTIA, AMORPHOUS, VOID, OMNI;

        public static final EnergyType DEFAULT = EnergyType.CORPOREAL;

        public static EnergyType getEnergyTypeFromOrdinal(int ordinal)
        {
            if (ordinal == CORPOREAL.ordinal())
            {
                return CORPOREAL;
            }
            else if (ordinal == KINETIC.ordinal())
            {
                return KINETIC;
            }
            else if (ordinal == TEMPORAL.ordinal())
            {
                return TEMPORAL;
            }
            else if (ordinal == ESSENTIA.ordinal())
            {
                return ESSENTIA;
            }
            else if (ordinal == AMORPHOUS.ordinal())
            {
                return AMORPHOUS;
            }
            else if (ordinal == VOID.ordinal())
            {
                return VOID;
            }
            else if (ordinal == OMNI.ordinal())
            {
                return OMNI;
            }
            else
            {
                return UNKNOWN;
            }
        }
    }
}
