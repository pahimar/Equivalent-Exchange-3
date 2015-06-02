package com.pahimar.ee3.init;

import com.pahimar.ee3.api.array.AlchemyArray;
import com.pahimar.ee3.api.array.AlchemyArrayRegistryProxy;
import com.pahimar.ee3.array.*;

public class AlchemyArrays
{
    public static final AlchemyArray accelerantAlchemyArray = new AlchemyArrayAccelerant();
    public static final AlchemyArray combustionAlchemyArray = new AlchemyArrayCombustion();
    public static final AlchemyArray constructionAlchemyArray = new AlchemyArrayConstruction();
    public static final AlchemyArray conveyorAlchemyArray = new AlchemyArrayConveyor();
    public static final AlchemyArray destructionAlchemyArray = new AlchemyArrayDestruction();
    public static final AlchemyArray gelidAlchemyArray = new AlchemyArrayGelid();
    public static final AlchemyArray parthenogenesisAlchemyArray = new AlchemyArrayParthenogenesis();
    public static final AlchemyArray transfigurationAlchemyArray = new AlchemyArrayTransfiguration();
    public static final AlchemyArray transmutationAlchemyArray = new AlchemyArrayTransmutation();

    public static void registerAlchemyArrays()
    {
        AlchemyArrayRegistryProxy.registerAlchemyArray(accelerantAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(combustionAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(constructionAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(conveyorAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(destructionAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(gelidAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(parthenogenesisAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(transfigurationAlchemyArray);
        AlchemyArrayRegistryProxy.registerAlchemyArray(transmutationAlchemyArray);
    }
}
