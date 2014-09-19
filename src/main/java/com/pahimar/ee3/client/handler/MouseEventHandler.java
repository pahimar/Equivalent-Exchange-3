package com.pahimar.ee3.client.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.MouseEvent;

@SideOnly(Side.CLIENT)
public class MouseEventHandler
{
    @SubscribeEvent
    public void onMouseEvent(MouseEvent event)
    {

    }
}
