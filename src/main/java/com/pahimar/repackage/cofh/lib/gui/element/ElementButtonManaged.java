package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiProps;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public abstract class ElementButtonManaged extends ElementBase {

    public static final ResourceLocation HOVER = new ResourceLocation(GuiProps.PATH_ELEMENTS + "Button_Hover.png");
    public static final ResourceLocation ENABLED = new ResourceLocation(GuiProps.PATH_ELEMENTS + "Button_Enabled.png");
    public static final ResourceLocation DISABLED = new ResourceLocation(GuiProps.PATH_ELEMENTS + "Button_Disabled.png");
    private String _text;

    public ElementButtonManaged(GuiBase containerScreen, int posX, int posY, int sizeX, int sizeY, String text) {

        super(containerScreen, posX, posY, sizeX, sizeY);
        _text = text;
    }

    public void setText(String text) {

        _text = text;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks) {

        if (!isEnabled()) {
            gui.bindTexture(DISABLED);
        } else if (intersectsWith(mouseX, mouseY)) {
            gui.bindTexture(HOVER);
        } else {
            gui.bindTexture(ENABLED);
        }
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexturedModalRect(posX, posY, 0, 0, sizeX / 2, sizeY / 2);
        drawTexturedModalRect(posX, posY + sizeY / 2, 0, 256 - sizeY / 2, sizeX / 2, sizeY / 2);
        drawTexturedModalRect(posX + sizeX / 2, posY, 256 - sizeX / 2, 0, sizeX / 2, sizeY / 2);
        drawTexturedModalRect(posX + sizeX / 2, posY + sizeY / 2, 256 - sizeX / 2, 256 - sizeY / 2, sizeX / 2, sizeY / 2);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

        String text = getFontRenderer().trimStringToWidth(_text, sizeX - 4);
        drawCenteredString(getFontRenderer(), text, posX + sizeX / 2, posY + (sizeY - 8) / 2, getTextColor(mouseX, mouseY));
    }

    protected int getTextColor(int mouseX, int mouseY) {

        if (!isEnabled()) {
            return -6250336;
        } else if (intersectsWith(mouseX, mouseY)) {
            return 16777120;
        } else {
            return 14737632;
        }
    }

    @Override
    public boolean onMousePressed(int mouseX, int mouseY, int mouseButton) {

        GuiBase.playSound("random.click", 1.0F, 1.0F);
        if (mouseButton == 0) {
            onClick();
        } else if (mouseButton == 1) {
            onRightClick();
        } else if (mouseButton == 2) {
            onMiddleClick();
        }
        return true;
    }

    public abstract void onClick();

    public void onRightClick() {

    }

    public void onMiddleClick() {

    }
}
