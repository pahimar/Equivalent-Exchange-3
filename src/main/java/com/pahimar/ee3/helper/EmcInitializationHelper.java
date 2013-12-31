package com.pahimar.ee3.helper;

import com.pahimar.ee3.emc.EmcRegistry;

public class EmcInitializationHelper implements Runnable
{
    private static EmcInitializationHelper instance = new EmcInitializationHelper();

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();
        EmcRegistry.getInstance();
        long duration = System.currentTimeMillis() - startTime;
        if (duration > 10)
        {
            LogHelper.info(String.format("DynEmc system initialized after %s ms", duration));
        }
    }

    public static void initEmcRegistry()
    {
        new Thread(instance).start();
    }
}
