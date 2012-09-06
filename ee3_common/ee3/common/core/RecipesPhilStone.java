package ee3.common.core;

import ee3.common.core.handlers.EquivalencyHandler;
import ee3.common.lib.Reference;

public class RecipesPhilStone {

    public static void init() {
        if (Reference.DEBUG_MODE) {
            EquivalencyHandler.debug();
        }
    }
    
}
