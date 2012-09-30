package ee3.common.core.handlers;

import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.core.helper.LocalizationHelper;
import ee3.common.lib.Localizations;

/**
 * LocalizationHandler
 * 
 * Loads in all specified localizations for the mod
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
		// For every file specified in the Localization library class, load them into the Language Registry
		for (String localizationFile : Localizations.localeFiles) {
			LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
		}
	}
	
}
