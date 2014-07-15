package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.item.ItemMatterPickAxe;
import com.pahimar.ee3.reference.ToolMode;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;

@SideOnly(Side.CLIENT)
public class DrawBlockHighlightEventHandler
{
    @SubscribeEvent
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event)
    {
        if (event.currentItem != null)
        {
            if (event.currentItem.getItem() instanceof ItemMatterPickAxe && event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                ToolMode toolMode = ((ItemMatterPickAxe) event.currentItem.getItem()).getCurrentToolMode(event.currentItem);

                if (toolMode == ToolMode.SINGLE)
                {

                }
                else if (toolMode == ToolMode.WIDE)
                {

                }
                else if (toolMode == ToolMode.TALL)
                {

                }
            }
        }
    }
}
