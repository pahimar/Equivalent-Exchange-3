package com.pahimar.ee3.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class RecipesPotions
{

    private static Multimap<WrappedStack, List<WrappedStack>> potionRecipes = null;

    private static WrappedStack reagentWater = new WrappedStack(new ItemStack(Blocks.water));
    private static WrappedStack reagentNetherWart = new WrappedStack(new ItemStack(Items.nether_wart));
    private static WrappedStack reagentGlowstoneDust = new WrappedStack(new ItemStack(Items.glowstone_dust));
    private static WrappedStack reagentRedstoneDust = new WrappedStack(new ItemStack(Items.redstone));
    private static WrappedStack reagentGunpowder = new WrappedStack(new ItemStack(Items.gunpowder));
    private static WrappedStack reagentGoldenCarrot = new WrappedStack(new ItemStack(Items.golden_carrot));
    private static WrappedStack reagentMagmaCream = new WrappedStack(new ItemStack(Items.magma_cream));
    private static WrappedStack reagentSugar = new WrappedStack(new ItemStack(Items.sugar));
    private static WrappedStack reagentGlisteringMelon = new WrappedStack(new ItemStack(Items.speckled_melon));
    private static WrappedStack reagentSpiderEye = new WrappedStack(new ItemStack(Items.spider_eye));
    private static WrappedStack reagentGhastTear = new WrappedStack(new ItemStack(Items.ghast_tear));
    private static WrappedStack reagentFermentedSpiderEye = new WrappedStack(new ItemStack(Items.fermented_spider_eye));
    private static WrappedStack reagentBlazePowder = new WrappedStack(new ItemStack(Items.blaze_powder));

    private static WrappedStack bottleEmpty = new WrappedStack(Items.glass_bottle);
    private static WrappedStack bottleWater = new WrappedStack(new ItemStack(Items.potionitem, 1, 0));

    private static WrappedStack potionAwkward = new WrappedStack(new ItemStack(Items.potionitem, 1, 16));
    private static WrappedStack potionThick = new WrappedStack(new ItemStack(Items.potionitem, 1, 32));
    private static WrappedStack potionMundane = new WrappedStack(new ItemStack(Items.potionitem, 1, 128));
    private static WrappedStack potionMundaneExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 64));
    private static WrappedStack potionMundaneSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16512));
    private static WrappedStack potionMundaneSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16448));

    private static WrappedStack potionRegeneration = new WrappedStack(new ItemStack(Items.potionitem, 1, 8193));
    private static WrappedStack potionRegenerationEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8225));
    private static WrappedStack potionRegenerationExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8257));
    private static WrappedStack potionRegenerationSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16385));
    private static WrappedStack potionRegenerationSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16417));
    private static WrappedStack potionRegenerationSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16449));

    private static WrappedStack potionSwiftness = new WrappedStack(new ItemStack(Items.potionitem, 1, 8194));
    private static WrappedStack potionSwiftnessEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8226));
    private static WrappedStack potionSwiftnessExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8258));
    private static WrappedStack potionSwiftnessSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16386));
    private static WrappedStack potionSwiftnessSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16418));
    private static WrappedStack potionSwiftnessSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16450));

    private static WrappedStack potionFireResist = new WrappedStack(new ItemStack(Items.potionitem, 1, 8195));
    private static WrappedStack potionFireResistExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8259));
    private static WrappedStack potionFireResistSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16387));
    private static WrappedStack potionFireResistSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16451));

    private static WrappedStack potionPoison = new WrappedStack(new ItemStack(Items.potionitem, 1, 8196));
    private static WrappedStack potionPoisonEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8228));
    private static WrappedStack potionPoisonExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8260));
    private static WrappedStack potionPoisonSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16388));
    private static WrappedStack potionPoisonSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16420));
    private static WrappedStack potionPoisonSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16452));

    private static WrappedStack potionHealing = new WrappedStack(new ItemStack(Items.potionitem, 1, 8197));
    private static WrappedStack potionHealingEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8229));
    private static WrappedStack potionHealingSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16389));
    private static WrappedStack potionHealingSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16421));

    private static WrappedStack potionNightVision = new WrappedStack(new ItemStack(Items.potionitem, 1, 8198));
    private static WrappedStack potionNightVisionExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8262));
    private static WrappedStack potionNightVisionSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16390));
    private static WrappedStack potionNightVisionSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16454));

    private static WrappedStack potionWeakness = new WrappedStack(new ItemStack(Items.potionitem, 1, 8200));
    private static WrappedStack potionWeaknessExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8264));
    private static WrappedStack potionWeaknessSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16392));
    private static WrappedStack potionWeaknessSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16456));

    private static WrappedStack potionStrength = new WrappedStack(new ItemStack(Items.potionitem, 1, 8201));
    private static WrappedStack potionStrengthEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8233));
    private static WrappedStack potionStrengthExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8265));
    private static WrappedStack potionStrengthSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16393));
    private static WrappedStack potionStrengthSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16425));
    private static WrappedStack potionStrengthSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16457));

    private static WrappedStack potionSlowness = new WrappedStack(new ItemStack(Items.potionitem, 1, 8202));
    private static WrappedStack potionSlownessExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8266));
    private static WrappedStack potionSlownessSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16394));
    private static WrappedStack potionSlownessSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16458));

    private static WrappedStack potionHarming = new WrappedStack(new ItemStack(Items.potionitem, 1, 8204));
    private static WrappedStack potionHarmingEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8236));
    private static WrappedStack potionHarmingSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16396));
    private static WrappedStack potionHarmingSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16428));

    private static WrappedStack potionInvisibility = new WrappedStack(new ItemStack(Items.potionitem, 1, 8206));
    private static WrappedStack potionInvisibilityExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8270));
    private static WrappedStack potionInvisibilitySplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16398));
    private static WrappedStack potionInvisibilitySplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16462));

    public static Multimap<WrappedStack, List<WrappedStack>> getPotionRecipes()
    {

        if (potionRecipes == null)
        {
            init();
        }

        return potionRecipes;
    }

    private static void init()
    {

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
