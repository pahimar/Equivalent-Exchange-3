## Welcome to Equivalent Exchange 3!
Setup MCP was done mostly by the people who update the wiki, I just changed it a bit.  Some credit goes to BuildCraft's README.md, which I based this README off of.

The Minecraft Forums page can be found [here] (http://www.minecraftforum.net/topic/1540010-equivalent-exchange-3).

### Compiling Equivalent Exchange 3
IMPORTANT: This is not guaranteed to work as it has not been tested extensively (Linux and Windows tested).
____________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
#### Prerequisites  (Tested for Windows 7 ONLY!)
0. WARNING:  Make sure you know EXACTLY what you're doing!  It's not any of our faults if your OS crashes, becomes corrupted, etc.
1. Download and install the Java JDK [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html).  Scroll down, accept the Oracle Binary Code License Agreement for Java SE, and download the one pertaining to your OS.
	* Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
	* Click on `Environment Variables`.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `JAVA_HOME`.
  * For `Variable Value`, input something similar to `;C:\Program Files (x86)\Java\jdk1.7.0_09` exactly as shown to the end.(or wherever your Java JDK installation is), and click `Ok`.
  * Scroll down to a variable named `Path`, and double-click on it.
  * Append `;C:\Program Files (x86)\Java\jdk1.7.0_09\bin` (or wherever your Java JDK installation is \bin), and click `Ok`.
2. Download Apache Ant [here] (http://ant.apache.org).
	* Unzip the files anywhere you want, eg `C:\Program Files (x86)\Ant`.
  * Again, go to `Environment Variables` just like you did for the Java JDK.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `ANT_HOME`.
  * For `Variable Value`, input `C:\Ant\apache-ant-1.8.4` (or your Ant directory \apache-ant-1.8.4).
  * Scroll down to `Path`, and double-click on it.
  * Append `;C:\Ant\apache-ant-1.8.4\bin` exactly as shown to the end (or your Ant directory \apache-ant-1.8.4\bin).
3. Download and install Github [here] (http://windows.github.com/).
	* Create an account.
  * Scroll to the top of this page, login at the top-right, and then click `Clone to Windows` near the top-left of the page..
  * You should see Github flash and `pahimar/Equivalent-Exchange-3` appear.  (The local repository defaults to `C:\Users\(username)\Documents\GitHub\Equivalent-Exchange-3`, you can change it if you want but then you have to find it again on Github).
4. Create an empty directory for EE3 development.  This directory is refernced as `mcdev` from now on.  It can be where you cloned EE3, but it'll be a little messy.

5. You are now ready to setup MCP!


#### Setup MCP (Linux and Windows 7 tested)
1. Download the latest version of MCP from [here] (http://mcp.ocean-labs.de/index.php/MCP_Releases), e.g. mcp723.zip. Install MCP dependencies as listed on the website if neccessary.

2. Inside `mcdev`, create a directory named `mcp` and unzip the MCP .zip file into it.
	* To verify, check if a file name `CHANGELOG` exists inside `mcp`.
3. Get an unmodded copy of minecraft's `bin` folder. Currently EE3 runs on 1.4.5 but that might have changed.

4. From your `.minecraft` directory (on Windows, defaults to `%appdata%\.minecraft`), copy the `bin` and the `resources` (not sure if necessary) directory to the `jars` directory inside `mcp`.  (Forge might do this for you, not confirmed.)

5. Get an unmodded copy of `minecraft_server.jar` and also place it into `jars`.  (Forge might do this for you)

6. Download the latest forge **source** for Minecraft 1.4.5 and unzip it into `mcp` so that `mcp\forge\install.sh` exists. You need at least Forge 6.4.0, best way is to get it from [here] (http://files.minecraftforge.net/).

7. Execute `install.sh` (Linux and Mac) or `install.cmd` (Windows), both found in `mcdev\mcp\forge`. On Linux you might have to `chmod +x` `install.sh` before you can execute it. On some system configurations you need to execute `install.sh` from within the `forge` directory whereas on others it doesn't matter. Just check the output for error messages to find out what you need to do.

#### Setup EE3 (Some tested for Linux, tested fully for Windows)
1. Inside `mcdev`, create a directory named `source`.

2. Move/clone `Equivalent-Exchange-3` into `source`.

3. Right now, you should have a directory that looks something like:

***

	mcdev
	\-mcp
		\-complicated mcp stuff (should have CHANGELOG).
		\-forge
		\-jars
	\-source
		\-Equivalent-Exchange-3
			\-EE3's files (should have build.xml).
***
4. Inside `Equivalent-Exchange-3`, create a new file called `build.properties`.
	* Open it up, and type into it the following:
 		* `dir.development=../../`
		*	`dir.share=Shared` (or what you want it to be-optional)
		*	`dir.release=Releases` (what you want it to be)
		*	`release.minecraft.version=1.4.5` (as of 11/30)
		*	`release.mod.version=pre2` (or whatever version # want it to be)
5. [FOR WINDOWS] Open up `cmd` by typing `cmd` in Run.

6. [FOR WINDOWS] Navigate to `mcdev\source\Equivalent-Exchange-3` by executing `cd mcdev's location\source\Equivalent-Exchange-3`.

7. Execute `ant release`. This will generally take around 5-15 minutes, depending on your computer.  If you've done everything right, `BUILD SUCCESSFUL` is displayed after it finishes.
	* If you see `BUILD FAILED`, check the error output (it should be right around `BUILD FAILED`), fix everything, and try again.
8. Go to `mcdev\source\Equivalent-Exchange-3\Releases\1.4.5\pre2` (This might be different according to what you put in build.properties).
	*  You should see a .jar named `ee3-universal-pre2.jar` (Again, might be different).
9. Copy the jar into your Minecraft mods folder, and play Minecraft!

### To Update EE3 (For Windows/Mac?)
1. Check to see if pahimar updated EE3 since you last compiled.  If he/she did, follow these instructions.

2. Open Github.

3. Double-click on pahimar/Equivalent-Exchange-3.

4. At the top, there is a button named `Sync` (or `Refreshing...` if its still checking).

5. Click `Sync`, and wait for it to finish.

6. Re-compile (or move it to `mcdev\source` then re-compile, depending on what you did.)