package com.pahimar.repackage.cofh.lib.gui;

import com.pahimar.repackage.cofh.lib.gui.element.TabBase;

/**
 * Keeps track of which tabs should be open by default when a player opens a GUI.
 *
 * @author King Lemming
 */
public class TabTracker {

    private static Class<? extends TabBase> openedLeftTab;
    private static Class<? extends TabBase> openedRightTab;

    public static Class<? extends TabBase> getOpenedLeftTab() {

        return openedLeftTab;
    }

    public static Class<? extends TabBase> getOpenedRightTab() {

        return openedRightTab;
    }

    public static void setOpenedLeftTab(Class<? extends TabBase> tabClass) {

        openedLeftTab = tabClass;
    }

    public static void setOpenedRightTab(Class<? extends TabBase> tabClass) {

        openedRightTab = tabClass;
    }

}
