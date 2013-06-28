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
    private static ItemStack glisteringMelon = new ItemStack(Item.speckledMelon);
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
    public static ItemStack potionStrengthEnhanced = new ItemStack(Item.potion.itemID, 1, 8233);
    public static ItemStack potionStrengthExtended = new ItemStack(Item.potion.itemID, 1, 8265);
    public static ItemStack potionStrengthSplash = new ItemStack(Item.potion.itemID, 1, 16393);
    public static ItemStack potionStrengthSplashEnhanced = new ItemStack(Item.potion.itemID, 1, 16425);
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
        potionRecipes.put(potionNightVision, Arrays.asList(potionNightVisionExtended, glowstoneDust));
        potionRecipes.put(potionNightVisionSplash, Arrays.asList(potionNightVisionSplashExtended, glowstoneDust));
        potionRecipes.put(potionNightVisionSplash, Arrays.asList(potionNightVision, gunpowder));
        
        potionRecipes.put(potionNightVisionExtended, Arrays.asList(potionNightVision, redstoneDust));
        potionRecipes.put(potionNightVisionSplashExtended, Arrays.asList(potionNightVisionSplash, redstoneDust));
        potionRecipes.put(potionNightVisionSplashExtended, Arrays.asList(potionNightVisionExtended, gunpowder));

        potionRecipes.put(potionInvisibility, Arrays.asList(potionNightVision, fermentedSpiderEye));
        potionRecipes.put(potionInvisibility, Arrays.asList(potionInvisibilityExtended, glowstoneDust));
        potionRecipes.put(potionInvisibilitySplash, Arrays.asList(potionNightVisionSplash, fermentedSpiderEye));
        potionRecipes.put(potionInvisibilitySplash, Arrays.asList(potionInvisibilitySplashExtended, glowstoneDust));
        potionRecipes.put(potionInvisibilitySplash, Arrays.asList(potionInvisibility, gunpowder));
        
        potionRecipes.put(potionInvisibilityExtended, Arrays.asList(potionInvisibility, redstoneDust));
        potionRecipes.put(potionInvisibilityExtended, Arrays.asList(potionNightVisionExtended, fermentedSpiderEye));
        potionRecipes.put(potionInvisibilitySplashExtended, Arrays.asList(potionInvisibilitySplash, redstoneDust));
        potionRecipes.put(potionInvisibilitySplashExtended, Arrays.asList(potionNightVisionSplashExtended, fermentedSpiderEye));
        potionRecipes.put(potionInvisibilitySplashExtended, Arrays.asList(potionInvisibilityExtended, gunpowder));

        potionRecipes.put(potionFireResist, Arrays.asList(potionAwkward, magmaCream));
        potionRecipes.put(potionFireResist, Arrays.asList(potionFireResistExtended, glowstoneDust));
        potionRecipes.put(potionFireResistSplash, Arrays.asList(potionFireResistSplashExtended, glowstoneDust));
        potionRecipes.put(potionFireResistSplash, Arrays.asList(potionFireResist, gunpowder));
        
        potionRecipes.put(potionFireResistExtended, Arrays.asList(potionFireResist, redstoneDust));
        potionRecipes.put(potionFireResistSplashExtended, Arrays.asList(potionFireResistSplash, redstoneDust));
        potionRecipes.put(potionFireResistSplashExtended, Arrays.asList(potionFireResistExtended, gunpowder));
        
        potionRecipes.put(potionSlowness, Arrays.asList(potionFireResist, fermentedSpiderEye));
        potionRecipes.put(potionSlowness, Arrays.asList(potionSlownessExtended, glowstoneDust));
        potionRecipes.put(potionSlowness, Arrays.asList(potionSwiftness, fermentedSpiderEye));
        potionRecipes.put(potionSlowness, Arrays.asList(potionSwiftnessExtended, fermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionFireResistSplash, fermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSlownessSplashExtended, glowstoneDust));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSwiftnessSplash, fermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSwiftnessSplashExtended, fermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSlowness, gunpowder));
        
        potionRecipes.put(potionSlownessExtended, Arrays.asList(potionFireResistExtended, fermentedSpiderEye));
        potionRecipes.put(potionSlownessExtended, Arrays.asList(potionSwiftnessEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionSlownessSplashExtended, Arrays.asList(potionFireResistSplashExtended, fermentedSpiderEye));
        potionRecipes.put(potionSlownessSplashExtended, Arrays.asList(potionSwiftnessSplashEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionSlownessSplashExtended, Arrays.asList(potionSlownessExtended, gunpowder));
        
        potionRecipes.put(potionSwiftness, Arrays.asList(potionAwkward, sugar));
        potionRecipes.put(potionSwiftnessSplash, Arrays.asList(potionSwiftness, gunpowder));
        
        potionRecipes.put(potionSwiftnessExtended, Arrays.asList(potionSwiftness, redstoneDust));
        potionRecipes.put(potionSwiftnessExtended, Arrays.asList(potionSwiftnessEnhanced, redstoneDust));
        potionRecipes.put(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessSplash, redstoneDust));
        potionRecipes.put(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessSplashEnhanced, redstoneDust));
        potionRecipes.put(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessExtended, gunpowder));
        
        potionRecipes.put(potionSwiftnessEnhanced, Arrays.asList(potionSwiftness, glowstoneDust));
        potionRecipes.put(potionSwiftnessEnhanced, Arrays.asList(potionSwiftnessExtended, glowstoneDust));
        potionRecipes.put(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessSplash, glowstoneDust));
        potionRecipes.put(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessSplashExtended, glowstoneDust));
        potionRecipes.put(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessEnhanced, gunpowder));
        
        potionRecipes.put(potionHealing, Arrays.asList(potionAwkward, glisteringMelon));
        potionRecipes.put(potionHealing, Arrays.asList(potionHealingEnhanced, redstoneDust));
        potionRecipes.put(potionHealingSplash, Arrays.asList(potionHealingSplashEnhanced, redstoneDust));
        potionRecipes.put(potionHealingSplash, Arrays.asList(potionHealing, gunpowder));
        
        potionRecipes.put(potionHealingEnhanced, Arrays.asList(potionHealing, glowstoneDust));
        potionRecipes.put(potionHealingSplashEnhanced, Arrays.asList(potionHealingSplash, glowstoneDust));
        potionRecipes.put(potionHealingSplashEnhanced, Arrays.asList(potionHealingEnhanced, gunpowder));
        
        potionRecipes.put(potionHarming, Arrays.asList(potionHealing, fermentedSpiderEye));
        potionRecipes.put(potionHarming, Arrays.asList(potionPoison, fermentedSpiderEye));
        potionRecipes.put(potionHarming, Arrays.asList(potionPoisonExtended, fermentedSpiderEye));
        potionRecipes.put(potionHarming, Arrays.asList(potionHarmingEnhanced, redstoneDust));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionHealingSplash, fermentedSpiderEye));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionPoisonSplash, fermentedSpiderEye));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionPoisonSplashExtended, fermentedSpiderEye));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionHarmingSplashEnhanced, redstoneDust));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionHarming, gunpowder));
        
        potionRecipes.put(potionHarmingEnhanced, Arrays.asList(potionHealingEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionHarmingEnhanced, Arrays.asList(potionHarming, glowstoneDust));
        potionRecipes.put(potionHarmingEnhanced, Arrays.asList(potionPoisonEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionHealingSplashEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionHarmingSplash, glowstoneDust));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionPoisonSplashEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionHarmingEnhanced, gunpowder));
        
        potionRecipes.put(potionPoison, Arrays.asList(potionAwkward, spiderEye));
        potionRecipes.put(potionPoisonSplash, Arrays.asList(potionPoison, gunpowder));
        
        potionRecipes.put(potionPoisonExtended, Arrays.asList(potionPoisonExtended, redstoneDust));
        potionRecipes.put(potionPoisonExtended, Arrays.asList(potionPoisonEnhanced, redstoneDust));
        potionRecipes.put(potionPoisonSplashExtended, Arrays.asList(potionPoisonSplashExtended, redstoneDust));
        potionRecipes.put(potionPoisonSplashExtended, Arrays.asList(potionPoisonSplashEnhanced, redstoneDust));
        potionRecipes.put(potionPoisonSplashExtended, Arrays.asList(potionPoisonExtended, gunpowder));
        
        potionRecipes.put(potionPoisonEnhanced, Arrays.asList(potionPoison, glowstoneDust));
        potionRecipes.put(potionPoisonEnhanced, Arrays.asList(potionPoisonExtended, glowstoneDust));
        potionRecipes.put(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonSplash, glowstoneDust));
        potionRecipes.put(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonSplashExtended, glowstoneDust));
        potionRecipes.put(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonEnhanced, gunpowder));
        
        potionRecipes.put(potionRegeneration, Arrays.asList(potionAwkward, ghastTear));
        potionRecipes.put(potionRegenerationSplash, Arrays.asList(potionRegeneration, gunpowder));
        
        potionRecipes.put(potionRegenerationExtended, Arrays.asList(potionRegeneration, redstoneDust));
        potionRecipes.put(potionRegenerationExtended, Arrays.asList(potionRegenerationEnhanced, redstoneDust));
        potionRecipes.put(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationSplash, redstoneDust));
        potionRecipes.put(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationSplashEnhanced, redstoneDust));
        potionRecipes.put(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationExtended, gunpowder));
        
        potionRecipes.put(potionRegenerationEnhanced, Arrays.asList(potionRegeneration, glowstoneDust));
        potionRecipes.put(potionRegenerationEnhanced, Arrays.asList(potionRegenerationExtended, glowstoneDust));
        potionRecipes.put(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationSplash, glowstoneDust));
        potionRecipes.put(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationSplashExtended, glowstoneDust));
        potionRecipes.put(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationEnhanced, gunpowder));
        
        potionRecipes.put(potionWeakness, Arrays.asList(potionAwkward, fermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionRegeneration, fermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionRegenerationEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionStrength, fermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionStrengthEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionMundaneBase, fermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionWeaknessExtended, glowstoneDust));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionRegenerationSplash, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionRegenerationSplashEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionStrengthSplash, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionStrengthSplashEnhanced, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionWeaknessSplashExtended, glowstoneDust));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionWeakness, gunpowder));
        
        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionWeakness, redstoneDust));
        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionRegenerationExtended, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionStrengthExtended, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionMundaneExtended, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionWeaknessSplash, redstoneDust));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionRegenerationSplashExtended, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionStrengthSplashExtended, fermentedSpiderEye));
        // TODO Figure out the meta for the mundane splash potions
        // potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionMundaneSplashExtended, fermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionWeaknessExtended, gunpowder));
        
        potionRecipes.put(potionStrength, Arrays.asList(potionAwkward, blazePowder));
        potionRecipes.put(potionStrengthSplash, Arrays.asList(potionStrength, gunpowder));
        
        potionRecipes.put(potionStrengthEnhanced, Arrays.asList(potionStrength, glowstoneDust));
        potionRecipes.put(potionStrengthEnhanced, Arrays.asList(potionStrengthExtended, glowstoneDust));
        potionRecipes.put(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthSplash, glowstoneDust));
        potionRecipes.put(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthSplashExtended, glowstoneDust));
        potionRecipes.put(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthEnhanced, gunpowder));
        
        potionRecipes.put(potionStrengthExtended, Arrays.asList(potionStrength, redstoneDust));
        potionRecipes.put(potionStrengthExtended, Arrays.asList(potionStrengthEnhanced, redstoneDust));
        potionRecipes.put(potionStrengthSplashExtended, Arrays.asList(potionStrengthSplash, redstoneDust));
        potionRecipes.put(potionStrengthSplashExtended, Arrays.asList(potionStrengthSplashEnhanced, redstoneDust));
        potionRecipes.put(potionStrengthSplashExtended, Arrays.asList(potionStrengthExtended, gunpowder));
        
        potionRecipes.put(potionThick, Arrays.asList(bottleWater, glowstoneDust));
        
        potionRecipes.put(potionMundaneBase, Arrays.asList(bottleWater, sugar));
        potionRecipes.put(potionMundaneBase, Arrays.asList(bottleWater, glisteringMelon));
        potionRecipes.put(potionMundaneBase, Arrays.asList(bottleWater, spiderEye));
        potionRecipes.put(potionMundaneBase, Arrays.asList(bottleWater, blazePowder));
        potionRecipes.put(potionMundaneBase, Arrays.asList(bottleWater, magmaCream));
        potionRecipes.put(potionMundaneBase, Arrays.asList(bottleWater, ghastTear));
        
        potionRecipes.put(potionMundaneExtended, Arrays.asList(bottleWater, redstoneDust));

        return potionRecipes;
    }
}
