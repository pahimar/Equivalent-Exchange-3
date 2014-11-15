package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.GuiColor;
import com.pahimar.repackage.cofh.lib.gui.element.listbox.IListBoxElement;
import com.pahimar.repackage.cofh.lib.util.helpers.StringHelper;
import net.minecraft.client.renderer.Tessellator;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public abstract class ElementListBox extends ElementBase {

    public int borderColor = new GuiColor(120, 120, 120, 255).getColor();
    public int backgroundColor = new GuiColor(0, 0, 0, 255).getColor();
    public int selectedLineColor = new GuiColor(0, 0, 0, 255).getColor();
    public int textColor = new GuiColor(150, 150, 150, 255).getColor();
    public int selectedTextColor = new GuiColor(255, 255, 255, 255).getColor();

    private final int _marginTop = 2;
    private final int _marginLeft = 2;
    private final int _marginRight = 2;
    private final int _marginBottom = 2;

    private final List<IListBoxElement> _elements = new LinkedList<IListBoxElement>();

    private int _firstIndexDisplayed;
    private int _selectedIndex;
    private int scrollHoriz;

    public ElementListBox(GuiBase containerScreen, int x, int y, int width, int height) {

        super(containerScreen, x, y, width, height);
    }

    public void add(IListBoxElement element) {

        _elements.add(element);
    }

    public void add(Collection<? extends IListBoxElement> elements) {

        _elements.addAll(elements);
    }

    public void remove(IListBoxElement element) {

        _elements.remove(element);
    }

    public void removeAt(int index) {

        _elements.remove(index);
    }

    public int getInternalWidth() {

        int width = 0;
        for (int i = 0; i < _elements.size(); i++) {
            width = Math.max(_elements.get(i).getWidth(), width);
        }
        return width;
    }

    public int getInternalHeight() {

        int height = 0;
        for (int i = 0; i < _elements.size(); i++) {
            height += _elements.get(i).getHeight();
        }
        return height;
    }

    public int getContentWidth() {

        return sizeX - _marginLeft - _marginRight;
    }

    public int getContentHeight() {

        return sizeY - _marginTop - _marginBottom;
    }

    public int getContentTop() {

        return posY + _marginTop;
    }

    public int getContentLeft() {

        return posX + _marginLeft;
    }

    public final int getContentBottom() {

        return getContentTop() + getContentHeight();
    }

    public final int getContentRight() {

        return getContentLeft() + getContentWidth();
    }

    @Override
    public void drawBackground(int mouseX, int mouseY, float gameTicks) {

        drawModalRect(posX - 1, posY - 1, posX + sizeX + 1, posY + sizeY + 1, borderColor);
        drawModalRect(posX, posY, posX + sizeX, posY + sizeY, backgroundColor);
    }

    @Override
    public void drawForeground(int mouseX, int mouseY) {

        int heightDrawn = 0;
        int nextElement = _firstIndexDisplayed;

        glDisable(GL_LIGHTING);
        glPushMatrix();

        glDisable(GL_TEXTURE_2D);
        glEnable(GL_STENCIL_TEST);
        glClear(GL_STENCIL_BUFFER_BIT);
        glStencilFunc(GL_ALWAYS, 1, 1);
        glStencilOp(GL_KEEP, GL_KEEP, GL_REPLACE);
        glStencilMask(1);
        glColorMask(false, false, false, false);
        glDepthMask(false);

        Tessellator.instance.startDrawingQuads();
        Tessellator.instance.addVertex(getContentLeft(), getContentBottom(), 0);
        Tessellator.instance.addVertex(getContentRight(), getContentBottom(), 0);
        Tessellator.instance.addVertex(getContentRight(), getContentTop(), 0);
        Tessellator.instance.addVertex(getContentLeft(), getContentTop(), 0);
        Tessellator.instance.draw();

        glEnable(GL_TEXTURE_2D);
        glStencilFunc(GL_EQUAL, 1, 1);
        glStencilMask(0);
        glColorMask(true, true, true, true);
        glDepthMask(true);
        glTranslated(-scrollHoriz, 0, 0);

        int e = _elements.size();
        while (nextElement < e && heightDrawn <= getContentHeight()) {
            if (nextElement == _selectedIndex) {
                _elements.get(nextElement).draw(this, getContentLeft(), getContentTop() + heightDrawn, selectedLineColor, selectedTextColor);
            } else {
                _elements.get(nextElement).draw(this, getContentLeft(), getContentTop() + heightDrawn, backgroundColor, textColor);
            }
            heightDrawn += _elements.get(nextElement).getHeight();
            nextElement++;
        }

        glDisable(GL_STENCIL_TEST);

        glPopMatrix();
    }

    @Override
    public boolean onMousePressed(int mouseX, int mouseY, int mouseButton) {

        int heightChecked = 0;
        for (int i = _firstIndexDisplayed; i < _elements.size(); i++) {
            if (heightChecked > getContentHeight()) {
                break;
            }
            int elementHeight = _elements.get(i).getHeight();
            if (getContentTop() + heightChecked <= mouseY && getContentTop() + heightChecked + elementHeight >= mouseY) {
                setSelectedIndex(i);
                onElementClicked(_elements.get(i));
                break;
            }
            heightChecked += elementHeight;
        }
        return true;
    }

    @Override
    public boolean onMouseWheel(int mouseX, int mouseY, int movement) {

        if (StringHelper.isControlKeyDown()) {
            if (movement > 0) {
                scrollLeft();
            } else if (movement < 0) {
                scrollRight();
            }
        } else {
            if (movement > 0) {
                scrollUp();
            } else if (movement < 0) {
                scrollDown();
            }
        }
        return true;
    }

    public void scrollDown() {

        int heightDisplayed = 0;
        int elementsDisplayed = 0;
        for (int i = _firstIndexDisplayed; i < _elements.size(); i++) {
            if (heightDisplayed + _elements.get(i).getHeight() > sizeY) {
                break;
            }
            heightDisplayed += _elements.get(i).getHeight();
            elementsDisplayed++;
        }

        if (_firstIndexDisplayed + elementsDisplayed < _elements.size()) {
            _firstIndexDisplayed++;
        }

        onScrollV(_firstIndexDisplayed);
    }

    public void scrollUp() {

        if (_firstIndexDisplayed > 0) {
            _firstIndexDisplayed--;
        }
        onScrollV(_firstIndexDisplayed);
    }

    public void scrollLeft() {

        scrollHoriz = Math.max(scrollHoriz - 15, 0);
        onScrollH(scrollHoriz);
    }

    public void scrollRight() {

        scrollHoriz = Math.min(scrollHoriz + 15, getLastScrollPositionH());
        onScrollH(scrollHoriz);
    }

    public int getLastScrollPosition() {

        int position = _elements.size() - 1;
        int heightUsed = _elements.get(position).getHeight();

        while (position > 0 && heightUsed < sizeY) {
            position--;
            heightUsed += _elements.get(position).getHeight();
        }

        return position + 1;
    }

    public int getLastScrollPositionH() {

        return Math.max(getInternalWidth() - getContentWidth(), 0);
    }

    public int getSelectedIndex() {

        return _selectedIndex;
    }

    public int getIndexOf(Object value) {

        for (int i = 0; i < _elements.size(); i++) {
            if (_elements.get(i).getValue().equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public IListBoxElement getSelectedElement() {

        return _elements.get(_selectedIndex);
    }

    public void setSelectedIndex(int index) {

        if (index >= 0 && index < _elements.size() && index != _selectedIndex) {
            _selectedIndex = index;
            onSelectionChanged(_selectedIndex, getSelectedElement());
        }
    }

    public IListBoxElement getElement(int index) {

        return _elements.get(index);
    }

    public int getElementCount() {

        return _elements.size();
    }

    public void scrollToV(int index) {

        if (index >= 0 && index < _elements.size()) {
            _firstIndexDisplayed = index;
        }
    }

    public void scrollToH(int index) {

        if (index >= 0 && index < getLastScrollPositionH()) {
            scrollHoriz = index;
        }
    }

    protected void onElementClicked(IListBoxElement element) {

    }

    protected void onScrollV(int newStartIndex) {

    }

    protected void onScrollH(int newStartIndex) {

    }

    protected abstract void onSelectionChanged(int newIndex, IListBoxElement newElement);

}
