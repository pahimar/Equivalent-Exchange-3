package com.pahimar.ee3.exchange;

public class EnergyComponent implements Comparable<EnergyComponent>
{

    public final EnergyType type;
    public final int weight;

    public EnergyComponent(EnergyType type, int weight)
    {

        this.type = type;

        if (weight > 0)
        {
            this.weight = weight;
        }
        else
        {
            this.weight = -1;
        }
    }

    public EnergyComponent(EnergyType type)
    {

        this(type, 1);
    }

    @Override
    public boolean equals(Object object)
    {

        if (!(object instanceof EnergyComponent))
        {
            return false;
        }

        EnergyComponent energyComponent = (EnergyComponent) object;

        return ((this.type == energyComponent.type) && (this.weight == energyComponent.weight));
    }

    @Override
    public String toString()
    {
        return String.format("<Energy Type: %s, Weight: %s>", type, weight);
    }

    @Override
    public int compareTo(EnergyComponent energyComponent)
    {

        if (energyComponent != null)
        {
            if (this.type == energyComponent.type)
            {
                return (this.weight - energyComponent.weight);
            }
            else
            {
                return this.type.compareTo(energyComponent.type);
            }
        }
        else
        {
            return 1;
        }
    }
}
