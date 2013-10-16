@ECHO off
title Building EE3...

echo Installing Forge (part 1/2)...
ant forge-install
echo Done installing Forge!

echo Building EE3 (part 2/2)...
ant build
pause