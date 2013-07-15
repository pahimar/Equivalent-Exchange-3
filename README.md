## Welcome to Equivalent Exchange 3!
**LATEST OFFICIAL VERSION**:  [EE3 pre1h for 1.5.1/1.5.2](http://adf.ly/PdBNy)

[Minecraft Forums page](http://www.minecraftforum.net/topic/1540010-equivalent-exchange-3)

[Compiling EE3](https://github.com/pahimar/Equivalent-Exchange-3#compiling-equivalent-exchange-3) - For those that want the latest unreleased features.

[Contributing](https://github.com/pahimar/Equivalent-Exchange-3#contributing) - For those that want to help out.

### Compiling Equivalent Exchange 3
IMPORTANT: Please report any issues you have, there might be some problems with the documentation!
***
#### Prerequisites
1. **WARNING:  Make sure you know EXACTLY what you're doing!  It's not any of our faults if your OS crashes, becomes corrupted, etc.**
2. Download and install the Java JDK [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Scroll down, accept the `Oracle Binary Code License Agreement for Java SE`, and download the one pertaining to your OS (necessary for MCP).
	* Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
	* Click on `Environment Variables`.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `JAVA_HOME`.
  * For `Variable Value`, input something similar to `;C:\Program Files (x86)\Java\jdk1.7.0_25` exactly as shown to the end (or wherever your Java JDK installation is), and click `Ok`.
  * Scroll down to a variable named `Path`, and double-click on it.
  * Append `;C:\Program Files (x86)\Java\jdk1.7.0_25\bin` (or your Java JDK installation directory\bin), and click `Ok`.
3. Download Apache Ant [here](http://ant.apache.org).
	* Unzip the files anywhere you want, eg `C:\Program Files (x86)\Ant`.
  * Again, go to `Environment Variables` just like you did for the Java JDK.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `ANT_HOME`.
  * For `Variable Value`, input `C:\Ant\apache-ant-1.9.0` (or your Ant directory\apache-ant-1.9.0).
  * Scroll down to `Path`, and double-click on it.
  * Append `;C:\Ant\apache-ant-1.9.0\bin` exactly as shown to the end (or your Ant directory\apache-ant-1.9.0\bin).
4. Download and install Github [here](http://windows.github.com/) (Windows) or [here](http://mac.github.com/) (Mac OS X 10.7+).  For Linux, you can use a different Git application.  NOTE:  Github For Windows/Mac is OPTIONAL.  You can use your own Git application.
	* Create an account.
  * Scroll to the top of this page, login at the top-right, and then click `Clone to Windows/Mac` at the bottom of the right-hand toolbar.
  * You should see Github flash and `pahimar/Equivalent-Exchange-3` appear.  (The local repository on Windows defaults to `C:\Users\(username)\Documents\GitHub\Equivalent-Exchange-3`, you can change it if you want but then you have to find it again on Github).
5. Create an empty directory for EE3 development.  This directory is refernced as `mcdev` from now on.  It can be where you cloned EE3, but it'll be a little messy.


#### Setup MCP
1. Download the latest version of MCP from [here] (http://mcp.ocean-labs.de/download.php?list.2), e.g. mcp751.zip.
2. Inside `mcdev`, create a directory named `mcp` and unzip the MCP .zip file into it.
	* To verify, check if a file named `CHANGELOG` exists inside `mcp`.
3. Download the latest Forge **source** for Minecraft 1.5.1/1.5.2 and unzip it into `mcp`.  You need at least Forge 7.7.1.500 (Forge 7.8.0.684 if using 1.5.2), the best way is to get it from [here] (http://files.minecraftforge.net/).
	* To verify, check if a application named `install.sh` exists. 
4. Execute `install.sh` (Linux and Mac) or `install.cmd` (Windows), both found in `mcdev\mcp\forge`. On Linux you might have to `chmod +x install.sh` before you can execute it.
	* This will take some time, be patient.

#### Setup EE3
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
	* Open it up with any text editor, and type into it the following (fully customizable except for `dir.development`):
 		* `dir.development=../../`
		* `dir.release=Releases`
		* `release.minecraft.version=1.5.2`
		* `release.mod.version=pre2`
5. Open up your OS's command line (Command Prompt in Windows, Terminal in Linux and Mac).
6. Navigate to `mcdev\source\Equivalent-Exchange-3` by executing `cd mcdev's location\source\Equivalent-Exchange-3`.
7. Execute `ant build`. This will generally take around 5-15 minutes, depending on your computer.  If you've done everything right, `BUILD SUCCESSFUL` is displayed after it finishes.
	* If you see `BUILD FAILED`, check the error output (it should be right around `BUILD FAILED`), fix everything, and try again.
8. Go to `mcdev\source\Equivalent-Exchange-3\Releases\1.5.2\pre2`.
	*  You should see a `.jar` file named `ee3-universal-pre2.jar`.
9. Copy the jar into your Minecraft mods folder, and play Minecraft!

#### Updating Your Repo (For Windows/Mac)
1. Check to see if pahimar updated EE3 since you last compiled.  If he did, follow these instructions.
2. Open Github.
3. Double-click on pahimar/Equivalent-Exchange-3.
4. At the top, there is a button named `Sync`/`Sync Branch` (or `Refreshing...` if it's still checking).
5. Click `Sync`, and wait for it to finish.
6. Re-compile (or move it to `mcdev\source` then re-compile, depending on what you did.)

###Contributing
***
#### Submitting a PR
So you found a bug in pahimar's code?  Think you can make it more efficient?  Want to help in general?  Great!

1. **IMPORTANT:  PAHIMAR DOES *NOT* WANT ANY** `build.xml` **CHANGES, UNLESS it fixes up something broken** (See [Pull Request #90](https://github.com/pahimar/Equivalent-Exchange-3/pull/90)).
2. If you haven't already, create a Github account.
3. Click the `Fork` icon at the top-right of this page (below your username).
4. Make the changes that you want to.
5. Click `Pull Request` at the right-hand side of the gray bar directly below your fork's name.
6. Click `Click to create a pull request for this comparison`, enter your PR's title, and create a detailed description telling pahimar what you changed.
7. Click `Send pull request`, and you're done!

#### Creating an Issue
EE3 crashes every time?  Have a suggestion?  Found a bug?  Create an issue now!

1. Please, please don't make any frivolous issues!  If it's a crash, try asking the people in IRC or MCF before creating an issue.  If it's a bug/suggestion, make sure it hasn't been reported/suggested already.  Thanks! :smile:
2. Go to [the issues page](http://github.com/pahimar/Equivalent-Exchange-3/issues).
3. Click `New Issue` right below `Star` and `Fork`.
4. Enter your Issue's title (something that summarizes your issue), and then create a detailed description ("Hey pahimar, could you add/change xxx?" or "Hey, found an exploit:  stuff").
5. Click `Submit new issue`, and you're done!
