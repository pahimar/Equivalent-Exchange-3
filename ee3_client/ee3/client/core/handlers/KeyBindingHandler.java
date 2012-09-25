package ee3.client.core.handlers;

import java.util.EnumSet;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.KeyBinding;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import ee3.client.lib.KeyBindings;
import ee3.common.EquivalentExchange3;
import ee3.common.item.ModItems;
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
        super(KeyBindings.gatherKeyBindings(), KeyBindings.gatherIsRepeating());
    }

    @Override
    public String getLabel() {
        return Reference.MOD_NAME + " KeyBindingHandler";
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
    	if ( !shouldKeyDownOperationProceed( tickEnd, kb ) ) return;

    	PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketKeyPressed(kb.keyDescription)));
   	
    	// Open the GUI
        EntityClientPlayerMP thePlayer = FMLClientHandler.instance().getClient().thePlayer;
        thePlayer.openGui(EquivalentExchange3.instance, GuiIds.PORTABLE_CRAFTING, thePlayer.worldObj, (int)thePlayer.posX, (int)thePlayer.posY, (int)thePlayer.posZ);

    }
    
    protected boolean shouldKeyDownOperationProceed( boolean isTickEnd, KeyBinding kb ){
    	if ( !isTickEnd ) return false;
    	if ( FMLClientHandler.instance().getClient().currentScreen != null ) return false;
    	if ( kb.keyDescription != Reference.KEYBINDING_EXTRA ) return false;
    	if ( !isCurrentlyEquippedItemMinimumStoneOrPhilospherStone() ) return false; 
    	return true;
    }
    
    private boolean isCurrentlyEquippedItemMinimumStoneOrPhilospherStone(){
    	ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();
    	if ( currentItem == null ) return false;
    	if ( currentItem.getItem().shiftedIndex == ModItems.miniumStone.shiftedIndex ) return true;
    	if ( currentItem.getItem().shiftedIndex == ModItems.philStone.shiftedIndex ) return true;
    	return false;
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {}

    @Override
    public EnumSet<TickType> ticks() {
        return EnumSet.of(TickType.CLIENT);
    }

}
