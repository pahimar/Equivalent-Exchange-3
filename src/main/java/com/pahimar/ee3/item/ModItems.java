package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.ItemIds;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ModItems
 *
 * @author pahimar
 */
public class ModItems
{
    // Mod item instances
    public static ItemEE miniumShard;
    public static ItemEE inertStone;
    public static ItemEE miniumStone;
    public static ItemEE philStone;
    public static ItemEE alchemicalDust;
    public static ItemEE alchemicalBag;
    public static ItemEE alchemicalChalk;
    public static ItemEE diviningRod;

    public static void init()
    {
        // Initialize each mod item individually
        miniumShard = new ItemMiniumShard(ItemIds.MINIUM_SHARD);
        inertStone = new ItemInertStone(ItemIds.INERT_STONE);
        miniumStone = new ItemMiniumStone(ItemIds.MINIUM_STONE);
        philStone = new ItemPhilosophersStone(ItemIds.PHILOSOPHERS_STONE);
        alchemicalDust = new ItemAlchemicalDust(ItemIds.ALCHEMICAL_DUST);
        alchemicalBag = new ItemAlchemicalBag(ItemIds.ALCHEMICAL_BAG);
        alchemicalChalk = new ItemAlchemicalChalk(ItemIds.ALCHEMICAL_CHALK);
        diviningRod = new ItemDiviningRod(ItemIds.DIVINING_ROD);

        // Set container items
        miniumStone.setContainerItem(miniumStone);
        philStone.setContainerItem(philStone);

        // Register items with the GameRegistry
        GameRegistry.registerItem(miniumShard, Strings.MINIUM_SHARD_NAME);
        GameRegistry.registerItem(inertStone, Strings.INERT_STONE_NAME);
        GameRegistry.registerItem(miniumStone, Strings.MINIUM_STONE_NAME);
        GameRegistry.registerItem(philStone, Strings.PHILOSOPHERS_STONE_NAME);
        GameRegistry.registerItem(alchemicalDust, Strings.ALCHEMICAL_DUST_NAME);
        GameRegistry.registerItem(alchemicalBag, Strings.ALCHEMICAL_BAG_NAME);
        GameRegistry.registerItem(alchemicalChalk, Strings.ALCHEMICAL_CHALK_NAME);
        GameRegistry.registerItem(diviningRod, Strings.DIVINING_ROD_NAME);

        // Add recipes for items
        GameRegistry.addRecipe(new ItemStack(inertStone), new Object[]{"sis", "igi", "sis", 's', Block.stone, 'i', Item.ingotIron, 'g', Item.ingotGold});
        GameRegistry.addRecipe(new ItemStack(miniumStone), new Object[]{"sss", "sis", "sss", 's', miniumShard, 'i', inertStone});
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(diviningRod), new Object[]{" s ", " s ", "s s", 's', Item.stick}));
        GameRegistry.addShapelessRecipe(new ItemStack(alchemicalChalk), new ItemStack(Item.clay), new ItemStack(Item.dyePowder.itemID, 1, 15), new ItemStack(Item.dyePowder.itemID, 1, 15), new ItemStack(Item.dyePowder.itemID, 1, 15), new ItemStack(Item.dyePowder.itemID, 1, 15));
    }
}
