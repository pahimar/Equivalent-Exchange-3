package com.pahimar.repackage.cofh.lib.gui.element.listbox;

import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.element.ElementSlider;

public abstract class SliderHorizontal extends ElementSlider {

    protected SliderHorizontal(GuiBase containerScreen, int x, int y, int width, int height, int maxValue) {

        super(containerScreen, x, y, width, height, maxValue);
        setSliderSize(maxValue == 0 ? width : Math.max(width / maxValue, 9), height);
    }

    @Override
    public int getSliderX() {

        return Math.min(_valueMax == 0 ? 0 : (sizeX - _sliderWidth) * _value / _valueMax, sizeX - _sliderWidth);
    }

    @Override
    public void dragSlider(int v, int y) {

        v += Math.round(_sliderWidth * (v / (float) sizeX - 0.5f));
        setValue(_valueMax * v / sizeX);
    }
}
