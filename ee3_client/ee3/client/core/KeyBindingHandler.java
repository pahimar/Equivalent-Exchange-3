package ee3.client.core;

import org.lwjgl.input.Keyboard;

import ee3.core.mod_EE3;
import ee3.item.ModItems;
import ee3.lib.GuiIds;

import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.Item;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.ModLoader;
import net.minecraft.src.World;

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
		// We only care about keybinding events that happen when the game is "in play"	
		if (!ModLoader.getMinecraftInstance().isGamePaused) {
			
			EntityPlayerSP thePlayer = ModLoader.getMinecraftInstance().thePlayer;
			World theWorld = ModLoader.getMinecraftInstance().theWorld;
			
			if (event.equals(Extra)) {
				if (thePlayer.inventory.getCurrentItem() != null) {
					Item currentItem = thePlayer.inventory.getCurrentItem().getItem();
					if ((currentItem.shiftedIndex == ModItems.miniumStone.shiftedIndex) || (currentItem.shiftedIndex == ModItems.philStone.shiftedIndex)) {
						thePlayer.openGui(mod_EE3.instance(), GuiIds.PORTABLE_CRAFTING, theWorld, (int)thePlayer.posX, (int)thePlayer.posY, (int)thePlayer.posZ);
					}
				}
			}
			else if (event.equals(Charge)) {
				// Check to see if the player is sneaking
				System.out.println("Charge Key Pressed");
			}
			else if (event.equals(Toggle)) {
				System.out.println("Toggle Key Pressed");
			}
			else if (event.equals(Release)) {
				System.out.println("Release Key Pressed");
			}
		}
	}
	
}
