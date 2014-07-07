package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.exchange.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
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
    private static DecimalFormat energyValueDecimalFormat = new DecimalFormat("###,###,###,###,###.###");

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event)
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
        {
            WrappedStack stack = new WrappedStack(event.itemStack);

            if (EnergyValueRegistry.getInstance().hasEnergyValue(stack))
            {
                EnergyValue energyValue = EnergyValueRegistry.getInstance().getEnergyValue(stack);
                if (stack.getStackSize() > 1)
                {
                    event.toolTip.add("Exchange Energy (Item): " + String.format("%s", energyValueDecimalFormat.format(energyValue.getValue())));
                    event.toolTip.add("Exchange Energy (Stack): " + String.format("%s", energyValueDecimalFormat.format(stack.getStackSize() * energyValue.getValue())));
                }
                else
                {
                    event.toolTip.add("Exchange Energy: " + String.format("%s", energyValueDecimalFormat.format(stack.getStackSize() * energyValue.getValue())));
                }
            }
            else
            {
                event.toolTip.add("No Exchange Energy value");
            }
        }
    }
}
