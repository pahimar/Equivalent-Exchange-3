package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageKeyPressed;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.util.IKeyBound;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler
{
    private static Key getPressedKeybinding()
    {
        if (Keybindings.charge.getIsKeyPressed())
        {
            return Key.CHARGE;
        }
        else if (Keybindings.extra.getIsKeyPressed())
        {
            return Key.EXTRA;
        }
        else if (Keybindings.release.getIsKeyPressed())
        {
            return Key.RELEASE;
        }
        else if (Keybindings.toggle.getIsKeyPressed())
        {
            return Key.TOGGLE;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event)
    {
        if (getPressedKeybinding() == Key.UNKNOWN)
        {
            return;
        }

        if (FMLClientHandler.instance().getClient().inGameHasFocus)
        {
            if (FMLClientHandler.instance().getClientPlayerEntity() != null)
            {
                EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

                if (entityPlayer.getCurrentEquippedItem() != null)
                {
                    ItemStack currentlyEquippedItemStack = entityPlayer.getCurrentEquippedItem();

                    if (currentlyEquippedItemStack.getItem() instanceof IKeyBound)
                    {
                        if (entityPlayer.worldObj.isRemote)
                        {
                            Network.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeybinding()));
                        }
                        else
                        {
                            ((IKeyBound) currentlyEquippedItemStack.getItem()).doKeyBindingAction(entityPlayer, currentlyEquippedItemStack, getPressedKeybinding());
                        }
                    }
                }
            }
        }
    }
}
