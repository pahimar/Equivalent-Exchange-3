package com.pahimar.ee3.util;

import com.pahimar.ee3.exchange.EnergyRegistry;

public class EmcInitializationHelper implements Runnable
{
    private static EmcInitializationHelper instance = new EmcInitializationHelper();

    public static void initEmcRegistry()
    {
        new Thread(instance, "EE3 - DynEmc Init Thread").start();
    }

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();
        EnergyRegistry.getInstance();
        long duration = System.currentTimeMillis() - startTime;
        if (duration > 10)
        {
            LogHelper.info(String.format("DynEmc system initialized after %s ms", duration));
        }
    }
}
