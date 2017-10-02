package com.pahimar.ee.util;

import com.pahimar.ee.EquivalentExchange;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

/**
 * TODO Finish Javadoc
 *
 * @author      pahimar
 *
 * @since       3.0.0
 */
public class ResourceLocationUtil {

    /**
     * TODO Finish Javadoc
     *
     * @since   3.0.0
     */
    private ResourceLocationUtil() {
        // NO-OP
    }

    /**
     * TODO Finish Javadoc
     *
     * @param   path
     *          TODO
     *
     * @return  TODO
     *
     * @since   3.0.0
     */
    public static ResourceLocation getResourceLocation(String path) {
        return new ResourceLocation(EquivalentExchange.MOD_ID, path);
    }

    /**
     * TODO Finish Javadoc
     *
     * @param   path
     *          TODO
     *
     * @return  TODO
     *
     * @since   3.0.0
     */
    public static ModelResourceLocation getModelResourceLocation(String path) {
        return new ModelResourceLocation(EquivalentExchange.MOD_ID + ":" + path);
    }
}
