package net.minecraft.src.ee3.gui;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.mod_EE3;
import net.minecraft.src.ee3.lib.GuiIds;
import net.minecraft.src.forge.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return mod_EE3.proxy.handleGuiElement(ID, player, world, x, y, z);
	}
}
