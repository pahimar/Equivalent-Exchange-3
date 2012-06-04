package ee3.core;

import java.io.InputStream;
import java.util.Properties;

import net.minecraft.src.StringTranslate;

/**
* Simple mod localization class. Shamelessly ripped from Buildcraft. All Javadoc after this point is from the original
*
* @author Jimeo Wan
* @license Public domain
*/
public class Localization {

	public static Localization instance = new Localization();
	
	private static final String DEFAULT_LANGUAGE = "en_US";

	private String loadedLanguage = null;
	private Properties defaultMappings = new Properties();
	private Properties mappings = new Properties();

	/**
	* Loads the mod's localization files. All language files must be stored in
	* "[modname]/lang/", in .properties files. (ex: for the mod 'invtweaks',
	* the french translation is in: "invtweaks/lang/fr_FR.properties")
	*
	* @param modName The mod name
	*/
	public Localization() {
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
			InputStream langStream = Localization.class.getResourceAsStream(
			"/lang/ee3/" + newLanguage + ".properties");
			InputStream defaultLangStream = Localization.class.getResourceAsStream(
			"/lang/ee3/" + DEFAULT_LANGUAGE + ".properties");
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
