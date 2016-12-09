package com.pahimar.ee3.command;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.reference.Files;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.test.EnergyValueTestSuite;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CommandRunTest extends CommandEE {

    @Override
    public String getName() {
        return Names.Commands.RUN_TEST;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getUsage(ICommandSender commandSender) {
        return Messages.Commands.RUN_TEST_USAGE;
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args) throws CommandException {

        if (args.length == 2) {

            boolean testFound = false;

            if (Files.globalTestDirectory != null) {
                for (File testCaseFile : Files.globalTestDirectory.listFiles()) {
                    if (testCaseFile.isFile() && testCaseFile.getName().equalsIgnoreCase(args[1])) {
                        testFound = true;
                        EnergyValueTestSuite energyValueTestSuite = new EnergyValueTestSuite(testCaseFile);
                        LogHelper.info(EnergyValueTestSuite.TEST_MARKER, "BEGIN TEST ({})", testCaseFile.getName());
                        energyValueTestSuite.run();
                        LogHelper.info(EnergyValueTestSuite.TEST_MARKER, "END TEST ({})", testCaseFile.getName());
                    }
                }

                if (testFound) {
                    commandSender.sendMessage(new TextComponentTranslation(Messages.Commands.RUN_TESTS_SUCCESS, args[1]));
                }
                else {
                    commandSender.sendMessage(new TextComponentTranslation(Messages.Commands.RUN_TESTS_NOT_FOUND, args[1]));
                }
            }
            else {
                throw new WrongUsageException(Messages.Commands.RUN_TEST_USAGE);
            }
        }
        else {
            throw new WrongUsageException(Messages.Commands.RUN_TEST_USAGE);
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer minecraftServer, ICommandSender commandSender, String[] args, @Nullable BlockPos pos) {

        if (args.length == 2) {

            File testCaseDirectory = new File(FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().getSaveHandler().getWorldDirectory(), "data" + File.separator + EquivalentExchange3.MOD_ID + File.separator + "energyvalues" + File.separator + "testcases");
            testCaseDirectory.mkdirs();

            ArrayList<String> fileNames = new ArrayList<>();

            if (Files.globalTestDirectory != null) {
                for (File testCaseFile : Files.globalTestDirectory.listFiles()) {
                    if (testCaseFile.isFile() && testCaseFile.getAbsolutePath().endsWith(".json")) {
                        fileNames.add(testCaseFile.getName());
                    }
                }
            }

            return getListOfStringsMatchingLastWord(args, fileNames.toArray(new String[0]));
        }

        return null;
    }
}
