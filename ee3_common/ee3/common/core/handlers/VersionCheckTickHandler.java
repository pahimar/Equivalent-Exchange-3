package ee3.common.core.handlers;

import java.util.EnumSet;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import ee3.common.core.helper.VersionHelper;
import ee3.common.lib.Reference;

public class VersionCheckTickHandler implements ITickHandler {

	private static boolean initialized = false;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) { }

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (!initialized) {
			for (TickType tickType : type) {
				if (tickType == TickType.CLIENT) {
					if (FMLClientHandler.instance().getClient().currentScreen == null) {
						initialized = true;
						FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage(VersionHelper.getResultMessage());
					}
				}
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
	}
	
}
