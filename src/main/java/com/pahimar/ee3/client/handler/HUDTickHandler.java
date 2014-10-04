package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.client.util.RenderUtils;
import com.pahimar.ee3.util.IOverlayItem;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class HUDTickHandler
{
    @SubscribeEvent
    public void renderTick(TickEvent.RenderTickEvent event)
    {
        if (event.phase == TickEvent.Phase.END)
        {
            Minecraft minecraft = FMLClientHandler.instance().getClient();
            EntityPlayer entityPlayer = minecraft.thePlayer;

            if (entityPlayer != null)
            {
                ItemStack currentItemStack = entityPlayer.inventory.getCurrentItem();
                if (Minecraft.isGuiEnabled() && minecraft.inGameHasFocus)
                {
                    if (currentItemStack != null && currentItemStack.getItem() instanceof IOverlayItem)
                    {
                        renderHUDOverlayItem(minecraft, entityPlayer, currentItemStack);
                    }
                }
            }
        }
    }

    private static void renderHUDOverlayItem(Minecraft minecraft, EntityPlayer entityPlayer, ItemStack itemStack)
    {
        float overlayScale = 2f;
        float overlayOpacity = 1f;
        GL11.glPushMatrix();
        ScaledResolution sr = new ScaledResolution(minecraft, minecraft.displayWidth, minecraft.displayHeight);
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
        GL11.glPushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glEnable(GL11.GL_LIGHTING);
        int hudOverlayX = 0;
        int hudOverlayY = 0;

        hudOverlayX = (int) (sr.getScaledWidth() - 16 * overlayScale);
        hudOverlayY = (int) (sr.getScaledHeight() - 16 * overlayScale);

        RenderUtils.renderItemIntoGUI(minecraft.fontRenderer, itemStack, hudOverlayX, hudOverlayY, overlayOpacity, overlayScale);

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
