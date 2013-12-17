package com.pahimar.ee3.core.helper;

import com.pahimar.ee3.lib.Reference;
import cpw.mods.fml.common.FMLLog;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Equivalent-Exchange-3
 * <p/>
 * LogHelper
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class LogHelper
{

    private static Logger eeLogger = Logger.getLogger(Reference.MOD_ID);

    public static void init()
    {

        eeLogger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, Object object)
    {
        if (object != null)
        {
            eeLogger.log(logLevel, object.toString());
        }
        else
        {
            eeLogger.log(logLevel, "null");
        }
    }

    public static void severe(Object object)
    {

        log(Level.SEVERE, object.toString());
    }

    public static void debug(Object object)
    {
        if (object != null)
        {
            log(Level.WARNING, String.format("[DEBUG] %s", object.toString()));
        }
        else
        {
            log(Level.WARNING, "[DEBUG] null");
        }
    }

    public static void warning(Object object)
    {

        log(Level.WARNING, object.toString());
    }

    public static void info(Object object)
    {

        log(Level.INFO, object.toString());
    }

    public static void config(Object object)
    {

        log(Level.CONFIG, object.toString());
    }

    public static void fine(Object object)
    {

        log(Level.FINE, object.toString());
    }

    public static void finer(Object object)
    {

        log(Level.FINER, object.toString());
    }

    public static void finest(Object object)
    {

        log(Level.FINEST, object.toString());
    }
}
