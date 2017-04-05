package com.pahimar.ee.client.gui;

import com.pahimar.ee.reference.Textures;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;

/**
 * TODO Finish Javadoc
 *
 * @author Pahimar (pahimar@gmail.com
 * @version 0.5.0
 * @since 0.5.0
 */
public class GuiAlchenomicon extends GuiScreen {

    final int guiWidth = 256;
    final int guiHeight = 256;

    private static final ResourceLocation TEXTURE = Textures.Gui.ALCHENOMICON;

    @Override
    public final void initGui() {
        super.initGui();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public final void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        GlStateManager.color(1f, 1f, 1f, 1f);
        mc.renderEngine.bindTexture(TEXTURE);
        drawTexturedModalRect(width / 2 - guiWidth / 2, height / 2 - guiHeight / 2, 0, 0, guiWidth, guiHeight);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        // NO-OP
    }
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }
}
