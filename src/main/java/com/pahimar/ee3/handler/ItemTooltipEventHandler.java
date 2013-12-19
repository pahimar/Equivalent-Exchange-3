package com.pahimar.ee3.handler;

import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.crafting.RecipeRegistry;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemTooltipEventHandler
 *
 * @author pahimar
 */
public class ItemTooltipEventHandler
{

    private static boolean debug = true;

    @ForgeSubscribe
    public void handleItemTooltipEvent(ItemTooltipEvent event)
    {

        WrappedStack stack = new WrappedStack(event.itemStack);
        if (debug)
        {
            event.toolTip.add(EnumChatFormatting.AQUA + "ID: " + event.itemStack.itemID + ", Meta: " + event.itemStack.getItemDamage());
            if (stack.getWrappedStack() instanceof OreStack)
            {
                event.toolTip.add(EnumChatFormatting.AQUA + "OreDictionary Item");
            }
            if (RecipeRegistry.getInstance().getRecipeMappings().containsKey(stack))
            {
                event.toolTip.add(EnumChatFormatting.AQUA + "Made from a recipe");
            }
        }

        if (EmcRegistry.hasEmcValue(stack))
        {
            EmcValue emcValue = EmcRegistry.getEmcValue(stack);

            event.toolTip.add("");
            if (emcValue != null)
            {
                event.toolTip.add("EMC: " + String.format("%.3f", stack.getStackSize() * emcValue.getValue()));

                if (debug)
                {
                    for (EmcType emcType : EmcType.TYPES)
                    {
                        if (emcValue.components[emcType.ordinal()] > 0)
                        {
                            event.toolTip.add("  * " + emcType + ": " + String.format("%.3f", stack.getStackSize() * emcValue.components[emcType.ordinal()]));
                        }
                    }
                }
            }
        }
    }
}
