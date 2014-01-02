package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.client.helper.KeyBindingHelper;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketKeyPressed;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.EnumSet;

/**
 * Equivalent-Exchange-3
 * <p/>
 * KeyBindingHandler
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class KeyBindingHandler extends KeyBindingRegistry.KeyHandler
{
    public KeyBindingHandler()
    {
        super(KeyBindingHelper.gatherKeyBindings(), KeyBindingHelper.gatherIsRepeating());
    }

    @Override
    public String getLabel()
    {
        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

    @Override
    public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
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
                                PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketKeyPressed(kb.keyDescription)));
                            }
                            else
                            {
                                ((IKeyBound) currentItem.getItem()).doKeyBindingAction(player, currentItem, kb.keyDescription);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
    {

    }

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }
}
