## Welcome to Equivalent Exchange 3!
Setup MCP was done mostly by Minalien, I just changed it a bit.  Some credit goes to BuildCraft's README.md, which I based this README off of.

The Minecraft Forums page can be found [here] (http://www.minecraftforum.net/topic/1540010-equivalent-exchange-3).

### Compiling Equivalent Exchange 3
IMPORTANT: This is not guaranteed to work as it has not been tested extensively (only done on Linux so far, partly on Windows).
____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
#### Prerequisites  (Tested for Windows 7 ONLY!)
0. Warning:  Please make sure you know *exactly* what you are doing.  It's not our fault if your system crashes and you lose everything by doing something wrong.
1. Download and install the Java JDK [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html).  Accept the license agreement and download the file pertaining to your system.  (Latest version=u9 as of 11/24)
	* Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
	* Click on `Environment Variables`.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `JAVA_HOME`.
  * For `Variable Value`, input `;C:\Program Files (x86)\Java\jdk1.7.0_09` exactly as shown to the end (or wherever your Java JDK installation is), and click `Ok`.  **DO NOT FORGET THE SEMICOLON!**
  * Scroll down to a variable named `Path`, and double-click on it.
  * Append `;C:\Program Files (x86)\Java\jdk1.7.0_09\bin` (or wherever your Java JDK installation is \bin), and click `Ok`.
2. Download Apache Ant [here] (http://ant.apache.org/bindownload.cgi).  Scroll down to "Current Release of Ant" and download an archive.
	* Unzip the files anywhere you want, eg `C:\Program Files (x86)\Ant`.
  * Again, go to `Environment Variables` just like you did for the Java JDK.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `ANT_HOME`.
  * For `Variable Value`, input `C:\Ant\apache-ant-1.8.4` (or your Ant directory \apache-ant-1.8.4).  (This pertains only to Ant 1.8.4!)
  * Scroll down to `Path`, and double-click on it.
  * Append `;C:\Ant\apache-ant-1.8.4\bin` exactly as shown to the end (or your Ant directory \apache-ant-1.8.4\bin).  **DO NOT FORGET THE SEMICOLON!**
3. Download and install Github [here] (http://windows.github.com/).
	* Create an account.
  * Go back to the Equivalent-Exchange-3 directory [here] (http://github.com/pahimar/Equivalent-Exchange-3).
  * Click `Clone to Windows` near the top-left of the page.
  * You should see Github pulse and `pahimar/Equivalent-Exchange-3` appear.  (The local repository defaults to `C:\Users\(username)\Documents\GitHub\Equivalent-Exchange-3`, you can change it if you want but then you have to find it again on Github).
4. Create an empty directory for EE3 development.  This directory is refernced as `mcdev` from now on.  It can be where you cloned EE3, but it'll be a little messy.

5. You are now ready to Setup MCP!


#### Setup MCP (Tested on Linux and Windows 7)
1. Download the latest version of MCP from [here] (http://mcp.ocean-labs.de/index.php/MCP_Releases) , e.g. mcp723.zip. Install MCP dependencies as listed on the website if neccessary.

2. Inside `mcdev`, create a directory named `mcp` and unzip the MCP .zip file into it.

3. To verify, check if a file name `CHANGELOG` exists inside the `mcp` directory.

4. Get a clean (unmodded!) copy of minecraft's `bin` folder. Currently EE3 runs on 1.4.5 but that might have changed.

5. From your `.minecraft` directory (on Windows, defaults to `%appdata%\.minecraft`), copy the `bin` and the `resources` (I don't think you need resources on Windows 7) directory to the `jars` directory inside `mcp`.  (I actually believe Forge's installation might do it for you)

6. Get a clean (unmodded!) copy of `minecraft_server.jar` and also place it into `jars`.  (Forge might do this for you)

7. Download the latest forge **source** for Minecraft 1.4.5 and unzip it into `mcp` so that `mcp/forge/install.sh` exists. You need at least Forge 6.4.0, best way is to get it from [here] (http://files.minecraftforge.net/).

8. Execute `install.sh` (Linux and Mac?) or `install.cmd` (Windows), both found in `mcdev/mcp/forge`. On Linux you might have to `chmod +x` `install.sh` before you can execute it. On some system configurations you need to execute `install.sh` from within the `forge` directory whereas on others it doesn't matter. Just check the output for error messages to find out what you need to do.

#### Setup EE3 (Some tested for Linux, some for Windows)
1. Inside `mcdev`, create a directory named `source`.  If you're doing this from the default clone directory, make two folders:  `source` and `Equivalent-Exchange-3`.  Copy and paste the original files into `Equivalent-Exchange-3`, and then move that into `source`.

2. If you haven't already, move/clone `Equivalent-Exchange-3` into `source`.

3. Right now, you should have a directory that looks something like:

***

	mcdev
	\-mcp
		\-mcp stuff blablabla (should have CHANGELOG).
		\-forge
		\-jars
	\-source
		\-Equivalent-Exchange-3
			\-EE3's files, including build.xml.
***
4. Inside `Equivalent-Exchange-3`, create a new file called `build.properties`.
	* Open it up, and type into it the following:
 		* `dir.development=../../`
		*	`dir.share=Shared` (or what you want it to be)
		*	`dir.release=Releases` (what you want it to be)
		*	`release.minecraft.version=1.4.5` (as of 11/24)
		*	`release.mod.version=pre2` (or whatever version # you want it to be)
5. [FOR WINDOWS] Open up `cmd` by typing `cmd` in Run.

6. [FOR WINDOWS] Navigate to `mcdev\source` by executing `cd mcdev's location\source`.

7. Inside `sources\Equivalent-Exchange-3` execute `ant release`. If you've done everything right, `BUILD SUCCESSFUL` is displayed.  If not, you probably did something wrong.

8. Go to `mcdev\source\Equivalent-Exchange-3\Releases\1.4.5\pre2` (or whatever you put into `build.properties` for `dir.release`, `release.minecraft.version`, and `release.mod.version`).  You should see a .jar named `ee3-universal-pre2.jar` (or whatever you put into `release.mod.version`).

9. Copy the jar into your Minecraft mods folder, delete all old versions of EE3, and play Minecraft!  (Please make sure you have all prerequisites for MC installed first)

### To Update EE3 (For Windows Only?)
1. Check to see if pahimar updated EE3 since you last compiled.  If he/she did, follow these instructions.

2. Open Github.

3. Double-click on `pahimar/Equivalent-Exchange-3`.

4. At the top, there is a button named `Sync` (or `Refreshing...` if its still checking).

5. Click `Sync`, and wait for it to finish.

6. Re-compile (or move it to `mcdev\source` then re-compile, depending on what you did.)