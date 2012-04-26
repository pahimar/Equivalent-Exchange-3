package net.minecraft.src.ee3.gui;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.ee3.core.GuiIds;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	/*
	 * Returns true of the GuiId id is for a item and not a Block/TE in the world
	 */
	private static boolean isItemGui(int id) {
		return ((id == GuiIds.PORT_CRAFTING) || (id == GuiIds.MERCURIAL_EYE) || (id == GuiIds.PORT_TRANS_TABLE) || (id == GuiIds.ALCH_BAG));
	}
}
