package com.pahimar.ee.array;

import com.pahimar.ee.api.array.AlchemyArray;
import com.pahimar.ee.util.LogHelper;
import net.minecraftforge.fml.common.Loader;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.Set;
import java.util.TreeSet;

public class AlchemyArrayRegistry {

    public static final AlchemyArrayRegistry INSTANCE = new AlchemyArrayRegistry();
    public static final Marker ALCHEMY_ARRAY_MARKER = MarkerManager.getMarker("EE_ALCHEMY_ARRAY", LogHelper.MOD_MARKER);
    private Set<AlchemyArray> alchemyArrays;

    private AlchemyArrayRegistry() {
        alchemyArrays = new TreeSet<>();
    }

    public Set<AlchemyArray> getAlchemyArrays() {
        return alchemyArrays;
    }

    public boolean register(AlchemyArray alchemyArray) {

        if (!alchemyArrays.contains(alchemyArray)) {
            LogHelper.trace(ALCHEMY_ARRAY_MARKER, "[{}]: Mod with ID '{}' added alchemy array {}", Loader.instance().getLoaderState(), Loader.instance().activeModContainer().getModId(), alchemyArray);
            return alchemyArrays.add(alchemyArray);
        }

        return false;
    }
}
