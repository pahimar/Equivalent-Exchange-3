package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

import com.pahimar.ee3.item.ItemAlchemicalBag;

/**
 * Equivalent-Exchange-3
 * 
 * RecipesAlchemicalBagDyes
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class RecipesAlchemicalBagDyes implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {

        ItemStack itemStack = null;
        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();

        for (int i = 0; i < inventoryCrafting.getSizeInventory(); ++i) {

            ItemStack currentStack = inventoryCrafting.getStackInSlot(i);

            if (currentStack != null) {

                if (currentStack.getItem() instanceof ItemAlchemicalBag) {

                    if (itemStack != null)
                        return false;

                    itemStack = currentStack;
                }
                else {

                    if (currentStack.itemID != Item.dyePowder.itemID)
                        return false;

                    arrayList.add(currentStack);
                }
            }
        }

        return itemStack != null && !arrayList.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {

        ItemStack itemStack = null;
        ItemAlchemicalBag itemAlchemicalBag = null;
        int[] colorChannels = new int[3];
        int i = 0;
        int j = 0;
        int k, j1, k1, l1;
        int currentColor, newColor;
        float red, green, blue;

        for (k = 0; k < inventoryCrafting.getSizeInventory(); ++k) {

            ItemStack currentStack = inventoryCrafting.getStackInSlot(k);

            if (currentStack != null) {

                if (currentStack.getItem() instanceof ItemAlchemicalBag) {

                    itemAlchemicalBag = (ItemAlchemicalBag) currentStack.getItem();

                    if (itemStack != null)
                        return null;

                    itemStack = currentStack.copy();
                    itemStack.stackSize = 1;

                    if (itemAlchemicalBag.hasColor(currentStack)) {

                        currentColor = itemAlchemicalBag.getColor(itemStack);
                        red = (currentColor >> 16 & 255) / 255.0F;
                        green = (currentColor >> 8 & 255) / 255.0F;
                        blue = (currentColor & 255) / 255.0F;
                        i = (int) (i + Math.max(red, Math.max(green, blue)) * 255.0F);
                        colorChannels[0] = (int) (colorChannels[0] + red * 255.0F);
                        colorChannels[1] = (int) (colorChannels[1] + green * 255.0F);
                        colorChannels[2] = (int) (colorChannels[2] + blue * 255.0F);
                        ++j;
                    }
                }
                else {

                    if (currentStack.itemID != Item.dyePowder.itemID)
                        return null;

                    float[] dyeColorChannels = EntitySheep.fleeceColorTable[BlockColored.getBlockFromDye(currentStack.getItemDamage())];
                    j1 = (int) (dyeColorChannels[0] * 255.0F);
                    k1 = (int) (dyeColorChannels[1] * 255.0F);
                    newColor = (int) (dyeColorChannels[2] * 255.0F);
                    i += Math.max(j1, Math.max(k1, newColor));
                    colorChannels[0] += j1;
                    colorChannels[1] += k1;
                    colorChannels[2] += newColor;
                    ++j;
                }
            }
        }

        if (itemAlchemicalBag == null)
            return null;
        else {

            k = colorChannels[0] / j;
            l1 = colorChannels[1] / j;
            currentColor = colorChannels[2] / j;
            red = (float) i / (float) j;
            green = Math.max(k, Math.max(l1, currentColor));
            k = (int) (k * red / green);
            l1 = (int) (l1 * red / green);
            currentColor = (int) (currentColor * red / green);
            newColor = (k << 8) + l1;
            newColor = (newColor << 8) + currentColor;
            itemAlchemicalBag.setColor(itemStack, newColor);
            return itemStack;
        }
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
