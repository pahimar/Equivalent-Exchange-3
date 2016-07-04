package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.knowledge.PlayerKnowledgeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemStackUtils;
import com.pahimar.ee3.util.SerializationHelper;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public class ItemTooltipEventHandler {

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {

            WrappedStack wrappedItemStack = WrappedStack.build(event.getItemStack());
            EnergyValue energyValue = EnergyValueRegistryProxy.getEnergyValue(wrappedItemStack);
            EnergyValue stackEnergyValue = EnergyValueRegistryProxy.getEnergyValueForStack(wrappedItemStack);

            if (energyValue != null && (BlacklistRegistryProxy.isExchangeable(wrappedItemStack) || BlacklistRegistryProxy.isLearnable(wrappedItemStack))) {

                if (wrappedItemStack.getStackSize() > 1) {
                    event.getToolTip().add(String.format("Exchange Energy (Item): %s", energyValue)); // TODO Localize
                    event.getToolTip().add(String.format("Exchange Energy (Stack of %s): %s", event.getItemStack().stackSize, stackEnergyValue)); // TODO Localize
                }
                else {

                    event.getToolTip().add(String.format("Exchange Energy: %s", stackEnergyValue)); // TODO Localize

                    // TODO Move away from deprecated FluidContainerRegistry
                    if (FluidContainerRegistry.getFluidForFilledItem(event.getItemStack()) != null) {

                        FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(event.getItemStack());
                        EnergyValue fluidStackEnergyValue = EnergyValueRegistryProxy.getEnergyValueForStack(fluidStack);

                        if (fluidStackEnergyValue != null) {
                            event.getToolTip().add(String.format(" - Exchange Energy (%smB of %s): %s", fluidStack.amount, fluidStack.getLocalizedName(), fluidStackEnergyValue)); // TODO Localize
                            event.getToolTip().add(String.format(" - Exchange Energy (Container): %s", new EnergyValue(energyValue.getValue() - fluidStackEnergyValue.getValue()))); // TODO Localize
                        }
                    }
                }
            }
            else {
                event.getToolTip().add("No Exchange Energy value"); // TODO Localize
            }

            if (PlayerKnowledgeRegistryProxy.doesPlayerKnow(event.getEntityPlayer(), event.getItemStack())) {
                event.getToolTip().add("You know how to transmute this"); // TODO Localize with better phrasing
            }

            // TODO This is more of a debug thing to determine the new NBT stuff
            event.getToolTip().add(SerializationHelper.GSON.toJson(event.getItemStack()));
        }

        if (event.getItemStack().getItem() instanceof IOwnable) {

            UUID playerUUID = ItemStackUtils.getOwnerUUID(event.getItemStack());

            if (playerUUID != null && UsernameCache.containsUUID(playerUUID)) {
                event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO, UsernameCache.getLastKnownUsername(playerUUID)));
            }
            else if (ItemStackUtils.getOwnerName(event.getItemStack()) != null) {
                event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO, ItemStackUtils.getOwnerName(event.getItemStack())));
            }
        }
    }
}
