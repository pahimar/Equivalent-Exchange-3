package com.pahimar.ee.api.array;

import com.pahimar.ee.EquivalentExchange;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

public final class AlchemyArrayRegistryProxy {

    /**
     * TODO Finish JavaDoc
     *
     * @param alchemyArray
     * @return
     */
    public static boolean register(AlchemyArray alchemyArray) {

        init();

        if (mod != null) {
            return ModWrapper.mod.getAlchemyArrayRegistry().register(alchemyArray);
        }

        return false;
    }

    /**
     * TODO Finish JavaDoc
     *
     * @return
     */
    public static Set<AlchemyArray> getAlchemyArrays() {

        init();

        if (mod != null) {
            return ModWrapper.mod.getAlchemyArrayRegistry().getAlchemyArrays();
        }

        return null;
    }

    @Mod.Instance("ee")
    private static Object mod;

    private static class ModWrapper {
        private static EquivalentExchange mod;
    }

    private static void init() {
        if (mod != null) {
            ModWrapper.mod = (EquivalentExchange) mod;
        }
    }
}
