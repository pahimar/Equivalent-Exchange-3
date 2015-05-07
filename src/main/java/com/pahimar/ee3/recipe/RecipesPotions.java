package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.exchange.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Arrays;

public class RecipesPotions
{
    private static WrappedStack reagentWater = WrappedStack.wrap(new ItemStack(Blocks.water));
    private static WrappedStack reagentNetherWart = WrappedStack.wrap(new ItemStack(Items.nether_wart));
    private static WrappedStack reagentGlowstoneDust = WrappedStack.wrap(new ItemStack(Items.glowstone_dust));
    private static WrappedStack reagentRedstoneDust = WrappedStack.wrap(new ItemStack(Items.redstone));
    private static WrappedStack reagentGunpowder = WrappedStack.wrap(new ItemStack(Items.gunpowder));
    private static WrappedStack reagentGoldenCarrot = WrappedStack.wrap(new ItemStack(Items.golden_carrot));
    private static WrappedStack reagentMagmaCream = WrappedStack.wrap(new ItemStack(Items.magma_cream));
    private static WrappedStack reagentSugar = WrappedStack.wrap(new ItemStack(Items.sugar));
    private static WrappedStack reagentGlisteringMelon = WrappedStack.wrap(new ItemStack(Items.speckled_melon));
    private static WrappedStack reagentSpiderEye = WrappedStack.wrap(new ItemStack(Items.spider_eye));
    private static WrappedStack reagentGhastTear = WrappedStack.wrap(new ItemStack(Items.ghast_tear));
    private static WrappedStack reagentFermentedSpiderEye = WrappedStack.wrap(new ItemStack(Items.fermented_spider_eye));
    private static WrappedStack reagentBlazePowder = WrappedStack.wrap(new ItemStack(Items.blaze_powder));

    private static WrappedStack bottleEmpty = WrappedStack.wrap(Items.glass_bottle);
    public static WrappedStack bottleWater = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 0));

    public static WrappedStack potionAwkward = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16));
    public static WrappedStack potionThick = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 32));
    public static WrappedStack potionMundane = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 128));
    public static WrappedStack potionMundaneExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 64));
    public static WrappedStack potionMundaneSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16512));
    public static WrappedStack potionMundaneSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16448));

    public static WrappedStack potionRegeneration = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8193));
    public static WrappedStack potionRegenerationEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8225));
    public static WrappedStack potionRegenerationExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8257));
    public static WrappedStack potionRegenerationSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16385));
    public static WrappedStack potionRegenerationSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16417));
    public static WrappedStack potionRegenerationSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16449));

    public static WrappedStack potionSwiftness = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8194));
    public static WrappedStack potionSwiftnessEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8226));
    public static WrappedStack potionSwiftnessExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8258));
    public static WrappedStack potionSwiftnessSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16386));
    public static WrappedStack potionSwiftnessSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16418));
    public static WrappedStack potionSwiftnessSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16450));

    public static WrappedStack potionFireResist = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8195));
    public static WrappedStack potionFireResistExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8259));
    public static WrappedStack potionFireResistSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16387));
    public static WrappedStack potionFireResistSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16451));

    public static WrappedStack potionPoison = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8196));
    public static WrappedStack potionPoisonEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8228));
    public static WrappedStack potionPoisonExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8260));
    public static WrappedStack potionPoisonSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16388));
    public static WrappedStack potionPoisonSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16420));
    public static WrappedStack potionPoisonSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16452));

    public static WrappedStack potionHealing = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8197));
    public static WrappedStack potionHealingEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8229));
    public static WrappedStack potionHealingSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16389));
    public static WrappedStack potionHealingSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16421));

    public static WrappedStack potionNightVision = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8198));
    public static WrappedStack potionNightVisionExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8262));
    public static WrappedStack potionNightVisionSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16390));
    public static WrappedStack potionNightVisionSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16454));

    public static WrappedStack potionWeakness = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8200));
    public static WrappedStack potionWeaknessExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8264));
    public static WrappedStack potionWeaknessSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16392));
    public static WrappedStack potionWeaknessSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16456));

    public static WrappedStack potionStrength = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8201));
    public static WrappedStack potionStrengthEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8233));
    public static WrappedStack potionStrengthExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8265));
    public static WrappedStack potionStrengthSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16393));
    public static WrappedStack potionStrengthSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16425));
    public static WrappedStack potionStrengthSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16457));

    public static WrappedStack potionSlowness = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8202));
    public static WrappedStack potionSlownessExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8266));
    public static WrappedStack potionSlownessSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16394));
    public static WrappedStack potionSlownessSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16458));

    public static WrappedStack potionHarming = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8204));
    public static WrappedStack potionHarmingEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8236));
    public static WrappedStack potionHarmingSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16396));
    public static WrappedStack potionHarmingSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16428));

    public static WrappedStack potionInvisibility = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8206));
    public static WrappedStack potionInvisibilityExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8270));
    public static WrappedStack potionInvisibilitySplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16398));
    public static WrappedStack potionInvisibilitySplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16462));

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
