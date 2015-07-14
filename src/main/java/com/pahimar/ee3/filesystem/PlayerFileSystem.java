package com.pahimar.ee3.filesystem;

import com.pahimar.ee3.reference.Files;
import net.minecraft.world.World;

import java.io.File;

public class PlayerFileSystem extends FileSystem
{
    private final File rootDirectory;

    public PlayerFileSystem(World world)
    {
        this.rootDirectory = world.getSaveHandler().getWorldDirectory();
    }

    @Override
    protected File getRootDirectory()
    {
        return this.rootDirectory;
    }

    @Override
    public File getDataDirectory()
    {
        return combine(this.getRootDirectory(), Files.PLAYER_DATA_DIRECTORY);
    }
}
