package com.pahimar.ee3.handler;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.helper.ItemStackNBTHelper;
import com.pahimar.ee3.item.ItemAlchemicalBag;
import com.pahimar.ee3.item.crafting.RecipesAlchemicalBagDyes;
import com.pahimar.ee3.item.crafting.RecipesTransmutationStones;
import com.pahimar.ee3.item.crafting.RecipesVanilla;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.recipe.RecipesAludel;

import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CraftingHandler
 *
 * @author pahimar
 */
public class CraftingHandler extends ItemCraftedEvent
{
    public CraftingHandler(EntityPlayer player, ItemStack crafting,
			IInventory craftMatrix) {
		super(player, crafting, craftMatrix);
		if (player.worldObj.isRemote)
        {
            doPortableCrafting(player, craftMatrix);
        }

        if (!player.worldObj.isRemote)
        {
            // Set the UUID on an Alchemical Bag when picked up from crafting
            if (crafting.getItem() instanceof ItemAlchemicalBag)
            {
                UUID itemUUID = UUID.randomUUID();
                ItemStackNBTHelper.setLong(crafting, Strings.NBT_ITEM_UUID_MOST_SIG, itemUUID.getMostSignificantBits());
                ItemStackNBTHelper.setLong(crafting, Strings.NBT_ITEM_UUID_LEAST_SIG, itemUUID.getLeastSignificantBits());
            }
        }
	}

	@SuppressWarnings("unchecked")
	public static void init()
    {
        // Register the Crafting Handler
        // Add in the ability to dye Alchemical Bags
        CraftingManager.getInstance().getRecipeList().add(new RecipesAlchemicalBagDyes());

        // Register our recipes
        RecipesVanilla.init();
        RecipesTransmutationStones.init();
        RecipesAludel.getInstance();
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

        ItemStack itemStack;
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
