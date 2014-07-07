package com.pahimar.ee3.thread;

import com.pahimar.ee3.exchange.EnergyValueRegistry;
import com.pahimar.ee3.util.LogHelper;

public class DynamicEnergyValueInitThread implements Runnable
{
    private static DynamicEnergyValueInitThread instance = new DynamicEnergyValueInitThread();

    public static void initEnergyValueRegistry()
    {
        new Thread(instance, "DynamicEV Thread").start();
    }

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();
        EnergyValueRegistry.getInstance();
        long duration = System.currentTimeMillis() - startTime;
        if (duration > 10)
        {
            LogHelper.info(String.format("DynamicEV system initialized after %s ms", duration));
        }
    }
}
