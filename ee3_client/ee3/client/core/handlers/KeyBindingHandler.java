package ee3.client.core.handlers;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.asm.SideOnly;
import ee3.client.lib.KeyBindings;

public class KeyBindingHandler extends KeyBindingRegistry.KeyHandler {
	
	public KeyBindingHandler() {
		super(KeyBindings.gatherKeyBindings(), KeyBindings.gatherIsRepeating());
	}

	@Override
	public String getLabel() {
		return "";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		if (tickEnd) {
			System.out.println(types.toString());
			System.out.println(kb.keyDescription);
		}
		
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) { }

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
