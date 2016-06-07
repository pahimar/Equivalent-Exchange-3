package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * TODO I don't think Potions work this way anymore. See {@link net.minecraft.potion.PotionUtils}
 */
public class RecipesPotions {

    private static final WrappedStack reagentWater = WrappedStack.build(new ItemStack(Blocks.WATER));
    private static final WrappedStack reagentNetherWart = WrappedStack.build(new ItemStack(Items.NETHER_WART));
    private static final WrappedStack reagentGlowstoneDust = WrappedStack.build(new ItemStack(Items.GLOWSTONE_DUST));
    private static final WrappedStack reagentRedstoneDust = WrappedStack.build(new ItemStack(Items.REDSTONE));
    private static final WrappedStack reagentGunpowder = WrappedStack.build(new ItemStack(Items.GUNPOWDER));
    private static final WrappedStack reagentGoldenCarrot = WrappedStack.build(new ItemStack(Items.GOLDEN_CARROT));
    private static final WrappedStack reagentMagmaCream = WrappedStack.build(new ItemStack(Items.MAGMA_CREAM));
    private static final WrappedStack reagentSugar = WrappedStack.build(new ItemStack(Items.SUGAR));
    private static final WrappedStack reagentGlisteringMelon = WrappedStack.build(new ItemStack(Items.SPECKLED_MELON));
    private static final WrappedStack reagentSpiderEye = WrappedStack.build(new ItemStack(Items.SPIDER_EYE));
    private static final WrappedStack reagentGhastTear = WrappedStack.build(new ItemStack(Items.GHAST_TEAR));
    private static final WrappedStack reagentFermentedSpiderEye = WrappedStack.build(new ItemStack(Items.FERMENTED_SPIDER_EYE));
    private static final WrappedStack reagentBlazePowder = WrappedStack.build(new ItemStack(Items.BLAZE_POWDER));

