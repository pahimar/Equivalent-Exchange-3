package com.pahimar.ee3.item.crafting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class RecipesPotions {

    private static ItemStack netherWart = new ItemStack(372, 1, 0);
    private static ItemStack glowstoneDust = new ItemStack(Item.lightStoneDust);
    private static ItemStack redstoneDust = new ItemStack(331, 1, 0);
    private static ItemStack gunpowder = new ItemStack(Item.gunpowder);
    private static ItemStack goldenCarrot = new ItemStack(Item.goldenCarrot);
    private static ItemStack magmaCream = new ItemStack(Item.magmaCream);
    private static ItemStack sugar = new ItemStack(Item.sugar);
    private static ItemStack glisteningMelon = new ItemStack(Item.speckledMelon);
    private static ItemStack spiderEye = new ItemStack(Item.spiderEye);
    private static ItemStack ghastTear = new ItemStack(Item.ghastTear);
    private static ItemStack fermentedSpiderEye = new ItemStack(Item.fermentedSpiderEye);
    private static ItemStack blazePowder = new ItemStack(Item.blazePowder);

    public static ItemStack bottleWater = new ItemStack(Item.potion.itemID, 1, 0);
    public static ItemStack potionAwkward = new ItemStack(Item.potion.itemID, 1, 16);
    public static ItemStack potionThick = new ItemStack(Item.potion.itemID, 1, 32);
    public static ItemStack potionMundaneBase = new ItemStack(Item.potion.itemID, 1, 64);
    public static ItemStack potionMundaneExtended = new ItemStack(Item.potion.itemID, 1, 128);

    public static ItemStack potionRegeneration = new ItemStack(Item.potion.itemID, 1, 8193);
    public static ItemStack potionRegenerationEnhanced = new ItemStack(Item.potion.itemID, 1, 8225);
    public static ItemStack potionRegenerationExtended = new ItemStack(Item.potion.itemID, 1, 8257);
    public static ItemStack potionRegenerationSplash = new ItemStack(Item.potion.itemID, 1, 16385);
    public static ItemStack potionRegenerationSplashEnhanced = new ItemStack(Item.potion.itemID, 1, 16417);
    public static ItemStack potionRegenerationSplashExtended = new ItemStack(Item.potion.itemID, 1, 16449);

    public static ItemStack potionSwiftness = new ItemStack(Item.potion.itemID, 1, 8194);
    public static ItemStack potionSwiftnessEnhanced = new ItemStack(Item.potion.itemID, 1, 8226);
    public static ItemStack potionSwiftnessExtended = new ItemStack(Item.potion.itemID, 1, 8258);
    public static ItemStack potionSwiftnessSplash = new ItemStack(Item.potion.itemID, 1, 16386);
    public static ItemStack potionSwiftnessSplashEnhanced = new ItemStack(Item.potion.itemID, 1, 16418);
    public static ItemStack potionSwiftnessSplashExtended = new ItemStack(Item.potion.itemID, 1, 16450);

    public static ItemStack potionFireResist = new ItemStack(Item.potion.itemID, 1, 8195);
    public static ItemStack potionFireResistExtended = new ItemStack(Item.potion.itemID, 1, 8259);
    public static ItemStack potionFireResistSplash = new ItemStack(Item.potion.itemID, 1, 16387);
    public static ItemStack potionFireResistSplashExtended = new ItemStack(Item.potion.itemID, 1, 16451);

    public static ItemStack potionPoison = new ItemStack(Item.potion.itemID, 1, 8196);
    public static ItemStack potionPoisonEnhanced = new ItemStack(Item.potion.itemID, 1, 8228);
    public static ItemStack potionPoisonExtended = new ItemStack(Item.potion.itemID, 1, 8260);
    public static ItemStack potionPoisonSplash = new ItemStack(Item.potion.itemID, 1, 16388);
    public static ItemStack potionPoisonSplashEnhanced = new ItemStack(Item.potion.itemID, 1, 16420);
    public static ItemStack potionPoisonSplashExtended = new ItemStack(Item.potion.itemID, 1, 16452);

    public static ItemStack potionHealing = new ItemStack(Item.potion.itemID, 1, 8197);
    public static ItemStack potionHealingEnhanced = new ItemStack(Item.potion.itemID, 1, 8229);
    public static ItemStack potionHealingSplash = new ItemStack(Item.potion.itemID, 1, 16389);
    public static ItemStack potionHealingSplashEnhanced = new ItemStack(Item.potion.itemID, 1, 16421);

    public static ItemStack potionNightVision = new ItemStack(Item.potion.itemID, 1, 8198);
    public static ItemStack potionNightVisionExtended = new ItemStack(Item.potion.itemID, 1, 8262);
    public static ItemStack potionNightVisionSplash = new ItemStack(Item.potion.itemID, 1, 16390);
    public static ItemStack potionNightVisionSplashExtended = new ItemStack(Item.potion.itemID, 1, 16454);

    public static ItemStack potionWeakness = new ItemStack(Item.potion.itemID, 1, 8200);
    public static ItemStack potionWeaknessExtended = new ItemStack(Item.potion.itemID, 1, 8264);
    public static ItemStack potionWeaknessSplash = new ItemStack(Item.potion.itemID, 1, 16392);
    public static ItemStack potionWeaknessSplashExtended = new ItemStack(Item.potion.itemID, 1, 16456);

    public static ItemStack potionStrength = new ItemStack(Item.potion.itemID, 1, 8201);
    public static ItemStack potionStrengthEnchanced = new ItemStack(Item.potion.itemID, 1, 8233);
    public static ItemStack potionStrengthExtended = new ItemStack(Item.potion.itemID, 1, 8265);
    public static ItemStack potionStrengthSplash = new ItemStack(Item.potion.itemID, 1, 16393);
    public static ItemStack potionStrengthSplashEnchanced = new ItemStack(Item.potion.itemID, 1, 16425);
    public static ItemStack potionStrengthSplashExtended = new ItemStack(Item.potion.itemID, 1, 16457);

    public static ItemStack potionSlowness = new ItemStack(Item.potion.itemID, 1, 8202);
    public static ItemStack potionSlownessExtended = new ItemStack(Item.potion.itemID, 1, 8266);
    public static ItemStack potionSlownessSplash = new ItemStack(Item.potion.itemID, 1, 16394);
    public static ItemStack potionSlownessSplashExtended = new ItemStack(Item.potion.itemID, 1, 16458);

    public static ItemStack potionHarming = new ItemStack(Item.potion.itemID, 1, 8204);
    public static ItemStack potionHarmingEnhanced = new ItemStack(Item.potion.itemID, 1, 8236);
    public static ItemStack potionHarmingSplash = new ItemStack(Item.potion.itemID, 1, 16396);
    public static ItemStack potionHarmingSplashEnhanced = new ItemStack(Item.potion.itemID, 1, 16428);

    public static ItemStack potionInvisibility = new ItemStack(Item.potion.itemID, 1, 8206);
    public static ItemStack potionInvisibilityExtended = new ItemStack(Item.potion.itemID, 1, 8270);
    public static ItemStack potionInvisibilitySplash = new ItemStack(Item.potion.itemID, 1, 16398);
    public static ItemStack potionInvisibilitySplashExtended = new ItemStack(Item.potion.itemID, 1, 16462);

    public static Multimap<ItemStack, List<ItemStack>> getPotionRecipes() {

        Multimap<ItemStack, List<ItemStack>> potionRecipes = HashMultimap.create();

        potionRecipes.put(bottleWater, Arrays.asList(new ItemStack(Block.waterStill)));

        potionRecipes.put(potionAwkward, Arrays.asList(bottleWater, netherWart));

        potionRecipes.put(potionNightVision, Arrays.asList(potionAwkward, goldenCarrot));
        potionRecipes.put(potionNightVisionExtended, Arrays.asList(potionNightVision, redstoneDust));
        potionRecipes.put(potionNightVision, Arrays.asList(potionNightVisionExtended, glowstoneDust));

        potionRecipes.put(potionInvisibility, Arrays.asList(potionNightVision, spiderEye));
        potionRecipes.put(potionInvisibilityExtended, Arrays.asList(potionInvisibility, redstoneDust));
        potionRecipes.put(potionInvisibility, Arrays.asList(potionInvisibilityExtended, glowstoneDust));
        potionRecipes.put(potionInvisibilityExtended, Arrays.asList(potionNightVisionExtended, spiderEye));

        potionRecipes.put(potionFireResist, Arrays.asList(potionAwkward, magmaCream));
        potionRecipes.put(potionFireResistExtended, Arrays.asList(potionFireResist, redstoneDust));
        potionRecipes.put(potionFireResist, Arrays.asList(potionFireResistExtended, glowstoneDust));
        potionRecipes.put(potionSlownessExtended, Arrays.asList(potionFireResistExtended, spiderEye));

        return potionRecipes;
    }
}
