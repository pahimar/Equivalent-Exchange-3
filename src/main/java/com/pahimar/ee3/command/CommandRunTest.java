package com.pahimar.ee3.command;

import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Tests;
import com.pahimar.ee3.test.EnergyValueTestSuite;
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
        if (args.length == 2) {

            boolean testFound = false;

            if (Tests.globalTestDirectory != null) {
                for (File testCaseFile : Tests.globalTestDirectory.listFiles()) {
                    if (testCaseFile.isFile() && testCaseFile.getName().equalsIgnoreCase(args[1])) {
                        testFound = true;
                        EnergyValueTestSuite energyValueTestSuite = new EnergyValueTestSuite(testCaseFile);
                        LogHelper.info(EnergyValueTestSuite.TEST_MARKER, "BEGIN TEST ({})", testCaseFile.getName());
                        energyValueTestSuite.run();
                        LogHelper.info(EnergyValueTestSuite.TEST_MARKER, "END TEST ({})", testCaseFile.getName());
                    }
                }

                if (testFound) {
                    commandSender.addChatMessage(new ChatComponentTranslation(Messages.Commands.RUN_TESTS_SUCCESS, args[1]));
                }
                else {
                    commandSender.addChatMessage(new ChatComponentTranslation(Messages.Commands.RUN_TESTS_NOT_FOUND, args[1]));
                }
            }
            else {
                throw new WrongUsageException(Messages.Commands.RUN_TEST_USAGE);
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
