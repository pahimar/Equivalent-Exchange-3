package ee3.client.core;

import java.util.EnumSet;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.asm.SideOnly;

public class KeyBindingHandler extends KeyBindingRegistry.KeyHandler {

	private static KeyBinding extra = new KeyBinding("ee3.mod.key.extra", Keyboard.KEY_C);
	private static KeyBinding[] keyBindings = {extra};
	private static boolean[] repeatings = {false};
	
	public KeyBindingHandler() {
		super(keyBindings, repeatings);
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
			System.out.println(tickEnd);
			System.out.println(isRepeat);
		}
		
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) { }

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

}
