package com.pahimar.ee3.lib;

import java.io.File;
import java.util.logging.Level;

import com.pahimar.ee3.core.helper.LogHelper;

/**
 * Equivalent-Exchange-3
 * 
 * Localizations
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Localizations {

    private static final String LANG_RESOURCE_LOCATION = "/mods/ee3/lang/";

    public static String[] localeFiles;
    
    /**
     * parseDir
     * 
     * Parses LANG_RESOURCE_LOCATION for any localizations and loads them into localeFiles
     * 
     * @author Robotic-Brain
     * 
     */
    public static void parseDir() {
        try {
            File resourceFolder = new File(Localizations.class.getResource(LANG_RESOURCE_LOCATION).toURI());
            File[] files = resourceFolder.listFiles();
            
            localeFiles = new String[files.length];
            
            int i = 0;
            for (File resource : files) {
                localeFiles[i++] = LANG_RESOURCE_LOCATION + resource.getName();
                LogHelper.log(Level.INFO, "Added localization file: " + resource.getName());
            }
        } catch (Exception e) {
            LogHelper.log(Level.WARNING, "Unable to load language files!");
        }
    }

}
