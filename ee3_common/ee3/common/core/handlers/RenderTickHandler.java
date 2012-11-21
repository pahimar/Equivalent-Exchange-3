package ee3.common.core.handlers;

import java.util.EnumSet;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderHelper;
import net.minecraft.src.RenderItem;
import net.minecraft.src.ScaledResolution;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import ee3.client.core.helper.RenderUtils;
import ee3.common.core.helper.VersionHelper;
import ee3.common.item.ItemPhilosopherStone;
import ee3.common.lib.ConfigurationSettings;
import ee3.common.lib.Reference;

public class RenderTickHandler implements ITickHandler {

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
            }

            if ((player != null) && (currentItemStack != null) && (minecraft.inGameHasFocus) && (currentItemStack.getItem() instanceof ItemPhilosopherStone)) {
                renderStoneHUD(minecraft, player, currentItemStack, (Float) tickData[0]);
            }
        }
    }

    @Override
    public EnumSet<TickType> ticks() {

        return EnumSet.of(TickType.CLIENT, TickType.CLIENTGUI, TickType.RENDER);
    }

    @Override
    public String getLabel() {

        return Reference.MOD_NAME + ": " + this.getClass().getSimpleName();
    }

    private static void renderStoneHUD(Minecraft minecraft, EntityPlayer player, ItemStack stack, float partialTicks) {
 
        int loc = player.inventory.currentItem*20;
        int shift = player.capabilities.isCreativeMode?0:20;
        GL11.glPushMatrix();
        
            ScaledResolution sr = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
            GL11.glClear(256);
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0.0D, sr.getScaledWidth_double(), sr.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();
            GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
            int k = sr.getScaledWidth();
            int l = sr.getScaledHeight();
            
            GL11.glPushMatrix();
                RenderHelper.enableGUIStandardItemLighting();
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glEnable(GL11.GL_COLOR_MATERIAL);
                GL11.glEnable(GL11.GL_LIGHTING);
                RenderItem itemRenderer = new RenderItem();
                RenderUtils.renderItemIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, stack, 0, 0);
                //itemRenderer.renderItemIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, stack, 0, 0);
                GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        
        GL11.glPopMatrix();
    }

}
