package com.pahimar.ee3.exchange;

import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.filesystem.IFileSystem;
import com.pahimar.ee3.util.SerializationHelper;

import java.io.File;
import java.util.Map;

public class FileSystemEnergyValuesSource implements IEnergyValuesSource
{
    private final String fileName;
    private final boolean isWorld;

    public FileSystemEnergyValuesSource(String fileName)
    {
        this(fileName, false);
    }

    public FileSystemEnergyValuesSource(String fileName, boolean isWorld)
    {
        this.fileName = fileName;
        this.isWorld = isWorld;
    }

    @Override
    public Map<WrappedStack, EnergyValue> getValues(IRegistryContext context)
    {
        File file = this.getFileSystem(context).getEnergyValueFile(this.fileName);
        return SerializationHelper.readEnergyValueStackMapFromJsonFile(file);
    }

    private IFileSystem getFileSystem(IRegistryContext context)
    {
        return this.isWorld
            ? context.getWorld()
            : context.getGlobal();
    }

    @Override
    public String toString()
    {
        return this.fileName;
    }
}
