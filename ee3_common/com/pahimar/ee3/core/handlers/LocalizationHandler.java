package com.pahimar.ee3.core.handlers;

import com.pahimar.ee3.core.helper.LocalizationHelper;
import com.pahimar.ee3.lib.Localizations;

import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * Equivalent-Exchange-3
 * 
 * LocalizationHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LocalizationHandler {

    /***
     * Loads in all the localization files from the Localizations library class
     */
    public static void loadLanguages() {
        
        // Parse Localization directory
        Localizations.parseDir();
        
        // For every file specified in the Localization library class, load them into the Language Registry
        for (String localizationFile : Localizations.localeFiles) {
            LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
        }
    }

}
