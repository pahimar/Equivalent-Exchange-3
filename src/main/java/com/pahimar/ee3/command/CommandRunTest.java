package com.pahimar.ee3.command;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.test.EnergyValueMappingsTestSuite;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentTranslation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandRunTest extends CommandEE
{
    @Override
    public String getCommandName()
    {
        return Names.Commands.RUN_TEST;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender)
    {
        return Messages.Commands.RUN_TEST_USAGE;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            File testCaseDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID + File.separator + "energyvalues" + File.separator + "testcases");
            testCaseDirectory.mkdirs();
            boolean testFound = false;

            for (File testCaseFile : testCaseDirectory.listFiles())
            {
                if (testCaseFile.isFile() && testCaseFile.getName().equalsIgnoreCase(args[1]))
                {
                    testFound = true;
                    EnergyValueMappingsTestSuite energyValueMappingsTestSuite = new EnergyValueMappingsTestSuite(testCaseFile);
                    LogHelper.info(String.format("BEGIN TEST (%s)", testCaseFile.getName()));
                    energyValueMappingsTestSuite.runTestSuite();
                    LogHelper.info(String.format("END TEST (%s)", testCaseFile.getName()));
                }
            }

            if (testFound)
            {
                commandSender.addChatMessage(new ChatComponentTranslation(Messages.Commands.RUN_TESTS_SUCCESS, args[1]));
            }
            else
            {
                commandSender.addChatMessage(new ChatComponentTranslation(Messages.Commands.RUN_TESTS_NOT_FOUND, args[1]));
            }
        }
        else
        {
            throw new WrongUsageException(Messages.Commands.RUN_TEST_USAGE);
        }
    }

    @Override
    public List addTabCompletionOptions(ICommandSender commandSender, String[] args)
    {
        if (args.length == 2)
        {
            File testCaseDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + Reference.LOWERCASE_MOD_ID + File.separator + "energyvalues" + File.separator + "testcases");
            testCaseDirectory.mkdirs();

            ArrayList<String> fileNames = new ArrayList<String>();

            for (File testCaseFile : testCaseDirectory.listFiles())
            {
                if (testCaseFile.isFile() && testCaseFile.getAbsolutePath().endsWith(".json"))
                {
                    fileNames.add(testCaseFile.getName());
                }
            }

            return getListOfStringsMatchingLastWord(args, fileNames.toArray(new String[0]));
        }

        return null;
    }
}
