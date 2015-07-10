package com.pahimar.ee3.reference;

import com.pahimar.ee3.configuration.EnergyRegenOption;
import com.pahimar.ee3.configuration.SoundModeOption;

public class Settings
{
    public static class General
    {
        public static int syncThreshold;
    }

    public static class Sounds
    {
        public static SoundModeOption soundMode;
    }

    public static class Abilities
    {
        public static boolean onlyLoadFile;
    }

    public static class DynamicEnergyValueGeneration
    {
        public static EnergyRegenOption regenerateEnergyValuesWhen;
    }

    public static class Debug
    {
        public static boolean logTraceToInfo;
    }
}
