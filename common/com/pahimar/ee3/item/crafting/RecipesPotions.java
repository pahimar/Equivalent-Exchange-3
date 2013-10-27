package com.pahimar.ee3.item.crafting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.item.CustomWrappedStack;

public class RecipesPotions {

    private static Multimap<CustomWrappedStack, List<CustomWrappedStack>> potionRecipes = null;

    private static CustomWrappedStack reagentWater = new CustomWrappedStack(new ItemStack(Block.waterStill));
    private static CustomWrappedStack reagentNetherWart = new CustomWrappedStack(new ItemStack(372, 1, 0));
    private static CustomWrappedStack reagentGlowstoneDust = new CustomWrappedStack(new ItemStack(Item.glowstone));
    private static CustomWrappedStack reagentRedstoneDust = new CustomWrappedStack(new ItemStack(331, 1, 0));
    private static CustomWrappedStack reagentGunpowder = new CustomWrappedStack(new ItemStack(Item.gunpowder));
    private static CustomWrappedStack reagentGoldenCarrot = new CustomWrappedStack(new ItemStack(Item.goldenCarrot));
    private static CustomWrappedStack reagentMagmaCream = new CustomWrappedStack(new ItemStack(Item.magmaCream));
    private static CustomWrappedStack reagentSugar = new CustomWrappedStack(new ItemStack(Item.sugar));
    private static CustomWrappedStack reagentGlisteringMelon = new CustomWrappedStack(new ItemStack(Item.speckledMelon));
    private static CustomWrappedStack reagentSpiderEye = new CustomWrappedStack(new ItemStack(Item.spiderEye));
    private static CustomWrappedStack reagentGhastTear = new CustomWrappedStack(new ItemStack(Item.ghastTear));
    private static CustomWrappedStack reagentFermentedSpiderEye = new CustomWrappedStack(new ItemStack(Item.fermentedSpiderEye));
    private static CustomWrappedStack reagentBlazePowder = new CustomWrappedStack(new ItemStack(Item.blazePowder));

