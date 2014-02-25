package com.pahimar.ee3.client.handler;

import java.text.DecimalFormat;

import net.minecraftforge.event.entity.player.ItemTooltipEvent;

import org.lwjgl.input.Keyboard;

import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemTooltipEventHandler
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class ItemTooltipEventHandler
{
    private static DecimalFormat emcDecimalFormat = new DecimalFormat("###,###,###,###,###.###");

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void handleItemTooltipEvent(ItemTooltipEvent event)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            WrappedStack stack = new WrappedStack(event.itemStack);

            if (EmcRegistry.getInstance().hasEmcValue(stack))
            {
                EmcValue emcValue = EmcRegistry.getInstance().getEmcValue(stack);
                if (stack.getStackSize() > 1)
                {
                    event.toolTip.add("EMC (Item): " + String.format("%s", emcDecimalFormat.format(emcValue.getValue())));
                    event.toolTip.add("EMC (Stack): " + String.format("%s", emcDecimalFormat.format(stack.getStackSize() * emcValue.getValue())));
                }
                else
                {
                    event.toolTip.add("EMC: " + String.format("%s", emcDecimalFormat.format(stack.getStackSize() * emcValue.getValue())));
                }
            }
            else
            {
                event.toolTip.add("No EMC value");
            }
        }
    }
}
