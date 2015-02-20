package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiColor;
import com.pahimar.repackage.cofh.lib.util.helpers.MathHelper;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;


public class ElementTextField extends ElementBase
{
    public int borderColor = new GuiColor(55, 55, 55).getColor();
    public int backgroundColor = new GuiColor(139, 139, 139).getColor();
    public int disabledColor = new GuiColor(198, 198, 198).getColor();
    public int selectedLineColor = new GuiColor(160, 160, 224).getColor();
    public int textColor = new GuiColor(224, 224, 224).getColor();
    public int selectedTextColor = new GuiColor(224, 224, 224).getColor();
    public int defaultCaretColor = new GuiColor(255, 255, 255).getColor();

    protected char[] text;
    protected int textLength;
    protected int selectionStart, selectionEnd;
    protected int renderStart, caret;

    private boolean isFocused;
    private boolean canFocusChange = true;

    private boolean selecting;

    private byte caretCounter;
    protected boolean caretInsert;
    protected boolean smartCaret = true;
    protected boolean smartCaretCase = true;

    protected boolean enableStencil = true;

    public ElementTextField(GuiBase gui, int posX, int posY, int width, int height)
    {
        this(gui, posX, posY, width, height, (short) 32);
    }

    public ElementTextField(GuiBase gui, int posX, int posY, int width, int height, short limit)
    {
        super(gui, posX, posY, width, height);
        setMaxLength(limit);
    }

    public ElementTextField(GuiBase gui, int posX, int posY, String name, int width, int height)
    {
        this(gui, posX, posY, name, width, height, (short) 32);
    }

    public ElementTextField(GuiBase gui, int posX, int posY, String name, int width, int height, short limit)
    {
        super(gui, posX, posY, width, height);
        setName(name);
        setMaxLength(limit);
    }

    public ElementTextField setTextColor(Number textColor, Number selectedTextColor)
    {
        if (textColor != null)
        {
            this.textColor = textColor.intValue();
        }
        if (selectedTextColor != null)
        {
            this.selectedTextColor = selectedTextColor.intValue();
        }
        return this;
    }

    public ElementTextField setSelectionColor(Number selectedLineColor, Number defaultCaretColor)
    {
        if (selectedLineColor != null)
        {
            this.selectedLineColor = selectedLineColor.intValue();
        }
        if (defaultCaretColor != null)
        {
            this.defaultCaretColor = defaultCaretColor.intValue();
        }
        return this;
    }

    public ElementTextField setBackgroundColor(Number backgroundColor, Number disabledColor, Number borderColor)
    {
        if (backgroundColor != null)
        {
            this.backgroundColor = backgroundColor.intValue();
        }
        if (disabledColor != null)
        {
            this.disabledColor = disabledColor.intValue();
        }
        if (borderColor != null)
        {
            this.borderColor = borderColor.intValue();
        }
        return this;
    }

    public ElementTextField setFocusable(boolean focusable)
    {
        canFocusChange = focusable;
        return this;
    }

    public ElementTextField setFocused(boolean focused)
    {
        if (canFocusChange)
        {
            isFocused = focused;
            caretCounter = 0;
        }
        return this;
    }

    public ElementTextField setText(String text)
    {
        selectionStart = 0;
        selectionEnd = textLength;
        writeText(text);
        return this;
    }

    public ElementTextField setMaxLength(short limit)
    {
        char[] oldText = text;
        text = new char[limit];
        textLength = Math.min(limit, textLength);
        if (oldText != null)
        {
            System.arraycopy(oldText, 0, text, 0, textLength);
        }
        findRenderStart();
        return this;
    }

    public int getMaxStringLength()
    {
        return text.length;
    }

    public boolean isFocused()
    {
        return isEnabled() && isFocused;
    }

    public boolean isFocusable()
    {
        return canFocusChange;
    }

    public int getContentWidth()
    {
        FontRenderer font = getFontRenderer();
        int width = 0;
        for (int i = 0; i < textLength; ++i)
        {
            width += font.getCharWidth(text[i]);
        }
        return width;
    }

    public int getVisibleWidth()
    {
        FontRenderer font = getFontRenderer();
        int width = 0, endX = sizeX - 1;
        for (int i = renderStart; i < textLength; ++i)
        {
            int charW = font.getCharWidth(text[i]);
            if (!enableStencil && (width + charW) > endX)
            {
                break;
            }
            width += charW;
            if (width >= endX)
            {
                width = Math.min(width, endX);
                break;
            }
        }
        return width;
    }

