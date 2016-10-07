package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.handler.ConfigurationHandler;
import com.pahimar.ee3.reference.Colors;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemStackUtils;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.UsernameCache;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.UUID;

@SideOnly(Side.CLIENT)
public class ItemTooltipEventHandler {

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (shouldBeDisplayed()) {

            WrappedStack wrappedItemStack = WrappedStack.build(event.getItemStack());
            EnergyValue energyValue = EnergyValueRegistryProxy.getEnergyValue(wrappedItemStack);
            EnergyValue stackEnergyValue = EnergyValueRegistryProxy.getEnergyValueForStack(wrappedItemStack);

            if (energyValue != null && (BlacklistRegistryProxy.isExchangeable(wrappedItemStack) || BlacklistRegistryProxy.isLearnable(wrappedItemStack))) {

                if (wrappedItemStack.getStackSize() > 1) {
                    event.getToolTip().add(String.format("%sEMC Value (Item):%s %s", Colors.TextColor.YELLOW, Colors.TextColor.WHITE, energyValue)); // TODO Localize
                    event.getToolTip().add(String.format("%sEMC Value (Stack of %s):%s %s", Colors.TextColor.YELLOW, event.getItemStack().stackSize, Colors.TextColor.WHITE, stackEnergyValue)); // TODO Localize
                }
                else {
                    event.getToolTip().add(String.format("%sEMC Value:%s %s", Colors.TextColor.YELLOW, Colors.TextColor.WHITE, stackEnergyValue)); // TODO Localize
                }
            }
            else {
                event.getToolTip().add("No Exchange Energy value"); // TODO Localize
            }
        }

        if (event.getItemStack().getItem() instanceof IOwnable) {

            // TODO Add a helper to ItemStackUtils or something that when given an ItemStack returns a player name String
            UUID playerUUID = ItemStackUtils.getOwnerUUID(event.getItemStack());
            if (playerUUID != null && UsernameCache.containsUUID(playerUUID)) {
                event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO, UsernameCache.getLastKnownUsername(playerUUID)));
            }
            else if (ItemStackUtils.getOwnerName(event.getItemStack()) != null) {
                event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO, ItemStackUtils.getOwnerName(event.getItemStack())));
            }
        }
    }

    private static boolean shouldBeDisplayed() {
        return !ConfigurationHandler.Settings.requireShiftToDisplayExtra || (ConfigurationHandler.Settings.requireShiftToDisplayExtra && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)));
    }
}
