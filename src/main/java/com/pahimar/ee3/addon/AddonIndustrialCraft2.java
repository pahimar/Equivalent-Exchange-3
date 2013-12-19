package com.pahimar.ee3.addon;

import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcValue;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Equivalent-Exchange-3
 * <p/>
 * AddonIndustrialCraft2
 *
 * @author pahimar
 */
public class AddonIndustrialCraft2
{

    public static void init()
    {
        addRecipes();
        addPreAssignmentEmcValues();
        addPostAssignmentEmcValues();
    }

    public static void addRecipes()
    {
        /**
         * Bronze
         */
        AddonHandler.sendAddRecipe(new OreStack("dustBronze"), new OreStack("dustTin"), new OreStack("dustCopper", 3));
        AddonHandler.sendAddRecipe(new OreStack("ingotBronze"), new OreStack("dustBronze"));
        AddonHandler.sendAddRecipe(new OreStack("plateBronze"), new OreStack("ingotBronze"));
        AddonHandler.sendAddRecipe(new OreStack("blockBronze"), new OreStack("ingotBronze", 9));
        AddonHandler.sendAddRecipe(new OreStack("plateDenseBronze"), new OreStack("ingotBronze", 9));

        /**
         * Clay
         */
        AddonHandler.sendAddRecipe(new OreStack("dustClay", 2), Block.blockClay);

        /**
         * Coal
         */
        AddonHandler.sendAddRecipe(new OreStack("dustCoal"), new ItemStack(Item.coal, 1));
        AddonHandler.sendAddRecipe(new OreStack("dustHydratedCoal"), new OreStack("dustCoal"), new WrappedStack(FluidRegistry.WATER));

        /**
         * Copper
         */
        AddonHandler.sendAddRecipe(new OreStack("dustCopper"), new OreStack("oreCopper"));
        AddonHandler.sendAddRecipe(new OreStack("dustTinyCopper", 9), new OreStack("dustCopper"));
        AddonHandler.sendAddRecipe(new OreStack("crushedCopper"), new OreStack("dustCopper"));
        AddonHandler.sendAddRecipe(new OreStack("crushedPurifiedCopper"), new OreStack("crushedCopper"));
        AddonHandler.sendAddRecipe(new OreStack("ingotCopper"), new OreStack("dustCopper"));
        AddonHandler.sendAddRecipe(new OreStack("plateCopper"), new OreStack("ingotCopper"));
        AddonHandler.sendAddRecipe(new OreStack("blockCopper"), new OreStack("ingotCopper", 9));
        AddonHandler.sendAddRecipe(new OreStack("plateDenseCopper"), new OreStack("ingotCopper", 9));

        /**
         * Diamond
         */
        AddonHandler.sendAddRecipe(new OreStack("dustDiamond"), Item.diamond);

        /**
         * Gold
         */
        AddonHandler.sendAddRecipe(new OreStack("crushedGold"), Block.oreGold);
        AddonHandler.sendAddRecipe(new OreStack("crushedPurifiedGold"), new OreStack("crushedGold"));
        AddonHandler.sendAddRecipe(new OreStack("dustGold"), new OreStack("crushedPurifiedGold"));
        AddonHandler.sendAddRecipe(new OreStack("dustTinyGold", 9), Block.oreGold);
        AddonHandler.sendAddRecipe(new OreStack("plateGold"), Item.ingotGold);
        AddonHandler.sendAddRecipe(new OreStack("plateDenseGold"), new ItemStack(Item.ingotGold, 9));

        /**
         * Industrial Diamond
         */
        AddonHandler.sendAddRecipe(new OreStack("gemDiamond"), Item.diamond);

        /**
         * Iron
         */
        AddonHandler.sendAddRecipe(new OreStack("crushedIron"), Block.oreIron);
        AddonHandler.sendAddRecipe(new OreStack("crushedPurifiedIron"), new OreStack("crushedIron"));
        AddonHandler.sendAddRecipe(new OreStack("dustIron"), new OreStack("crushedPurifiedIron"));
        AddonHandler.sendAddRecipe(new OreStack("dustTinyIron", 9), Block.oreIron);
        AddonHandler.sendAddRecipe(new OreStack("ingotRefinedIron"), Item.ingotIron);
        AddonHandler.sendAddRecipe(new OreStack("plateIron"), Item.ingotIron);
        AddonHandler.sendAddRecipe(new OreStack("plateRefinedIron"), new OreStack("ingotRefinedIron"));
        AddonHandler.sendAddRecipe(new OreStack("plateDenseIron"), new ItemStack(Item.ingotIron, 9));

        /**
         * Lapis
         */
        AddonHandler.sendAddRecipe(new OreStack("dustLapis"), new ItemStack(Item.dyePowder, 1, 4));
        AddonHandler.sendAddRecipe(new OreStack("plateLapis"), new OreStack("dustLapis"));
        AddonHandler.sendAddRecipe(new OreStack("plateDenseLapis"), new OreStack("plateLapis", 9));

        /**
         * Lead
         */
        AddonHandler.sendAddRecipe(new OreStack("crushedLead"), new OreStack("oreLead"));
        AddonHandler.sendAddRecipe(new OreStack("crushedPurifiedLead"), new OreStack("crushedLead"));
        AddonHandler.sendAddRecipe(new OreStack("dustLead"), new OreStack("crushedPurifiedLead"));
        AddonHandler.sendAddRecipe(new OreStack("dustTinyLead", 9), new OreStack("dustLead"));
        AddonHandler.sendAddRecipe(new OreStack("ingotLead"), new OreStack("dustLead"));
        AddonHandler.sendAddRecipe(new OreStack("plateLead"), new OreStack("ingotLead"));
        AddonHandler.sendAddRecipe(new OreStack("plateDenseLead"), new OreStack("ingotLead", 9));
        AddonHandler.sendAddRecipe(new OreStack("blockLead"), new OreStack("ingotLead", 9));

        /**
         * Lithium
         */
        AddonHandler.sendAddRecipe(new OreStack("dustTinyLithium", 9), new OreStack("dustLithium"));

        /**
         * Obsidian
         */
        AddonHandler.sendAddRecipe(new OreStack("dustObsidian", 4), Block.obsidian);
        AddonHandler.sendAddRecipe(new OreStack("plateObsidian"), new OreStack("dustObsidian"));
        AddonHandler.sendAddRecipe(new OreStack("plateDenseObsidian"), new OreStack("plateObsidian", 9));

        /**
         * Silver
         */
        AddonHandler.sendAddRecipe(new OreStack("crushedSilver"), new OreStack("oreSilver"));
        AddonHandler.sendAddRecipe(new OreStack("crushedPurifiedSilver"), new OreStack("crushedSilver"));
        AddonHandler.sendAddRecipe(new OreStack("dustSilver"), new OreStack("crushedPurifiedSilver"));
        AddonHandler.sendAddRecipe(new OreStack("dustTinySilver", 9), new OreStack("dustSilver"));
        AddonHandler.sendAddRecipe(new OreStack("ingotSilver"), new OreStack("dustSilver"));

        /**
         * Sulfur
         */
        AddonHandler.sendAddRecipe(new OreStack("dustTinySulfur", 9), new OreStack("dustSulfur"));

        /**
         * Tin
         */
        AddonHandler.sendAddRecipe(new OreStack("dustTin"), new OreStack("oreTin"));
        AddonHandler.sendAddRecipe(new OreStack("dustTinyTin", 9), new OreStack("dustTin"));
        AddonHandler.sendAddRecipe(new OreStack("crushedTin"), new OreStack("dustTin"));
        AddonHandler.sendAddRecipe(new OreStack("crushedPurifiedTin"), new OreStack("crushedTin"));
        AddonHandler.sendAddRecipe(new OreStack("ingotTin"), new OreStack("dustTin"));
        AddonHandler.sendAddRecipe(new OreStack("plateTin"), new OreStack("ingotTin"));
        AddonHandler.sendAddRecipe(new OreStack("blockTin"), new OreStack("ingotTin", 9));
        AddonHandler.sendAddRecipe(new OreStack("plateDenseTin"), new OreStack("ingotTin", 9));

        /**
         * Uranium
         */
        AddonHandler.sendAddRecipe(new OreStack("crushedUranium"), new OreStack("oreUranium"));
        AddonHandler.sendAddRecipe(new OreStack("crushedPurifiedUranium"), new OreStack("crushedUranium"));
        AddonHandler.sendAddRecipe(new OreStack("blockUranium"), new OreStack("oreUranium", 9));

        /**
         * Tools
         */
        AddonHandler.sendAddRecipe(new OreStack("craftingToolForgeHammer"), new ItemStack(Item.ingotIron, 5), new OreStack("stickWood", 2));
        AddonHandler.sendAddRecipe(new OreStack("craftingToolWireCutter"), new ItemStack(Item.ingotIron, 2), new OreStack("plateIron", 3));

        /**
         * Items
         */
        AddonHandler.sendAddRecipe(new OreStack("itemRubber"), new OreStack("woodRubber"));
    }

