package ee3.client.core.handlers;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.asm.SideOnly;
import ee3.client.lib.KeyBindings;
import ee3.common.lib.Reference;
import ee3.common.lib.Sounds;

/**
* KeyBindingHandler
* 
* Client specific handler for handling keybinding related events
* 
* @author pahimar
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*
*/
public class KeyBindingHandler extends KeyBindingRegistry.KeyHandler {
	
	public KeyBindingHandler() {
		super(KeyBindings.gatherKeyBindings(), KeyBindings.gatherIsRepeating());
	}

	@Override
	public String getLabel() {
		return Reference.MOD_NAME + " KeyBindingHandler";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		// Only operate at the end of the tick
		if (tickEnd) {
			// If we are not in a GUI of any kind, continue execution
			if (FMLClientHandler.instance().getClient().currentScreen == null) {
				
			}
		}
		
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) { }

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
