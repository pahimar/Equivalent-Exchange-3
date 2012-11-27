package ee3.client.core.handlers;

import java.util.EnumSet;
import java.util.logging.Level;

import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.client.core.helper.KeyBindingHelper;
import ee3.common.EquivalentExchange3;
import ee3.common.core.helper.LogHelper;
import ee3.common.item.ITransmutationStone;
import ee3.common.item.ModItems;
import ee3.common.lib.ConfigurationSettings;
import ee3.common.lib.GuiIds;
import ee3.common.lib.Reference;
import ee3.common.network.PacketEE;
import ee3.common.network.PacketKeyPressed;
import ee3.common.network.PacketTypeHandler;

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
        super(KeyBindingHelper.gatherKeyBindings(), KeyBindingHelper.gatherIsRepeating());
    }

    @Override
    public String getLabel() {
    	return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        // Only operate at the end of the tick
        if (tickEnd) {
            // If we are not in a GUI of any kind, continue execution
            if (FMLClientHandler.instance().getClient().inGameHasFocus) {
                // TODO Clean this up properly
                if (kb.keyDescription == Reference.KEYBINDING_EXTRA) {
                    ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();
                    
                    if (currentItem != null) {
                        if (currentItem.getItem() instanceof ITransmutationStone) {
                            ((ITransmutationStone)currentItem.getItem()).openPortableCrafting(kb.keyDescription);
                        }
                    }
                }
                else if (kb.keyDescription == Reference.KEYBINDING_TOGGLE) {
                    ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();
                    
                    if (currentItem != null) {
                        if (currentItem.getItem() instanceof ITransmutationStone) {
                            ConfigurationSettings.ENABLE_OVERLAY_PHILOSOPHER_STONE = !ConfigurationSettings.ENABLE_OVERLAY_PHILOSOPHER_STONE;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {}

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

    private static String getLocalizedKey(String key) {
    	return LanguageRegistry.instance().getStringLocalization(key);
    }
}
