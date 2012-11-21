package ee3.common.core.helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.LanguageRegistry;
import ee3.common.lib.Colours;
import ee3.common.lib.ConfigurationSettings;
import ee3.common.lib.Reference;
import ee3.common.lib.Strings;

/**
 * VersionHelper
 * 
 * Contains methods for checking the version of the currently running instance
 * of the mod against a remote version number authority. Meant to help users by
 * notifying them if they are behind the latest published version of the mod
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class VersionHelper {

    // The (publicly available) remote version number authority file
    private static final String REMOTE_VERSION_XML_FILE = "https://raw.github.com/pahimar/Equivalent-Exchange-3/master/version.xml";

    public static Properties remoteVersionProperties = new Properties();

    // All possible results of the remote version number check
    public static final byte UNINITIALIZED = 0;
    public static final byte CURRENT = 1;
    public static final byte OUTDATED = 2;
    public static final byte GENERAL_ERROR = 3;

    // Var to hold the result of the remote version check, initially set to uninitialized
    public static byte result = UNINITIALIZED;
    public static String remoteVersion = null;
    public static String remoteUpdateLocation = null;

    /***
     * Checks the version of the currently running instance of the mod against
     * the remote version authority, and sets the result of the check
     * appropriately
     */
    public static void checkVersion() {

        InputStream remoteVersionRepoStream = null;

        try {
            URL remoteVersionURL = new URL(REMOTE_VERSION_XML_FILE);
            remoteVersionRepoStream = remoteVersionURL.openStream();
            remoteVersionProperties.loadFromXML(remoteVersionRepoStream);

            String remoteVersionProperty = remoteVersionProperties.getProperty(Loader.instance().getMCVersionString());

            if (remoteVersionProperty != null) {
                remoteVersion = remoteVersionProperty.substring(0, remoteVersionProperty.indexOf("|"));
                remoteUpdateLocation = remoteVersionProperty.substring(remoteVersionProperty.indexOf("|") + 1);
            }

            if ((remoteVersion != null) && (remoteVersion.equals(Reference.VERSION))) {
                result = CURRENT;
                return;
            }

            result = OUTDATED;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (result == UNINITIALIZED) {
                result = GENERAL_ERROR;
            }

            try {
                if (remoteVersionRepoStream != null) {
                    remoteVersionRepoStream.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void logResult() {

        if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
            LogHelper.log(Level.INFO, LanguageRegistry.instance().getStringLocalization(Strings.VERSION_CHECK_INIT_LOG_MESSAGE) + " " + REMOTE_VERSION_XML_FILE);
            if ((result == CURRENT) || (result == OUTDATED)) {
                LogHelper.log(Level.INFO, getResultMessage());
            }
            else {
                LogHelper.log(Level.WARNING, getResultMessage());
            }
        }
        else {
            LogHelper.log(Level.INFO, getResultMessage());
        }
    }

    public static String getResultMessage() {

        if (ConfigurationSettings.ENABLE_VERSION_CHECK) {
            if (result == UNINITIALIZED) {
                return LanguageRegistry.instance().getStringLocalization(Strings.UNINITIALIZED_MESSAGE);
            }
            else if (result == CURRENT) {
                String returnString = LanguageRegistry.instance().getStringLocalization(Strings.CURRENT_MESSAGE);
                returnString = returnString.replace("@REMOTE_MOD_VERSION@", remoteVersion);
                returnString = returnString.replace("@MINECRAFT_VERSION@", Loader.instance().getMCVersionString());
                return returnString;
            }
            else if (result == OUTDATED) {
                String returnString = LanguageRegistry.instance().getStringLocalization(Strings.OUTDATED_MESSAGE);
                returnString = returnString.replace("@MOD_NAME@", Reference.MOD_NAME);
                returnString = returnString.replace("@REMOTE_MOD_VERSION@", remoteVersion);
                returnString = returnString.replace("@MINECRAFT_VERSION@", Loader.instance().getMCVersionString());
                returnString = returnString.replace("@MOD_UPDATE_LOCATION@", remoteUpdateLocation);
                return returnString;
            }
            else if (result == GENERAL_ERROR) {
                return LanguageRegistry.instance().getStringLocalization(Strings.GENERAL_ERROR_MESSAGE);
            }
            else {
                return null;
            }
        }
        else {
            return LanguageRegistry.instance().getStringLocalization(Strings.VERSION_CHECK_DISABLED);
        }
    }

    public static String getResultMessageForClient() {

        String returnString = LanguageRegistry.instance().getStringLocalization(Strings.OUTDATED_MESSAGE);
        returnString = returnString.replace("@MOD_NAME@", Colours.TEXT_COLOUR_PREFIX_YELLOW + Reference.MOD_NAME + Colours.TEXT_COLOUR_PREFIX_WHITE);
        returnString = returnString.replace("@REMOTE_MOD_VERSION@", Colours.TEXT_COLOUR_PREFIX_YELLOW + VersionHelper.remoteVersion + Colours.TEXT_COLOUR_PREFIX_WHITE);
        returnString = returnString.replace("@MINECRAFT_VERSION@", Colours.TEXT_COLOUR_PREFIX_YELLOW + Loader.instance().getMCVersionString() + Colours.TEXT_COLOUR_PREFIX_WHITE);
        returnString = returnString.replace("@MOD_UPDATE_LOCATION@", Colours.TEXT_COLOUR_PREFIX_YELLOW + VersionHelper.remoteUpdateLocation + Colours.TEXT_COLOUR_PREFIX_WHITE);
        return returnString;
    }

}
