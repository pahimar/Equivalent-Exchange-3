package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiProps;
import com.pahimar.repackage.cofh.lib.gui.TabTracker;
import com.pahimar.repackage.cofh.lib.render.RenderHelper;
import com.pahimar.repackage.cofh.lib.util.Rectangle4i;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Base class for a tab element. Has self-contained rendering methods and a link back to the {@link GuiBase} it is a part of.
 *
 * @author King Lemming
 */
public abstract class TabBase extends ElementBase
{

    public static int tabExpandSpeed = 8;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    protected int offsetX = 0;
    protected int offsetY = 0;

    public boolean open;
    public boolean fullyOpen;
    public int side = RIGHT;

    public int headerColor = 0xe1c92f;
    public int subheaderColor = 0xaaafb8;
    public int textColor = 0x000000;
    public int backgroundColor = 0xffffff;

    protected int currentShiftX = 0;
    protected int currentShiftY = 0;

    public int minWidth = 22;
    public int maxWidth = 124;
    public int currentWidth = minWidth;

    public int minHeight = 22;
    public int maxHeight = 22;
    public int currentHeight = minHeight;

    public static final ResourceLocation DEFAULT_TEXTURE_LEFT = new ResourceLocation(GuiProps.PATH_ELEMENTS + "Tab_Left.png");
    public static final ResourceLocation DEFAULT_TEXTURE_RIGHT = new ResourceLocation(GuiProps.PATH_ELEMENTS + "Tab_Right.png");

    public TabBase(GuiBase gui)
    {

        super(gui, 0, 0);
        texture = DEFAULT_TEXTURE_RIGHT;
    }

    public TabBase(GuiBase gui, int side)
    {

        super(gui, 0, 0);
        this.side = side;

        if (side == LEFT)
        {
            texture = DEFAULT_TEXTURE_LEFT;
        }
        else
        {
            texture = DEFAULT_TEXTURE_RIGHT;
        }
    }

    public TabBase setOffsets(int x, int y)
    {

        offsetX = x;
        offsetY = y;

        return this;
    }

    public void draw(int x, int y)
    {

        posX = x + offsetX;
        posY = y + offsetY;
        draw();
    }

    public void draw()
    {

        return;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks)
    {

    }

    @Override
    public void drawForeground(int mouseX, int mouseY)
    {

    }

    @Override
    public void update()
    {

        if (open && currentWidth < maxWidth)
        {
            currentWidth += tabExpandSpeed;
        }
        else if (!open && currentWidth > minWidth)
        {
            currentWidth -= tabExpandSpeed;
        }
        if (currentWidth > maxWidth)
        {
            currentWidth = maxWidth;
        }
        else if (currentWidth < minWidth)
        {
            currentWidth = minWidth;
        }
        if (open && currentHeight < maxHeight)
        {
            currentHeight += tabExpandSpeed;
        }
        else if (!open && currentHeight > minHeight)
        {
            currentHeight -= tabExpandSpeed;
        }
        if (currentHeight > maxHeight)
        {
            currentHeight = maxHeight;
        }
        else if (currentHeight < minHeight)
        {
            currentHeight = minHeight;
        }
        if (!fullyOpen && open && currentWidth == maxWidth && currentHeight == maxHeight)
        {
            setFullyOpen();
        }
    }

    protected void drawBackground()
    {

        float colorR = (backgroundColor >> 16 & 255) / 255.0F;
        float colorG = (backgroundColor >> 8 & 255) / 255.0F;
        float colorB = (backgroundColor & 255) / 255.0F;

        GL11.glColor4f(colorR, colorG, colorB, 1.0F);

        RenderHelper.bindTexture(texture);

        int xPosition = posX();

        gui.drawTexturedModalRect(xPosition, posY + 4, 0, 256 - currentHeight + 4, 4, currentHeight - 4);
        gui.drawTexturedModalRect(xPosition + 4, posY, 256 - currentWidth + 4, 0, currentWidth - 4, 4);
        gui.drawTexturedModalRect(xPosition, posY, 0, 0, 4, 4);
        gui.drawTexturedModalRect(xPosition + 4, posY + 4, 256 - currentWidth + 4, 256 - currentHeight + 4, currentWidth - 4, currentHeight - 4);

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0F);
    }

    protected void drawTabIcon(String iconName)
    {

        gui.drawIcon(iconName, posXOffset(), posY + 3, 1);
    }

    /**
     * Shortcut to correct for the proper X position.
     */
    protected int posX()
    {

        if (side == LEFT)
        {
            return posX - currentWidth;
        }
        return posX;
    }

    /**
     * Corrects for shadowing differences in tabs to ensure that they always look nice - used in font rendering, typically.
     */
    protected int posXOffset()
    {

        return posX() + sideOffset();
    }

    protected int sideOffset()
    {

        return (side == LEFT ? 4 : 2);
    }

    public boolean intersectsWith(int mouseX, int mouseY, int shiftX, int shiftY)
    {

        shiftX += offsetX;
        shiftY += offsetY;

        if (side == LEFT)
        {
            if (mouseX <= shiftX && mouseX >= shiftX - currentWidth && mouseY >= shiftY && mouseY <= shiftY + currentHeight)
            {
                return true;
            }
        }
        else if (mouseX >= shiftX && mouseX <= shiftX + currentWidth && mouseY >= shiftY && mouseY <= shiftY + currentHeight)
        {
            return true;
        }
        return false;
    }

    public boolean isFullyOpened()
    {

        return fullyOpen;
    }

    public void setCurrentShift(int x, int y)
    {

        currentShiftX = x + offsetX;
        currentShiftY = y + offsetY;
    }

    public void setFullyOpen()
    {

        open = true;
        currentWidth = maxWidth;
        currentHeight = maxHeight;
        fullyOpen = true;
    }

    public void toggleOpen()
    {

        if (open)
        {
            open = false;
            if (side == LEFT)
            {
                TabTracker.setOpenedLeftTab(null);
            }
            else
            {
                TabTracker.setOpenedRightTab(null);
            }
            fullyOpen = false;
        }
        else
        {
            open = true;
            if (side == LEFT)
            {
                TabTracker.setOpenedLeftTab(this.getClass());
            }
            else
            {
                TabTracker.setOpenedRightTab(this.getClass());
            }
        }
    }

    public Rectangle4i getBounds()
    {

        if (isVisible())
        {
            return new Rectangle4i(posX() + gui.getGuiLeft(), posY + gui.getGuiTop(), currentWidth, currentHeight);
        }
        else
        {
            return new Rectangle4i(posX() + gui.getGuiLeft(), posY + gui.getGuiTop(), 0, 0);
        }

    }

}
