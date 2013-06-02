package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

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
    
    public List<CustomWrappedStack> getBlackListStacks() {
        
        return stackBlackList;
    }

    public void add(ItemStack itemStack) {

        CustomWrappedStack customStackWrapper = new CustomWrappedStack(itemStack);

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

        return stackBlackList.contains(new CustomWrappedStack(itemStack));
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

        CustomWrappedStack customStackWrapper = new CustomWrappedStack(itemStack);

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
