package com.pahimar.ee3.fluid;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.lib.Strings;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;


public class FluidRedWater extends Fluid {

    public FluidRedWater() {


        super(Strings.FLUID_RED_WATER_NAME);
        setDensity(ConfigurationSettings.RED_WATER_DENSITY);
        setViscosity(ConfigurationSettings.RED_WATER_VISCOSITY);
        FluidRegistry.registerFluid(this);

    }

    //This code needs the texture things to work, since im no good at it you can do it <3

    /*

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getStillIcon(){
        return BlockRedWater.redWaterStillIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getFlowingIcon(){
        return BlockRedWater.redWaterFlowingIcon;
    }

*/



}
