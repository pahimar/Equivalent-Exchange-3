package com.pahimar.ee.client.handler;

import com.pahimar.ee.api.blacklist.BlacklistRegistryProxy;
import com.pahimar.ee.api.exchange.EnergyValue;
import com.pahimar.ee.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee.exchange.WrappedStack;
import com.pahimar.ee.handler.ConfigurationHandler;
import com.pahimar.ee.reference.Colors;
import com.pahimar.ee.reference.Messages;
import com.pahimar.ee.util.IOwnable;
import com.pahimar.ee.util.ItemStackUtils;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
public class ItemTooltipEventHandler {

    @SubscribeEvent
    public void handleItemTooltipEvent(ItemTooltipEvent event) {

        if (shouldBeDisplayed()) {

            WrappedStack wrappedItemStack = WrappedStack.build(event.getItemStack());
            EnergyValue energyValue = EnergyValueRegistryProxy.getEnergyValue(wrappedItemStack);

            if (energyValue != null && (BlacklistRegistryProxy.isExchangeable(wrappedItemStack) || BlacklistRegistryProxy.isLearnable(wrappedItemStack))) {
                event.getToolTip().add(String.format("%s%s:%s %s", Colors.TextColor.YELLOW, I18n.format(Messages.Tooltips.ENERGY_VALUE), Colors.TextColor.WHITE, energyValue));
            }
            else {
                event.getToolTip().add(String.format("%s%s", Colors.TextColor.YELLOW, I18n.format(Messages.Tooltips.NO_ENERGY_VALUE)));
            }

            if (event.getItemStack().getItem() instanceof IOwnable) {
                String playerName = ItemStackUtils.getOwner(event.getItemStack());
                if (playerName != null) {
                    event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO, playerName));
                }
                else {
                    event.getToolTip().add(I18n.format(Messages.Tooltips.ITEM_BELONGS_TO_NO_ONE));
                }
            }
        }
    }

    /**
     * TODO Documentation
     *
     * @return
     */
    private static boolean shouldBeDisplayed() {
        return !ConfigurationHandler.Settings.requireShiftToDisplayExtra || (ConfigurationHandler.Settings.requireShiftToDisplayExtra && (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)));
    }


}
