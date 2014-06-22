package com.pahimar.ee3.configuration;

import com.pahimar.ee3.EquivalentExchange3;

import java.io.File;

public class ConfigurationHandler
{
    public static void init(String configPath)
    {
        EquivalentExchange3.proxy.initClientConfiguration(new File(configPath + "client.properties"));
    }
}
