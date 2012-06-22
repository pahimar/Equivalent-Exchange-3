package ee3.client.core;

import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;
import net.minecraft.src.mod_EE3;

/**
 * TODO Class Description 
 * @author pahimar
 *
 */
public class KeyHandler {
	
	public static KeyBinding Extra;
	public static KeyBinding Charge;
	public static KeyBinding Toggle;
	public static KeyBinding Release;
	
	
	public static void init() {
		Extra = new KeyBinding("ee3.mod.key.extra", 46);
		Charge = new KeyBinding("ee3.mod.key.charge", 47);
		Toggle = new KeyBinding("ee3.mod.key.toggle", 34);
		Release = new KeyBinding("ee3.mod.key.release", 19);
		
		ModLoader.registerKey(mod_EE3.instance(), Extra, false);
		ModLoader.registerKey(mod_EE3.instance(), Charge, false);
		ModLoader.registerKey(mod_EE3.instance(), Toggle, false);
		ModLoader.registerKey(mod_EE3.instance(), Release, false);
	}
	
	public static void keyboardEvent(KeyBinding event) {
		System.out.println(event.keyDescription);
	}
	
}
