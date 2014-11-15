package com.pahimar.repackage.cofh.lib.gui.element;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;

import java.util.HashMap;
import java.util.Map;

public abstract class ElementButtonOption extends ElementButtonManaged {

    private final Map<Integer, String> _values = new HashMap<Integer, String>();
    private int _currentValue = 0;
    private int _maxValue;

    public ElementButtonOption(GuiBase containerScreen, int x, int y, int width, int height) {

        super(containerScreen, x, y, width, height, "");
    }

    public void setValue(int value, String label) {

        _values.put(value, label);
        if (value > _maxValue) {
            _maxValue = value;
        }
    }

    @Override
    public void onClick() {

        int nextValue = _currentValue;
        do {
            nextValue++;
            if (nextValue > _maxValue) {
                nextValue = 0;
            }
        } while (_values.get(nextValue) == null);
        setSelectedIndex(nextValue);
    }

    @Override
    public void onRightClick() {

        int nextValue = _currentValue;

        do {
            nextValue--;
            if (nextValue < 0) {
                nextValue = _maxValue;
            }
        } while (_values.get(nextValue) == null);
        setSelectedIndex(nextValue);
    }

    public int getSelectedIndex() {

        return _currentValue;
    }

    public void setSelectedIndex(int index) {

        _currentValue = index;
        setText(_values.get(_currentValue));
        onValueChanged(_currentValue, _values.get(_currentValue));
    }

    public String getValue() {

        return _values.get(_currentValue);
    }

    public abstract void onValueChanged(int value, String label);

}
