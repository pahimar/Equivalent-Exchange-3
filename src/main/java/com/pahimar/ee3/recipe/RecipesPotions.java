package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.WrappedStack;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * TODO I don't think Potions work this way anymore. See {@link net.minecraft.potion.PotionUtils}
 */
public class RecipesPotions {

    private static final ItemStack GLASS_BOTTLE = new ItemStack(Items.GLASS_BOTTLE);
    private static final ItemStack UNCRAFTABLE_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.EMPTY);
    private static final ItemStack WATER_BOTTLE = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER);
    private static final ItemStack MUNDANE_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.MUNDANE);
    private static final ItemStack THICK_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.THICK);
    private static final ItemStack AWKWARD_POTION = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.AWKWARD);

    private static final WrappedStack REAGENT_WATER = WrappedStack.build(FluidRegistry.WATER);
    private static final ItemStack REAGENT_NETHER_WART = new ItemStack(Items.NETHER_WART);
    private static final ItemStack REAGENT_GLOWSTONE_DUST = new ItemStack(Items.GLOWSTONE_DUST);
    private static final ItemStack REAGENT_REDSTONE_DUST = new ItemStack(Items.REDSTONE);
    private static final ItemStack REAGENT_GUNPOWDER = new ItemStack(Items.GUNPOWDER);
    private static final ItemStack REAGENT_GOLDEN_CARROT = new ItemStack(Items.GOLDEN_CARROT);
    private static final ItemStack REAGENT_MAGMA_CREAM = new ItemStack(Items.MAGMA_CREAM);
    private static final ItemStack REAGENT_SUGAR = new ItemStack(Items.SUGAR);
    private static final ItemStack REAGENT_GLISTERING_MELON = new ItemStack(Items.SPECKLED_MELON);
    private static final ItemStack REAGENT_SPIDER_EYE = new ItemStack(Items.SPIDER_EYE);
    private static final ItemStack REAGENT_GHAST_TEAR = new ItemStack(Items.GHAST_TEAR);
    private static final ItemStack REAGENT_FERMENTED_SPIDER_EYE = new ItemStack(Items.FERMENTED_SPIDER_EYE);
    private static final ItemStack REAGENT_BLAZE_POWDER = new ItemStack(Items.BLAZE_POWDER);
    private static final ItemStack REAGENT_RABBITS_FOOT = new ItemStack(Items.RABBIT_FOOT);
    private static final ItemStack REAGENT_DRAGONS_BREATH = new ItemStack(Items.DRAGON_BREATH);

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

        RecipeRegistryProxy.addRecipe(bottleWater, bottleEmpty, REAGENT_WATER);

        RecipeRegistryProxy.addRecipe(potionAwkwardOutput, bottleWater, REAGENT_NETHER_WART);

        RecipeRegistryProxy.addRecipe(potionNightVisionOutput, potionAwkward, REAGENT_GOLDEN_CARROT);
        RecipeRegistryProxy.addRecipe(potionNightVisionOutput, potionNightVisionExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashOutput, potionNightVisionSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashOutput, potionNightVision, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionNightVisionExtendedOutput, potionNightVision, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtendedOutput, potionNightVisionSplash, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionNightVisionSplashExtendedOutput, potionNightVisionExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionInvisibilityOutput, potionNightVision, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionInvisibilityOutput, potionInvisibilityExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, potionNightVisionSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, potionInvisibilitySplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashOutput, potionInvisibility, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionInvisibilityExtendedOutput, potionInvisibility, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionInvisibilityExtendedOutput, potionNightVisionExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, potionInvisibilitySplash, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, potionNightVisionSplashExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionInvisibilitySplashExtendedOutput, potionInvisibilityExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionFireResistOutput, potionAwkward, REAGENT_MAGMA_CREAM);
        RecipeRegistryProxy.addRecipe(potionFireResistOutput, potionFireResistExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashOutput, potionFireResistSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashOutput, potionFireResist, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionFireResistExtendedOutput, potionFireResist, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtendedOutput, potionFireResistSplash, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionFireResistSplashExtendedOutput, potionFireResistExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionFireResist, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionSlownessExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionSwiftness, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessOutput, potionSwiftnessExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionFireResistSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSlownessSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSwiftnessSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSwiftnessSplashExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashOutput, potionSlowness, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionSlownessExtendedOutput, potionFireResistExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessExtendedOutput, potionSwiftnessEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, potionFireResistSplashExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, potionSwiftnessSplashEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionSlownessSplashExtendedOutput, potionSlownessExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionSwiftnessOutput, potionAwkward, REAGENT_SUGAR);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashOutput, potionSwiftness, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionSwiftnessExtendedOutput, potionSwiftness, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessExtendedOutput, potionSwiftnessEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, potionSwiftnessSplash, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, potionSwiftnessSplashEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashExtendedOutput, potionSwiftnessExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhancedOutput, potionSwiftness, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessEnhancedOutput, potionSwiftnessExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, potionSwiftnessSplash, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, potionSwiftnessSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionSwiftnessSplashEnhancedOutput, potionSwiftnessEnhanced, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionHealingOutput, potionAwkward, REAGENT_GLISTERING_MELON);
        RecipeRegistryProxy.addRecipe(potionHealingOutput, potionHealingEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHealingSplashOutput, potionHealingSplashEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHealingSplashOutput, potionHealing, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionHealingEnhancedOutput, potionHealing, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhancedOutput, potionHealingSplash, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHealingSplashEnhancedOutput, potionHealingEnhanced, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionHealing, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionPoison, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionPoisonExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingOutput, potionHarmingEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionHealingSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionPoisonSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionPoisonSplashExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionHarmingSplashEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashOutput, potionHarming, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, potionHealingEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, potionHarming, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHarmingEnhancedOutput, potionPoisonEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionHealingSplashEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionHarmingSplash, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionPoisonSplashEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionHarmingSplashEnhancedOutput, potionHarmingEnhanced, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionPoisonOutput, potionAwkward, REAGENT_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashOutput, potionPoison, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionPoisonExtendedOutput, potionPoisonExtended, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonExtendedOutput, potionPoisonEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, potionPoisonSplashExtended, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, potionPoisonSplashEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashExtendedOutput, potionPoisonExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionPoisonEnhancedOutput, potionPoison, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonEnhancedOutput, potionPoisonExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, potionPoisonSplash, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, potionPoisonSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionPoisonSplashEnhancedOutput, potionPoisonEnhanced, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionRegenerationOutput, potionAwkward, REAGENT_GHAST_TEAR);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashOutput, potionRegeneration, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionRegenerationExtendedOutput, potionRegeneration, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationExtendedOutput, potionRegenerationEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, potionRegenerationSplash, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, potionRegenerationSplashEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashExtendedOutput, potionRegenerationExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionRegenerationEnhancedOutput, potionRegeneration, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationEnhancedOutput, potionRegenerationExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, potionRegenerationSplash, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, potionRegenerationSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionRegenerationSplashEnhancedOutput, potionRegenerationEnhanced, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionAwkward, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionRegeneration, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionRegenerationEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionStrength, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionStrengthEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionMundane, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessOutput, potionWeaknessExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionRegenerationSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionRegenerationSplashEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionStrengthSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionStrengthSplashEnhanced, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionMundaneSplash, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionWeaknessSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashOutput, potionWeakness, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionWeakness, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionRegenerationExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionStrengthExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessExtendedOutput, potionMundaneExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionWeaknessSplash, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionRegenerationSplashExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionStrengthSplashExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionMundaneSplashExtended, REAGENT_FERMENTED_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionWeaknessSplashExtendedOutput, potionWeaknessExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionStrengthOutput, potionAwkward, REAGENT_BLAZE_POWDER);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashOutput, potionStrength, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionStrengthEnhancedOutput, potionStrength, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthEnhancedOutput, potionStrengthExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, potionStrengthSplash, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, potionStrengthSplashExtended, REAGENT_GLOWSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashEnhancedOutput, potionStrengthEnhanced, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionStrengthExtendedOutput, potionStrength, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthExtendedOutput, potionStrengthEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, potionStrengthSplash, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, potionStrengthSplashEnhanced, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionStrengthSplashExtendedOutput, potionStrengthExtended, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionThickOutput, bottleWater, REAGENT_GLOWSTONE_DUST);

        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, REAGENT_SUGAR);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, REAGENT_GLISTERING_MELON);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, REAGENT_SPIDER_EYE);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, REAGENT_BLAZE_POWDER);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, REAGENT_MAGMA_CREAM);
        RecipeRegistryProxy.addRecipe(potionMundaneOutput, bottleWater, REAGENT_GHAST_TEAR);
        RecipeRegistryProxy.addRecipe(potionMundaneSplashOutput, potionMundane, REAGENT_GUNPOWDER);

        RecipeRegistryProxy.addRecipe(potionMundaneExtendedOutput, bottleWater, REAGENT_REDSTONE_DUST);
        RecipeRegistryProxy.addRecipe(potionMundaneSplashExtendedOutput, potionMundaneExtended, REAGENT_GUNPOWDER);
    }
}
