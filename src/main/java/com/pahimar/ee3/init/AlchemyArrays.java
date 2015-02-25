package com.pahimar.ee3.init;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.api.AlchemyArrayRegistryProxy;
import com.pahimar.ee3.array.BasicAlchemyArray;

public class AlchemyArrays
{
    public static final AlchemyArray basicAlchemyArray = new BasicAlchemyArray();

    public static void registerAlchemyArrays()
    {
        AlchemyArrayRegistryProxy.registerAlchemyArray(basicAlchemyArray);
    }
}
