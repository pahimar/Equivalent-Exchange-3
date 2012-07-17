package ee3.gui;

import ee3.mod_EE3;
import ee3.lib.GuiIds;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import net.minecraft.src.forge.IGuiHandler;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class GuiHandler implements IGuiHandler {

	@Override
	public Object getGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return mod_EE3.proxy.handleGuiElement(ID, player, world, x, y, z);
	}
}
