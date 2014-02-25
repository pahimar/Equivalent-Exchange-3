package com.pahimar.ee3.client.handler;

import java.util.EnumSet;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.network.packet.PacketEEKeyPressed;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * <p/>
 * KeyBindingHandler
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class KeyBindingHandler
{

    @SubscribeEvent
    public void keyDown(InputEvent event, KeyBinding kb, boolean tickEnd, boolean isRepeat)
    {
        // Only operate at the end of the tick
        if (tickEnd)
        {
            // If we are not in a GUI of any kind, continue execution
            if (FMLClientHandler.instance().getClient().inGameHasFocus)
            {
                EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
                if (player != null)
                {
                    ItemStack currentItem = FMLClientHandler.instance().getClient().thePlayer.getCurrentEquippedItem();

                    if (currentItem != null)
                    {
                        if (currentItem.getItem() instanceof IKeyBound)
                        {
                            if (player.worldObj.isRemote)
                            {
                            	EquivalentExchange3.packetpipeline.sendToServer(new PacketEEKeyPressed(kb.getKeyDescription()));
                            }
                            else
                            {
                                ((IKeyBound) currentItem.getItem()).doKeyBindingAction(player, currentItem, kb.getKeyDescription());
                            }
                        }
                    }
                }
            }
        }
    }
}
