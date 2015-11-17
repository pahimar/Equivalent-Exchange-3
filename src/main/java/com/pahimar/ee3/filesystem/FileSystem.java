package com.pahimar.ee3.filesystem;

import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Reference;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.world.World;

import javax.naming.OperationNotSupportedException;
import java.io.File;

public abstract class FileSystem implements IFileSystem
{
    private static IFileSystem world;
    private static IFileSystem player;
    private static IFileSystem global;

    public static void initGlobal(FMLPreInitializationEvent event)
    {
        global = ensure(new GlobalFileSystem(event));
    }

    public static void setWorld(World world)
    {
        FileSystem.world = ensure(new WorldFileSystem(world));
        FileSystem.player = ensure(new PlayerFileSystem(world));
    }

    private static IFileSystem ensure(IFileSystem fileSystem)
    {
        if(fileSystem != null)
            fileSystem.ensureDirectories();

        return fileSystem;
    }

    public static IFileSystem getWorld()
            throws OperationNotSupportedException
    {
        if(world == null)
            throw new OperationNotSupportedException("The world file-system is not loaded.");

        return world;
    }

    public static IFileSystem getWorld(World world)
    {
        return new WorldFileSystem(world);
    }

    public static IFileSystem getPlayer()
            throws OperationNotSupportedException
    {
        if(player == null)
            throw new OperationNotSupportedException("The player file-system is not loaded.");

        return player;
    }

    public static IFileSystem getPlayer(World world)
    {
        return new PlayerFileSystem(world);
    }

    public static IFileSystem getGlobal()
            throws OperationNotSupportedException
    {
        if(global == null)
            throw new OperationNotSupportedException("The global file-system is not loaded.");

        return global;
    }

    @Override
    public File getDataDirectory()
    {
        return combine(getRootDirectory(), Files.DATA_DIRECTORY);
    }

    @Override
    public File getEEDataDirectory()
    {
        return combine(getDataDirectory(), Reference.LOWERCASE_MOD_ID);
    }

    @Override
    public File getEnergyValuesDirectory()
    {
        return combine(getEEDataDirectory(), Files.ENERGY_VALUES_DIRECTORY);
    }

    @Override
    public File getAbilitiesDirectory()
    {
        return combine(getEEDataDirectory(), Files.ABILITIES_DIRECTORY);
    }

    @Override
    public File getKnowledgeDirectory()
    {
        return combine(getEEDataDirectory(), Files.KNOWLEDGE_DIRECTORY);
    }

    @Override
    public File getTransmutationDirectory()
    {
        return combine(getKnowledgeDirectory(), Files.TRANSMUTATION_DIRECTORY);
    }

    @Override
    public File getEnergyValueFile(String fileName)
    {
        return combine(getEnergyValuesDirectory(), fileName);
    }

    @Override
    public File getPreCalcluationEnergyValueFile()
    {
        return getEnergyValueFile(Files.PRE_CALCULATION_ENERGY_VALUES);
    }

    @Override
    public File getPostCalcluationEnergyValueFile()
    {
        return getEnergyValueFile(Files.POST_CALCULATION_ENERGY_VALUES);
    }

    @Override
    public File getStaticEnergyValueFile()
    {
        return getEnergyValueFile(Files.STATIC_ENERGY_VALUES_JSON);
    }

    @Override
    public File getAbilitiesFile()
    {
        return combine(getAbilitiesDirectory(), Files.ABILITIES_JSON_FILE);
    }

    @Override
    public File getTransmutationFile()
    {
        return combine(getTransmutationDirectory(), Files.TEMPLATE_JSON_FILE);
    }

    @Override
    public void ensureDirectories()
    {
        this.getEEDataDirectory().mkdirs();
        this.getEnergyValuesDirectory().mkdirs();
        this.getKnowledgeDirectory().mkdirs();
        this.getAbilitiesDirectory().mkdirs();
        this.getTransmutationDirectory().mkdirs();
    }

    protected static File combine(String path, String path2)
    {
        File basePath = new File(path);
        return combine(basePath, path2);
    }

    protected static File combine(File path, String path2)
    {
        return new File(path, path2);
    }

    protected abstract File getRootDirectory();
}
