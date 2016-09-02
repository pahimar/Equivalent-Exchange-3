package com.pahimar.ee3.util;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {

    private ResourceLocationHelper() {}

    public static ResourceLocation getResourceLocation(String path) {
        return new ResourceLocation(EquivalentExchange3.MOD_ID, path);
    }

    public static ModelResourceLocation getModelResourceLocation(String path) {
        return new ModelResourceLocation(EquivalentExchange3.MOD_ID + ":" + path);
    }
}
