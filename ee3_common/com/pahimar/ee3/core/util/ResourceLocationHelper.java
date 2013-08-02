package com.pahimar.ee3.core.util;

import com.pahimar.ee3.lib.Reference;

import net.minecraft.util.ResourceLocation;

public class ResourceLocationHelper {

	public static ResourceLocation getResourceLocation(String path) {

		return new ResourceLocation(Reference.MOD_ID.toLowerCase(), path);
	}
}
