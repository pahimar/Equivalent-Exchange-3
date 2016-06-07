package com.pahimar.ee3.util;

import com.pahimar.ee3.EquivalentExchange3;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper
{
    public static ResourceLocation getResourceLocation(String modId, String path)
    {
        return new ResourceLocation(modId, path);
    }

    public static ResourceLocation getResourceLocation(String path)
    {
        return getResourceLocation(EquivalentExchange3.MOD_ID, path);
    }
}
