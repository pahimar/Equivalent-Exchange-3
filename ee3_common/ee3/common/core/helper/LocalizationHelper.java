package ee3.common.core.helper;

import java.util.HashMap;
import java.util.Properties;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.src.StringTranslate;
import ee3.common.core.handlers.LocalizationHandler;

/**
 * LocalizationHelper
 * 
 * Helper class for looking up localized strings in the Language Registry
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class LocalizationHelper {

	// The language data field name for localization data in the Language Registry 
	private static final String LANGUAGE_REGISTRY_LANGUAGE_DATA_FIELD = "modLanguageData";
	
	/***
	 * Returns the localized version of the text represented by key for the current language from the Language Registry
	 * @param key The key that represents the text we are attempting to localize
	 * @return The localized string for the specified key for the current language, null if no localized version of the key exists in the Language Registry
	 */
	public static String localize(String key) {
		return localize(StringTranslate.getInstance().getCurrentLanguage(), key);
	}

	/***
	 * Returns the localized version of the text represented by key for the specified language from the Language Registry
	 * @param language The language for which to search for the localized version of the key
	 * @param key The key that represents the text we are attempting to localize
	 * @return The localized string for the specified key for the specified language, null if no localized version of the key exists in the Language Registry
	 */
	public static String localize(String language, String key) {
		String localizedValue = "";
		HashMap<String,Properties> modLanguageData = null;
		Properties languageMapping = null;
		
		try {
			modLanguageData = ReflectionHelper.getPrivateValue(cpw.mods.fml.common.registry.LanguageRegistry.class, LanguageRegistry.instance(), LANGUAGE_REGISTRY_LANGUAGE_DATA_FIELD);
			languageMapping = modLanguageData.get(language);
			localizedValue = languageMapping.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		return localizedValue;
	}
	
	/***
	 * Simple test to determine if a specified file name represents a XML file or not
	 * @param fileName String representing the file name of the file in question
	 * @return True if the file name represents a XML file, false otherwise
	 */
	public static boolean isXMLLanguageFile(String fileName) {
		return fileName.endsWith(".xml");
	}
	
	/***
	 * Returns the locale from file name
	 * @param fileName String representing the file name of the file in question
	 * @return String representation of the locale snipped from the file name
	 */
	public static String getLocaleFromFileName(String fileName) {
		return fileName.substring(fileName.lastIndexOf('/') + 1, fileName.lastIndexOf('.'));
	}
	
}
