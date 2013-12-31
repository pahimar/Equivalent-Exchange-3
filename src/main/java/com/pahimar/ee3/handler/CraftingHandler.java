package com.pahimar.ee3.handler;

import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CraftingHandler
 *
 * @author pahimar
 */
public class CraftingHandler implements ICraftingHandler
{
    public static void init()
    {
        // Register the Crafting Handler
        GameRegistry.registerCraftingHandler(new CraftingHandler());

        // Add in the ability to dye Alchemical Bags
        CraftingManager.getInstance().getRecipeList().add(new RecipesAlchemicalBagDyes());

        // Register our recipes
        initBlockRecipes();
        initItemRecipes();
    }

    @Override
    public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
    {
        if (player.worldObj.isRemote)
        {
            doPortableCrafting(player, craftMatrix);
        }
    }

    @Override
    public void onSmelting(EntityPlayer player, ItemStack item)
    {

    }

    /**
     * Check to see if the crafting is occurring from the portable crafting interface. If so, do durability damage to
     * the appropriate transmutation stone that was used for portable crafting.
     *
     * @param player
     *         The player that is completing the crafting
     * @param craftMatrix
     *         The contents of the crafting matrix
     */
    private void doPortableCrafting(EntityPlayer player, IInventory craftMatrix)
    {
        ItemStack openStone = null;

        for (ItemStack itemStack : player.inventory.mainInventory)
        {
            if (itemStack != null)
            {
                if (ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN))
                {
                    openStone = itemStack;
                }
            }
        }

        ItemStack itemStack = null;
        if (openStone != null)
        {
            for (int i = 0; i < craftMatrix.getSizeInventory(); i++)
            {
                itemStack = craftMatrix.getStackInSlot(i);
                if (itemStack != null)
                {
                    if (ItemStackNBTHelper.hasTag(itemStack, Strings.NBT_ITEM_TRANSMUTATION_GUI_OPEN))
                    {
                        openStone = itemStack;
                    }
                }
            }
        }

        if (openStone != null)
        {
            openStone.damageItem(ConfigurationSettings.TRANSMUTE_COST_ITEM, player);
        }
    }

    private static void initBlockRecipes()
    {
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuel, 1, 0), new Object[]{"iii", "iii", "iii", 'i', new ItemStack(ModItems.alchemicalFuel, 1, 0)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuel, 1, 1), new Object[]{"iii", "iii", "iii", 'i', new ItemStack(ModItems.alchemicalFuel, 1, 1)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.alchemicalFuel, 1, 2), new Object[]{"iii", "iii", "iii", 'i', new ItemStack(ModItems.alchemicalFuel, 1, 2)});

        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalk), new Object[]{"bcb", "cbc", "bcb", 'b', new ItemStack(Item.dyePowder.itemID, 1, 15), 'c', new ItemStack(Item.clay)});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.chalk), new Object[]{"cc", "cc", 'c', new ItemStack(ModItems.chalk)});

        GameRegistry.addRecipe(new ItemStack(ModBlocks.glassBell), new Object[]{"iii", "i i", "i i", 'i', Block.glass});
        GameRegistry.addRecipe(new ItemStack(ModBlocks.aludelBase), new Object[]{"iii", "sis", "iii", 'i', Item.ingotIron, 's', Block.stone});
    }

    private static void initItemRecipes()
    {
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 0), new ItemStack(ModBlocks.alchemicalFuel, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 1), new ItemStack(ModBlocks.alchemicalFuel, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(ModItems.alchemicalFuel, 9, 2), new ItemStack(ModBlocks.alchemicalFuel, 1, 2));

        GameRegistry.addRecipe(new ItemStack(ModItems.inertStone), new Object[]{"sis", "igi", "sis", 's', Block.stone, 'i', Item.ingotIron, 'g', Item.ingotGold});
        GameRegistry.addRecipe(new ItemStack(ModItems.miniumStone), new Object[]{"sss", "sis", "sss", 's', ModItems.miniumShard, 'i', ModItems.inertStone});
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ModItems.diviningRod), new Object[]{" s ", " s ", "s s", 's', Item.stick}));
    }
}