    private static final WrappedStack bottleEmpty = WrappedStack.build(Items.GLASS_BOTTLE);
    private static final WrappedStack bottleWater = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 0));

    private static final WrappedStack potionAwkward = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16));
    private static final WrappedStack potionAwkwardOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16));
    private static final WrappedStack potionThickOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 32));
    private static final WrappedStack potionMundane = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 128));
    private static final WrappedStack potionMundaneOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 128));
    private static final WrappedStack potionMundaneExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 64));
    private static final WrappedStack potionMundaneExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 64));
    private static final WrappedStack potionMundaneSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16512));
    private static final WrappedStack potionMundaneSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16512));
    private static final WrappedStack potionMundaneSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16448));
    private static final WrappedStack potionMundaneSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16448));

    private static final WrappedStack potionRegeneration = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8193));
    private static final WrappedStack potionRegenerationOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8193));
    private static final WrappedStack potionRegenerationEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8225));
    private static final WrappedStack potionRegenerationEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8225));
    private static final WrappedStack potionRegenerationExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8257));
    private static final WrappedStack potionRegenerationExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8257));
    private static final WrappedStack potionRegenerationSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16385));
    private static final WrappedStack potionRegenerationSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16385));
    private static final WrappedStack potionRegenerationSplashEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16417));
    private static final WrappedStack potionRegenerationSplashEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16417));
    private static final WrappedStack potionRegenerationSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16449));
    private static final WrappedStack potionRegenerationSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16449));

    private static final WrappedStack potionSwiftness = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8194));
    private static final WrappedStack potionSwiftnessOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8194));
    private static final WrappedStack potionSwiftnessEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8226));
    private static final WrappedStack potionSwiftnessEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8226));
    private static final WrappedStack potionSwiftnessExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8258));
    private static final WrappedStack potionSwiftnessExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8258));
    private static final WrappedStack potionSwiftnessSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16386));
    private static final WrappedStack potionSwiftnessSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16386));
    private static final WrappedStack potionSwiftnessSplashEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16418));
    private static final WrappedStack potionSwiftnessSplashEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16418));
    private static final WrappedStack potionSwiftnessSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16450));
    private static final WrappedStack potionSwiftnessSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16450));

    private static final WrappedStack potionFireResist = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8195));
    private static final WrappedStack potionFireResistOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8195));
    private static final WrappedStack potionFireResistExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8259));
    private static final WrappedStack potionFireResistExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8259));
    private static final WrappedStack potionFireResistSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16387));
    private static final WrappedStack potionFireResistSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16387));
    private static final WrappedStack potionFireResistSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16451));
    private static final WrappedStack potionFireResistSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16451));

    private static final WrappedStack potionPoison = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8196));
    private static final WrappedStack potionPoisonOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8196));
    private static final WrappedStack potionPoisonEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8228));
    private static final WrappedStack potionPoisonEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8228));
    private static final WrappedStack potionPoisonExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8260));
    private static final WrappedStack potionPoisonExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8260));
    private static final WrappedStack potionPoisonSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16388));
    private static final WrappedStack potionPoisonSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16388));
    private static final WrappedStack potionPoisonSplashEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16420));
    private static final WrappedStack potionPoisonSplashEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16420));
    private static final WrappedStack potionPoisonSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16452));
    private static final WrappedStack potionPoisonSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16452));

    private static final WrappedStack potionHealing = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8197));
    private static final WrappedStack potionHealingOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8197));
    private static final WrappedStack potionHealingEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8229));
    private static final WrappedStack potionHealingEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8229));
    private static final WrappedStack potionHealingSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16389));
    private static final WrappedStack potionHealingSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16389));
    private static final WrappedStack potionHealingSplashEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16421));
    private static final WrappedStack potionHealingSplashEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16421));

    private static final WrappedStack potionNightVision = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8198));
    private static final WrappedStack potionNightVisionOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8198));
    private static final WrappedStack potionNightVisionExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8262));
    private static final WrappedStack potionNightVisionExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8262));
    private static final WrappedStack potionNightVisionSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16390));
    private static final WrappedStack potionNightVisionSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16390));
    private static final WrappedStack potionNightVisionSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16454));
    private static final WrappedStack potionNightVisionSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16454));

    private static final WrappedStack potionWeakness = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8200));
    private static final WrappedStack potionWeaknessOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8200));
    private static final WrappedStack potionWeaknessExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8264));
    private static final WrappedStack potionWeaknessExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8264));
    private static final WrappedStack potionWeaknessSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16392));
    private static final WrappedStack potionWeaknessSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16392));
    private static final WrappedStack potionWeaknessSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16456));
    private static final WrappedStack potionWeaknessSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16456));

    private static final WrappedStack potionStrength = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8201));
    private static final WrappedStack potionStrengthOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8201));
    private static final WrappedStack potionStrengthEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8233));
    private static final WrappedStack potionStrengthEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8233));
    private static final WrappedStack potionStrengthExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8265));
    private static final WrappedStack potionStrengthExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8265));
    private static final WrappedStack potionStrengthSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16393));
    private static final WrappedStack potionStrengthSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16393));
    private static final WrappedStack potionStrengthSplashEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16425));
    private static final WrappedStack potionStrengthSplashEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16425));
    private static final WrappedStack potionStrengthSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16457));
    private static final WrappedStack potionStrengthSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16457));

    private static final WrappedStack potionSlowness = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8202));
    private static final WrappedStack potionSlownessOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8202));
    private static final WrappedStack potionSlownessExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8266));
    private static final WrappedStack potionSlownessExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8266));
    private static final WrappedStack potionSlownessSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16394));
    private static final WrappedStack potionSlownessSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16394));
    private static final WrappedStack potionSlownessSplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16458));
    private static final WrappedStack potionSlownessSplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16458));

    private static final WrappedStack potionHarming = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8204));
    private static final WrappedStack potionHarmingOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8204));
    private static final WrappedStack potionHarmingEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8236));
    private static final WrappedStack potionHarmingEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8236));
    private static final WrappedStack potionHarmingSplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16396));
    private static final WrappedStack potionHarmingSplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16396));
    private static final WrappedStack potionHarmingSplashEnhanced = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16428));
    private static final WrappedStack potionHarmingSplashEnhancedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16428));

    private static final WrappedStack potionInvisibility = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8206));
    private static final WrappedStack potionInvisibilityOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8206));
    private static final WrappedStack potionInvisibilityExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 8270));
    private static final WrappedStack potionInvisibilityExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 8270));
    private static final WrappedStack potionInvisibilitySplash = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16398));
    private static final WrappedStack potionInvisibilitySplashOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16398));
    private static final WrappedStack potionInvisibilitySplashExtended = WrappedStack.build(new ItemStack(Items.POTIONITEM, 1, 16462));
    private static final WrappedStack potionInvisibilitySplashExtendedOutput = WrappedStack.build(new ItemStack(Items.POTIONITEM, 3, 16462));

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
