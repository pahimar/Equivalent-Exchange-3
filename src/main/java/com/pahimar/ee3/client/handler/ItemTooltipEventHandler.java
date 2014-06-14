package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.exchange.EnergyRegistry;
import com.pahimar.ee3.exchange.EnergyValue;
import com.pahimar.ee3.exchange.WrappedStack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.input.Keyboard;

import java.text.DecimalFormat;

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

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            WrappedStack stack = new WrappedStack(event.itemStack);

            if (EnergyRegistry.getInstance().hasEnergyValue(stack))
            {
                EnergyValue emcValue = EnergyRegistry.getInstance().getEnergyValue(stack);
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
