package com.pahimar.ee3.filesystem;

import com.pahimar.ee3.reference.Reference;

import java.io.File;

public interface IFileSystem
{
    File getDataDirectory();
    File getEEDataDirectory();

    File getEnergyValuesDirectory();
    File getAbilitiesDirectory();
    File getKnowledgeDirectory();
    File getTransmutationDirectory();

    File getEnergyValueFile(String fileName);
    File getPreCalcluationEnergyValueFile();
    File getPostCalcluationEnergyValueFile();
    File getStaticEnergyValueFile();

    File getAbilitiesFile();
    File getTransmutationFile();

    void ensureDirectories();
}
