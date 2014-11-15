package com.pahimar.repackage.cofh.lib.gui.element.listbox;

import com.pahimar.repackage.cofh.lib.gui.element.ElementListBox;

public interface IListBoxElement {

    public int getHeight();

    public int getWidth();

    public Object getValue();

    public void draw(ElementListBox listBox, int x, int y, int backColor, int textColor);
}
