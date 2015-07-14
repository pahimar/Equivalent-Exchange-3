package com.pahimar.ee3.filesystem;

import net.minecraft.world.World;

import java.io.File;

public class WorldFileSystem extends FileSystem
{
    private final File rootDirectory;

    public WorldFileSystem(World world)
    {
        this.rootDirectory = world.getSaveHandler().getWorldDirectory();
    }

    @Override
    protected File getRootDirectory()
    {
        return this.rootDirectory;
    }
}
