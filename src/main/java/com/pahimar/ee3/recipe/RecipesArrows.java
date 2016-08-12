package com.pahimar.ee3.recipe;

import com.pahimar.ee3.api.recipe.RecipeRegistryProxy;
import com.pahimar.ee3.util.ItemStackUtils;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class RecipesArrows {

    private static final ItemStack ARROWS = new ItemStack(Items.ARROW, 8);
    public static final ItemStack ARROW_UNCRAFTABLE = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.EMPTY);
    public static final ItemStack ARROW_SPLASHING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.WATER);
    public static final ItemStack ARROW_MUNDANE = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.MUNDANE);
    public static final ItemStack ARROW_THICK = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.THICK);
    public static final ItemStack ARROW_AWKWARD = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.AWKWARD);
    public static final ItemStack ARROW_NIGHT_VISION = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.NIGHT_VISION);
    public static final ItemStack ARROW_NIGHT_VISION_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_NIGHT_VISION);
    public static final ItemStack ARROW_INVISIBILITY = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.INVISIBILITY);
    public static final ItemStack ARROW_INVISIBILITY_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_INVISIBILITY);
    public static final ItemStack ARROW_LEAPING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LEAPING);
    public static final ItemStack ARROW_LEAPING_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_LEAPING);
    public static final ItemStack ARROW_LEAPING_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_LEAPING);
    public static final ItemStack ARROW_FIRE_RESISTANCE = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.FIRE_RESISTANCE);
    public static final ItemStack ARROW_FIRE_RESISTANCE_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_FIRE_RESISTANCE);
    public static final ItemStack ARROW_SWIFTNESS = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.SWIFTNESS);
    public static final ItemStack ARROW_SWIFTNESS_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_SWIFTNESS);
    public static final ItemStack ARROW_SWIFTNESS_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_SWIFTNESS);
    public static final ItemStack ARROW_SLOWNESS = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.SLOWNESS);
    public static final ItemStack ARROW_SLOWNESS_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_SLOWNESS);
    public static final ItemStack ARROW_WATER_BREATHING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.WATER_BREATHING);
    public static final ItemStack ARROW_WATER_BREATHING_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_WATER_BREATHING);
    public static final ItemStack ARROW_HEALING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.HEALING);
    public static final ItemStack ARROW_HEALING_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_HEALING);
    public static final ItemStack ARROW_HARMING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.HARMING);
    public static final ItemStack ARROW_HARMING_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_HARMING);
    public static final ItemStack ARROW_POISON = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.POISON);
    public static final ItemStack ARROW_POISON_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_POISON);
    public static final ItemStack ARROW_POISON_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_POISON);
    public static final ItemStack ARROW_REGENERATION = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.REGENERATION);
    public static final ItemStack ARROW_REGENERATION_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_REGENERATION);
    public static final ItemStack ARROW_REGENERATION_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_REGENERATION);
    public static final ItemStack ARROW_STRENGTH = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRENGTH);
    public static final ItemStack ARROW_STRENGTH_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_STRENGTH);
    public static final ItemStack ARROW_STRENGTH_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_STRENGTH);
    public static final ItemStack ARROW_WEAKNESS = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.WEAKNESS);
    public static final ItemStack ARROW_WEAKNESS_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_WEAKNESS);
    public static final ItemStack ARROW_LUCK = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), new PotionType("luck", new PotionEffect(MobEffects.LUCK, 6000)));

    public static void registerRecipes() {

        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_UNCRAFTABLE, 8), RecipesPotions.LINGERING_POTION_UNCRAFTABLE, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_SPLASHING, 8), RecipesPotions.LINGERING_BOTTLE_WATER, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_MUNDANE, 8), RecipesPotions.LINGERING_POTION_MUNDANE, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_THICK, 8), RecipesPotions.LINGERING_POTION_THICK, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_AWKWARD, 8), RecipesPotions.LINGERING_POTION_AWKWARD, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_NIGHT_VISION, 8), RecipesPotions.LINGERING_POTION_NIGHT_VISION, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_NIGHT_VISION_LONG, 8), RecipesPotions.LINGERING_POTION_NIGHT_VISION_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_INVISIBILITY, 8), RecipesPotions.LINGERING_POTION_INVISIBILITY, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_INVISIBILITY_LONG, 8), RecipesPotions.LINGERING_POTION_INVISIBILITY_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_LEAPING, 8), RecipesPotions.LINGERING_POTION_LEAPING, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_LEAPING_LONG, 8), RecipesPotions.LINGERING_POTION_LEAPING_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_LEAPING_STRONG, 8), RecipesPotions.LINGERING_POTION_LEAPING_STRONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_FIRE_RESISTANCE, 8), RecipesPotions.LINGERING_POTION_FIRE_RESISTANCE, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_FIRE_RESISTANCE_LONG, 8), RecipesPotions.LINGERING_POTION_FIRE_RESISTANCE_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_SWIFTNESS, 8), RecipesPotions.LINGERING_POTION_SWIFTNESS, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_SWIFTNESS_LONG, 8), RecipesPotions.LINGERING_POTION_SWIFTNESS_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_SWIFTNESS_STRONG, 8), RecipesPotions.LINGERING_POTION_SWIFTNESS_STRONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_SLOWNESS, 8), RecipesPotions.LINGERING_POTION_SLOWNESS, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_SLOWNESS_LONG, 8), RecipesPotions.LINGERING_POTION_SLOWNESS_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_WATER_BREATHING, 8), RecipesPotions.LINGERING_POTION_WATER_BREATHING, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_WATER_BREATHING_LONG, 8), RecipesPotions.LINGERING_POTION_WATER_BREATHING_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_HEALING, 8), RecipesPotions.LINGERING_POTION_HEALING, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_HEALING_STRONG, 8), RecipesPotions.LINGERING_POTION_HEALING_STRONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_HARMING, 8), RecipesPotions.LINGERING_POTION_HARMING, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_HARMING_STRONG, 8), RecipesPotions.LINGERING_POTION_HARMING_STRONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_POISON, 8), RecipesPotions.LINGERING_POTION_POISON, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_POISON_LONG, 8), RecipesPotions.LINGERING_POTION_POISON_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_POISON_STRONG, 8), RecipesPotions.LINGERING_POTION_POISON_STRONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_REGENERATION, 8), RecipesPotions.LINGERING_POTION_REGENERATION, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_REGENERATION_LONG, 8), RecipesPotions.LINGERING_POTION_REGENERATION_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_REGENERATION_STRONG, 8), RecipesPotions.LINGERING_POTION_REGENERATION_STRONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_STRENGTH, 8), RecipesPotions.LINGERING_POTION_STRENGTH, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_STRENGTH_LONG, 8), RecipesPotions.LINGERING_POTION_STRENGTH_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_STRENGTH_STRONG, 8), RecipesPotions.LINGERING_POTION_STRENGTH_STRONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_WEAKNESS, 8), RecipesPotions.LINGERING_POTION_WEAKNESS, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_WEAKNESS_LONG, 8), RecipesPotions.LINGERING_POTION_WEAKNESS_LONG, ARROWS);
        RecipeRegistryProxy.addRecipe(ItemStackUtils.clone(ARROW_LUCK, 8), RecipesPotions.LINGERING_POTION_LUCK, ARROWS);
    }
}
