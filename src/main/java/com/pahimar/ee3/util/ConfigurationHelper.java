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
        boolean isValueValid = false;

        for (int i = 0; i < validValues.length; i++)
        {
            if (value.equalsIgnoreCase(validValues[i]))
            {
                isValueValid = true;
                value = validValues[i];
            }
        }

        if (isValueValid)
        {
            return value;
        }

        return defaultValue;
    }
}
