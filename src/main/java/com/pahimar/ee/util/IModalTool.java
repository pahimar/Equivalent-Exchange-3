package com.pahimar.ee.util;

import com.pahimar.ee.reference.ToolMode;
import net.minecraft.item.ItemStack;

import java.util.List;

public interface IModalTool {

    List<ToolMode> getAvailableToolModes();

    ToolMode getCurrentToolMode(ItemStack itemStack);

    void setToolMode(ItemStack itemStack, ToolMode toolMode);

    void changeToolMode(ItemStack itemStack);
}
