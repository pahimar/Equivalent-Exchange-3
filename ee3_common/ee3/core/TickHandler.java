package ee3.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.logging.Logger;

import net.minecraft.src.BaseMod;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.ModLoader;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import ee3.addons.*;
import ee3.core.helper.Helper;
import ee3.item.ModItems;
import ee3.lib.Reference;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class TickHandler implements ITickHandler {
	private Method HandleItems;
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
				//Get the minum in water handler
				try {
					HandleItems = Class.forName("ee3.client.core.MinumInWaterHandler").getDeclaredMethod("HandleItems");
				} catch (Exception e) {
					try{
						HandleItems = Class.forName("ee3.server.core.MinumInWaterHandler").getDeclaredMethod("HandleItems");
					} catch (Exception f) {
						f.printStackTrace();
					}
				}
			}
			//Handle Minum Shards that are in water
			if(tickType == TickType.GAME){
				try {
					HandleItems.invoke(null);
				} catch (Exception e) {
					e.printStackTrace();
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
