package com.pahimar.ee3.exchange;

import com.pahimar.ee3.filesystem.IFileSystem;

public interface IRegistryContext
{
    boolean hasEnergyValue(Object object, boolean strict);

    IFileSystem getGlobal();
    IFileSystem getWorld();
}
