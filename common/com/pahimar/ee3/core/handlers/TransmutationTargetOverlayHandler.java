package com.pahimar.ee3.core.handlers;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.pahimar.ee3.client.renderer.RenderUtils;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.item.ITransmutationStone;
import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * TransmutationTargetOverlayHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class TransmutationTargetOverlayHandler implements ITickHandler {

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData) {

    }

    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {

        Minecraft minecraft = FMLClientHandler.instance().getClient();
        EntityPlayer player = minecraft.thePlayer;
        ItemStack currentItemStack = null;

        if (type.contains(TickType.RENDER)) {
            if (player != null) {
                currentItemStack = player.inventory.getCurrentItem();

                if (Minecraft.isGuiEnabled() && minecraft.inGameHasFocus) {
                    if (currentItemStack != null && currentItemStack.getItem() instanceof ITransmutationStone && ConfigurationSettings.ENABLE_OVERLAY_WORLD_TRANSMUTATION) {
                        renderStoneHUD(minecraft, player, currentItemStack, (Float) tickData[0]);
                    }
                }
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.CLIENT, TickType.RENDER);
    }

    @Override
    public String getLabel() {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

    private static void renderStoneHUD(Minecraft minecraft, EntityPlayer player, ItemStack stack, float partialTicks) {

        float overlayScale = ConfigurationSettings.TARGET_BLOCK_OVERLAY_SCALE;
        float blockScale = overlayScale / 2;
        float overlayOpacity = ConfigurationSettings.TARGET_BLOCK_OVERLAY_OPACITY;

        GL11.glPushMatrix();
        ScaledResolution sr = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
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
        int hudBlockX = 0;
        int hudBlockY = 0;

        switch (ConfigurationSettings.TARGET_BLOCK_OVERLAY_POSITION) {
            case 0: {
                hudOverlayX = 0;
                hudBlockX = (int) (16 * overlayScale / 2 - 8);
                hudOverlayY = 0;
                hudBlockY = (int) (16 * overlayScale / 2 - 8);
                break;
            }
            case 1: {
                hudOverlayX = (int) (sr.getScaledWidth() - 16 * overlayScale);
                hudBlockX = (int) (sr.getScaledWidth() - 16 * overlayScale / 2 - 8);
                hudOverlayY = 0;
                hudBlockY = (int) (16 * overlayScale / 2 - 8);
                break;
            }
            case 2: {
                hudOverlayX = 0;
                hudBlockX = (int) (16 * overlayScale / 2 - 8);
                hudOverlayY = (int) (sr.getScaledHeight() - 16 * overlayScale);
                hudBlockY = (int) (sr.getScaledHeight() - 16 * overlayScale / 2 - 8);
                break;
            }
            case 3: {
                hudOverlayX = (int) (sr.getScaledWidth() - 16 * overlayScale);
                hudBlockX = (int) (sr.getScaledWidth() - 16 * overlayScale / 2 - 8);
                hudOverlayY = (int) (sr.getScaledHeight() - 16 * overlayScale);
                hudBlockY = (int) (sr.getScaledHeight() - 16 * overlayScale / 2 - 8);
                break;
            }
            default: {
                break;
            }
        }

        RenderUtils.renderItemIntoGUI(minecraft.fontRenderer, stack, hudOverlayX, hudOverlayY, overlayOpacity, overlayScale);

        if (TransmutationHelper.targetBlockStack != null && TransmutationHelper.targetBlockStack.getItem() instanceof ItemBlock) {
            RenderUtils.renderRotatingBlockIntoGUI(minecraft.fontRenderer, TransmutationHelper.targetBlockStack, hudBlockX, hudBlockY, -90, blockScale);
        }

        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

}
