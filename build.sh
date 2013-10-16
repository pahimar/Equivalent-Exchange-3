#!/bin/sh

echo "Installing Forge... (Part 1/2)";
ant forge-install;
echo "Done installing Forge!";

echo "Building EE3... (Part 2/2)";
ant build;