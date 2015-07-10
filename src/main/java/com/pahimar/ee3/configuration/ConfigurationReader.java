package com.pahimar.ee3.configuration;

import com.pahimar.ee3.reference.Settings;
import net.minecraftforge.common.config.Configuration;

public final class ConfigurationReader
{
    public static void ReadConfig(Configuration configuration)
    {
        Settings.General.syncThreshold =
                ConfigEntries.General.SyncThreshold
                        .entry.getValue(configuration);

        Settings.Sounds.soundMode =
                ConfigEntries.Sounds.SoundMode
                        .entry.getValue(configuration);

        Settings.Abilities.onlyLoadFile =
                ConfigEntries.Abilities.OnlyLoadFile
                        .entry.getValue(configuration);

        Settings.DynamicEnergyValueGeneration.regenerateEnergyValuesWhen =
                ConfigEntries.DynamicEnergyValueGeneration.RegenerateEnergyValues
                        .entry.getValue(configuration);

        Settings.Debug.logTraceToInfo =
                ConfigEntries.Debug.LogTraceToInfo
                        .entry.getValue(configuration);
    }
}
