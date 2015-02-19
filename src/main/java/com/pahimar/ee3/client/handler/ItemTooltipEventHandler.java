package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.inventory.ContainerAlchemicalTome;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemHelper;
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
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || event.entityPlayer.openContainer instanceof ContainerAlchemicalTome)
        {
            WrappedStack stack = new WrappedStack(event.itemStack);

            if (EnergyValueRegistry.getInstance().hasEnergyValue(stack))
            {
                EnergyValue energyValue = EnergyValueRegistry.getInstance().getEnergyValue(stack);
                if (stack.getStackSize() > 1)
                {
                    event.toolTip.add(String.format("Exchange Energy (Item): %s", energyValueDecimalFormat.format(energyValue.getEnergyValue())));
                    event.toolTip.add(String.format("Exchange Energy (Stack of %s): %s", event.itemStack.stackSize, energyValueDecimalFormat.format(stack.getStackSize() * energyValue.getEnergyValue())));
                }
                else
                {
                    event.toolTip.add(String.format("Exchange Energy: %s", energyValueDecimalFormat.format(stack.getStackSize() * energyValue.getEnergyValue())));
                }
            }
            else
            {
                event.toolTip.add("No Exchange Energy value");
            }
        }

        if (event.itemStack.getItem() instanceof IOwnable)
        {
            event.toolTip.add("Owner: " + ItemHelper.getOwnerName(event.itemStack));
        }
    }
}
