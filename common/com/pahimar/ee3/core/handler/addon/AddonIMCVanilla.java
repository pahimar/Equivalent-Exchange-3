package com.pahimar.ee3.core.handler.addon;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.lib.InterModComms;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.nbt.NBTHelper;

import cpw.mods.fml.common.event.FMLInterModComms;

/**
 * Equivalent-Exchange-3
 * 
 * AddonVanilla
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class AddonIMCVanilla {

    public static void init() {

        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, NBTHelper.encodeRecipeAsNBT(Item.bucketWater, Arrays.asList(Item.bucketEmpty, Block.waterStill)));
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, NBTHelper.encodeRecipeAsNBT(Item.bucketLava, Arrays.asList(Item.bucketEmpty, Block.lavaStill)));
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, NBTHelper.encodeRecipeAsNBT(new ItemStack(Block.anvil, 1, 1), Arrays.asList(new ItemStack(Item.ingotIron, 20))));
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, NBTHelper.encodeRecipeAsNBT(new ItemStack(Block.anvil, 1, 2), Arrays.asList(new ItemStack(Item.ingotIron, 10))));
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, NBTHelper.encodeRecipeAsNBT(Item.horseArmorIron, Arrays.asList(new ItemStack(Item.ingotIron, 6))));
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, NBTHelper.encodeRecipeAsNBT(Item.horseArmorGold, Arrays.asList(new ItemStack(Item.ingotGold, 6))));
        FMLInterModComms.sendMessage(Reference.MOD_ID, InterModComms.ADD_RECIPE, NBTHelper.encodeRecipeAsNBT(Item.horseArmorDiamond, Arrays.asList(new ItemStack(Item.diamond, 6))));
    }
}
