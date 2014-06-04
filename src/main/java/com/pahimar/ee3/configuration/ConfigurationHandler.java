package com.pahimar.ee3.configuration;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.proxy.ClientProxy;
import java.io.File;

public class ConfigurationHandler
{
    public static void init(String configPath)
    {
	if(EquivalentExchange3.proxy instanceof ClientProxy)
        	ClientConfiguration.init(new File(configPath + "client.properties"));
    }
}
