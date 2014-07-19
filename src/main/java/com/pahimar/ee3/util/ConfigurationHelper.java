package com.pahimar.ee3.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigurationHelper
{
    public static String getString(Configuration configuration, String name, String category, String defaultValue, String comment, String[] validValues, String langKey)
    {
        Property property = configuration.get(category, name, defaultValue);
        property.setValidValues(validValues);
        property.setLanguageKey(langKey);
        property.comment = comment + " [default: " + defaultValue + "]";
        String value = property.getString();

        for (int i = 0; i < validValues.length; i++)
        {
            if (value.equalsIgnoreCase(validValues[i]))
            {
                return validValues[i];
            }
        }

        return defaultValue;
    }
}
