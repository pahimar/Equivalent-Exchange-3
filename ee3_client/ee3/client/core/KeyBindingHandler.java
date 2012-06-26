package ee3.client.core;

import org.lwjgl.input.Keyboard;

import ee3.core.mod_EE3;

import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class KeyBindingHandler {
	
	public static KeyBinding Extra;
	public static KeyBinding Charge;
	public static KeyBinding Toggle;
	public static KeyBinding Release;
	
	public static void init() {
		Extra = new KeyBinding("ee3.mod.key.extra", Keyboard.KEY_C);
		Charge = new KeyBinding("ee3.mod.key.charge", Keyboard.KEY_V);
		Toggle = new KeyBinding("ee3.mod.key.toggle", Keyboard.KEY_G);
		Release = new KeyBinding("ee3.mod.key.release", Keyboard.KEY_R);
		
		ModLoader.registerKey(mod_EE3.instance(), Extra, false);
		ModLoader.registerKey(mod_EE3.instance(), Charge, false);
		ModLoader.registerKey(mod_EE3.instance(), Toggle, false);
		ModLoader.registerKey(mod_EE3.instance(), Release, false);
	}
	
	public static void keyboardEvent(KeyBinding event) {
		System.out.println(event.keyDescription);
	}
	
}
