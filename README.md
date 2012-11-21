## Welcome to Equivalent Exchange 3!
The Compilation part (Setup) was done mostly by Minalien, I just changed it a bit.  Some credit goes to BuildCraft's README.md, which I based this README off of.

The Minecraft Forums page can be found [here] (http://www.minecraftforum.net/topic/1540010-equivalent-exchange-3).

### Compiling Equivalent Exchange 3
IMPORTANT: This is not guaranteed to work as it has not been tested extensively (only done on Linux so far, partly on Windows).
____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
#### Setup MCP
1. Create an empty directory for EE3 development. This directory is referenced as `mcdev` from now on.
2. Download the latest version of MCP from [here] (http://mcp.ocean-labs.de/index.php/MCP_Releases) , e.g. mcp723.zip. Install MCP dependencies as listed on the website if neccessary.
3. Install Apache Ant [here] (http://ant.apache.org)
3. Inside `mcdev`, create a directory named `mcp` and unzip the MCP ZIP file into it
4. To verify, check if a file name `CHANGELOG` exists inside the mcp directory.
5. Get a clean (unmodded!) copy of minecraft. Currently EE3 runs on 1.4.5 but that might have changed.
6. From your .minecraft directory, copy the bin and the resources directory to the `jars` directory inside `mcp`.
7. Get a clean (unmodded!) copy of minecraft_server.jar and also place it into `jars`.
8. Download the latest forge source for Minecraft 1.4.5 and unzip it into `mcp` so that `mcp/forge/install.sh` exists. You need at least Forge 6.4.0, best way is to get it from [here] (http://files.minecraftforge.net/).
9. Execute `mcdev/mcp/forge/install.sh` or `mcp/forge/install.cmd`, depending on your platform. On Linux you might have to `chmod +x` `install.sh` before you can execute it. On some system configurations you need to execute `install.sh` from within the forge directory whereas on others it doesn't matter. Just check the output for error messages to find out what you need to do.

#### Setup EE3
1. Inside `mcdev`, create a directory named `source`. Clone EE3 into this directory.
2. [FOR WINDOWS] Open up cmd.  Type `cmd` in Run.
3. [FOR WINDOWS] Navigate to `mcdev\source` by executing `cd [wherever your mcdev is located, just copy and paste the path in]`.
2. Inside `sources\Equivalent-Exchange-3` execute `ant release -Ddir.development=../../`. If you've done everything right, BUILD SUCCESSFUL is displayed.
3. Go to `mcdev\source\Equivalent-Exchange-3\Releases\1.4.5\pre2`.  You should see a .jar named `ee3-universal-pre2.jar`.
4. Copy the jar into your Minecraft mods folder, and play Minecraft!
