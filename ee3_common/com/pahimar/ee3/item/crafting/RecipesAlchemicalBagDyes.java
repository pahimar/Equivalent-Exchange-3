package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.pahimar.ee3.item.ItemAlchemicalBag;

public class RecipesAlchemicalBagDyes implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {

        ItemStack itemStack = null;
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
        
        for (int i = 0; i < inventoryCrafting.getSizeInventory(); ++i) {
            
            ItemStack currentItemStack = inventoryCrafting.getStackInSlot(i);
            
            if (currentItemStack != null) {
                
                if (currentItemStack.getItem() instanceof ItemAlchemicalBag) {

                    if (itemStack != null) {
                        return false;
                    }

                    itemStack = currentItemStack;
                }
                else {
                    
                    if (currentItemStack.itemID != Item.dyePowder.itemID) {
                        
                        return false;
                    }

                    arrayList.add(currentItemStack);
                }
            }
        }
        
        return ((itemStack != null) && (!arrayList.isEmpty()));
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getRecipeSize() {

        return 10;
    }

    @Override
    public ItemStack getRecipeOutput() {

        return null;
    }

}
