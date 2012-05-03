package net.minecraft.src.ee3.core;

import net.minecraft.src.ModLoader;
import net.minecraft.src.ee3.lib.Reference;
import net.minecraft.src.forge.MinecraftForge;

public enum ServerClientProxy {
	CLIENT("net.minecraft.src.ee3.client.EEProxy"),
	SERVER("net.minecraft.src.ee3.server.EEProxy");
	
	private String className;
	
	private ServerClientProxy(String proxyClassName) {
		className = proxyClassName;
	}
	
	private IProxy buildProxy() {
		try {
			return (IProxy)Class.forName(className).newInstance();
		} catch (Exception e) {
			ModLoader.getLogger().severe("[" + Reference.CHANNEL_NAME +  "] A fatal error has occured initializing Equivalent Exchange");
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}
	
	public static IProxy getProxy() {
		if (MinecraftForge.isClient()) {
			return CLIENT.buildProxy();
		}
		else {
			return SERVER.buildProxy();
		}
	}
}