    public static void addPreAssignmentEmcValues()
    {
        AddonHandler.sendPreValueAssignment(new OreStack("oreCopper"), COPPER_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("oreTin"), TIN_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("oreLead"), LEAD_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("oreSilver"), SILVER_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("oreUranium"), URANIUM_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("dustSulfur"), SULFUR_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("dustLithium"), LITHIUM_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("dustSiliconDioxide"), SILICON_DIOXIDE_EMC_VALUE);
        AddonHandler.sendPreValueAssignment(new OreStack("woodRubber"), RUBBER_WOOD_EMC_VALUE);
    }

    public static void addPostAssignmentEmcValues()
    {

    }

    /**
     * TODO Helper method to grab items from IC2's API
     *
     */

    /**
     * EmcValues for various IC2 things
     */
    private static final EmcValue COPPER_EMC_VALUE = new EmcValue(72);
    private static final EmcValue TIN_EMC_VALUE = new EmcValue(256);
    private static final EmcValue LEAD_EMC_VALUE = new EmcValue(512);
    private static final EmcValue SILVER_EMC_VALUE = new EmcValue(1024);
    private static final EmcValue URANIUM_EMC_VALUE = new EmcValue(4096);
    private static final EmcValue SULFUR_EMC_VALUE = new EmcValue(512);
    private static final EmcValue LITHIUM_EMC_VALUE = new EmcValue(512);
    private static final EmcValue SILICON_DIOXIDE_EMC_VALUE = new EmcValue(256);
    private static final EmcValue RUBBER_WOOD_EMC_VALUE = new EmcValue(24);
}
