package ee3.client.core;

import net.minecraft.src.KeyBinding;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import ee3.client.core.handlers.KeyBindingHandler;
import ee3.client.lib.KeyBindings;
import ee3.common.core.CommonProxy;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerKeyBindingHandler() {
		KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
	}
	
	@Override
	public void setKeyBinding(String name, int value) {
		KeyBindings.addKeyBinding(name, value);
		KeyBindings.addIsRepeating(false);
	}
	
}
