package com.pahimar.ee3.core.addons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.core.helper.RecipeHelper;

import java.util.ArrayList;


public class CopperTransmute
{
    
    public static void copper(ItemStack transmutationStone)
    {   
        ArrayList<ItemStack > copperOres = OreDictionary.getOres("ingotCopper");
        ItemStack copperItem = copperOres.get(0);
        try
        {
            RecipeHelper.addRecipe(copperItem, transmutationStone , Item.ingotIron, Item.ingotIron);
        }
        catch (Exception e)
        {
            //swallowed exception so ignored if copper not present
        }
    }
}