package com.pahimar.ee3.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.ArrayList;

public class RecipeAlchemicalBagDyes implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inventoryCrafting, World world) {

        ItemStack itemStack = null;
        ArrayList<ItemStack> arrayList = new ArrayList<>();

        for (int i = 0; i < inventoryCrafting.getSizeInventory(); ++i) {
            ItemStack currentStack = inventoryCrafting.getStackInSlot(i);

            if (currentStack != null) {
                // TODO Uncomment when Alchemical Bags are reimplemented
//                if (currentStack.getItem() instanceof ItemAlchemicalBag) {
//                    if (itemStack != null) {
//                        return false;
//                    }
//
//                    itemStack = currentStack;
//                }
//                else {
//                    if (currentStack.getItem() != Items.DYE) {
//                        return false;
//                    }
//
//                    arrayList.add(currentStack);
//                }
            }
        }

        return itemStack != null && !arrayList.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
        return null;
//        ItemStack itemStack = null;
//        ItemAlchemicalBag itemAlchemicalBag = null;
//        int[] colorChannels = new int[3];
//        int i = 0;
//        int j = 0;
//        int k, j1, k1, l1;
//        int currentColor, newColor;
//        float red, green, blue;
//
//        for (k = 0; k < inventoryCrafting.getSizeInventory(); ++k) {
//            ItemStack currentStack = inventoryCrafting.getStackInSlot(k);
//
//            if (currentStack != null) {
//                if (currentStack.getItem() instanceof ItemAlchemicalBag) {
//                    itemAlchemicalBag = (ItemAlchemicalBag) currentStack.getItem();
//
//                    if (itemStack != null) {
//                        return null;
//                    }
//
//                    itemStack = currentStack.copy();
//                    itemStack.stackSize = 1;
//
//                    if (NBTUtils.hasColor(currentStack)) {
//                        currentColor = NBTUtils.getColor(itemStack);
//                        red = (currentColor >> 16 & 255) / 255.0F;
//                        green = (currentColor >> 8 & 255) / 255.0F;
//                        blue = (currentColor & 255) / 255.0F;
//                        i = (int) (i + Math.max(red, Math.max(green, blue)) * 255.0F);
//                        colorChannels[0] = (int) (colorChannels[0] + red * 255.0F);
//                        colorChannels[1] = (int) (colorChannels[1] + green * 255.0F);
//                        colorChannels[2] = (int) (colorChannels[2] + blue * 255.0F);
//                        ++j;
//                    }
//                }
//                else {
//                    if (currentStack.getItem() != Items.DYE) {
//                        return null;
//                    }
//
//                    float[] dyeColorChannels = ColorUtils.getRGB(EnumDyeColor.byDyeDamage(currentStack.getMetadata()));
//                    j1 = (int) (dyeColorChannels[0] * 255.0F);
//                    k1 = (int) (dyeColorChannels[1] * 255.0F);
//                    newColor = (int) (dyeColorChannels[2] * 255.0F);
//                    i += Math.max(j1, Math.max(k1, newColor));
//                    colorChannels[0] += j1;
//                    colorChannels[1] += k1;
//                    colorChannels[2] += newColor;
//                    ++j;
//                }
//            }
//        }
//
//        if (itemAlchemicalBag == null) {
//            return null;
//        }
//        else {
//            k = colorChannels[0] / j;
//            l1 = colorChannels[1] / j;
//            currentColor = colorChannels[2] / j;
//            red = (float) i / (float) j;
//            green = Math.max(k, Math.max(l1, currentColor));
//            k = (int) (k * red / green);
//            l1 = (int) (l1 * red / green);
//            currentColor = (int) (currentColor * red / green);
//            newColor = (k << 8) + l1;
//            newColor = (newColor << 8) + currentColor;
//            NBTUtils.setColor(itemStack, newColor);
//            return itemStack;
//        }
    }

    @Override
    public int getRecipeSize() {
        return 10;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {

        ItemStack[] itemStacks = new ItemStack[inventoryCrafting.getSizeInventory()];

        for (int i = 0; i < itemStacks.length; ++i) {
            ItemStack itemstack = inventoryCrafting.getStackInSlot(i);
            itemStacks[i] = ForgeHooks.getContainerItem(itemstack);
        }

        return itemStacks;
    }
}
