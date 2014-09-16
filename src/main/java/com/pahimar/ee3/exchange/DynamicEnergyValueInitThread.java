package com.pahimar.ee3.exchange;

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
        EnergyValueRegistry.getInstance().init();
        LogHelper.info(String.format("DynamicEV system initialized after %s ms", System.currentTimeMillis() - startTime));
    }
}