    private static CustomWrappedStack bottleEmpty = new CustomWrappedStack(Item.glassBottle);
    private static CustomWrappedStack bottleWater = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 0));

    private static CustomWrappedStack potionAwkward = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16));
    private static CustomWrappedStack potionThick = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 32));
    private static CustomWrappedStack potionMundane = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 128));
    private static CustomWrappedStack potionMundaneExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 64));
    private static CustomWrappedStack potionMundaneSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16512));
    private static CustomWrappedStack potionMundaneSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16448));

    private static CustomWrappedStack potionRegeneration = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8193));
    private static CustomWrappedStack potionRegenerationEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8225));
    private static CustomWrappedStack potionRegenerationExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8257));
    private static CustomWrappedStack potionRegenerationSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16385));
    private static CustomWrappedStack potionRegenerationSplashEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16417));
    private static CustomWrappedStack potionRegenerationSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16449));

    private static CustomWrappedStack potionSwiftness = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8194));
    private static CustomWrappedStack potionSwiftnessEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8226));
    private static CustomWrappedStack potionSwiftnessExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8258));
    private static CustomWrappedStack potionSwiftnessSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16386));
    private static CustomWrappedStack potionSwiftnessSplashEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16418));
    private static CustomWrappedStack potionSwiftnessSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16450));

    private static CustomWrappedStack potionFireResist = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8195));
    private static CustomWrappedStack potionFireResistExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8259));
    private static CustomWrappedStack potionFireResistSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16387));
    private static CustomWrappedStack potionFireResistSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16451));

    private static CustomWrappedStack potionPoison = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8196));
    private static CustomWrappedStack potionPoisonEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8228));
    private static CustomWrappedStack potionPoisonExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8260));
    private static CustomWrappedStack potionPoisonSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16388));
    private static CustomWrappedStack potionPoisonSplashEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16420));
    private static CustomWrappedStack potionPoisonSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16452));

    private static CustomWrappedStack potionHealing = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8197));
    private static CustomWrappedStack potionHealingEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8229));
    private static CustomWrappedStack potionHealingSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16389));
    private static CustomWrappedStack potionHealingSplashEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16421));

    private static CustomWrappedStack potionNightVision = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8198));
    private static CustomWrappedStack potionNightVisionExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8262));
    private static CustomWrappedStack potionNightVisionSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16390));
    private static CustomWrappedStack potionNightVisionSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16454));

    private static CustomWrappedStack potionWeakness = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8200));
    private static CustomWrappedStack potionWeaknessExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8264));
    private static CustomWrappedStack potionWeaknessSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16392));
    private static CustomWrappedStack potionWeaknessSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16456));

    private static CustomWrappedStack potionStrength = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8201));
    private static CustomWrappedStack potionStrengthEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8233));
    private static CustomWrappedStack potionStrengthExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8265));
    private static CustomWrappedStack potionStrengthSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16393));
    private static CustomWrappedStack potionStrengthSplashEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16425));
    private static CustomWrappedStack potionStrengthSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16457));

    private static CustomWrappedStack potionSlowness = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8202));
    private static CustomWrappedStack potionSlownessExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8266));
    private static CustomWrappedStack potionSlownessSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16394));
    private static CustomWrappedStack potionSlownessSplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16458));

    private static CustomWrappedStack potionHarming = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8204));
    private static CustomWrappedStack potionHarmingEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8236));
    private static CustomWrappedStack potionHarmingSplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16396));
    private static CustomWrappedStack potionHarmingSplashEnhanced = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16428));

    private static CustomWrappedStack potionInvisibility = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8206));
    private static CustomWrappedStack potionInvisibilityExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 8270));
    private static CustomWrappedStack potionInvisibilitySplash = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16398));
    private static CustomWrappedStack potionInvisibilitySplashExtended = new CustomWrappedStack(new ItemStack(Item.potion.itemID, 1, 16462));

    public static Multimap<CustomWrappedStack, List<CustomWrappedStack>> getPotionRecipes() {

        if (potionRecipes == null) {
            init();
        }

        return potionRecipes;
    }

    private static void init() {

        potionRecipes = HashMultimap.create();

        potionRecipes.put(bottleWater, Arrays.asList(bottleEmpty, reagentWater));

        potionRecipes.put(potionAwkward, Arrays.asList(bottleWater, reagentNetherWart));

        potionRecipes.put(potionNightVision, Arrays.asList(potionAwkward, reagentGoldenCarrot));
        potionRecipes.put(potionNightVision, Arrays.asList(potionNightVisionExtended, reagentGlowstoneDust));
        potionRecipes.put(potionNightVisionSplash, Arrays.asList(potionNightVisionSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionNightVisionSplash, Arrays.asList(potionNightVision, reagentGunpowder));

        potionRecipes.put(potionNightVisionExtended, Arrays.asList(potionNightVision, reagentRedstoneDust));
        potionRecipes.put(potionNightVisionSplashExtended, Arrays.asList(potionNightVisionSplash, reagentRedstoneDust));
        potionRecipes.put(potionNightVisionSplashExtended, Arrays.asList(potionNightVisionExtended, reagentGunpowder));

        potionRecipes.put(potionInvisibility, Arrays.asList(potionNightVision, reagentFermentedSpiderEye));
        potionRecipes.put(potionInvisibility, Arrays.asList(potionInvisibilityExtended, reagentGlowstoneDust));
        potionRecipes.put(potionInvisibilitySplash, Arrays.asList(potionNightVisionSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionInvisibilitySplash, Arrays.asList(potionInvisibilitySplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionInvisibilitySplash, Arrays.asList(potionInvisibility, reagentGunpowder));

        potionRecipes.put(potionInvisibilityExtended, Arrays.asList(potionInvisibility, reagentRedstoneDust));
        potionRecipes.put(potionInvisibilityExtended, Arrays.asList(potionNightVisionExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionInvisibilitySplashExtended, Arrays.asList(potionInvisibilitySplash, reagentRedstoneDust));
        potionRecipes.put(potionInvisibilitySplashExtended, Arrays.asList(potionNightVisionSplashExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionInvisibilitySplashExtended, Arrays.asList(potionInvisibilityExtended, reagentGunpowder));

        potionRecipes.put(potionFireResist, Arrays.asList(potionAwkward, reagentMagmaCream));
        potionRecipes.put(potionFireResist, Arrays.asList(potionFireResistExtended, reagentGlowstoneDust));
        potionRecipes.put(potionFireResistSplash, Arrays.asList(potionFireResistSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionFireResistSplash, Arrays.asList(potionFireResist, reagentGunpowder));

        potionRecipes.put(potionFireResistExtended, Arrays.asList(potionFireResist, reagentRedstoneDust));
        potionRecipes.put(potionFireResistSplashExtended, Arrays.asList(potionFireResistSplash, reagentRedstoneDust));
        potionRecipes.put(potionFireResistSplashExtended, Arrays.asList(potionFireResistExtended, reagentGunpowder));

        potionRecipes.put(potionSlowness, Arrays.asList(potionFireResist, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlowness, Arrays.asList(potionSlownessExtended, reagentGlowstoneDust));
        potionRecipes.put(potionSlowness, Arrays.asList(potionSwiftness, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlowness, Arrays.asList(potionSwiftnessExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionFireResistSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSlownessSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSwiftnessSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSwiftnessSplashExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessSplash, Arrays.asList(potionSlowness, reagentGunpowder));

        potionRecipes.put(potionSlownessExtended, Arrays.asList(potionFireResistExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessExtended, Arrays.asList(potionSwiftnessEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessSplashExtended, Arrays.asList(potionFireResistSplashExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessSplashExtended, Arrays.asList(potionSwiftnessSplashEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionSlownessSplashExtended, Arrays.asList(potionSlownessExtended, reagentGunpowder));

        potionRecipes.put(potionSwiftness, Arrays.asList(potionAwkward, reagentSugar));
        potionRecipes.put(potionSwiftnessSplash, Arrays.asList(potionSwiftness, reagentGunpowder));

        potionRecipes.put(potionSwiftnessExtended, Arrays.asList(potionSwiftness, reagentRedstoneDust));
        potionRecipes.put(potionSwiftnessExtended, Arrays.asList(potionSwiftnessEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessSplash, reagentRedstoneDust));
        potionRecipes.put(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessSplashEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessExtended, reagentGunpowder));

        potionRecipes.put(potionSwiftnessEnhanced, Arrays.asList(potionSwiftness, reagentGlowstoneDust));
        potionRecipes.put(potionSwiftnessEnhanced, Arrays.asList(potionSwiftnessExtended, reagentGlowstoneDust));
        potionRecipes.put(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessSplash, reagentGlowstoneDust));
        potionRecipes.put(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessEnhanced, reagentGunpowder));

        potionRecipes.put(potionHealing, Arrays.asList(potionAwkward, reagentGlisteringMelon));
        potionRecipes.put(potionHealing, Arrays.asList(potionHealingEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionHealingSplash, Arrays.asList(potionHealingSplashEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionHealingSplash, Arrays.asList(potionHealing, reagentGunpowder));

        potionRecipes.put(potionHealingEnhanced, Arrays.asList(potionHealing, reagentGlowstoneDust));
        potionRecipes.put(potionHealingSplashEnhanced, Arrays.asList(potionHealingSplash, reagentGlowstoneDust));
        potionRecipes.put(potionHealingSplashEnhanced, Arrays.asList(potionHealingEnhanced, reagentGunpowder));

        potionRecipes.put(potionHarming, Arrays.asList(potionHealing, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarming, Arrays.asList(potionPoison, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarming, Arrays.asList(potionPoisonExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarming, Arrays.asList(potionHarmingEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionHealingSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionPoisonSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionPoisonSplashExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionHarmingSplashEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionHarmingSplash, Arrays.asList(potionHarming, reagentGunpowder));

        potionRecipes.put(potionHarmingEnhanced, Arrays.asList(potionHealingEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarmingEnhanced, Arrays.asList(potionHarming, reagentGlowstoneDust));
        potionRecipes.put(potionHarmingEnhanced, Arrays.asList(potionPoisonEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionHealingSplashEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionHarmingSplash, reagentGlowstoneDust));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionPoisonSplashEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionHarmingSplashEnhanced, Arrays.asList(potionHarmingEnhanced, reagentGunpowder));

        potionRecipes.put(potionPoison, Arrays.asList(potionAwkward, reagentSpiderEye));
        potionRecipes.put(potionPoisonSplash, Arrays.asList(potionPoison, reagentGunpowder));

        potionRecipes.put(potionPoisonExtended, Arrays.asList(potionPoisonExtended, reagentRedstoneDust));
        potionRecipes.put(potionPoisonExtended, Arrays.asList(potionPoisonEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionPoisonSplashExtended, Arrays.asList(potionPoisonSplashExtended, reagentRedstoneDust));
        potionRecipes.put(potionPoisonSplashExtended, Arrays.asList(potionPoisonSplashEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionPoisonSplashExtended, Arrays.asList(potionPoisonExtended, reagentGunpowder));

        potionRecipes.put(potionPoisonEnhanced, Arrays.asList(potionPoison, reagentGlowstoneDust));
        potionRecipes.put(potionPoisonEnhanced, Arrays.asList(potionPoisonExtended, reagentGlowstoneDust));
        potionRecipes.put(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonSplash, reagentGlowstoneDust));
        potionRecipes.put(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonEnhanced, reagentGunpowder));

        potionRecipes.put(potionRegeneration, Arrays.asList(potionAwkward, reagentGhastTear));
        potionRecipes.put(potionRegenerationSplash, Arrays.asList(potionRegeneration, reagentGunpowder));

        potionRecipes.put(potionRegenerationExtended, Arrays.asList(potionRegeneration, reagentRedstoneDust));
        potionRecipes.put(potionRegenerationExtended, Arrays.asList(potionRegenerationEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationSplash, reagentRedstoneDust));
        potionRecipes.put(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationSplashEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationExtended, reagentGunpowder));

        potionRecipes.put(potionRegenerationEnhanced, Arrays.asList(potionRegeneration, reagentGlowstoneDust));
        potionRecipes.put(potionRegenerationEnhanced, Arrays.asList(potionRegenerationExtended, reagentGlowstoneDust));
        potionRecipes.put(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationSplash, reagentGlowstoneDust));
        potionRecipes.put(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationEnhanced, reagentGunpowder));

        potionRecipes.put(potionWeakness, Arrays.asList(potionAwkward, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionRegeneration, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionRegenerationEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionStrength, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionStrengthEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionMundane, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeakness, Arrays.asList(potionWeaknessExtended, reagentGlowstoneDust));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionRegenerationSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionRegenerationSplashEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionStrengthSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionStrengthSplashEnhanced, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionMundaneSplash, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionWeaknessSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionWeaknessSplash, Arrays.asList(potionWeakness, reagentGunpowder));

        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionWeakness, reagentRedstoneDust));
        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionRegenerationExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionStrengthExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessExtended, Arrays.asList(potionMundaneExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionWeaknessSplash, reagentRedstoneDust));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionRegenerationSplashExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionStrengthSplashExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionMundaneSplashExtended, reagentFermentedSpiderEye));
        potionRecipes.put(potionWeaknessSplashExtended, Arrays.asList(potionWeaknessExtended, reagentGunpowder));

        potionRecipes.put(potionStrength, Arrays.asList(potionAwkward, reagentBlazePowder));
        potionRecipes.put(potionStrengthSplash, Arrays.asList(potionStrength, reagentGunpowder));

        potionRecipes.put(potionStrengthEnhanced, Arrays.asList(potionStrength, reagentGlowstoneDust));
        potionRecipes.put(potionStrengthEnhanced, Arrays.asList(potionStrengthExtended, reagentGlowstoneDust));
        potionRecipes.put(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthSplash, reagentGlowstoneDust));
        potionRecipes.put(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthSplashExtended, reagentGlowstoneDust));
        potionRecipes.put(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthEnhanced, reagentGunpowder));

        potionRecipes.put(potionStrengthExtended, Arrays.asList(potionStrength, reagentRedstoneDust));
        potionRecipes.put(potionStrengthExtended, Arrays.asList(potionStrengthEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionStrengthSplashExtended, Arrays.asList(potionStrengthSplash, reagentRedstoneDust));
        potionRecipes.put(potionStrengthSplashExtended, Arrays.asList(potionStrengthSplashEnhanced, reagentRedstoneDust));
        potionRecipes.put(potionStrengthSplashExtended, Arrays.asList(potionStrengthExtended, reagentGunpowder));

        potionRecipes.put(potionThick, Arrays.asList(bottleWater, reagentGlowstoneDust));

        potionRecipes.put(potionMundane, Arrays.asList(bottleWater, reagentSugar));
        potionRecipes.put(potionMundane, Arrays.asList(bottleWater, reagentGlisteringMelon));
        potionRecipes.put(potionMundane, Arrays.asList(bottleWater, reagentSpiderEye));
        potionRecipes.put(potionMundane, Arrays.asList(bottleWater, reagentBlazePowder));
        potionRecipes.put(potionMundane, Arrays.asList(bottleWater, reagentMagmaCream));
        potionRecipes.put(potionMundane, Arrays.asList(bottleWater, reagentGhastTear));
        potionRecipes.put(potionMundaneSplash, Arrays.asList(potionMundane, reagentGunpowder));

        potionRecipes.put(potionMundaneExtended, Arrays.asList(bottleWater, reagentRedstoneDust));
        potionRecipes.put(potionMundaneSplashExtended, Arrays.asList(potionMundaneExtended, reagentGunpowder));
    }
}
