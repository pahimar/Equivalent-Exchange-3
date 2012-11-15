## Welcome to Equivalent Exchange 3!
The Compilation part (Setup) was done mostly by Minalien, I just changed it a bit.
The Minecraft Forums page can be found [here] (www.minecraftforum.net/topic/1540010-equivalent-exchange-3).

### Compiling Equivalent Exchange 3
IMPORTANT: This is not guaranteed to work as it has not been tested extensively (only done on Linux so far, partly on Windows).
____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
#### Setup MCP
1. Create an empty directory for EE3 development. This directory is referenced as `mcdev` from now on.
2. Download the latest version of MCP from [here] (http://mcp.ocean-labs.de/index.php/MCP_Releases) , e.g. mcp719.zip. Install MCP dependencies as listed on the website if neccessary.
3. Install Apache Ant [here] (ant.apache.org.)
3. Inside `mcdev`, create a directory named `mcp` and unzip the MCP ZIP file into it
4. To verify, check if a file name `CHANGELOG` exists inside the mcp directory.
5. Get a clean (unmodded!) copy of minecraft. Currently EE3 runs on 1.4.2 but that might have changed.
6. From your .minecraft directory, copy the bin and the resources directory to the `jars` directory inside `mcp`.
7. Get a clean (unmodded!) copy of minecraft_server.jar and also place it into `jars`.
8. Download the latest forge source for Minecraft 1.4.2 and unzip it into `mcp` so that `mcp/forge/install.sh` exists. You need at least Forge 6.0.1, best way is to get it from [here] (http://files.minecraftforge.net/): http://files.minecraftforge.net/ NOTE: 6.0.1.355 was the last version to support 1.4.2, do not get anything later than that for 1.4.2 versions of EE3!
9. Execute `mcdev/mcp/forge/install.sh` or `mcp/forge/install.cmd`, depending on your platform. On Linux you might have to `chmod +x` `install.sh` before you can execute it. On some system configurations you need to execute `install.sh` from within the forge directory whereas on others it doesn't matter. Just check the output for error messages to find out what you need to do.

#### Setup EE3
1. Inside `mcdev`, create a directory named `source`. Clone EE3 into this directory.
2. [FOR WINDOWS] Open up cmd.  Type `cmd` in Run.
3. [FOR WINDOWS] Navigate to `mcdev\source` by executing `cd [wherever your mcdev is located, just copy and paste the path in]`.
2. Inside `sources\Equivalent-Exchange-3` execute `ant build -Ddir.development=../../`. If you've done everything right, BUILD SUCCESSFUL is displayed.
3. Execute `ant recompile -Ddir.development=../../`.
4. Execute `ant reobfuscate -Ddir.development=../../`.
5. If you've done everything right AND the source code currently compiles, you can execute startclient.sh or startclient.bat inside the mcp directory now to start Minecraft with EE3.
