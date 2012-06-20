package ee3.core;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import ee3.lib.Reference;

public class TickHandler implements ITickHandler {

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) { }

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		// TODO This is where all our tick related code goes :)
		
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.GAME);
	}

	@Override
	public String getLabel() {
		return Reference.MOD_NAME;
	}

}
