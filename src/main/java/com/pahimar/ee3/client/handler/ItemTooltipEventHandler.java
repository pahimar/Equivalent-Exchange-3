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
import com.pahimar.ee3.util.ItemStackUtils;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import org.lwjgl.input.Keyboard;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public class ItemTooltipEventHandler {

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || (event.entityPlayer != null && (event.entityPlayer.openContainer instanceof ContainerAlchenomicon || event.entityPlayer.openContainer instanceof ContainerTransmutationTablet))) {

            WrappedStack wrappedItemStack = WrappedStack.wrap(event.itemStack);
            EnergyValue energyValue = EnergyValueRegistryProxy.getEnergyValue(wrappedItemStack);
            EnergyValue stackEnergyValue = EnergyValueRegistryProxy.getEnergyValueForStack(wrappedItemStack);

            if (energyValue != null) {

                if (wrappedItemStack.getStackSize() > 1) {
                    event.toolTip.add(String.format("Exchange Energy (Item): %s", energyValue)); // TODO Localize
                    event.toolTip.add(String.format("Exchange Energy (Stack of %s): %s", event.itemStack.stackSize, stackEnergyValue)); // TODO Localize
                }
                else {

                    event.toolTip.add(String.format("Exchange Energy: %s", stackEnergyValue)); // TODO Localize

                    if (FluidContainerRegistry.getFluidForFilledItem(event.itemStack) != null) {

                        FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(event.itemStack);
                        EnergyValue fluidStackEnergyValue = EnergyValueRegistryProxy.getEnergyValueForStack(fluidStack);

                        if (fluidStackEnergyValue != null) {
                            event.toolTip.add(String.format(" - Exchange Energy (%smB of %s): %s", fluidStack.amount, fluidStack.getLocalizedName(), fluidStackEnergyValue)); // TODO Localize
                            event.toolTip.add(String.format(" - Exchange Energy (Container): %s", new EnergyValue(energyValue.getValue() - fluidStackEnergyValue.getValue()))); // TODO Localize
                        }
                    }
                }
            }
            else {
                event.toolTip.add("No Exchange Energy value"); // TODO Localize
            }

            if (!BlacklistRegistryProxy.isLearnable(wrappedItemStack)) {
                event.toolTip.add("Not learnable"); // TODO Localize
            }

            if (!BlacklistRegistryProxy.isExchangeable(wrappedItemStack)) {
                event.toolTip.add("Not exchangeable"); // TODO Localize
            }
        }

        if (((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) && (event.entityPlayer != null && event.entityPlayer.openContainer instanceof ContainerResearchStation))) {

            if (PlayerKnowledgeRegistryProxy.doesPlayerKnow(event.entityPlayer, event.itemStack)) {
                event.toolTip.add("You know how to transmute this"); // TODO Localize with better phrasing
            }
        }

        if (event.itemStack.getItem() instanceof IOwnable) {

            UUID playerUUID = ItemStackUtils.getOwnerUUID(event.itemStack);

            if (playerUUID != null && UsernameCache.containsUUID(playerUUID)) {
                event.toolTip.add(StatCollector.translateToLocalFormatted(Messages.Tooltips.ITEM_BELONGS_TO, UsernameCache.getLastKnownUsername(playerUUID)));
            }
            else if (ItemStackUtils.getOwnerName(event.itemStack) != null) {
                event.toolTip.add(StatCollector.translateToLocalFormatted(Messages.Tooltips.ITEM_BELONGS_TO, ItemStackUtils.getOwnerName(event.itemStack)));
            }
        }
    }
}
