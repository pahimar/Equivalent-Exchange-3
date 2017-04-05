package com.pahimar.ee.util;

import com.pahimar.ee.EquivalentExchange;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {

    private ResourceLocationHelper() {}

    public static ResourceLocation getResourceLocation(String path) {
        return new ResourceLocation(EquivalentExchange.MOD_ID, path);
    }

    public static ModelResourceLocation getModelResourceLocation(String path) {
        return new ModelResourceLocation(EquivalentExchange.MOD_ID + ":" + path);
    }
}
