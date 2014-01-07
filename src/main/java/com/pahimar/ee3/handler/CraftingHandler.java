package com.pahimar.ee3.handler;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import com.pahimar.ee3.item.crafting.RecipesTransmutationStones;
import com.pahimar.ee3.item.crafting.RecipesVanilla;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.recipe.RecipesAludel;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

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
        RecipesVanilla.init();
        RecipesTransmutationStones.init();
        RecipesAludel.getInstance();

        RecipesAludel.getInstance().debugDumpMap();
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
}
