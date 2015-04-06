package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.api.EnergyValue;
import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.inventory.ContainerAlchemicalTome;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.input.Keyboard;

import java.text.DecimalFormat;
import java.util.UUID;

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
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || (event.entityPlayer != null && event.entityPlayer.openContainer instanceof ContainerAlchemicalTome))
        {
            WrappedStack stack = new WrappedStack(event.itemStack);

            if (EnergyValueRegistry.getInstance().getEnergyValue(stack) != null)
            {
                EnergyValue energyValue = EnergyValueRegistry.getInstance().getEnergyValue(stack);
                if (stack.getStackSize() > 1)
                {
                    event.toolTip.add(String.format("Exchange Energy (Item): %s", energyValueDecimalFormat.format(energyValue.getEnergyValue()))); // TODO Localize
                    event.toolTip.add(String.format("Exchange Energy (Stack of %s): %s", event.itemStack.stackSize, energyValueDecimalFormat.format(stack.getStackSize() * energyValue.getEnergyValue()))); // TODO Localize
                }
                else
                {
                    event.toolTip.add(String.format("Exchange Energy: %s", energyValueDecimalFormat.format(stack.getStackSize() * energyValue.getEnergyValue()))); // TODO Localize
                }
            }
            else
            {
                event.toolTip.add("No Exchange Energy value"); // TODO Localize
            }
        }

        if (event.itemStack.getItem() instanceof IOwnable)
        {
            UUID playerUUID = ItemHelper.getOwnerUUID(event.itemStack);
            if (playerUUID != null && UsernameCache.containsUUID(playerUUID))
            {
                event.toolTip.add(StatCollector.translateToLocalFormatted(Messages.Tooltips.ITEM_BELONGS_TO, UsernameCache.getLastKnownUsername(playerUUID)));
            }
            else if (ItemHelper.hasOwnerName(event.itemStack))
            {
                event.toolTip.add(StatCollector.translateToLocalFormatted(Messages.Tooltips.ITEM_BELONGS_TO, ItemHelper.getOwnerName(event.itemStack)));
            }
            else
            {
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
                {
                    event.toolTip.add(StatCollector.translateToLocal(Messages.Tooltips.ITEM_BELONGS_TO_NO_ONE));
                }
            }
        }
    }
}
