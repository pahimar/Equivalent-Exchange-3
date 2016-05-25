package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.knowledge.PlayerKnowledgeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.inventory.ContainerAlchenomicon;
import com.pahimar.ee3.inventory.ContainerResearchStation;
import com.pahimar.ee3.inventory.ContainerTransmutationTablet;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
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
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || (event.entityPlayer != null && (event.entityPlayer.openContainer instanceof ContainerAlchenomicon || event.entityPlayer.openContainer instanceof ContainerTransmutationTablet))) {

            WrappedStack wrappedItemStack = WrappedStack.wrap(event.itemStack);
            EnergyValue energyValue = EnergyValueRegistryProxy.getEnergyValue(wrappedItemStack);

            if (energyValue != null && (BlacklistRegistryProxy.isExchangeable(wrappedItemStack) || BlacklistRegistryProxy.isLearnable(wrappedItemStack))) {

                if (wrappedItemStack.getStackSize() > 1) {
                    event.toolTip.add(String.format("Exchange Energy (Item): %s", energyValueDecimalFormat.format(energyValue.getValue()))); // TODO Localize
                    event.toolTip.add(String.format("Exchange Energy (Stack of %s): %s", event.itemStack.stackSize, energyValueDecimalFormat.format(wrappedItemStack.getStackSize() * energyValue.getValue()))); // TODO Localize
                }
                else {

                    event.toolTip.add(String.format("Exchange Energy: %s", energyValueDecimalFormat.format(wrappedItemStack.getStackSize() * energyValue.getValue()))); // TODO Localize

                    if (FluidContainerRegistry.getFluidForFilledItem(event.itemStack) != null) {

                        FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(event.itemStack);

                        if (EnergyValueRegistryProxy.getEnergyValueForStack(fluidStack) != null) {

                            EnergyValue fluidStackEnergyValue = EnergyValueRegistryProxy.getEnergyValueForStack(fluidStack);
                            event.toolTip.add(String.format(" - Exchange Energy (%s): %s", fluidStack.getLocalizedName(), energyValueDecimalFormat.format(fluidStackEnergyValue.getValue()))); // TODO Localize
                            event.toolTip.add(String.format(" - Exchange Energy (Container): %s", energyValueDecimalFormat.format(energyValue.getValue() - fluidStackEnergyValue.getValue()))); // TODO Localize
                        }
                    }
                }
            }
            else
            {
                event.toolTip.add("No Exchange Energy value"); // TODO Localize
            }
        }

        if (((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && (event.entityPlayer != null && event.entityPlayer.openContainer instanceof ContainerResearchStation)))
        {
            if (PlayerKnowledgeRegistryProxy.doesPlayerKnow(event.entityPlayer, event.itemStack))
            {
                event.toolTip.add("You know how to transmute this"); // TODO Localize with better phrasing
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
