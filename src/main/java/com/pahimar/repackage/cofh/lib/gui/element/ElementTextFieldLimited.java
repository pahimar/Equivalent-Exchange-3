package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;

public class ElementTextFieldLimited extends ElementTextField
{

    protected boolean includeVanilla = true;
    protected String filter;

    public ElementTextFieldLimited(GuiBase gui, int posX, int posY, int width, int height)
    {

        super(gui, posX, posY, width, height);
    }

    public ElementTextFieldLimited(GuiBase gui, int posX, int posY, int width, int height, short limit)
    {

        super(gui, posX, posY, width, height, limit);
    }

    /**
     * @param pattern        String containing all characters permitted
     * @param includeVanilla Include vanilla disallowed characters
     * @return this
     */
    public ElementTextFieldLimited setFilter(String pattern, boolean includeVanilla)
    {

        filter = pattern;
        this.includeVanilla = includeVanilla;
        return this;
    }

    @Override
    public boolean isAllowedCharacter(char charTyped)
    {

        return (!includeVanilla || super.isAllowedCharacter(charTyped)) && (filter == null || filter.indexOf(charTyped) >= 0);
    }

}

