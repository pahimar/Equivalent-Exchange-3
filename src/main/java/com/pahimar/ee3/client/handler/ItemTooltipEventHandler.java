package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

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
    private static boolean debug = false;
    private static DecimalFormat emcDecimalFormat = new DecimalFormat("#.###");

    @ForgeSubscribe
    public void handleItemTooltipEvent(ItemTooltipEvent event)
    {
        WrappedStack stack = new WrappedStack(event.itemStack);

        if (EmcRegistry.getInstance().hasEmcValue(stack))
        {
            EmcValue emcValue = EmcRegistry.getInstance().getEmcValue(stack);

            event.toolTip.add("");
            if (emcValue != null && debug)
            {
                event.toolTip.add("EMC: " + String.format("%s", emcDecimalFormat.format(stack.getStackSize() * emcValue.getValue())));

                for (EmcType emcType : EmcType.TYPES)
                {
                    if (emcValue.components[emcType.ordinal()] > 0)
                    {
                        event.toolTip.add("  * " + emcType + ": " + String.format("%s", emcDecimalFormat.format(stack.getStackSize() * emcValue.components[emcType.ordinal()])));
                    }
                }
            }
        }
    }
}
