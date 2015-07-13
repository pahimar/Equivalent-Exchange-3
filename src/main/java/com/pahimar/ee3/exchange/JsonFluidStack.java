package com.pahimar.ee3.exchange;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class JsonFluidStack
{
    public Fluid fluid;
    public int amount;
    public NBTTagCompound tag;

    public JsonFluidStack()
    {
        this.fluid = null;
        this.amount = 0;
        this.tag = null;
    }

    public JsonFluidStack(FluidStack fluidStack)
    {
        this.fluid = fluidStack.getFluid();
        this.amount = fluidStack.amount;
        this.tag = fluidStack.tag;
    }

    @Override
    public String toString()
    {
        return String.format("fluid: %s, amount: %s, tag: %s", fluid, amount, tag);
    }
}
