package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

public class RecipesPotions
{
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
    public static WrappedStack bottleWater = new WrappedStack(new ItemStack(Items.potionitem, 1, 0));

    public static WrappedStack potionAwkward = new WrappedStack(new ItemStack(Items.potionitem, 1, 16));
    public static WrappedStack potionThick = new WrappedStack(new ItemStack(Items.potionitem, 1, 32));
    public static WrappedStack potionMundane = new WrappedStack(new ItemStack(Items.potionitem, 1, 128));
    public static WrappedStack potionMundaneExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 64));
    public static WrappedStack potionMundaneSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16512));
    public static WrappedStack potionMundaneSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16448));

    public static WrappedStack potionRegeneration = new WrappedStack(new ItemStack(Items.potionitem, 1, 8193));
    public static WrappedStack potionRegenerationEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8225));
    public static WrappedStack potionRegenerationExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8257));
    public static WrappedStack potionRegenerationSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16385));
    public static WrappedStack potionRegenerationSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16417));
    public static WrappedStack potionRegenerationSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16449));

    public static WrappedStack potionSwiftness = new WrappedStack(new ItemStack(Items.potionitem, 1, 8194));
    public static WrappedStack potionSwiftnessEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8226));
    public static WrappedStack potionSwiftnessExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8258));
    public static WrappedStack potionSwiftnessSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16386));
    public static WrappedStack potionSwiftnessSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16418));
    public static WrappedStack potionSwiftnessSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16450));

    public static WrappedStack potionFireResist = new WrappedStack(new ItemStack(Items.potionitem, 1, 8195));
    public static WrappedStack potionFireResistExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8259));
    public static WrappedStack potionFireResistSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16387));
    public static WrappedStack potionFireResistSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16451));

    public static WrappedStack potionPoison = new WrappedStack(new ItemStack(Items.potionitem, 1, 8196));
    public static WrappedStack potionPoisonEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8228));
    public static WrappedStack potionPoisonExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8260));
    public static WrappedStack potionPoisonSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16388));
    public static WrappedStack potionPoisonSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16420));
    public static WrappedStack potionPoisonSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16452));

    public static WrappedStack potionHealing = new WrappedStack(new ItemStack(Items.potionitem, 1, 8197));
    public static WrappedStack potionHealingEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8229));
    public static WrappedStack potionHealingSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16389));
    public static WrappedStack potionHealingSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16421));

    public static WrappedStack potionNightVision = new WrappedStack(new ItemStack(Items.potionitem, 1, 8198));
    public static WrappedStack potionNightVisionExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8262));
    public static WrappedStack potionNightVisionSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16390));
    public static WrappedStack potionNightVisionSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16454));

    public static WrappedStack potionWeakness = new WrappedStack(new ItemStack(Items.potionitem, 1, 8200));
    public static WrappedStack potionWeaknessExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8264));
    public static WrappedStack potionWeaknessSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16392));
    public static WrappedStack potionWeaknessSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16456));

    public static WrappedStack potionStrength = new WrappedStack(new ItemStack(Items.potionitem, 1, 8201));
    public static WrappedStack potionStrengthEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8233));
    public static WrappedStack potionStrengthExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8265));
    public static WrappedStack potionStrengthSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16393));
    public static WrappedStack potionStrengthSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16425));
    public static WrappedStack potionStrengthSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16457));

    public static WrappedStack potionSlowness = new WrappedStack(new ItemStack(Items.potionitem, 1, 8202));
    public static WrappedStack potionSlownessExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8266));
    public static WrappedStack potionSlownessSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16394));
    public static WrappedStack potionSlownessSplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16458));

    public static WrappedStack potionHarming = new WrappedStack(new ItemStack(Items.potionitem, 1, 8204));
    public static WrappedStack potionHarmingEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 8236));
    public static WrappedStack potionHarmingSplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16396));
    public static WrappedStack potionHarmingSplashEnhanced = new WrappedStack(new ItemStack(Items.potionitem, 1, 16428));

    public static WrappedStack potionInvisibility = new WrappedStack(new ItemStack(Items.potionitem, 1, 8206));
    public static WrappedStack potionInvisibilityExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 8270));
    public static WrappedStack potionInvisibilitySplash = new WrappedStack(new ItemStack(Items.potionitem, 1, 16398));
    public static WrappedStack potionInvisibilitySplashExtended = new WrappedStack(new ItemStack(Items.potionitem, 1, 16462));

    public static void registerRecipes()
    {
        RecipeRegistryProxy.addRecipe(bottleWater, Arrays.asList(bottleEmpty, reagentWater));

        RecipeRegistryProxy.addRecipe(potionAwkward, Arrays.asList(bottleWater, reagentNetherWart));

        RecipeRegistryProxy.addRecipe(potionNightVision, Arrays.asList(potionAwkward, reagentGoldenCarrot));
        RecipeRegistryProxy.addRecipe(potionNightVision, Arrays.asList(potionNightVisionExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplash, Arrays.asList(potionNightVisionSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplash, Arrays.asList(potionNightVision, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionNightVisionExtended, Arrays.asList(potionNightVision, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtended, Arrays.asList(potionNightVisionSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtended, Arrays.asList(potionNightVisionExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionInvisibility, Arrays.asList(potionNightVision, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibility, Arrays.asList(potionInvisibilityExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplash, Arrays.asList(potionNightVisionSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplash, Arrays.asList(potionInvisibilitySplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplash, Arrays.asList(potionInvisibility, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionInvisibilityExtended, Arrays.asList(potionInvisibility, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilityExtended, Arrays.asList(potionNightVisionExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtended, Arrays.asList(potionInvisibilitySplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtended, Arrays.asList(potionNightVisionSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtended, Arrays.asList(potionInvisibilityExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionFireResist, Arrays.asList(potionAwkward, reagentMagmaCream));
        RecipeRegistryProxy.addRecipe(potionFireResist, Arrays.asList(potionFireResistExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplash, Arrays.asList(potionFireResistSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplash, Arrays.asList(potionFireResist, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionFireResistExtended, Arrays.asList(potionFireResist, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtended, Arrays.asList(potionFireResistSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtended, Arrays.asList(potionFireResistExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSlowness, Arrays.asList(potionFireResist, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlowness, Arrays.asList(potionSlownessExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSlowness, Arrays.asList(potionSwiftness, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlowness, Arrays.asList(potionSwiftnessExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplash, Arrays.asList(potionFireResistSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplash, Arrays.asList(potionSlownessSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSlownessSplash, Arrays.asList(potionSwiftnessSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplash, Arrays.asList(potionSwiftnessSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplash, Arrays.asList(potionSlowness, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSlownessExtended, Arrays.asList(potionFireResistExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessExtended, Arrays.asList(potionSwiftnessEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtended, Arrays.asList(potionFireResistSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtended, Arrays.asList(potionSwiftnessSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtended, Arrays.asList(potionSlownessExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSwiftness, Arrays.asList(potionAwkward, reagentSugar));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplash, Arrays.asList(potionSwiftness, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSwiftnessExtended, Arrays.asList(potionSwiftness, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessExtended, Arrays.asList(potionSwiftnessEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtended, Arrays.asList(potionSwiftnessExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhanced, Arrays.asList(potionSwiftness, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhanced, Arrays.asList(potionSwiftnessExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhanced, Arrays.asList(potionSwiftnessEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHealing, Arrays.asList(potionAwkward, reagentGlisteringMelon));
        RecipeRegistryProxy.addRecipe(potionHealing, Arrays.asList(potionHealingEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplash, Arrays.asList(potionHealingSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplash, Arrays.asList(potionHealing, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHealingEnhanced, Arrays.asList(potionHealing, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhanced, Arrays.asList(potionHealingSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhanced, Arrays.asList(potionHealingEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHarming, Arrays.asList(potionHealing, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarming, Arrays.asList(potionPoison, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarming, Arrays.asList(potionPoisonExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarming, Arrays.asList(potionHarmingEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingSplash, Arrays.asList(potionHealingSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplash, Arrays.asList(potionPoisonSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplash, Arrays.asList(potionPoisonSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplash, Arrays.asList(potionHarmingSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingSplash, Arrays.asList(potionHarming, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHarmingEnhanced, Arrays.asList(potionHealingEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingEnhanced, Arrays.asList(potionHarming, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingEnhanced, Arrays.asList(potionPoisonEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhanced, Arrays.asList(potionHealingSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhanced, Arrays.asList(potionHarmingSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhanced, Arrays.asList(potionPoisonSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhanced, Arrays.asList(potionHarmingEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionPoison, Arrays.asList(potionAwkward, reagentSpiderEye));
        RecipeRegistryProxy.addRecipe(potionPoisonSplash, Arrays.asList(potionPoison, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionPoisonExtended, Arrays.asList(potionPoisonExtended, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonExtended, Arrays.asList(potionPoisonEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtended, Arrays.asList(potionPoisonSplashExtended, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtended, Arrays.asList(potionPoisonSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtended, Arrays.asList(potionPoisonExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionPoisonEnhanced, Arrays.asList(potionPoison, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonEnhanced, Arrays.asList(potionPoisonExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhanced, Arrays.asList(potionPoisonEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionRegeneration, Arrays.asList(potionAwkward, reagentGhastTear));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplash, Arrays.asList(potionRegeneration, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionRegenerationExtended, Arrays.asList(potionRegeneration, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationExtended, Arrays.asList(potionRegenerationEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtended, Arrays.asList(potionRegenerationExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionRegenerationEnhanced, Arrays.asList(potionRegeneration, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationEnhanced, Arrays.asList(potionRegenerationExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhanced, Arrays.asList(potionRegenerationEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionWeakness, Arrays.asList(potionAwkward, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeakness, Arrays.asList(potionRegeneration, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeakness, Arrays.asList(potionRegenerationEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeakness, Arrays.asList(potionStrength, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeakness, Arrays.asList(potionStrengthEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeakness, Arrays.asList(potionMundane, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeakness, Arrays.asList(potionWeaknessExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplash, Arrays.asList(potionRegenerationSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplash, Arrays.asList(potionRegenerationSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplash, Arrays.asList(potionStrengthSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplash, Arrays.asList(potionStrengthSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplash, Arrays.asList(potionMundaneSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplash, Arrays.asList(potionWeaknessSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplash, Arrays.asList(potionWeakness, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionWeaknessExtended, Arrays.asList(potionWeakness, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessExtended, Arrays.asList(potionRegenerationExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessExtended, Arrays.asList(potionStrengthExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessExtended, Arrays.asList(potionMundaneExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtended, Arrays.asList(potionWeaknessSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtended, Arrays.asList(potionRegenerationSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtended, Arrays.asList(potionStrengthSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtended, Arrays.asList(potionMundaneSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtended, Arrays.asList(potionWeaknessExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionStrength, Arrays.asList(potionAwkward, reagentBlazePowder));
        RecipeRegistryProxy.addRecipe(potionStrengthSplash, Arrays.asList(potionStrength, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionStrengthEnhanced, Arrays.asList(potionStrength, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthEnhanced, Arrays.asList(potionStrengthExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhanced, Arrays.asList(potionStrengthEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionStrengthExtended, Arrays.asList(potionStrength, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthExtended, Arrays.asList(potionStrengthEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtended, Arrays.asList(potionStrengthSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtended, Arrays.asList(potionStrengthSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtended, Arrays.asList(potionStrengthExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionThick, Arrays.asList(bottleWater, reagentGlowstoneDust));

        RecipeRegistryProxy.addRecipe(potionMundane, Arrays.asList(bottleWater, reagentSugar));
        RecipeRegistryProxy.addRecipe(potionMundane, Arrays.asList(bottleWater, reagentGlisteringMelon));
        RecipeRegistryProxy.addRecipe(potionMundane, Arrays.asList(bottleWater, reagentSpiderEye));
        RecipeRegistryProxy.addRecipe(potionMundane, Arrays.asList(bottleWater, reagentBlazePowder));
        RecipeRegistryProxy.addRecipe(potionMundane, Arrays.asList(bottleWater, reagentMagmaCream));
        RecipeRegistryProxy.addRecipe(potionMundane, Arrays.asList(bottleWater, reagentGhastTear));
        RecipeRegistryProxy.addRecipe(potionMundaneSplash, Arrays.asList(potionMundane, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionMundaneExtended, Arrays.asList(bottleWater, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionMundaneSplashExtended, Arrays.asList(potionMundaneExtended, reagentGunpowder));
    }
}
