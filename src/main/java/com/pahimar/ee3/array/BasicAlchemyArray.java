package com.pahimar.ee3.array;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BasicAlchemyArray extends AlchemyArray
{
    public BasicAlchemyArray()
    {
        super(Textures.AlchemyArray.BASIC_ALCHEMY_ARRAY, Names.AlchemyArrays.BASIC_ALCHEMY_ARRAY);
    }

    @Override
    public void onArrayActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        LogHelper.info("You attempted to perform alchemy!");
    }
}
