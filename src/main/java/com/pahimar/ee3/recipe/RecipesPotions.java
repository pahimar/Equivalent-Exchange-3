package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
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
    private static WrappedStack bottleWater = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 0));

    private static WrappedStack potionAwkward = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16));
    private static WrappedStack potionAwkwardOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16));
    private static WrappedStack potionThickOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 32));
    private static WrappedStack potionMundane = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 128));
    private static WrappedStack potionMundaneOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 128));
    private static WrappedStack potionMundaneExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 64));
    private static WrappedStack potionMundaneExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 64));
    private static WrappedStack potionMundaneSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16512));
    private static WrappedStack potionMundaneSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16512));
    private static WrappedStack potionMundaneSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16448));
    private static WrappedStack potionMundaneSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16448));

    private static WrappedStack potionRegeneration = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8193));
    private static WrappedStack potionRegenerationOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8193));
    private static WrappedStack potionRegenerationEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8225));
    private static WrappedStack potionRegenerationEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8225));
    private static WrappedStack potionRegenerationExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8257));
    private static WrappedStack potionRegenerationExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8257));
    private static WrappedStack potionRegenerationSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16385));
    private static WrappedStack potionRegenerationSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16385));
    private static WrappedStack potionRegenerationSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16417));
    private static WrappedStack potionRegenerationSplashEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16417));
    private static WrappedStack potionRegenerationSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16449));
    private static WrappedStack potionRegenerationSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16449));

    private static WrappedStack potionSwiftness = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8194));
    private static WrappedStack potionSwiftnessOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8194));
    private static WrappedStack potionSwiftnessEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8226));
    private static WrappedStack potionSwiftnessEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8226));
    private static WrappedStack potionSwiftnessExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8258));
    private static WrappedStack potionSwiftnessExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8258));
    private static WrappedStack potionSwiftnessSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16386));
    private static WrappedStack potionSwiftnessSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16386));
    private static WrappedStack potionSwiftnessSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16418));
    private static WrappedStack potionSwiftnessSplashEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16418));
    private static WrappedStack potionSwiftnessSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16450));
    private static WrappedStack potionSwiftnessSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16450));

    private static WrappedStack potionFireResist = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8195));
    private static WrappedStack potionFireResistOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8195));
    private static WrappedStack potionFireResistExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8259));
    private static WrappedStack potionFireResistExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8259));
    private static WrappedStack potionFireResistSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16387));
    private static WrappedStack potionFireResistSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16387));
    private static WrappedStack potionFireResistSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16451));
    private static WrappedStack potionFireResistSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16451));

    private static WrappedStack potionPoison = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8196));
    private static WrappedStack potionPoisonOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8196));
    private static WrappedStack potionPoisonEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8228));
    private static WrappedStack potionPoisonEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8228));
    private static WrappedStack potionPoisonExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8260));
    private static WrappedStack potionPoisonExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8260));
    private static WrappedStack potionPoisonSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16388));
    private static WrappedStack potionPoisonSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16388));
    private static WrappedStack potionPoisonSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16420));
    private static WrappedStack potionPoisonSplashEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16420));
    private static WrappedStack potionPoisonSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16452));
    private static WrappedStack potionPoisonSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16452));

    private static WrappedStack potionHealing = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8197));
    private static WrappedStack potionHealingOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8197));
    private static WrappedStack potionHealingEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8229));
    private static WrappedStack potionHealingEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8229));
    private static WrappedStack potionHealingSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16389));
    private static WrappedStack potionHealingSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16389));
    private static WrappedStack potionHealingSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16421));
    private static WrappedStack potionHealingSplashEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16421));

    private static WrappedStack potionNightVision = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8198));
    private static WrappedStack potionNightVisionOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8198));
    private static WrappedStack potionNightVisionExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8262));
    private static WrappedStack potionNightVisionExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8262));
    private static WrappedStack potionNightVisionSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16390));
    private static WrappedStack potionNightVisionSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16390));
    private static WrappedStack potionNightVisionSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16454));
    private static WrappedStack potionNightVisionSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16454));

    private static WrappedStack potionWeakness = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8200));
    private static WrappedStack potionWeaknessOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8200));
    private static WrappedStack potionWeaknessExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8264));
    private static WrappedStack potionWeaknessExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8264));
    private static WrappedStack potionWeaknessSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16392));
    private static WrappedStack potionWeaknessSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16392));
    private static WrappedStack potionWeaknessSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16456));
    private static WrappedStack potionWeaknessSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16456));

    private static WrappedStack potionStrength = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8201));
    private static WrappedStack potionStrengthOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8201));
    private static WrappedStack potionStrengthEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8233));
    private static WrappedStack potionStrengthEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8233));
    private static WrappedStack potionStrengthExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8265));
    private static WrappedStack potionStrengthExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8265));
    private static WrappedStack potionStrengthSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16393));
    private static WrappedStack potionStrengthSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16393));
    private static WrappedStack potionStrengthSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16425));
    private static WrappedStack potionStrengthSplashEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16425));
    private static WrappedStack potionStrengthSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16457));
    private static WrappedStack potionStrengthSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16457));

    private static WrappedStack potionSlowness = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8202));
    private static WrappedStack potionSlownessOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8202));
    private static WrappedStack potionSlownessExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8266));
    private static WrappedStack potionSlownessExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8266));
    private static WrappedStack potionSlownessSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16394));
    private static WrappedStack potionSlownessSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16394));
    private static WrappedStack potionSlownessSplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16458));
    private static WrappedStack potionSlownessSplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16458));

    private static WrappedStack potionHarming = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8204));
    private static WrappedStack potionHarmingOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8204));
    private static WrappedStack potionHarmingEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8236));
    private static WrappedStack potionHarmingEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8236));
    private static WrappedStack potionHarmingSplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16396));
    private static WrappedStack potionHarmingSplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16396));
    private static WrappedStack potionHarmingSplashEnhanced = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16428));
    private static WrappedStack potionHarmingSplashEnhancedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16428));

    private static WrappedStack potionInvisibility = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8206));
    private static WrappedStack potionInvisibilityOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8206));
    private static WrappedStack potionInvisibilityExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 8270));
    private static WrappedStack potionInvisibilityExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 8270));
    private static WrappedStack potionInvisibilitySplash = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16398));
    private static WrappedStack potionInvisibilitySplashOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16398));
    private static WrappedStack potionInvisibilitySplashExtended = WrappedStack.wrap(new ItemStack(Items.potionitem, 1, 16462));
    private static WrappedStack potionInvisibilitySplashExtendedOutput = WrappedStack.wrap(new ItemStack(Items.potionitem, 3, 16462));

    public static void registerRecipes()
    {
        RecipeRegistryProxy.addRecipe(bottleWater, Arrays.asList(bottleEmpty, reagentWater));

        RecipeRegistryProxy.addRecipe(potionAwkwardOutput, Arrays.asList(bottleWater, reagentNetherWart));

        RecipeRegistryProxy.addRecipe(potionNightVisionOutput, Arrays.asList(potionAwkward, reagentGoldenCarrot));
        RecipeRegistryProxy.addRecipe(potionNightVisionOutput, Arrays.asList(potionNightVisionExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashOutput, Arrays.asList(potionNightVisionSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashOutput, Arrays.asList(potionNightVision, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionNightVisionExtendedOutput, Arrays.asList(potionNightVision, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtendedOutput, Arrays.asList(potionNightVisionSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtendedOutput, Arrays.asList(potionNightVisionExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionInvisibilityOutput, Arrays.asList(potionNightVision, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibilityOutput, Arrays.asList(potionInvisibilityExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, Arrays.asList(potionNightVisionSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, Arrays.asList(potionInvisibilitySplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, Arrays.asList(potionInvisibility, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionInvisibilityExtendedOutput, Arrays.asList(potionInvisibility, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilityExtendedOutput, Arrays.asList(potionNightVisionExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, Arrays.asList(potionInvisibilitySplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, Arrays.asList(potionNightVisionSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, Arrays.asList(potionInvisibilityExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionFireResistOutput, Arrays.asList(potionAwkward, reagentMagmaCream));
        RecipeRegistryProxy.addRecipe(potionFireResistOutput, Arrays.asList(potionFireResistExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplashOutput, Arrays.asList(potionFireResistSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplashOutput, Arrays.asList(potionFireResist, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionFireResistExtendedOutput, Arrays.asList(potionFireResist, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtendedOutput, Arrays.asList(potionFireResistSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtendedOutput, Arrays.asList(potionFireResistExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSlownessOutput, Arrays.asList(potionFireResist, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, Arrays.asList(potionSlownessExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, Arrays.asList(potionSwiftness, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, Arrays.asList(potionSwiftnessExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, Arrays.asList(potionFireResistSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, Arrays.asList(potionSlownessSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, Arrays.asList(potionSwiftnessSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, Arrays.asList(potionSwiftnessSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, Arrays.asList(potionSlowness, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSlownessExtendedOutput, Arrays.asList(potionFireResistExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessExtendedOutput, Arrays.asList(potionSwiftnessEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, Arrays.asList(potionFireResistSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, Arrays.asList(potionSwiftnessSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, Arrays.asList(potionSlownessExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSwiftnessOutput, Arrays.asList(potionAwkward, reagentSugar));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashOutput, Arrays.asList(potionSwiftness, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSwiftnessExtendedOutput, Arrays.asList(potionSwiftness, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessExtendedOutput, Arrays.asList(potionSwiftnessEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, Arrays.asList(potionSwiftnessSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, Arrays.asList(potionSwiftnessSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, Arrays.asList(potionSwiftnessExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhancedOutput, Arrays.asList(potionSwiftness, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhancedOutput, Arrays.asList(potionSwiftnessExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, Arrays.asList(potionSwiftnessSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, Arrays.asList(potionSwiftnessSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, Arrays.asList(potionSwiftnessEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHealingOutput, Arrays.asList(potionAwkward, reagentGlisteringMelon));
        RecipeRegistryProxy.addRecipe(potionHealingOutput, Arrays.asList(potionHealingEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplashOutput, Arrays.asList(potionHealingSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplashOutput, Arrays.asList(potionHealing, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHealingEnhancedOutput, Arrays.asList(potionHealing, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhancedOutput, Arrays.asList(potionHealingSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhancedOutput, Arrays.asList(potionHealingEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHarmingOutput, Arrays.asList(potionHealing, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, Arrays.asList(potionPoison, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, Arrays.asList(potionPoisonExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, Arrays.asList(potionHarmingEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, Arrays.asList(potionHealingSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, Arrays.asList(potionPoisonSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, Arrays.asList(potionPoisonSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, Arrays.asList(potionHarmingSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, Arrays.asList(potionHarming, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, Arrays.asList(potionHealingEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, Arrays.asList(potionHarming, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, Arrays.asList(potionPoisonEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, Arrays.asList(potionHealingSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, Arrays.asList(potionHarmingSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, Arrays.asList(potionPoisonSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, Arrays.asList(potionHarmingEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionPoisonOutput, Arrays.asList(potionAwkward, reagentSpiderEye));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashOutput, Arrays.asList(potionPoison, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionPoisonExtendedOutput, Arrays.asList(potionPoisonExtended, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonExtendedOutput, Arrays.asList(potionPoisonEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, Arrays.asList(potionPoisonSplashExtended, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, Arrays.asList(potionPoisonSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, Arrays.asList(potionPoisonExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionPoisonEnhancedOutput, Arrays.asList(potionPoison, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonEnhancedOutput, Arrays.asList(potionPoisonExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, Arrays.asList(potionPoisonSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, Arrays.asList(potionPoisonSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, Arrays.asList(potionPoisonEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionRegenerationOutput, Arrays.asList(potionAwkward, reagentGhastTear));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashOutput, Arrays.asList(potionRegeneration, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionRegenerationExtendedOutput, Arrays.asList(potionRegeneration, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationExtendedOutput, Arrays.asList(potionRegenerationEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, Arrays.asList(potionRegenerationSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, Arrays.asList(potionRegenerationSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, Arrays.asList(potionRegenerationExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionRegenerationEnhancedOutput, Arrays.asList(potionRegeneration, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationEnhancedOutput, Arrays.asList(potionRegenerationExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, Arrays.asList(potionRegenerationSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, Arrays.asList(potionRegenerationSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, Arrays.asList(potionRegenerationEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, Arrays.asList(potionAwkward, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, Arrays.asList(potionRegeneration, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, Arrays.asList(potionRegenerationEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, Arrays.asList(potionStrength, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, Arrays.asList(potionStrengthEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, Arrays.asList(potionMundane, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, Arrays.asList(potionWeaknessExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, Arrays.asList(potionRegenerationSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, Arrays.asList(potionRegenerationSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, Arrays.asList(potionStrengthSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, Arrays.asList(potionStrengthSplashEnhanced, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, Arrays.asList(potionMundaneSplash, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, Arrays.asList(potionWeaknessSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, Arrays.asList(potionWeakness, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, Arrays.asList(potionWeakness, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, Arrays.asList(potionRegenerationExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, Arrays.asList(potionStrengthExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, Arrays.asList(potionMundaneExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, Arrays.asList(potionWeaknessSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, Arrays.asList(potionRegenerationSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, Arrays.asList(potionStrengthSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, Arrays.asList(potionMundaneSplashExtended, reagentFermentedSpiderEye));
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, Arrays.asList(potionWeaknessExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionStrengthOutput, Arrays.asList(potionAwkward, reagentBlazePowder));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashOutput, Arrays.asList(potionStrength, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionStrengthEnhancedOutput, Arrays.asList(potionStrength, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthEnhancedOutput, Arrays.asList(potionStrengthExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, Arrays.asList(potionStrengthSplash, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, Arrays.asList(potionStrengthSplashExtended, reagentGlowstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, Arrays.asList(potionStrengthEnhanced, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionStrengthExtendedOutput, Arrays.asList(potionStrength, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthExtendedOutput, Arrays.asList(potionStrengthEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, Arrays.asList(potionStrengthSplash, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, Arrays.asList(potionStrengthSplashEnhanced, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, Arrays.asList(potionStrengthExtended, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionThickOutput, Arrays.asList(bottleWater, reagentGlowstoneDust));

        RecipeRegistryProxy.addRecipe(potionMundaneOutput, Arrays.asList(bottleWater, reagentSugar));
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, Arrays.asList(bottleWater, reagentGlisteringMelon));
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, Arrays.asList(bottleWater, reagentSpiderEye));
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, Arrays.asList(bottleWater, reagentBlazePowder));
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, Arrays.asList(bottleWater, reagentMagmaCream));
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, Arrays.asList(bottleWater, reagentGhastTear));
        RecipeRegistryProxy.addRecipe(potionMundaneSplashOutput, Arrays.asList(potionMundane, reagentGunpowder));

        RecipeRegistryProxy.addRecipe(potionMundaneExtendedOutput, Arrays.asList(bottleWater, reagentRedstoneDust));
        RecipeRegistryProxy.addRecipe(potionMundaneSplashExtendedOutput, Arrays.asList(potionMundaneExtended, reagentGunpowder));
    }
}
