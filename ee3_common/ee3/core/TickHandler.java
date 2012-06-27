package ee3.core;

import java.util.EnumSet;

import net.minecraft.src.BaseMod;
import net.minecraft.src.ModLoader;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import ee3.addons.*;
import ee3.core.helper.Helper;
import ee3.lib.Reference;

public class TickHandler implements ITickHandler {

	private static boolean addonsInitialized = false;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) { 
		for (TickType tickType : type) {
			// WORLDLOAD ticks only happen once, so we'll use it to finish initialising things
			if (tickType == TickType.WORLDLOAD) {
				// Initialise 
				if (!addonsInitialized) {
					addonsInitialized = true;
					Helper.initAddons();
				}
			}
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		for (TickType tickType : type) {
			if (tickType == TickType.GAME) {
				
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.GAME, TickType.WORLDLOAD);
	}

	@Override
	public String getLabel() {
		return Reference.MOD_NAME;
	}

}