    public String getText()
    {
        return new String(text, 0, textLength);
    }

    public String getSelectedText()
    {

        if (selectionStart != selectionEnd)
        {
            return new String(text, selectionStart, selectionEnd);
        }
        return getText();
    }

    public void writeText(String text)
    {
        int i = 0;
        for (int e = text.length(); i < e; ++i)
        {
            if (!insertCharacter(text.charAt(i)))
            {
                break;
            }
        }
        clearSelection();
        findRenderStart();
        onCharacterEntered(i > 0);
    }

    public boolean isAllowedCharacter(char charTyped)
    {
        return ChatAllowedCharacters.isAllowedCharacter(charTyped);
    }

    protected boolean onEnter()
    {
        return false;
    }

    protected void onFocusLost()
    {

    }

    protected void onCharacterEntered(boolean success)
    {

    }

    protected boolean insertCharacter(char charTyped)
    {

        if (isAllowedCharacter(charTyped))
        {

            if (selectionStart != selectionEnd)
            {
                if (caret == selectionStart)
                {
                    ++caret;
                }
                text[selectionStart++] = charTyped;
                return true;
            }

            if ((caretInsert && caret == text.length) || textLength == text.length)
            {
                return false;
            }

            if (!caretInsert)
            {
                if (caret < textLength)
                {
                    System.arraycopy(text, caret, text, caret + 1, textLength - caret);
                }
                ++textLength;
            }
            text[caret++] = charTyped;
            return true;
        }
        else
        {
            return true;
        }
    }

    protected void findRenderStart()
    {

        caret = MathHelper.clampI(caret, 0, textLength);
        if (caret < renderStart)
        {
            renderStart = caret;
            return;
        }

        FontRenderer font = getFontRenderer();
        int endX = sizeX - 2;

        for (int i = renderStart, width = 0; i < caret; ++i)
        {
            width += font.getCharWidth(text[i]);
            while (width >= endX)
            {
                width -= font.getCharWidth(text[renderStart++]);
                if (renderStart >= textLength)
                {
                    return;
                }
            }
        }
    }

    protected void clearSelection()
    {

        if (selectionStart != selectionEnd)
        {
            if (selectionEnd < textLength)
            {
                System.arraycopy(text, selectionEnd, text, selectionStart, textLength - selectionEnd);
            }
            textLength -= selectionEnd - selectionStart;

            selectionEnd = caret = selectionStart;
            findRenderStart();
        }
    }

    protected final int seekNextCaretLocation(int pos)
    {

        return seekNextCaretLocation(pos, true);
    }

    protected int seekNextCaretLocation(int pos, boolean forward)
    {

        int dir = forward ? 1 : -1;
        int e = forward ? textLength : 0;
        if (pos == textLength)
        {
            --pos;
        }
        char prevChar = text[pos];
        while (pos != e && Character.isSpaceChar(prevChar))
        {
            prevChar = text[pos += dir];
        }

        if (smartCaret)
        {
            for (int i = pos; i != e; i += dir)
            {
                char curChar = text[i];
                boolean caze = Character.isUpperCase(curChar) != Character.isUpperCase(prevChar);
                if (caze || Character.isSpaceChar(curChar) != Character.isSpaceChar(prevChar) ||
                        Character.isLetterOrDigit(curChar) != Character.isLetterOrDigit(prevChar))
                {
                    if ((pos + dir) != i || !Character.isLetterOrDigit(curChar))
                    {
                        return i + (smartCaretCase && caze && Character.isUpperCase(prevChar) ? -dir : 0);
                    }
                }
                prevChar = curChar;
            }
        }
        for (int i = pos; i != e; i += dir)
        {
            char curChar = text[i];
            if (Character.isSpaceChar(curChar) != Character.isSpaceChar(prevChar))
            {
                return i;
            }
        }
        return forward ? textLength : 0;
    }

