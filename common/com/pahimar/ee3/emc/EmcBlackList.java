package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;

import com.pahimar.ee3.item.CustomWrappedStack;

public class EmcBlackList {

    private static EmcBlackList emcBlackList = null;

    private ArrayList<CustomWrappedStack> stackBlackList = new ArrayList<CustomWrappedStack>();

    private EmcBlackList() {

    }

    public static EmcBlackList getInstance() {

        if (emcBlackList == null) {
            emcBlackList = new EmcBlackList();
            emcBlackList.init();
        }

        return emcBlackList;
    }

    public List<CustomWrappedStack> getBlackList() {

        return stackBlackList;
    }

    public boolean add(Object object) {

        boolean wasAdded = false;

        if (CustomWrappedStack.canBeWrapped(object)) {

            CustomWrappedStack wrappedStack = new CustomWrappedStack(object);
            wrappedStack.setStackSize(1);

            if (!stackBlackList.contains(wrappedStack)) {
                stackBlackList.add(wrappedStack);
                wasAdded = true;
            }
        }

        return wasAdded;
    }

    public boolean contains(Object object) {

        if (CustomWrappedStack.canBeWrapped(object)) {

            CustomWrappedStack wrappedStack = new CustomWrappedStack(object);
            wrappedStack.setStackSize(1);

            return stackBlackList.contains(wrappedStack);
        }

        return false;
    }

    public boolean remove(Object object) {

        boolean wasRemoved = false;

        if (CustomWrappedStack.canBeWrapped(object)) {

            CustomWrappedStack wrappedStack = new CustomWrappedStack(object);
            wrappedStack.setStackSize(1);

            if (stackBlackList.contains(wrappedStack)) {
                stackBlackList.remove(wrappedStack);
                wasRemoved = true;
            }
        }

        return wasRemoved;
    }

    private void init() {

        add(Block.bed);
        add(Block.pistonExtension);
        add(Block.pistonMoving);
        add(Block.mobSpawner);
        add(Block.redstoneWire);
        add(Block.crops);
        add(Block.furnaceBurning);
        add(Block.signPost);
        add(Block.doorWood);
        add(Block.signWall);
        add(Block.doorIron);
        add(Block.torchRedstoneIdle);
        add(Block.reed);
        add(Block.portal);
        add(Block.cake);
        add(Block.redstoneRepeaterIdle);
        add(Block.redstoneRepeaterActive);
        add(Block.lockedChest);
        add(Block.pumpkinStem);
        add(Block.melonStem);
        add(Block.netherStalk);
        add(Block.brewingStand);
        add(Block.cauldron);
        add(Block.endPortal);
        add(Block.redstoneLampActive);
        add(Block.commandBlock);
        add(Block.carrot);
        add(Block.potato);
        add(Block.skull);
        add(Block.redstoneComparatorIdle);
        add(Block.redstoneComparatorActive);
    }
}
