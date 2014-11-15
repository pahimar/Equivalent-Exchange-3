package com.pahimar.repackage.cofh.lib.gui.element.listbox;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.element.ElementSlider;

public abstract class SliderVertical extends ElementSlider {

    protected SliderVertical(GuiBase containerScreen, int x, int y, int width, int height, int maxValue) {

        super(containerScreen, x, y, width, height, maxValue);
        setSliderSize(width, maxValue == 0 ? height : Math.max(height / maxValue, 9));
    }

    @Override
    public int getSliderY() {

        return Math.min(_valueMax == 0 ? 0 : (sizeY - _sliderHeight) * _value / _valueMax, sizeY - _sliderHeight);
    }

    @Override
    public void dragSlider(int x, int v) {

        v += Math.round(_sliderHeight * (v / (float) sizeY - 0.5f));
        setValue(_valueMax * v / sizeY);
    }
}
