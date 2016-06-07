package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.client.settings.Keybindings;
import com.pahimar.ee3.network.Network;
import com.pahimar.ee3.network.message.MessageKeyPressed;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.util.IKeyBound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

    private static Key getPressedKeybinding() {

        if (Keybindings.CHARGE.isPressed()) {
            return Key.CHARGE;
        }
        else if (Keybindings.EXTRA.isPressed()) {
            return Key.EXTRA;
        }
        else if (Keybindings.RELEASE.isPressed()) {
            return Key.RELEASE;
        }
        else if (Keybindings.TOGGLE.isPressed()) {
            return Key.TOGGLE;
        }

        return Key.UNKNOWN;
    }

    @SubscribeEvent
    public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {

        if (getPressedKeybinding() == Key.UNKNOWN) {
            return;
        }

        if (FMLClientHandler.instance().getClient().inGameHasFocus && FMLClientHandler.instance().getClientPlayerEntity() != null) {

            EntityPlayer entityPlayer = FMLClientHandler.instance().getClientPlayerEntity();

            if (entityPlayer.getHeldItemMainhand() != null && entityPlayer.getHeldItemMainhand().getItem() instanceof IKeyBound) {
                if (entityPlayer.worldObj.isRemote) {
                    Network.INSTANCE.sendToServer(new MessageKeyPressed(getPressedKeybinding()));
                }
                else {
                    ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doKeyBindingAction(entityPlayer, entityPlayer.getHeldItemMainhand(), getPressedKeybinding());
                }
            }
        }
    }
}
