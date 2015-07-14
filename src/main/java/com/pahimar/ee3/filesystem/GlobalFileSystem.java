package com.pahimar.ee3.filesystem;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class GlobalFileSystem extends FileSystem
{
    private final File rootDirectory;

    public GlobalFileSystem(FMLPreInitializationEvent event)
    {
        this.rootDirectory = event.getModConfigurationDirectory().getParentFile();
    }

    @Override
    protected File getRootDirectory()
    {
        return this.rootDirectory;
    }
}
