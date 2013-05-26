package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.item.CustomStackWrapper;

public class EMCBlackList {

    private static final EMCBlackList emcBlackList = new EMCBlackList();

    private static final ArrayList<CustomStackWrapper> stackBlackList = new ArrayList<CustomStackWrapper>();

    private EMCBlackList() {

    }

    public static EMCBlackList getInstance() {

        return emcBlackList;
    }
    
    public List<CustomStackWrapper> getBlackListStacks() {
        
        return stackBlackList;
    }

    public void add(ItemStack itemStack) {

        if (itemStack != null) {
            itemStack.stackSize = 1;
        }

        CustomStackWrapper customStackWrapper = new CustomStackWrapper(itemStack);

        if (!stackBlackList.contains(customStackWrapper)) {
            stackBlackList.add(customStackWrapper);
        }
    }

    public void add(Item item) {

        this.add(new ItemStack(item.itemID, 1, OreDictionary.WILDCARD_VALUE));
    }

    public void add(Block block) {

        this.add(new ItemStack(block.blockID, 1, OreDictionary.WILDCARD_VALUE));
    }
    
    public void add(int id, int meta) {
        
        this.add(new ItemStack(id, 1, meta));
    }
    
    public void add(int id) {
        
        this.add(id, OreDictionary.WILDCARD_VALUE);
    }
    
    
    public boolean contains(ItemStack itemStack) {

        if (itemStack != null) {
            itemStack.stackSize = 1;
        }

        return stackBlackList.contains(new CustomStackWrapper(itemStack));
    }

    public boolean contains(Item item) {

        return this.contains(new ItemStack(item.itemID, 1, OreDictionary.WILDCARD_VALUE));
    }

    public boolean contains(Block block) {

        return this.contains(new ItemStack(block.blockID, 1, OreDictionary.WILDCARD_VALUE));
    }
    
    public boolean contains(int id, int meta) {
        
        return this.contains(new ItemStack(id, 1, meta));
    }
    
    public boolean contains(int id) {
        
        return this.contains(id, OreDictionary.WILDCARD_VALUE);
    }

    public void remove(ItemStack itemStack) {

        if (itemStack != null) {
            itemStack.stackSize = 1;
        }

        CustomStackWrapper customStackWrapper = new CustomStackWrapper(itemStack);

        while (stackBlackList.contains(customStackWrapper)) {
            stackBlackList.remove(customStackWrapper);
        }
    }

    public void remove(Item item) {

        this.remove(new ItemStack(item.itemID, 1, OreDictionary.WILDCARD_VALUE));
    }

    public void remove(Block block) {

        this.remove(new ItemStack(block.blockID, 1, OreDictionary.WILDCARD_VALUE));
    }
    
    public void remove(int id, int meta) {
        
        this.remove(new ItemStack(id, 1, meta));
    }
    
    public void remove(int id) {
        
        this.remove(id, OreDictionary.WILDCARD_VALUE);
    }
    
    static {
        getInstance().add(Block.bed);
        getInstance().add(Block.pistonExtension);
        getInstance().add(Block.pistonMoving);
        getInstance().add(Block.mobSpawner);
        getInstance().add(Block.redstoneWire);
        getInstance().add(Block.crops);
        getInstance().add(Block.furnaceBurning);
        getInstance().add(Block.signPost);
        getInstance().add(Block.doorWood);
        getInstance().add(Block.signWall);
        getInstance().add(Block.doorIron);
        getInstance().add(Block.torchRedstoneIdle);
        getInstance().add(Block.reed);
        getInstance().add(Block.portal);
        getInstance().add(Block.cake);
        getInstance().add(Block.redstoneRepeaterIdle);
        getInstance().add(Block.redstoneRepeaterActive);
        getInstance().add(Block.lockedChest);
        getInstance().add(Block.pumpkinStem);
        getInstance().add(Block.melonStem);
        getInstance().add(Block.netherStalk);
        getInstance().add(Block.brewingStand);
        getInstance().add(Block.cauldron);
        getInstance().add(Block.endPortal);
        getInstance().add(Block.redstoneLampActive);
        getInstance().add(Block.commandBlock);
        getInstance().add(Block.carrot);
        getInstance().add(Block.potato);
        getInstance().add(Block.skull);
        getInstance().add(Block.redstoneComparatorIdle);
        getInstance().add(Block.redstoneComparatorActive);
    }
}
