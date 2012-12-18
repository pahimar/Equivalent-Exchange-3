package com.pahimar.ee3.core.handlers;

import com.pahimar.ee3.core.addons.AddonRedPower2;

/**
 * AddonHandler
 * 
 * Takes care of initializing of addons to the mod. Occurs after all mods are
 * loaded
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class AddonHandler {

    public static void init() {
        AddonRedPower2.initWorld();
    }

}
