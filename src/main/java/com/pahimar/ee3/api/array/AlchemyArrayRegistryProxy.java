package com.pahimar.ee3.api.array;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

public class AlchemyArrayRegistryProxy {

    public static boolean register(AlchemyArray alchemyArray) {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getAlchemyArrayRegistry().register(alchemyArray);
        }

        return false;
    }

    public static Set<AlchemyArray> getAlchemyArrays() {

        init();

        if (ee3Mod != null) {
            return EE3Wrapper.ee3mod.getAlchemyArrayRegistry().getAlchemyArrays();
        }

        return null;
    }

    @Mod.Instance("EE3")
    private static Object ee3Mod;

    private static class EE3Wrapper {
        private static EquivalentExchange3 ee3mod;
    }

    private static void init() {
        if (ee3Mod != null) {
            EE3Wrapper.ee3mod = (EquivalentExchange3) ee3Mod;
        }
    }
}