    @Override
    public boolean onKeyTyped(char charTyped, int keyTyped)
    {
        if (!isFocused())
        {
            return false;
        }

        switch (charTyped)
        {
            case 1: // ^A
                selectionEnd = caret = textLength;
                selectionStart = 0;
                findRenderStart();
                return true;
            case 3: // ^C
                if (selectionStart != selectionEnd)
                {
                    GuiScreen.setClipboardString(getSelectedText());
                }
                return true;
            case 24: // ^X
                if (selectionStart != selectionEnd)
                {
                    GuiScreen.setClipboardString(getSelectedText());
                    clearSelection();
                }

                return true;
            case 22: // ^V
                writeText(GuiScreen.getClipboardString());

                return true;
            default:
                switch (keyTyped)
                {
                    case Keyboard.KEY_ESCAPE:
                        setFocused(false);
                        return !isFocused();
                    case Keyboard.KEY_RETURN:
                    case Keyboard.KEY_NUMPADENTER:
                        return onEnter();
                    case Keyboard.KEY_INSERT:
                        if (GuiScreen.isShiftKeyDown())
                        {
                            writeText(GuiScreen.getClipboardString());
                        }
                        else
                        {
                            caretInsert = !caretInsert;
                        }

                        return true;
                    case Keyboard.KEY_CLEAR: // mac only (clear selection)
                        clearSelection();

                        return true;
                    case Keyboard.KEY_DELETE: // delete
                        if (!GuiScreen.isShiftKeyDown())
                        {
                            if (selectionStart != selectionEnd)
                            {
                                clearSelection();
                            }
                            else if (GuiScreen.isCtrlKeyDown())
                            {
                                int size = seekNextCaretLocation(caret, true) - caret;
                                selectionStart = caret;
                                selectionEnd = caret + size;
                                clearSelection();
                            }
                            else
                            {
                                if (caret < textLength && textLength > 0)
                                {
                                    --textLength;
                                    System.arraycopy(text, caret + 1, text, caret, textLength - caret);
                                }
                            }
                            if (caret <= renderStart)
                            {
                                renderStart = MathHelper.clampI(caret - 3, 0, textLength);
                            }
                            findRenderStart();
                            onCharacterEntered(true);
                            return true;
                        }
                        // continue.. (shift+delete = backspace)
                    case Keyboard.KEY_BACK: // backspace
                        if (selectionStart != selectionEnd)
                        {
                            clearSelection();
                        }
                        else if (GuiScreen.isCtrlKeyDown())
                        {
                            int size = seekNextCaretLocation(caret, false) - caret;
                            selectionStart = caret + size;
                            selectionEnd = caret;
                            clearSelection();
                        }
                        else
                        {
                            if (caret > 0 && textLength > 0)
                            {
                                --caret;
                                System.arraycopy(text, caret + 1, text, caret, textLength - caret);
                                --textLength;
                            }
                        }
                        if (caret <= renderStart)
                        {
                            renderStart = MathHelper.clampI(caret - 3, 0, textLength);
                        }
                        findRenderStart();
                        onCharacterEntered(true);
                        return true;
                    case Keyboard.KEY_HOME: // home
                        if (GuiScreen.isShiftKeyDown())
                        {
                            if (caret > selectionEnd)
                            {
                                selectionEnd = selectionStart;
                            }
                            selectionStart = 0;
                        }
                        else
                        {
                            selectionStart = selectionEnd = 0;
                        }
                        renderStart = caret = 0;

                        return true;
                    case Keyboard.KEY_END: // end
                        if (GuiScreen.isShiftKeyDown())
                        {
                            if (caret < selectionStart)
                            {
                                selectionStart = selectionEnd;
                            }
                            selectionEnd = textLength;
                        }
                        else
                        {
                            selectionStart = selectionEnd = textLength;
                        }
                        caret = textLength;
                        findRenderStart();

                        return true;
                    case Keyboard.KEY_LEFT: // left arrow
                    case Keyboard.KEY_RIGHT: // right arrow
                        int size = keyTyped == 203 ? -1 : 1;
                        if (GuiScreen.isCtrlKeyDown())
                        {
                            size = seekNextCaretLocation(caret, keyTyped == 205) - caret;
                        }

                        if (selectionStart == selectionEnd || !GuiScreen.isShiftKeyDown())
                        {
                            selectionStart = selectionEnd = caret;
                        }

                    {
                        int t = caret;
                        caret = MathHelper.clampI(caret + size, 0, textLength);
                        size = caret - t;
                    }
                    findRenderStart();

                    if (GuiScreen.isShiftKeyDown())
                    {
                        if (caret == selectionStart + size)
                        {
                            selectionStart = caret;
                        }
                        else if (caret == selectionEnd + size)
                        {
                            selectionEnd = caret;
                        }
                        // this logic is 'broken' in that the selection doesn't wrap
                        // such that a|bc|def becomes abc|def| but it will highlight
                        // the rest of the word the caret is on   i.e., a|bc|def -> a|bcdef|
                        // i don't know that it matters (home+end exhibit the former)

                        if (selectionStart > selectionEnd)
                        {
                            int t = selectionStart;
                            selectionStart = selectionEnd;
                            selectionEnd = t;
                        }
                    }

                    return true;
                    default:
                        if (isAllowedCharacter(charTyped))
                        {
                            boolean typed = insertCharacter(charTyped);
                            clearSelection();
                            findRenderStart();
                            onCharacterEntered(typed);
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                }
        }
    }

    @Override
    public boolean onMousePressed(int mouseX, int mouseY, int mouseButton)
    {

        selecting = mouseButton == 0;
        l:
        if (selecting)
        {
            if (textLength == 0)
            {
                selectionStart = selectionEnd = caret = 0;
                break l;
            }
            FontRenderer font = getFontRenderer();
            int pos = mouseX - posX - 1;
            for (int i = renderStart, width = 0; ; )
            {
                int charW = font.getCharWidth(text[i]);
                if ((width += charW) > pos || ++i >= textLength)
                {
                    selectionStart = selectionEnd = caret = i;
                    break;
                }
            }
        }

        setFocused(true);
        return true;
    }

    @Override
    public void update(int mouseX, int mouseY)
    {

        ++caretCounter;
        //if (selecting) {
        //	FontRenderer font = getFontRenderer();
        //	int pos = mouseX - posX - 1;
        //	for (int i = renderStart, width = 0; i < textLength; ++i) {
        //	}
        //}
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY)
    {

        if (!selecting)
        {
            boolean focus = isFocused();
            setFocused(false);
            if (focus && !isFocused())
            {
                onFocusLost();
            }
        }
        selecting = false;
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks)
    {

        drawModalRect(posX - 1, posY - 1, posX + sizeX + 1, posY + sizeY + 1, borderColor);
        drawModalRect(posX, posY, posX + sizeX, posY + sizeY, isEnabled() ? backgroundColor : disabledColor);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY)
    {

        if (enableStencil)
        {
            glEnable(GL_STENCIL_TEST);
            glClear(GL_STENCIL_BUFFER_BIT);
            drawStencil(posX + 1, posY + 1, posX + sizeX - 1, posY + sizeY - 1, 1);
        }

        FontRenderer font = getFontRenderer();
        char[] text = this.text;
        int startX = posX + 3, endX = sizeX - 3, startY = posY + 6, endY = startY + font.FONT_HEIGHT;
        for (int i = renderStart, width = 0; i <= textLength; ++i)
        {
            boolean end = i == textLength;
            int charW = 2;
            if (!end)
            {
                charW = font.getCharWidth(text[i]);
                if (!enableStencil && (width + charW) > endX)
                {
                    break;
                }
            }

            boolean drawCaret = i == caret && (caretCounter &= 31) < 16 && isFocused();
            if (drawCaret)
            {
                int caretEnd = width + 2;
                if (caretInsert)
                {
                    caretEnd = width + charW;
                }
                drawModalRect(startX + width, startY - 1, startX + caretEnd, endY, (0xFF000000 & defaultCaretColor) | (~defaultCaretColor & 0xFFFFFF));
            }

            if (!end)
            {
                boolean selected = i >= selectionStart & i < selectionEnd;
                if (selected)
                {
                    drawModalRect(startX + width, startY, startX + width + charW, endY, selectedLineColor);
                }
                font.drawStringWithShadow(String.valueOf(text[i]), startX + width, startY, selected ? selectedTextColor : textColor);
            }

            if (drawCaret)
            {
                int caretEnd = width + 2;
                if (caretInsert)
                {
                    caretEnd = width + charW;
                }

                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_ONE_MINUS_DST_COLOR, GL11.GL_ZERO);
                gui.drawSizedModalRect(startX + width, startY - 1, startX + caretEnd, endY, -1);
                GL11.glDisable(GL11.GL_BLEND);
            }

            width += charW;
            if (width > endX)
            {
                break;
            }
        }

        if (enableStencil)
        {
            glDisable(GL_STENCIL_TEST);
        }
    }

}
