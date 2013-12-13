package com.pahimar.ee3.fluid;

import com.pahimar.ee3.lib.Strings;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;


public class BlockRedWater extends BlockFluidClassic {

    public BlockRedWater(int id){

        super(id, ModFluids.redWater, Material.water);
        this.setUnlocalizedName(Strings.BLOCK_RED_WATER_NAME);


    }

    //Texture code goes here


}
