package ee3.core.helper;

import java.io.InputStream;
import java.util.Properties;

import ee3.lib.Reference;

import net.minecraft.src.StringTranslate;

/**
* Simple mod localization class. Shamelessly ripped from Buildcraft, renamed LocalizationHelper, and modified. 
*
* @author Jimeo Wan
* @license Public domain
*/
public class LocalizationHelper {

	public static LocalizationHelper instance = new LocalizationHelper();
	
	private static final String DEFAULT_LANGUAGE = "en_US";

	private String loadedLanguage = null;
	private Properties defaultMappings = new Properties();
	private Properties mappings = new Properties();

	/**
	* Loads the mod's localization files. All language files must be stored in
	* "/lang[modname]/", in .lang files. (ex: for the mod 'invtweaks',
	* the french translation is in: "lang/invtweaks/fr_FR.lang")
	*
	* @param modName The mod name
	*/
	public LocalizationHelper() {
		load(getCurrentLanguage());
	}

	/**
	* Get a string for the given key, in the currently active translation.
	*
	* @param key
	* @return
	*/
	public synchronized String get(String key) {
		String currentLanguage = getCurrentLanguage();
		if (!currentLanguage.equals(loadedLanguage))
		load(currentLanguage);
	
		return mappings.getProperty(key, defaultMappings.getProperty(key, key));
	}

	private void load(String newLanguage) {
		defaultMappings.clear();
		mappings.clear();
		try {
			InputStream langStream = LocalizationHelper.class.getResourceAsStream(
				Reference.LANG_RESOURCE_LOCATION + newLanguage + ".lang");
			InputStream defaultLangStream = LocalizationHelper.class.getResourceAsStream(
				Reference.LANG_RESOURCE_LOCATION + DEFAULT_LANGUAGE + ".lang");
			mappings.load((langStream == null) ? defaultLangStream : langStream);
			defaultMappings.load(defaultLangStream);
		
			if (langStream != null) {
				langStream.close();
			}
			defaultLangStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		loadedLanguage = newLanguage;
	}

	private static String getCurrentLanguage() {
		return StringTranslate.getInstance().getCurrentLanguage();
	}
}
