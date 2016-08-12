package com.pahimar.ee3.recipe;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

public class RecipesArrows {

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

    }
}
