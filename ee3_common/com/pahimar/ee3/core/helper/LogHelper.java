package com.pahimar.ee3.core.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.common.FMLLog;

/**
 * LogHelper
 * 
 * Helper methods for logging to the EE3 logger
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LogHelper {

    private static Logger eeLogger = Logger.getLogger(Reference.MOD_ID);

    public static void init() {

        eeLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {

        eeLogger.log(logLevel, message);
    }

}
