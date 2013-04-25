package com.pahimar.ee3.lib;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
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
    private static final String JAR_SUBDIRECTORY = "mods/ee3/lang/";
    
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
            URL resourceURL = Localizations.class.getResource(LANG_RESOURCE_LOCATION);
            
            if (resourceURL == null) {
                throw new Exception("NULL POINTER!");
            }
            
            if (resourceURL.getProtocol().equals("file")) {
                parseNormalDirectory(resourceURL);
                
            } else if (resourceURL.getProtocol().equals("jar")) {
                parseJarFile(resourceURL);
            }
            
            LogHelper.log(Level.INFO, "Loaded " + localeFiles.length + " localizations");
        } catch (Exception e) {
            LogHelper.log(Level.SEVERE, "Unable to load language files!");
            e.printStackTrace(System.err);
        }
    }
    
    /**
     * parseNormalDirectory
     * 
     * Parses a normal Directory on the filesystem into localeFiles
     * @author Robotic-Brain
     * 
     * @param resourceURL Parent directory URL
     * @throws URISyntaxException
     */
    private static void parseNormalDirectory(URL resourceURL) throws URISyntaxException {
        LogHelper.log(Level.INFO, "Loading FILE");
        
        File resourceFolder = new File(resourceURL.toURI());
        File[] files = resourceFolder.listFiles();
        
        localeFiles = new String[files.length];
        
        int i = 0;
        for (File resource : files) {
            localeFiles[i++] = LANG_RESOURCE_LOCATION + resource.getName();
            LogHelper.log(Level.INFO, "Added localization file: " + resource.getName());
        }
    }
    
    
    /**
     * parseJarFile
     * 
     * Walks a jar file and ads all files in given "subdirectory" (JAR_SUBDIRECTORY) to localeFiles
     * @author Robotic-Brain
     * 
     * @param resourceURL
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
    private static void parseJarFile(URL resourceURL) throws UnsupportedEncodingException, IOException {
        LogHelper.log(Level.INFO, "Loading JAR");
        
        // Getting Jar file Object
        String jarFileString = resourceURL.getPath().substring(5, resourceURL.getPath().indexOf("!"));
        JarFile jarFile = new JarFile(URLDecoder.decode(jarFileString, "UTF-8"));
        
        // Iterate over entries
        ArrayList<String> files = new ArrayList<String>();
        Enumeration<JarEntry> entries = jarFile.entries();
        while (entries.hasMoreElements()) {
            String jarSubPath = entries.nextElement().getName();
            
            // Only search specific Directory
            if (jarSubPath.startsWith(JAR_SUBDIRECTORY)) {
                jarSubPath = jarSubPath.substring(JAR_SUBDIRECTORY.length());
                if (!jarSubPath.trim().isEmpty() && jarSubPath.indexOf("/") < 0) {
                    // If file and not dir add to list
                    files.add(LANG_RESOURCE_LOCATION + jarSubPath);
                    LogHelper.log(Level.INFO, "Added localization file: " + jarSubPath);
                }
            }
        }
        
        localeFiles = files.toArray(new String[files.size()]);
    }

}
