## Welcome to Equivalent Exchange 3!

The MinecraftForum page can be found [here] (www.minecraftforum.net/topic/1540010-equivalent-exchange-3).

### Compiling Equivalent Exchange 3
____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
#### Setup MCP
1. Create an empty directory for EE3 development. This directory is referenced as mcdev from now on
2. Download the latest version of MCP from [here] (http://mcp.ocean-labs.de/index.php/MCP_Releases) , e.g. mcp719.zip. Install MCP dependencies as listed on the website if neccessary.
3. Install Apache Ant [here] (ant.apache.org.)
3. Inside mcdev, create a directory named mcp and unzip the MCP ZIP file into it
4. To verify, check if a file name CHANGELOG exists inside the mcp directory.
5. Get a clean (unmodded!) copy of minecraft. Currently EE3 runs on 1.4.2 but that might have changed.
6. From your .minecraft directory, copy the bin and the resources directory to the jars directory inside the mcp directory.
7. Get a clean (unmodded!) copy of minecraft_server.jar and also place it into the jars directory inside mcp
8. Download the latest forge source for Minecraft 1.4.2 and unzip it into the mcp directory so that mcp/forge/install.sh exists. You need at least Forge 6.0.1, best way is to get it from [here] (http://files.minecraftforge.net/): http://files.minecraftforge.net/ NOTE: 6.0.1.355 was the last version to support 1.4.2, do not get anything later than that for 1.4.2 versions of EE3!
9. Execute mcp/forge/install.sh or mcp/forge/install.cmd, depending on your platform. On Linux you might have to chmod +x install.sh before you can execute it. On some system configurations you need to execute install.sh from within the forge directory whereas on others it doesn't matter. Just check the output for error messages to find out what you need to do.

#### Setup EE3