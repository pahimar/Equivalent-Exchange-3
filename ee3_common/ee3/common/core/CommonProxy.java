package ee3.common.core;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;

/**
* CommonProxy
* 
* The common proxy class between client and server. Client proxy extends this for further client specific functionality
* 
* @author pahimar
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*
*/
public class CommonProxy implements IGuiHandler {

	public void registerKeyBindingHandler() { }
	
	public void setKeyBinding(String name, int value) { }
	
	public void registerSoundHandler() { }
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		return null;
	}

}
