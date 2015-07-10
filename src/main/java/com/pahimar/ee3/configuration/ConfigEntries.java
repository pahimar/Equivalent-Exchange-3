package com.pahimar.ee3.configuration;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Settings;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public final class ConfigEntries
{
    private final static String CATEGORY_DEBUG = "general.Debug";

    public static final class General
    {
        public static final class SyncThreshold
        {
            public static final int minValue = 0;
            public static final int maxValue = Short.MAX_VALUE;
            public static final int defaultValue = 5;

            public static final IntConfigEntry entry;

            static
            {
                entry = new IntConfigEntry(
                        Messages.Configuration.GENERAL_SYNC_THRESHOLD,
                        Configuration.CATEGORY_GENERAL,
                        StatCollector.translateToLocal(Messages.Configuration.GENERAL_SYNC_THRESHOLD_COMMENT),
                        Messages.Configuration.GENERAL_SYNC_THRESHOLD_LABEL,
                        defaultValue, minValue, maxValue);
            }
        }
    }

    public static final class Abilities
    {
        public static final class OnlyLoadFile
        {
            public static final boolean defaultValue = false;

            public static final BooleanConfigEntry entry;

            static
            {
                entry = new BooleanConfigEntry(
                        Messages.Configuration.ABILITIES_ONLY_LOAD_FILE,
                        Configuration.CATEGORY_GENERAL,
                        StatCollector.translateToLocal(Messages.Configuration.ABILITIES_ONLY_LOAD_FILE_COMMENT),
                        Messages.Configuration.ABILITIES_ONLY_LOAD_FILE_LABEL,
                        defaultValue);
            }
        }
    }

    public static final class Debug
    {
        public static final class LogTraceToInfo
        {
            public static final boolean defaultValue = false;

            public static final BooleanConfigEntry entry;

            static
            {
                entry = new BooleanConfigEntry(
                        Messages.Configuration.LOG_TRACE_TO_INFO,
                        CATEGORY_DEBUG,
                        StatCollector.translateToLocal(Messages.Configuration.LOG_TRACE_TO_INFO_COMMENT),
                        Messages.Configuration.LOG_TRACE_TO_INFO_LABEL,
                        defaultValue);
            }
        }
    }

    public static final class Sounds
    {
        public static final class SoundMode
        {
            public static final EnumConfigEntry<SoundModeOption> entry;

            static
            {
                entry = ConfigEntry.Create(
                        Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN,
                        Configuration.CATEGORY_GENERAL,
                        StatCollector.translateToLocal(Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN_COMMENT),
                        Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN_LABEL,
                        new ValueTransformer<SoundModeOption>(SoundModeOption.All)
                                .addTransform("all", SoundModeOption.All)
                                .addTransform("self", SoundModeOption.Self)
                                .addTransform("none", SoundModeOption.None)
                );
            }
        }
    }

    public static final class DynamicEnergyValueGeneration
    {
        public static final class RegenerateEnergyValues
        {
            public static final EnumConfigEntry<EnergyRegenOption> entry;

            static
            {
                entry = ConfigEntry.Create(
                        Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN,
                        Configuration.CATEGORY_GENERAL,
                        StatCollector.translateToLocal(Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN_COMMENT),
                        Messages.Configuration.REGENERATE_ENERGYVALUES_WHEN_LABEL,
                        new ValueTransformer<EnergyRegenOption>(EnergyRegenOption.Always)
                                .addTransform("always", EnergyRegenOption.Always)
                                .addTransform("never", EnergyRegenOption.Never)
                                .addTransform("when mods change", EnergyRegenOption.ModsChange)
                                .addTransform("mods change", EnergyRegenOption.ModsChange)
                                .addTransform("mods", EnergyRegenOption.ModsChange)
                );
            }
        }
    }
}
