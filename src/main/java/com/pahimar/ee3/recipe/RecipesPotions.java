package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

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

    public void registerRecipes() {

        RecipeRegistryProxy.addRecipe(bottleWater, bottleEmpty, reagentWater);

        RecipeRegistryProxy.addRecipe(potionAwkwardOutput, bottleWater, reagentNetherWart);

        RecipeRegistryProxy.addRecipe(potionNightVisionOutput, potionAwkward, reagentGoldenCarrot);
        RecipeRegistryProxy.addRecipe(potionNightVisionOutput, potionNightVisionExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashOutput, potionNightVisionSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashOutput, potionNightVision, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionNightVisionExtendedOutput, potionNightVision, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtendedOutput, potionNightVisionSplash, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtendedOutput, potionNightVisionExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionInvisibilityOutput, potionNightVision, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionInvisibilityOutput, potionInvisibilityExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, potionNightVisionSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, potionInvisibilitySplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, potionInvisibility, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionInvisibilityExtendedOutput, potionInvisibility, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionInvisibilityExtendedOutput, potionNightVisionExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, potionInvisibilitySplash, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, potionNightVisionSplashExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, potionInvisibilityExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionFireResistOutput, potionAwkward, reagentMagmaCream);
        RecipeRegistryProxy.addRecipe(potionFireResistOutput, potionFireResistExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashOutput, potionFireResistSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashOutput, potionFireResist, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionFireResistExtendedOutput, potionFireResist, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtendedOutput, potionFireResistSplash, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtendedOutput, potionFireResistExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionFireResist, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionSlownessExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionSwiftness, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionSwiftnessExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionFireResistSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSlownessSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSwiftnessSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSwiftnessSplashExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSlowness, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionSlownessExtendedOutput, potionFireResistExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessExtendedOutput, potionSwiftnessEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, potionFireResistSplashExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, potionSwiftnessSplashEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, potionSlownessExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionSwiftnessOutput, potionAwkward, reagentSugar);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashOutput, potionSwiftness, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionSwiftnessExtendedOutput, potionSwiftness, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessExtendedOutput, potionSwiftnessEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, potionSwiftnessSplash, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, potionSwiftnessSplashEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, potionSwiftnessExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhancedOutput, potionSwiftness, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhancedOutput, potionSwiftnessExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, potionSwiftnessSplash, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, potionSwiftnessSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, potionSwiftnessEnhanced, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionHealingOutput, potionAwkward, reagentGlisteringMelon);
        RecipeRegistryProxy.addRecipe(potionHealingOutput, potionHealingEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionHealingSplashOutput, potionHealingSplashEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionHealingSplashOutput, potionHealing, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionHealingEnhancedOutput, potionHealing, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhancedOutput, potionHealingSplash, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhancedOutput, potionHealingEnhanced, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionHealing, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionPoison, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionPoisonExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionHarmingEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionHealingSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionPoisonSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionPoisonSplashExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionHarmingSplashEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionHarming, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, potionHealingEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, potionHarming, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, potionPoisonEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionHealingSplashEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionHarmingSplash, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionPoisonSplashEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionHarmingEnhanced, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionPoisonOutput, potionAwkward, reagentSpiderEye);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashOutput, potionPoison, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionPoisonExtendedOutput, potionPoisonExtended, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonExtendedOutput, potionPoisonEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, potionPoisonSplashExtended, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, potionPoisonSplashEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, potionPoisonExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionPoisonEnhancedOutput, potionPoison, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonEnhancedOutput, potionPoisonExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, potionPoisonSplash, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, potionPoisonSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, potionPoisonEnhanced, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionRegenerationOutput, potionAwkward, reagentGhastTear);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashOutput, potionRegeneration, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionRegenerationExtendedOutput, potionRegeneration, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationExtendedOutput, potionRegenerationEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, potionRegenerationSplash, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, potionRegenerationSplashEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, potionRegenerationExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionRegenerationEnhancedOutput, potionRegeneration, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationEnhancedOutput, potionRegenerationExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, potionRegenerationSplash, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, potionRegenerationSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, potionRegenerationEnhanced, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionAwkward, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionRegeneration, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionRegenerationEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionStrength, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionStrengthEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionMundane, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionWeaknessExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionRegenerationSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionRegenerationSplashEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionStrengthSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionStrengthSplashEnhanced, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionMundaneSplash, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionWeaknessSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionWeakness, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionWeakness, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionRegenerationExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionStrengthExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionMundaneExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionWeaknessSplash, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionRegenerationSplashExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionStrengthSplashExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionMundaneSplashExtended, reagentFermentedSpiderEye);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionWeaknessExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionStrengthOutput, potionAwkward, reagentBlazePowder);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashOutput, potionStrength, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionStrengthEnhancedOutput, potionStrength, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthEnhancedOutput, potionStrengthExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, potionStrengthSplash, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, potionStrengthSplashExtended, reagentGlowstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, potionStrengthEnhanced, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionStrengthExtendedOutput, potionStrength, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthExtendedOutput, potionStrengthEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, potionStrengthSplash, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, potionStrengthSplashEnhanced, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, potionStrengthExtended, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionThickOutput, bottleWater, reagentGlowstoneDust);

        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, reagentSugar);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, reagentGlisteringMelon);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, reagentSpiderEye);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, reagentBlazePowder);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, reagentMagmaCream);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, reagentGhastTear);
        RecipeRegistryProxy.addRecipe(potionMundaneSplashOutput, potionMundane, reagentGunpowder);

        RecipeRegistryProxy.addRecipe(potionMundaneExtendedOutput, bottleWater, reagentRedstoneDust);
        RecipeRegistryProxy.addRecipe(potionMundaneSplashExtendedOutput, potionMundaneExtended, reagentGunpowder);
    }
}
