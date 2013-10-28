package com.pahimar.ee3.core.handlers;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.core.helper.KeyBindingHelper;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketKeyPressed;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;

/**
 * Equivalent-Exchange-3
 * 
 * KeyBindingHandler
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
                EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
                if (player != null) {
                    ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();

                    if (currentItem != null) {
                        if (currentItem.getItem() instanceof IKeyBound) {
                            if (!KeyBindingHelper.isClientSided(kb.keyDescription)) {
                                PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketKeyPressed(kb.keyDescription)));
                            }
                            else {
                                ((IKeyBound) currentItem.getItem()).doKeyBindingAction(player, currentItem, kb.keyDescription);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {

    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.CLIENT);
    }
}
