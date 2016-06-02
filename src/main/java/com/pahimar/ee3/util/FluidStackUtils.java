package com.pahimar.ee3.util;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Comparator;

public class FluidStackUtils {

    public static final Comparator<FluidStack> COMPARATOR = new Comparator<FluidStack>() {

        @Override
        public int compare(FluidStack fluidStack1, FluidStack fluidStack2) {

            if (fluidStack1 != null && fluidStack2 != null) {
                if (fluidStack1.getFluid() != null &&  fluidStack2.getFluid() != null) {
                    if (FluidRegistry.getFluidName(fluidStack1) != null && FluidRegistry.getFluidName(fluidStack2) != null) {
                        if (FluidRegistry.getFluidName(fluidStack1).equalsIgnoreCase(FluidRegistry.getFluidName(fluidStack2))) {
                            if (fluidStack1.amount == fluidStack2.amount) {
                                if (fluidStack1.tag != null && fluidStack2.tag != null) {
                                    return fluidStack1.tag.hashCode() - fluidStack2.tag.hashCode();
                                }
                                else if (fluidStack1.tag != null) {
                                    return -1;
                                }
                                else if (fluidStack2.tag != null) {
                                    return 1;
                                }
                                else {
                                    return 0;
                                }
                            }
                            else {
                                return fluidStack1.amount - fluidStack2.amount;
                            }
                        }
                        else {
                            return FluidRegistry.getFluidName(fluidStack1).compareToIgnoreCase(FluidRegistry.getFluidName(fluidStack2));
                        }
                    }
                    else if (FluidRegistry.getFluidName(fluidStack1) != null) {
                        return -1;
                    }
                    else if (FluidRegistry.getFluidName(fluidStack2) != null) {
                        return 1;
                    }
                    else {
                        return 0;
                    }

                }
                else if (fluidStack1.getFluid() != null) {
                    return -1;
                }
                else if (fluidStack2.getFluid() != null) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
            else if (fluidStack1 != null) {
                return -1;
            }
            else if (fluidStack2 != null) {
                return 1;
            }
            else {
                return 0;
            }
        }
    };

    /**
     * TODO Finish JavaDoc
     *
     * @param fluidStack1
     * @param fluidStack2
     * @return
     */
    public static int compare(FluidStack fluidStack1, FluidStack fluidStack2) {
        return COMPARATOR.compare(fluidStack1, fluidStack2);
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param fluidStack1
     * @param fluidStack2
     * @return
     */
    public static boolean equals(FluidStack fluidStack1, FluidStack fluidStack2) {
        return compare(fluidStack1, fluidStack2) == 0;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @param fluidStack
     * @return
     */
    public static String toString(FluidStack fluidStack) {

        if (fluidStack != null) {
            return String.format("%sxfluidStack.%s", fluidStack.amount, fluidStack.getFluid().getName());
        }

        return "fluidStack[null]";
    }
}
