## Welcome to Equivalent Exchange 3!
**LATEST OFFICIAL VERSION**:  [EE3 pre1h for 1.5.1/1.5.2](http://adf.ly/PdBNy)

[Minecraft Forums page](http://www.minecraftforum.net/topic/1540010-equivalent-exchange-3)

[Compiling EE3](https://github.com/pahimar/Equivalent-Exchange-3#compiling-equivalent-exchange-3) - For those that want the latest unreleased features.

[Contributing](https://github.com/pahimar/Equivalent-Exchange-3#contributing) - For those that want to help out.

### Compiling Equivalent Exchange 3
IMPORTANT: Please report any issues you have, there might be some problems with the documentation!
***
#### Windows Prerequisites
1. **WARNING:  Make sure you know EXACTLY what you're doing!  It's not any of our faults if your OS crashes, becomes corrupted, etc.**
2. Download and install the Java JDK [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Scroll down, accept the `Oracle Binary Code License Agreement for Java SE`, and download it.  (If you have a 64-bit OS, please download the 64-bit version.)
	* Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
	* Click on `Environment Variables`.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `JAVA_HOME`.
  * For `Variable Value`, input something similar to `;C:\Program Files\Java\jdk1.7.0_40` exactly as shown to the end (or wherever your Java JDK installation is), and click `Ok`.
  * Scroll down to a variable named `Path`, and double-click on it.
  * Append `;%JAVA_HOME%\bin`, and click `Ok`.
3. Download Apache Ant [here](http://ant.apache.org).
	* Unzip the files anywhere you want, eg `C:\Program Files\Ant`.
  * Again, go to `Environment Variables` just like you did for the Java JDK.
  * Under `System Variables`, click `New`.
  * For `Variable Name`, input `ANT_HOME`.
  * For `Variable Value`, input `C:\Ant\apache-ant-1.9.2` (or your Ant directory\apache-ant-1.9.2).
  * Scroll down to `Path`, and double-click on it.
  * Append `;%ANT_HOME%\bin` exactly as shown to the end.
4. Download and install Github [here](http://windows.github.com/) NOTE: This Github application is optional, you can use whatever you want, e.g. TortoiseGit.
	* Create an account.
  * Scroll to the top of this page, login at the top-right, and then click `Clone to Windows` at the bottom of the right-hand toolbar.
  * You should see Github flash and `pahimar/Equivalent-Exchange-3` appear.  (The local repository on Windows defaults to `C:\Users\(username)\Documents\GitHub\Equivalent-Exchange-3`, you can change it if you want but then you have to find it again on Github).
5. Create an empty directory for EE3 development.  This directory is referenced as `mcdev` from now on.  It can be where you cloned EE3, but it'll be a little messy.

#### Linux Prerequisites
1. Make sure you have the latest Java JDK installed.  To install manually, go [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Otherwise, install from the package manager or the terminal (listed).
	* In Gentoo, `emerge dev-java/oracle-jdk-bin`
	* In Archlinux, `pacman -S jdk7-openjdk`
	* In Ubuntu/Debian, `apt-get install openjdk-7-jdk`
	* In Fedora, `yum install java-1.7.0-openjdk`
		* If your distribution is not listed, follow the instructions specific to your package manager.
2. Install Apache Ant.  To install manually, go [here](http://ant.apache.org).
	* In Gentoo, `emerge dev-java/ant`
	* In Archlinux, `pacman -S apache-ant`
	* In Ubuntu/Debian, `apt-get install ant`
	* In Fedora, `yum install ant`
		* If your distribution is not listed, follow the instructions specific to your package manager.
3. Install Git.  To install manually, go [here](http://git-scm.com/).
	* In Gentoo, `emerge dev-vcs/git`
	* In Archlinux, `pacman -S git`
	* In Ubuntu/Debian, `apt-get install git`
	* In Fedora, `yum install git`
		* If your distribution is not listed, follow the instructions specific to your package manager.
4. Open your shell and move to a convenient directory, then run `git clone https://github.com/pahimar/Equivalent-Exchange-3.git`.  This will download the repository.
5. Create an empty directory for EE3 development.  This directory is referenced as `mcdev` from now on.  It can be where you cloned EE3, but it'll be a little messy.

#### Mac Prerequisites
1. Download and install the Java JDK [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Scroll down, accept the `Oracle Binary Code License Agreement for Java SE`, and download it. (Mac OS X comes with the JRE, but it is often Java 6, which does not always work.)
2. Apache Ant should already be installed on your computer.  To check, go into Terminal, and type `ant --version`.  It should return a version string.  If you get a "command not found" error, it's not installed.
3. Download and install Github for Mac OSX (10.7+) [here](http://mac.github.com/) NOTE: This Github application is optional, you can use whatever you want.
	* Create an account.
  * Scroll to the top of this page, login at the top-right, and then click `Clone to Mac` at the bottom of the right-hand toolbar.
  * You should see Github flash and `pahimar/Equivalent-Exchange-3` appear.  (The local repository on Mac defaults to `/Users/[username]/github/Equivalent-Exchange-3/`.  To change it, change the "Local Path")
5. Create an empty directory for EE3 development.  This directory is referenced as `mcdev` from now on.  It can be where you cloned EE3, but it'll be a little messy.


#### Setup MCP
NOTE: You may skip to "Setup EE3" if you want to let EE3 download and set up MCP for you.  Simply run `ant forge-install`, then `ant build`.

1. Download the latest version of Forge source from [here](http://files.minecraftforge.net)
2. Inside `mcdev`, unzip the zip file. 
	* You should get a folder named "forge" inside "mcdev" containing the forge patches and licenses, etc. 
3. Execute `install.sh` (Linux and Mac) or `install.cmd` (Windows), both found in `mcdev\forge`. On Linux you might have to `chmod +x install.sh` before you can execute it. 
	* This will take some time, be patient. 

#### Setup EE3
1. Inside `mcdev`, create a directory named `source`.
2. Move/clone `Equivalent-Exchange-3` into `source`.
3. Right now, you should have a directory that looks something like:

***

	mcdev
	\-forge
		\-mcp
			\-jars
			\-CHANGELOG, etc.
		\-install.cmd
		\-install.sh
		\-MinecraftForge-Changelog.txt, etc.
	\-source
		\-Equivalent-Exchange-3
			\-EE3's files (should have build.xml).
***

4. Inside `Equivalent-Exchange-3`, create a new file called `environment.properties`.
	* Open it up with any text editor, and type the following into it (change `base_location` to wherever you have "mcdev". NOTE: Paths must use forward slashes.  All of the variables are changeable for your setup.):
 		* `base_location=C:/mcdev/`
 		* `source_location=${base_location}/source/Equivalent-Exchange-3`
 		* `mcp_location=${base_location}/forge/mcp`
 		* `release_location=${base_location}/source/Equivalent-Exchange-3/Releases`
 	* You may also edit the build.properties to change the build string (`mod_version` and `build_number`), and the Minecraft and Forge version you are building for (`forge_version` is only used when running `ant forge-install`)
 		* `minecraft_version=1.6.4`
 		* `forge_version=9.11.1.934`
 		* `mod_version=pre2`
 		* `build_number=1`
5. Open up your OS's command line (Command Prompt in Windows, Terminal in Linux and Mac).
6. Navigate to `mcdev\source\Equivalent-Exchange-3` by executing `cd mcdev's location\source\Equivalent-Exchange-3`.
7. Execute `ant build`. (If you want EE3 to setup Forge, run `ant forge-install` first.) This will generally take around 5-15 minutes, depending on your computer.  If you've done everything right, `BUILD SUCCESSFUL` is displayed after it finishes.
	* If you see `BUILD FAILED`, check the error output (it should be right around `BUILD FAILED`), fix everything (if possible), and try again.
8. Go to `mcdev\source\Equivalent-Exchange-3\Releases\MC 1.6.4\pre2`.
	*  You should see a `.jar` file named `ee3-universal-pre2.jar`.
9. Copy the jar into your Minecraft mods folder, and play Minecraft (If you have Forge installed on your client).

#### Updating Your Repo (For Windows/Mac)
1. Check to see if pahimar updated EE3 since you last compiled.  If he did, follow these instructions.
2. Open Github.
3. Double-click on pahimar/Equivalent-Exchange-3.
4. At the top, there is a button named `Sync`/`Sync Branch` (or `Refreshing...` if it's still checking).
5. Click `Sync`, and wait for it to finish.
6. Re-compile (or move it to `mcdev\source` then re-compile, depending on what you did.)

#### Updating Your Repo (For Linux)
1. Check to see if pahimar updated EE3 since you last compiled.  If he did, follow these instructions.
2. Navigate to the repository location.
3. Run `git pull` in Terminal.
4. Re-compile (or move it to `mcdev\source` then re-compile, depending on what you did).

###Contributing
***
#### Submitting a PR
So you found a bug in pahimar's code?  Think you can make it more efficient?  Want to help in general?  Great!

1. **IMPORTANT:  PAHIMAR DOES *NOT* WANT ANY** `build.xml` **CHANGES, UNLESS it fixes up something broken** (See [Pull Request #90](https://github.com/pahimar/Equivalent-Exchange-3/pull/90)).
2. If you haven't already, create a Github account.
3. Click the `Fork` icon at the top-right of this page (below your username).
4. Make the changes that you want to (In Linux, you'll have to run `git commit -a`, and `git push` after cloning your forked repository to upload the changes).
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
