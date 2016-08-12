package com.pahimar.ee3.test;

import com.pahimar.ee3.reference.Files;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraftforge.oredict.OreDictionary;

import java.io.File;

import static com.pahimar.ee3.recipe.RecipesPotions.*;

public class VanillaTestSuite extends EnergyValueTestSuite {

    public VanillaTestSuite() {
        super();
    }

    public VanillaTestSuite build() {

        addBuildingBlocksTabTestCases();
        addDecorationBlocksTabTestCases();
        addRedstoneTabTestCases();
        addTransportationTabTestCases();
        addMiscellaneousTabTestCases();
        addFoodstuffsTabTestCases();
        addToolsTabTestCases();
        addCombatTabTestCases();
        addBrewingTabTestCases();
        addMaterialsTabTestCases();

        return this;
    }

    private void addBuildingBlocksTabTestCases() {

        add(Blocks.STONE, 1);                                           //  SMOOTH STONE
        // TODO GRANITE
        // TODO POLISHED GRANITE
        // TODO DIORITE
        // TODO POLISH DIORITE
        // TODO ANDESITE
        // TODO POLISHED ANDESITE
        add(Blocks.GRASS, 1);                                           // GRASS BLOCK
        add(Blocks.DIRT, 1);                                            // DIRT
        add(new ItemStack(Blocks.DIRT, 1, 1), 1);                       // COARSE DIRT
        add(new ItemStack(Blocks.DIRT, 1, 2), 1);                       // PODZOL
        add(Blocks.COBBLESTONE, 1);                                     // COBBLESTONE
        add(new ItemStack(Blocks.PLANKS, 1, 0), 8);                     // OAK WOOD PLANKS
        add(new ItemStack(Blocks.PLANKS, 1, 1), 8);                     // SPRUCE WOOD PLANKS
        add(new ItemStack(Blocks.PLANKS, 1, 2), 8);                     // BIRCH WOOD PLANKS
        add(new ItemStack(Blocks.PLANKS, 1, 3), 8);                     // JUNGLE WOOD PLANKS
        add(new ItemStack(Blocks.PLANKS, 1, 4), 8);                     // ACACIA WOOD PLANKS
        add(new ItemStack(Blocks.PLANKS, 1, 5), 8);                     // DARK OAK WOOD PLANKS
        add(Blocks.BEDROCK, null);                                      // BEDROCK
        add(Blocks.SAND, 1);                                            // SAND
        add(new ItemStack(Blocks.SAND, 1, 1), 1);                       // RED SAND
        add(Blocks.GRAVEL, 4);                                          // GRAVEL
        add(Blocks.GOLD_ORE, 2048);                                     // GOLD ORE
        add(Blocks.IRON_ORE, 256);                                      // IRON ORE
        add(Blocks.COAL_ORE, 32);                                       // COAL ORE
        add(new ItemStack(Blocks.LOG, 1, 0), 32);                       // OAK WOOD
        add(new ItemStack(Blocks.LOG, 1, 1), 32);                       // SPRUCE WOOD
        add(new ItemStack(Blocks.LOG, 1, 2), 32);                       // BIRCH WOOD
        add(new ItemStack(Blocks.LOG, 1, 3), 32);                       // JUNGLE WOOD
        add(Blocks.SPONGE, null);                                       // SPONGE
        add(new ItemStack(Blocks.SPONGE, 1, 1), null);                  // WET SPONGE
        add(Blocks.GLASS, 1);                                           // GLASS
        add(Blocks.LAPIS_ORE, 864);                                     // LAPIS LAZULI ORE
        add(Blocks.LAPIS_BLOCK, 9 * 864);                               // LAPIS LAZULI BLOCK
        add(new ItemStack(Blocks.SANDSTONE, 1, 0), 4);                  // SANDSTONE
        add(new ItemStack(Blocks.SANDSTONE, 1, 1), 4);                  // CHISELED SANDSTONE
        add(new ItemStack(Blocks.SANDSTONE, 1, 2), 4);                  // SMOOTH SANDSTONE
        add(new ItemStack(Blocks.WOOL, 1, 0), 48);                      // WOOL (WHITE)
        add(new ItemStack(Blocks.WOOL, 1, 1), 64);                      // WOOL (ORANGE)
        add(new ItemStack(Blocks.WOOL, 1, 2), 64);                      // WOOL (MAGENTA)
        add(new ItemStack(Blocks.WOOL, 1, 3), 64);                      // WOOL (LIGHT BLUE)
        add(new ItemStack(Blocks.WOOL, 1, 4), 64);                      // WOOL (YELLOW)
        add(new ItemStack(Blocks.WOOL, 1, 5), 64);                      // WOOL (LIME)
        add(new ItemStack(Blocks.WOOL, 1, 6), 64);                      // WOOL (PINK)
        add(new ItemStack(Blocks.WOOL, 1, 7), 64);                      // WOOL (GRAY)
        add(new ItemStack(Blocks.WOOL, 1, 8), 64);                      // WOOL (LIGHT GRAY)
        add(new ItemStack(Blocks.WOOL, 1, 9), 64);                      // WOOL (CYAN)
        add(new ItemStack(Blocks.WOOL, 1, 10), 64);                     // WOOL (PURPLE)
        add(new ItemStack(Blocks.WOOL, 1, 11), 64);                     // WOOL (BLUE)
        add(new ItemStack(Blocks.WOOL, 1, 12), 64);                     // WOOL (BROWN)
        add(new ItemStack(Blocks.WOOL, 1, 13), 64);                     // WOOL (GREEN)
        add(new ItemStack(Blocks.WOOL, 1, 14), 64);                     // WOOL (RED)
        add(new ItemStack(Blocks.WOOL, 1, 15), 64);                     // WOOL (BLACK)
        add(Blocks.GOLD_BLOCK, 9 * 2048);                               // BLOCK OF GOLD
        add(Blocks.IRON_BLOCK, 9 * 256);                                // BLOCK OF IRON
        add(new ItemStack(Blocks.STONE_SLAB, 1, 0), 0.5);               // STONE SLAB
        add(new ItemStack(Blocks.STONE_SLAB, 1, 1), 2);                 // SANDSTONE SLAB
        add(new ItemStack(Blocks.STONE_SLAB, 1, 3), 0.5);               // COBBLESTONE SLAB
        add(new ItemStack(Blocks.STONE_SLAB, 1, 4), 128);               // BRICKS SLAB
        add(new ItemStack(Blocks.STONE_SLAB, 1, 5), 0.5);               // STONE BRICKS SLAB
        add(new ItemStack(Blocks.STONE_SLAB, 1, 6), 2);                 // NETHER BRICK SLAB
        add(new ItemStack(Blocks.STONE_SLAB, 1, 7), 512);               // QUARTZ SLAB
        add(Blocks.BRICK_BLOCK, 256);                                   // BRICKS
        add(Blocks.BOOKSHELF, 528);                                     // BOOKSHELF
        add(Blocks.MOSSY_COBBLESTONE, 1);                               // MOSS STONE
        add(Blocks.OBSIDIAN, 64);                                       // OBSIDIAN
        add(Blocks.OAK_STAIRS, 12);                                     // OAK WOOD STAIRS
        add(Blocks.DIAMOND_ORE, 8192);                                  // DIAMOND ORE
        add(Blocks.DIAMOND_BLOCK, 9 * 8192);                            // BLOCK OF DIAMOND
        add(Blocks.STONE_STAIRS, 1.5);                                  // COBBLESTONE STAIRS
        add(Blocks.REDSTONE_ORE, 32);                                   // REDSTONE ORE
        add(Blocks.ICE, 1);                                             // ICE
        add(Blocks.SNOW, 1);                                            // SNOW (BLOCK)
        add(Blocks.CLAY, 256);                                          // CLAY
        add(Blocks.PUMPKIN, 144);                                       // PUMPKIN
        add(Blocks.NETHERRACK, 1);                                      // NETHERRACK
        add(Blocks.SOUL_SAND, 49);                                      // SOUL SAND
        add(Blocks.GLOWSTONE, 1536);                                    // GLOWSTONE
        add(Blocks.LIT_PUMPKIN, 153);                                   // JACK O'LANTERN
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 0), 3);              // STAINED GLASS (WHITE)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 1), 3);              // STAINED GLASS (ORANGE)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 2), 3);              // STAINED GLASS (MAGENTA)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 3), 3);              // STAINED GLASS (LIGHT BLUE)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 4), 3);              // STAINED GLASS (YELLOW)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 5), 3);              // STAINED GLASS (LIME)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 6), 3);              // STAINED GLASS (PINK)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 7), 3);              // STAINED GLASS (GRAY)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 8), 3);              // STAINED GLASS (LIGHT GRAY)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 9), 3);              // STAINED GLASS (CYAN)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 10), 3);             // STAINED GLASS (PURPLE)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 11), 3);             // STAINED GLASS (BLUE)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 12), 3);             // STAINED GLASS (BROWN)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 13), 3);             // STAINED GLASS (GREEN)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 14), 3);             // STAINED GLASS (RED)
        add(new ItemStack(Blocks.STAINED_GLASS, 1, 15), 3);             // STAINED GLASS (BLACK)
        add(new ItemStack(Blocks.STONEBRICK, 1, 0), 1);                 // STONE BRICKS
        add(new ItemStack(Blocks.STONEBRICK, 1, 1), 1);                 // MOSSY STONE BRICKS
        add(new ItemStack(Blocks.STONEBRICK, 1, 2), 1);                 // CRACKED STONE BRICKS
        add(new ItemStack(Blocks.STONEBRICK, 1, 3), 1);                 // CHISELED STONE BRICKS
        add(Blocks.MELON_BLOCK, 144);                                   // MELON
        add(Blocks.BRICK_STAIRS, 384);                                  // BRICK STAIRS
        add(Blocks.STONE_BRICK_STAIRS, 1.5);                            // STONE BRICK STAIRS
        add(Blocks.MYCELIUM, 1);                                        // MYCELIUM
        add(Blocks.NETHER_BRICK, 4);                                    // NETHER BRICK
        add(Blocks.NETHER_BRICK_STAIRS, 6);                             // NETHER BRICK STAIRS
        add(Blocks.END_STONE, 1);                                       // END STONE
        add(new ItemStack(Blocks.WOODEN_SLAB, 1, 0), 4);                // OAK WOOD SLAB
        add(new ItemStack(Blocks.WOODEN_SLAB, 1, 1), 4);                // SPRUCE WOOD SLAB
        add(new ItemStack(Blocks.WOODEN_SLAB, 1, 2), 4);                // BIRCH WOOD SLAB
        add(new ItemStack(Blocks.WOODEN_SLAB, 1, 3), 4);                // JUNGLE WOOD SLAB
        add(new ItemStack(Blocks.WOODEN_SLAB, 1, 4), 4);                // ACACIA WOOD SLAB
        add(new ItemStack(Blocks.WOODEN_SLAB, 1, 5), 4);                // DARK OAK WOOD SLAB
        add(Blocks.SANDSTONE_STAIRS, 6);                                // SANDSTONE STAIRS
        add(Blocks.EMERALD_ORE, 8192);                                  // EMERALD ORE
        add(Blocks.EMERALD_BLOCK, 9 * 8192);                            // BLOCK OF EMERALD
        add(Blocks.SPRUCE_STAIRS, 12);                                  // SPRUCE WOOD STAIRS
        add(Blocks.BIRCH_STAIRS, 12);                                   // BIRCH WOOD STAIRS
        add(Blocks.JUNGLE_STAIRS, 12);                                  // JUNGLE WOOD STAIRS
        add(new ItemStack(Blocks.COBBLESTONE_WALL, 1, 0), 1);           // COBBLESTONE WALL
        add(new ItemStack(Blocks.COBBLESTONE_WALL, 1, 1), 1);           // MOSSY COBBLESTONE WALL
        add(Blocks.QUARTZ_ORE, 256);                                    // NETHER QUARTZ ORE
        add(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 0), 1024);            // BLOCK OF QUARTZ
        add(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 1), 1024);            // CHISELED QUARTZ BLOCK
        add(new ItemStack(Blocks.QUARTZ_BLOCK, 1, 2), 1024);            // PILLAR QUARTZ BLOCK
        add(Blocks.QUARTZ_STAIRS, 1536);                                // QUARTZ STAIRS
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 0), 258);    // STAINED CLAY (WHITE)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 1), 258);    // STAINED CLAY (ORANGE)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 2), 258);    // STAINED CLAY (MAGENTA)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 3), 258);    // STAINED CLAY (LIGHT BLUE)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 4), 258);    // STAINED CLAY (YELLOW)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 5), 258);    // STAINED CLAY (LIME)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 6), 258);    // STAINED CLAY (PINK)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 7), 258);    // STAINED CLAY (GRAY)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 8), 258);    // STAINED CLAY (LIGHT GRAY)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 9), 258);    // STAINED CLAY (CYAN)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 10), 258);   // STAINED CLAY (PURPLE)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 11), 258);   // STAINED CLAY (BLUE)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 12), 258);   // STAINED CLAY (BROWN)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 13), 258);   // STAINED CLAY (GREEN)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 14), 258);   // STAINED CLAY (RED)
        add(new ItemStack(Blocks.STAINED_HARDENED_CLAY, 1, 15), 258);   // STAINED CLAY (BLACK)
        add(new ItemStack(Blocks.LOG2, 1, 0), 32);                      // ACACIA WOOD
        add(new ItemStack(Blocks.LOG2, 1, 1), 32);                      // DARK OAK WOOD
        add(Blocks.ACACIA_STAIRS, 12);                                  // ACACIA WOOD STAIRS
        add(Blocks.DARK_OAK_STAIRS, 12);                                // DARK OAK WOOD STAIRS
        // TODO PRISMARINE
        // TODO PRISMARINE BRICKS
        // TODO DARK PRISMARINE
        // TODO SEA LANTERN
        add(Blocks.HAY_BLOCK, 216);                                     // HAY BALE
        add(Blocks.HARDENED_CLAY, 256);                                 // HARDENED CLAY
        add(Blocks.COAL_BLOCK, 288);                                    // BLOCK OF COAL
        add(Blocks.PACKED_ICE, null);                                   // PACKED ICE
        add(Blocks.RED_SANDSTONE, 4);                                   // RED SANDSTONE
        add(new ItemStack(Blocks.RED_SANDSTONE, 1, 1), 4);              // CHISELED RED SANDSTONE
        add(new ItemStack(Blocks.RED_SANDSTONE, 1, 2), 4);              // SMOOTH RED SANDSTONE
        add(Blocks.RED_SANDSTONE_STAIRS, 6);                            // RED SANDSTONE STAIRS
        add(Blocks.STONE_SLAB2, 2);                                     // RED SANDSTONE SLAB
        // TODO PURPUR BLOCK
        // TODO PURPUR PILLAR
        // TODO PURPUR STAIRS
        // TODO PURPUR SLAB
        add(Blocks.END_BRICKS, 1);                                      // END STONE BRICKS
    }

    private void addDecorationBlocksTabTestCases() {

        add(new ItemStack(Blocks.SAPLING, 1, 0), 32);                   // OAK SAPLING
        add(new ItemStack(Blocks.SAPLING, 1, 1), 32);                   // SPRUCE SAPLING
        add(new ItemStack(Blocks.SAPLING, 1, 2), 32);                   // BIRCH SAPLING
        add(new ItemStack(Blocks.SAPLING, 1, 3), 32);                   // JUNGLE SAPLING
        add(new ItemStack(Blocks.SAPLING, 1, 4), 32);                   // ACACIA SAPLING
        add(new ItemStack(Blocks.SAPLING, 1, 5), 32);                   // DARK OAK SAPLING
        add(new ItemStack(Blocks.LEAVES, 1, 0), 1);                     // OAK LEAVES
        add(new ItemStack(Blocks.LEAVES, 1, 1), 1);                     // SPRUCE LEAVES
        add(new ItemStack(Blocks.LEAVES, 1, 2), 1);                     // BIRCH LEAVES
        add(new ItemStack(Blocks.LEAVES, 1, 3), 1);                     // JUNGLE LEAVES
        add(Blocks.WEB, 12);                                            // COBWEB
        add(new ItemStack(Blocks.TALLGRASS, 1, 0), 1);                  // GRASS
        add(new ItemStack(Blocks.TALLGRASS, 1, 1), 1);                  // FERN
        add(Blocks.DEADBUSH, 1);                                        // DEAD BUSH
        add(Blocks.YELLOW_FLOWER, 16);                                  // DANDELION
        add(new ItemStack(Blocks.RED_FLOWER, 1, 0), 16);                // POPPY
        add(new ItemStack(Blocks.RED_FLOWER, 1, 1), 16);                // BLUE ORCHID
        add(new ItemStack(Blocks.RED_FLOWER, 1, 2), 16);                // ALLIUM
        add(new ItemStack(Blocks.RED_FLOWER, 1, 3), 16);                // AZURE BLUET
        add(new ItemStack(Blocks.RED_FLOWER, 1, 4), 16);                // RED TULIP
        add(new ItemStack(Blocks.RED_FLOWER, 1, 5), 16);                // ORANGE TULIP
        add(new ItemStack(Blocks.RED_FLOWER, 1, 6), 16);                // WHITE TULIP
        add(new ItemStack(Blocks.RED_FLOWER, 1, 7), 16);                // PINK TULIP
        add(new ItemStack(Blocks.RED_FLOWER, 1, 8), 16);                // OXEYE DAISY
        add(Blocks.BROWN_MUSHROOM, 32);                                 // BROWN MUSHROOM
        add(Blocks.RED_MUSHROOM, 32);                                   // RED MUSHROOM
        add(Blocks.TORCH, 9);                                           // TORCH
        add(Blocks.CHEST, 64);                                          // CHEST
        add(Blocks.CRAFTING_TABLE, 32);                                 // CRAFTING TABLE
        add(Blocks.FURNACE, 8);                                         // FURNACE
        add(Blocks.LADDER, 9.333);                                      // LADDER
        add(Blocks.SNOW_LAYER, 0.125);                                  // SNOW (LAYER)
        add(Blocks.CACTUS, 8);                                          // CACTUS
        add(Blocks.JUKEBOX, 8256);                                      // JUKEBOX
        add(Blocks.OAK_FENCE, 13.333);                                  // OAK FENCE
        add(new ItemStack(Blocks.MONSTER_EGG, 1, 0), null);             // STONE MONSTER EGG
        add(new ItemStack(Blocks.MONSTER_EGG, 1, 1), null);             // COBBLESTONE MONSTER EGG
        add(new ItemStack(Blocks.MONSTER_EGG, 1, 2), null);             // STONE BRICK MONSTER EGG
        add(new ItemStack(Blocks.MONSTER_EGG, 1, 3), null);             // MOSSY STONE BRICK MONSTER EGG
        add(new ItemStack(Blocks.MONSTER_EGG, 1, 4), null);             // CRACKED STONE BRICK MONSTER EGG
        add(new ItemStack(Blocks.MONSTER_EGG, 1, 5), null);             // CHISELED STONE BRICK MONSTER EGG
        add(Blocks.IRON_BARS, 96);                                      // IRON BARS
        add(Blocks.GLASS_PANE, 0.375);                                  // GLASS PANE
        add(Blocks.VINE, 8);                                            // VINES
        add(Blocks.WATERLILY, 16);                                      // LILY PAD
        add(Blocks.NETHER_BRICK_FENCE, 4);                              // NETHER BRICK FENCE
        add(Blocks.ENCHANTING_TABLE, 16800);                            // ENCHANTING TABLE
        add(Blocks.END_PORTAL_FRAME, null);                             // END PORTAL (FRAME)
        add(Blocks.ENDER_CHEST, 2304);                                  // ENDER CHEST
        add(new ItemStack(Blocks.ANVIL, 1, 0), 7936);                   // ANVIL (NOT DAMAGED)
        add(new ItemStack(Blocks.ANVIL, 1, 1), 5290.667);               // ANVIL (SLIGHTLY DAMAGED)
        add(new ItemStack(Blocks.ANVIL, 1, 2), 2645.333);               // ANVIL (VERY DAMAGED)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 0), 1.125);     // STAINED GLASS (WHITE)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 1), 1.125);     // STAINED GLASS (ORANGE)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 2), 1.125);     // STAINED GLASS (MAGENTA)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 3), 1.125);     // STAINED GLASS (LIGHT BLUE)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 4), 1.125);     // STAINED GLASS (YELLOW)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 5), 1.125);     // STAINED GLASS (LIME)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 6), 1.125);     // STAINED GLASS (PINK)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 7), 1.125);     // STAINED GLASS (GRAY)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 8), 1.125);     // STAINED GLASS (LIGHT GRAY)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 9), 1.125);     // STAINED GLASS (CYAN)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 10), 1.125);    // STAINED GLASS (PURPLE)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 11), 1.125);    // STAINED GLASS (BLUE)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 12), 1.125);    // STAINED GLASS (BROWN)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 13), 1.125);    // STAINED GLASS (GREEN)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 14), 1.125);    // STAINED GLASS (RED)
        add(new ItemStack(Blocks.STAINED_GLASS_PANE, 1, 15), 1.125);    // STAINED GLASS (BLACK)
        add(new ItemStack(Blocks.LEAVES2, 1, 0), 1);                    // ACACIA LEAVES
        add(new ItemStack(Blocks.LEAVES2, 1, 1), 1);                    // DARK OAK LEAVES
        add(new ItemStack(Blocks.CARPET, 1, 0), 32);                    // CARPET (WHITE)
        add(new ItemStack(Blocks.CARPET, 1, 1), 42.667);                // CARPET (ORANGE)
        add(new ItemStack(Blocks.CARPET, 1, 2), 42.667);                // CARPET (MAGENTA)
        add(new ItemStack(Blocks.CARPET, 1, 3), 42.667);                // CARPET (LIGHT BLUE)
        add(new ItemStack(Blocks.CARPET, 1, 4), 42.667);                // CARPET (YELLOW)
        add(new ItemStack(Blocks.CARPET, 1, 5), 42.667);                // CARPET (LIME)
        add(new ItemStack(Blocks.CARPET, 1, 6), 42.667);                // CARPET (PINK)
        add(new ItemStack(Blocks.CARPET, 1, 7), 42.667);                // CARPET (GRAY)
        add(new ItemStack(Blocks.CARPET, 1, 8), 42.667);                // CARPET (LIGHT GRAY)
        add(new ItemStack(Blocks.CARPET, 1, 9), 42.667);                // CARPET (CYAN)
        add(new ItemStack(Blocks.CARPET, 1, 10), 42.667);               // CARPET (PURPLE)
        add(new ItemStack(Blocks.CARPET, 1, 11), 42.667);               // CARPET (BLUE)
        add(new ItemStack(Blocks.CARPET, 1, 12), 42.667);               // CARPET (BROWN)
        add(new ItemStack(Blocks.CARPET, 1, 13), 42.667);               // CARPET (GREEN)
        add(new ItemStack(Blocks.CARPET, 1, 14), 42.667);               // CARPET (RED)
        add(new ItemStack(Blocks.CARPET, 1, 15), 42.667);               // CARPET (BLACK)
        add(new ItemStack(Blocks.DOUBLE_PLANT, 1, 0), 32);              // SUNFLOWER
        add(new ItemStack(Blocks.DOUBLE_PLANT, 1, 1), 32);              // LILAC
        add(new ItemStack(Blocks.DOUBLE_PLANT, 1, 2), 32);              // DOUBLE TALL GRASS
        add(new ItemStack(Blocks.DOUBLE_PLANT, 1, 3), 32);              // LARGE FERN
        add(new ItemStack(Blocks.DOUBLE_PLANT, 1, 4), 32);              // ROSE BUSH
        add(new ItemStack(Blocks.DOUBLE_PLANT, 1, 5), 32);              // PEONY
        add(Blocks.SPRUCE_FENCE, 13.333);                               // SPRUCE FENCE
        add(Blocks.BIRCH_FENCE, 13.333);                                // BIRCH FENCE
        add(Blocks.JUNGLE_FENCE, 13.333);                               // JUNGLE FENCE
        add(Blocks.DARK_OAK_FENCE, 13.333);                             // DARK OAK FENCE
        add(Blocks.ACACIA_FENCE, 13.333);                               // ACACIA LEAVES
        // TODO END ROD
        // TODO CHORUS PLANT
        // TODO CHORUS FLOWER
        add(Items.PAINTING, 80);                                        // PAINTING
        add(Items.SIGN, 17.333);                                        // SIGN
        add(Items.BED, 168);                                            // BED
        add(Items.ITEM_FRAME, 96);                                      // ITEM FRAME
        add(Items.FLOWER_POT, 192);                                     // FLOWER POT
        add(new ItemStack(Blocks.SKULL, 1, 0), null);                   // SKELETON SKULL
        add(new ItemStack(Blocks.SKULL, 1, 1), null);                   // WITHER SKELETON SKULL
        add(new ItemStack(Blocks.SKULL, 1, 2), null);                   // ZOMBIE HEAD
        add(new ItemStack(Blocks.SKULL, 1, 3), null);                   // HEAD (PLAYER)
        add(new ItemStack(Blocks.SKULL, 1, 4), null);                   // CREEPER HEAD
        add(new ItemStack(Blocks.SKULL, 1, 5), null);                   // DRAGON HEAD
        add(Items.ARMOR_STAND, 24.5);                                   // ARMOR STAND

        for (EnumDyeColor enumDyeColor : EnumDyeColor.values()) {
            NBTTagCompound bannerColorTagCompound = new NBTTagCompound();
            TileEntityBanner.setBaseColorAndPatterns(bannerColorTagCompound, enumDyeColor.getDyeDamage(), null);
            NBTTagCompound itemTagCompound = new NBTTagCompound();
            itemTagCompound.setTag("BlockEntityTag", bannerColorTagCompound);
            ItemStack itemStack = new ItemStack(Blocks.STANDING_BANNER, 1, enumDyeColor.getDyeDamage());
            itemStack.setTagCompound(itemTagCompound);
            add(itemStack, 388);
        }

        add(Items.END_CRYSTAL, 5895);                                   // END CRYSTAL
    }

    private void addRedstoneTabTestCases() {

        add(Blocks.DISPENSER, 87);                                      // DISPENSER
        add(Blocks.NOTEBLOCK, 96);                                      // NOTE BLOCK
        add(Blocks.STICKY_PISTON, 340);                                 // STICKY PISTON
        add(Blocks.PISTON, 316);                                        // PISTON
        add(Blocks.TNT, 964);                                           // TNT
        add(Blocks.LEVER, 5);                                           // LEVER
        add(Blocks.STONE_PRESSURE_PLATE, 2);                            // STONE PRESSURE PLATE
        add(Blocks.WOODEN_PRESSURE_PLATE, 16);                          // WOODEN PRESSURE PLATE
        add(Blocks.REDSTONE_TORCH, 36);                                 // REDSTONE TORCH
        add(Blocks.STONE_BUTTON, 1);                                    // STONE BUTTON
        add(Blocks.TRAPDOOR, 24);                                       // WOODEN TRAPDOOR
        add(Blocks.OAK_FENCE_GATE, 32);                                 // OAK FENCE GATE
        add(Blocks.REDSTONE_LAMP, 1664);                                // REDSTONE LAMP
        add(Blocks.TRIPWIRE_HOOK, 134);                                 // TRIPWIRE HOOK
        add(Blocks.WOODEN_BUTTON, 8);                                   // WOODEN BUTTON
        add(Blocks.TRAPPED_CHEST, 198);                                 // TRAPPED CHEST
        add(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, 4096);                // WEIGHTED PRESSURE PLATE (LIGHT)
        add(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, 512);                 // WEIGHTED PRESSURE PLATE (HEAVY)
        add(Blocks.DAYLIGHT_DETECTOR, 783);                             // DAYLIGHT SENSOR
        add(Blocks.REDSTONE_BLOCK, 288);                                // BLOCK OF REDSTONE
        add(Blocks.HOPPER, 1344);                                       // HOPPER
        add(Blocks.DROPPER, 39);                                        // DROPPER
        add(Blocks.IRON_TRAPDOOR, 1024);                                // IRON TRAP DOOR
        add(Blocks.SPRUCE_FENCE_GATE, 32);                              // SPRUCE FENCE GATE
        add(Blocks.BIRCH_FENCE_GATE, 32);                               // BIRCH FENCE GATE
        add(Blocks.JUNGLE_FENCE_GATE, 32);                              // JUNGLE FENCE GATE
        add(Blocks.DARK_OAK_FENCE_GATE, 32);                            // DARK OAK FENCE GATE
        add(Blocks.ACACIA_FENCE_GATE, 32);                              // ACACIA FENCE GATE
        add(Items.OAK_DOOR, 16);                                        // OAK DOOR
        add(Items.IRON_DOOR, 512);                                      // IRON DOOR
        add(Items.REDSTONE, 32);                                        // REDSTONE
        add(Items.REPEATER, 107);                                       // REDSTONE REPEATER
        add(Items.COMPARATOR, 367);                                     // REDSTONE COMPARATOR
        add(Blocks.SPRUCE_DOOR, 48);                                    // SPRUCE DOOR
        add(Blocks.BIRCH_DOOR, 48);                                     // BIRCH DOOR
        add(Blocks.JUNGLE_DOOR, 48);                                    // JUNGLE DOOR
        add(Blocks.ACACIA_DOOR, 48);                                    // ACACIA DOOR
        add(Blocks.DARK_OAK_DOOR, 48);                                  // DARK OAK DOOR
    }

    private void addTransportationTabTestCases() {

        add(Blocks.GOLDEN_RAIL, 2054);                                  // POWERED RAIL
        add(Blocks.DETECTOR_RAIL, 261.667);                             // DETECTOR RAIL
        add(Blocks.RAIL, 96.25);                                        // RAIL
        add(Blocks.ACTIVATOR_RAIL, 263.333);                            // ACTIVATOR RAIL
        add(Items.MINECART, 1280);                                      // MINECART
        add(Items.SADDLE, 192);                                         // SADDLE
        add(Items.BOAT, 40);                                            // OAK BOAT
        add(Items.CHEST_MINECART, 1344);                                // MINECART WITH CHEST
        add(Items.FURNACE_MINECART, 1288);                              // MINECART WITH FURNACE
        add(Items.CARROT_ON_A_STICK, 60);                               // CARROT ON A STICK
        add(Items.TNT_MINECART, 2244);                                  // MINECART WITH TNT
        add(Items.HOPPER_MINECART, 2624);                               // MINECARFT WITH HOPPER
        // TODO ELYTRA
        add(Items.SPRUCE_BOAT, 40);                                     // SPRUCE BOAT
        add(Items.BIRCH_BOAT, 40);                                      // BIRCH BOAT
        add(Items.JUNGLE_BOAT, 40);                                     // JUNGLE BOAT
        add(Items.ACACIA_BOAT, 40);                                     // ACACIA BOAT
        add(Items.DARK_OAK_BOAT, 40);                                   // DARK OAK BOAT
    }

    private void addMiscellaneousTabTestCases() {

        add(Blocks.BEACON, 24773);                                      // BEACON
        add(Items.BUCKET, 768);                                         // BUCKET
        add(Items.WATER_BUCKET, 769);                                   // WATER BUCKET
        add(Items.LAVA_BUCKET, 832);                                    // LAVA BUCKET
        add(Items.SNOWBALL, 0.25);                                      // SNOWBALL
        add(Items.MILK_BUCKET, 832);                                    // MILK BUCKET
        add(Items.PAPER, 32);                                           // PAPER
        add(Items.BOOK, 160);                                           // BOOK
        add(Items.SLIME_BALL, 24);                                      // SLIMEBALL
        add(Items.BONE, 48);                                            // BONE
        add(Items.ENDER_PEARL, 1024);                                   // ENDER PEARL
        add(Items.ENDER_EYE, 1792);                                     // EYE OF ENDER
        add(new ItemStack(Items.SPAWN_EGG, 1, 50), null);               // SPAWN CREEPER EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 51), null);               // SPAWN SKELETON EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 52), null);               // SPAWN SPIDER EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 54), null);               // SPAWN ZOMBIE EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 55), null);               // SPAWN SLIME EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 56), null);               // SPAWN GHAST EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 57), null);               // SPAWN ZOMBIE PIGMEN EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 58), null);               // SPAWN ENDERMEN EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 59), null);               // SPAWN CAVE SPIDER EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 60), null);               // SPAWN SILVERFISH EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 61), null);               // SPAWN BLAZE EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 62), null);               // SPAWN MAGMA CUBE EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 65), null);               // SPAWN BAT EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 66), null);               // SPAWN WITCH EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 67), null);               // SPAWN ENDERMITE EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 68), null);               // SPAWN GUARDIAN EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 69), null);               // SPAWN SHULKER EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 90), null);               // SPAWN PIG EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 91), null);               // SPAWN SHEEP EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 92), null);               // SPAWN COW EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 93), null);               // SPAWN CHICKEN EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 94), null);               // SPAWN SQUID EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 95), null);               // SPAWN WOLF EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 96), null);               // SPAWN MOOSHROOM EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 98), null);               // SPAWN OCELOT EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 100), null);              // SPAWN HORSE EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 101), null);              // SPAWN RABBIT EGG
        add(new ItemStack(Items.SPAWN_EGG, 1, 120), null);              // SPAWN VILLAGER EGG
        add(Items.EXPERIENCE_BOTTLE, null);                             // BOTTLE O'ENCHANTING
        add(Items.FIRE_CHARGE, 330.667);                                // FIRE CHARGE
        add(Items.WRITABLE_BOOK, 224);                                  // BOOK AND QUILL
        add(Items.MAP, 1312);                                           // EMPTY MAP
        add(Items.FIREWORK_CHARGE, null);                               // FIREWORK STAR
        add(Items.IRON_HORSE_ARMOR, null);                              // IRON HORSE ARMOR
        add(Items.GOLDEN_HORSE_ARMOR, null);                            // GOLD HORSE ARMOR
        add(Items.DIAMOND_HORSE_ARMOR, null);                           // DIAMOND HORSE ARMOR
        add(Items.RECORD_13, 2048);                                     // MUSIC DISC (13)
        add(Items.RECORD_CAT, 2048);                                    // MUSIC DISC (CAT)
        add(Items.RECORD_BLOCKS, 2048);                                 // MUSIC DISC (BLOCKS)
        add(Items.RECORD_CHIRP, 2048);                                  // MUSIC DISC (CHIRP)
        add(Items.RECORD_FAR, 2048);                                    // MUSIC DISC (FAR)
        add(Items.RECORD_MALL, 2048);                                   // MUSIC DISC (MALL)
        add(Items.RECORD_MELLOHI, 2048);                                // MUSIC DISC (MELLOHI)
        add(Items.RECORD_STAL, 2048);                                   // MUSIC DISC (STAL)
        add(Items.RECORD_STRAD, 2048);                                  // MUSIC DISC (STRAD)
        add(Items.RECORD_WARD, 2048);                                   // MUSIC DISC (WARD)
        add(Items.RECORD_11, 2048);                                     // MUSIC DISC (11)
        add(Items.RECORD_WAIT, 2048);                                   // MUSIC DISC (WAIT)
    }

    private void addFoodstuffsTabTestCases() {

        add(Items.APPLE, 24);                                           // APPLE
        add(Items.MUSHROOM_STEW, 70);                                   // MUSHROOM STEW
        add(Items.BREAD, 72);                                           // BREAD
        add(Items.PORKCHOP, 24);                                        // RAW PORKCHOP
        add(Items.COOKED_PORKCHOP, 24);                                 // COOKED PORKCHOP
        add(new ItemStack(Items.GOLDEN_APPLE, 1, 0), 16408);            // GOLDEN APPLE
        add(new ItemStack(Items.GOLDEN_APPLE, 1, 1), 147480);           // GOLDEN APPLE (NOTCH)
        add(new ItemStack(Items.FISH, 1, 0), 24);                       // RAW FISH
        add(new ItemStack(Items.FISH, 1, 1), 24);                       // RAW SALMON
        add(new ItemStack(Items.FISH, 1, 2), 24);                       // CLOWNFISH
        add(new ItemStack(Items.FISH, 1, 3), 24);                       // PUFFERFISH
        add(new ItemStack(Items.COOKED_FISH, 1, 0), 24);                // COOKED FISH
        add(new ItemStack(Items.COOKED_FISH, 1, 1), 24);                // COOKED SALMON
        add(Items.CAKE, 360);                                           // CAKE
        add(Items.COOKIE, 8);                                           // COOKIE
        add(Items.MELON, 16);                                           // MELON
        add(Items.BEEF, 24);                                            // RAW BEEF
        add(Items.COOKED_BEEF, 24);                                     // STEAK
        add(Items.CHICKEN, 24);                                         // RAW CHICKEN
        add(Items.COOKED_CHICKEN, 24);                                  // COOKED CHICKEN
        add(Items.ROTTEN_FLESH, 24);                                    // ROTTEN FLESH
        add(Items.SPIDER_EYE, 128);                                     // SPIDER EYE
        add(Items.CARROT, 24);                                          // CARROT
        add(Items.POTATO, 24);                                          // POTATO
        add(Items.BAKED_POTATO, 24);                                    // BAKED POTATO
        add(Items.POISONOUS_POTATO, 24);                                // POISONOUS POTATO
        add(Items.PUMPKIN_PIE, 208);                                    // PUMPKIN PIE
        add(Items.RABBIT, 24);                                          // RAW RABBIT
        add(Items.COOKED_RABBIT, 24);                                   // COOKED RABBIT
        add(Items.RABBIT_STEW, 110);                                    // RABBIT STEW
        add(Items.MUTTON, 24);                                          // RAW MUTTON
        add(Items.COOKED_MUTTON, 24);                                   // COOKED MUTTON
        add(Items.BEETROOT, 24);                                        // BEETROOT
        add(Items.BEETROOT_SOUP, 150);                                  // BEETROOT SOUP
    }

    private void addToolsTabTestCases() {

        add(Items.IRON_SHOVEL, 264);                                    // IRON SHOVEL
        add(Items.IRON_PICKAXE, 776);                                   // IRON PICKAXE
        add(Items.IRON_AXE, 776);                                       // IRON AXE
        add(Items.FLINT_AND_STEEL, 260);                                // FLINT AND STEEL
        add(Items.WOODEN_SHOVEL, 16);                                   // WOODEN SHOVEL
        add(Items.WOODEN_PICKAXE, 32);                                  // WOODEN PICKAXE
        add(Items.WOODEN_AXE, 32);                                      // WOODEN AXE
        add(Items.STONE_SHOVEL, 9);                                     // STONE SHOVEL
        add(Items.STONE_PICKAXE, 11);                                   // STONE PICKAXE
        add(Items.STONE_AXE, 11);                                       // STONE AXE
        add(Items.DIAMOND_SHOVEL, 8200);                                // DIAMOND SHOVEL
        add(Items.DIAMOND_PICKAXE, 24584);                              // DIAMOND PICKAXE
        add(Items.DIAMOND_AXE, 24584);                                  // DIAMOND AXE
        add(Items.GOLDEN_SHOVEL, 2056);                                 // GOLDEN SHOVEL
        add(Items.GOLDEN_PICKAXE, 6152);                                // GOLDEN PICKAXE
        add(Items.GOLDEN_AXE, 6152);                                    // GOLDEN AXE
        add(Items.WOODEN_HOE, 24);                                      // WOODEN HOE
        add(Items.STONE_HOE, 10);                                       // STONE HOE
        add(Items.IRON_HOE, 520);                                       // IRON HOE
        add(Items.DIAMOND_HOE, 16392);                                  // DIAMOND HOE
        add(Items.GOLDEN_HOE, 4104);                                    // GOLDEN HOE
        add(Items.COMPASS, 1056);                                       // COMPASS
        add(Items.FISHING_ROD, 36);                                     // FISHING ROD
        add(Items.CLOCK, 8224);                                         // CLOCK
        add(Items.SHEARS, 512);                                         // SHEARS
        add(Items.LEAD, 36);                                            // LEAD
        add(Items.NAME_TAG, null);                                      // NAME TAG
        add(new ItemStack(Items.ENCHANTED_BOOK, 1, OreDictionary.WILDCARD_VALUE), null);
    }

    private void addCombatTabTestCases() {

        add(Items.BOW, 48);                                             // BOW
        add(Items.ARROW, 14);                                           // ARROW
        add(Items.IRON_SWORD, 516);                                     // IRON SWORD
        add(Items.WOODEN_SWORD, 20);                                    // WOODEN SWORD
        add(Items.STONE_SWORD, 6);                                      // STONE SWORD
        add(Items.DIAMOND_SWORD, 16388);                                // DIAMOND SWORD
        add(Items.GOLDEN_SWORD, 4100);                                  // GOLDEN SWORD
        add(Items.LEATHER_HELMET, 320);                                 // LEATHER CAP
        add(Items.LEATHER_CHESTPLATE, 512);                             // LEATHER TUNIC
        add(Items.LEATHER_LEGGINGS, 448);                               // LEATHER PANTS
        add(Items.LEATHER_BOOTS, 256);                                  // LEATHER BOOTS
        add(Items.CHAINMAIL_HELMET, null);                              // CHAIN HELMET
        add(Items.CHAINMAIL_CHESTPLATE, null);                          // CHAIN CHESTPLATE
        add(Items.CHAINMAIL_LEGGINGS, null);                            // CHAIN LEGGINGS
        add(Items.CHAINMAIL_BOOTS, null);                               // CHAIN BOOTS
        add(Items.IRON_HELMET, 1280);                                   // IRON HELMET
        add(Items.IRON_CHESTPLATE, 2048);                               // IRON CHESTPLATE
        add(Items.IRON_LEGGINGS, 1792);                                 // IRON LEGGINGS
        add(Items.IRON_BOOTS, 1024);                                    // IRON BOOTS
        add(Items.DIAMOND_HELMET, 40960);                               // DIAMOND HELMET
        add(Items.DIAMOND_CHESTPLATE, 65536);                           // DIAMOND CHESTPLATE
        add(Items.DIAMOND_LEGGINGS, 57344);                             // DIAMOND LEGGINGS
        add(Items.DIAMOND_BOOTS, 32768);                                // DIAMOND BOOTS
        add(Items.GOLDEN_HELMET, 10240);                                // GOLDEN HELMET
        add(Items.GOLDEN_CHESTPLATE, 16384);                            // GOLDEN CHESTPLATE
        add(Items.GOLDEN_LEGGINGS, 14336);                              // GOLDEN LEGGINGS
        add(Items.GOLDEN_BOOTS, 8192);                                  // GOLDEN BOOTS
        add(Items.SPECTRAL_ARROW, 775);                                 // SPECTRAL ARROW
        add(Arrows.ARROW_UNCRAFTABLE, null);                            // TIPPED ARROW (LINGERING UNCRAFTABLE POTION)
        add(Arrows.ARROW_SPLASHING, 54.25);                             // ARROW OF SPLASHING
        add(Arrows.ARROW_MUNDANE, 55.583);                              // TIPPED ARROW (MUNDANE LINGERING POTION)
        add(Arrows.ARROW_THICK, 70.25);                                 // TIPPED ARROW (THICK LINGERING POTION)
        add(Arrows.ARROW_AWKWARD, 55.25);                               // TIPPED ARROW (AWKWARD LINGERING POTION)
        add(Arrows.ARROW_NIGHT_VISION, 132.102);                        // ARROW OF NIGHT VISION (NIGHT VISION, 0:22)
        add(Arrows.ARROW_NIGHT_VISION_LONG, 141.435);                   // ARROW OF NIGHT VISION (NIGHT VISION, 1:00)
        add(Arrows.ARROW_INVISIBILITY, 140.102);                        // ARROW OF INVISIBILITY (INVISIBILITY, 0:22)
        add(Arrows.ARROW_INVISIBILITY_LONG, 141.435);                   // ARROW OF INVISIBILITY (INVISIBILITY, 1:00)
        add(Arrows.ARROW_LEAPING, 56.583);                              // ARROW OF LEAPING (JUMP BOOST, 0:22)
        add(Arrows.ARROW_LEAPING_LONG, 57.917);                         // ARROW OF LEAPING (JUMP BOOST, 1:00)
        add(Arrows.ARROW_LEAPING_STRONG, 72.583);                       // ARROW OF LEAPING (JUMP BOOST 2, 0:11)
        add(Arrows.ARROW_FIRE_RESISTANCE, 88.25);                       // ARROW OF FIRE RESISTANCE (FIRE RESISTANCE, 0:22)
        add(Arrows.ARROW_FIRE_RESISTANCE_LONG, 89.583);                 // ARROW OF FIRE RESISTANCE (FIRE RESISTANCE, 1:00)
        add(Arrows.ARROW_SWIFTNESS, 56.583);                            // ARROW OF SWIFTNESS (SPEED, 0:22)
        add(Arrows.ARROW_SWIFTNESS_LONG, 57.917);                       // ARROW OF SWIFTNESS (SPEED, 1:00)
        add(Arrows.ARROW_SWIFTNESS_STRONG, 72.583);                     // ARROW OF SWIFTNESS (SPEED 2, 0:11)
        add(Arrows.ARROW_SLOWNESS, 64.583);                             // ARROW OF SLOWNESS (SLOWNESS, 0:11)
        add(Arrows.ARROW_SLOWNESS_LONG, 65.917);                        // ARROW OF SLOWNESS (SLOWNESS, 0:30)
        add(Arrows.ARROW_WATER_BREATHING, 56.25);                       // ARROW OF WATER BREATHING (WATER BREATHING, 0:22)
        add(Arrows.ARROW_WATER_BREATHING_LONG, 57.583);                 // ARROW OF WATER BREATHING (WATER BREATHING, 1:00)
        add(Arrows.ARROW_HEALING, 131.769);                             // ARROW OF HEALING (INSTANT HEALTH)
        add(Arrows.ARROW_HEALING_STRONG, 147.769);                      // ARROW OF HEALING (INSTANT HEALTH 2)
        add(Arrows.ARROW_HARMING, 68.583);                              // ARROW OF HARMING (INSTANT DAMAGE)
        add(Arrows.ARROW_HARMING_STRONG, 84.583);                       // ARROW OF HARMING (INSTANT DAMAGE 2)
        add(Arrows.ARROW_POISON, 60.583);                               // ARROW OF POISON (POISON, 0:05)
        add(Arrows.ARROW_POISON_LONG, 61.917);                          // ARROW OF POISON (POISON, 0:11)
        add(Arrows.ARROW_POISON_STRONG, 76.583);                        // ARROW OF POISON (POISON 2, 0:02)
        add(Arrows.ARROW_REGENERATION, 225.917);                        // ARROW OF REGENERATION (REGENERATION, 0:05)
        add(Arrows.ARROW_REGENERATION_LONG, 227.25);                    // ARROW OF REGENERATION (REGENERATION, 0:11)
        add(Arrows.ARROW_REGENERATION_STRONG, 241.917);                 // ARROW OF REGENERATION (REGENERATION 2, 0:02)
        add(Arrows.ARROW_STRENGTH, 87.25);                              // ARROW OF STRENGTH (STRENGTH, 0:22)
        add(Arrows.ARROW_STRENGTH_LONG, 88.583);                        // ARROW OF STRENGTH (STRENGTH, 1:00)
        add(Arrows.ARROW_STRENGTH_STRONG, 103.25);                      // ARROW OF STRENGTH (STRENGTH 2, 0:11)
        add(Arrows.ARROW_WEAKNESS, 62.25);                              // ARROW OF WEAKNESS (WEAKNESS, 0:11)
        add(Arrows.ARROW_WEAKNESS_LONG, 63.583);                        // ARROW OF WEAKNESS (WEAKNESS, 0:30)
        add(Arrows.ARROW_LUCK, null);                                   // ARROW OF LUCK (LUCK, 0:37)
        add(Items.SHIELD, 304);                                         // SHIELD
    }

    private void addBrewingTabTestCases() {

        add(Items.GHAST_TEAR, 4096);                                    // GHAST TEAR
        add(POTION_UNCRAFTABLE, null);                                  // UNCRAFTABLE POTION
        add(BOTTLE_WATER, 2);                                           // WATER BOTTLE
        add(POTION_MUNDANE, 12.667);                                    // MUNDANE POTION
        add(POTION_THICK, 130);                                         // THICK POTION
        add(POTION_AWKWARD, 10);                                        // AWKWARD POTION
        add(POTION_NIGHT_VISION, 624.816);                              // POTION OF NIGHT VISION (NIGHT VISION, 3:00)
        add(POTION_NIGHT_VISION_LONG, 635.483);                         // POTION OF NIGHT VISION (NIGHT VISION, 8:00)
        add(POTION_INVISIBILITY, 688.816);                              // POTION OF INVISIBILITY (INVISIBILITY, 3:00)
        add(POTION_INVISIBILITY_LONG, 699.483);                         // POTION OF INVISIBILITY (INVISIBILITY, 8:00)
        add(POTION_LEAPING, 20.667);                                    // POTION OF LEAPING (JUMP BOOST, 3:00)
        add(POTION_LEAPING_LONG, 31.334);                               // POTION OF LEAPING (JUMP BOOST, 8:00)
        add(POTION_LEAPING_STRONG, 148.667);                            // POTION OF LEAPING (JUMP BOOT 2, 1:30)
        add(POTION_FIRE_RESISTANCE, 274);                               // POTION OF FIRE RESISTANCE (FIRE RESISTANCE, 3:00)
        add(POTION_FIRE_RESISTANCE_LONG, 284.667);                      // POTION OF FIRE RESISTANCE (FIRE RESISTANCE, 8:00)
        add(POTION_SWIFTNESS, 20.667);                                  // POTION OF SWIFTNESS (SPEED, 3:00)
        add(POTION_SWIFTNESS_LONG, 31.334);                             // POTION OF SWIFTNESS (SPEED, 8:00)
        add(POTION_SWIFTNESS_STRONG, 148.667);                          // POTION OF SWIFTNESS (SPEED 2, 1:30)
        add(POTION_SLOWNESS, 84.667);                                   // POTION OF SLOWNESS (SLOWNESS, 1:30)
        add(POTION_SLOWNESS_LONG, 95.334);                              // POTION OF SLOWNESS (SLOWNESS, 4:00)
        add(POTION_WATER_BREATHING, 18);                                // POTION OF WATER BREATHING (WATER BREATHING, 3:00)
        add(POTION_WATER_BREATHING_LONG, 28.667);                       // POTION OF WATER BREATHING (WATER BREATHING, 8:00)
        add(POTION_HEALING, 622.149);                                   // POTION OF HEALING (INSTANT HEALTH)
        add(POTION_HEALING_STRONG, 750.149);                            // POTION OF HEALING (INSTANT HEALTH 2)
        add(POTION_HARMING, 116.667);                                   // POTION OF HARMING (INSTANT DAMAGE)
        add(POTION_HARMING_STRONG, 244.667);                            // POTION OF HARMING (INSTANT DAMAGE 2)
        add(POTION_POISON, 52.667);                                     // POTION OF POISON (POISON, 0:45)
        add(POTION_POISON_LONG, 63.334);                                // POTION OF POISON (POISON, 1:30)
        add(POTION_POISON_STRONG, 180.667);                             // POTION OF POISON (POISON 2, 0:21)
        add(POTION_REGENERATION, 1375.333);                             // POTION OF REGENERATION (REGENERATION, 0:45)
        add(POTION_REGENERATION_LONG, 1386);                            // POTION OF REGENERATION (REGENERATION, 1:30)
        add(POTION_REGENERATION_STRONG, 1503.333);                      // POTION OF REGENERATION (REGENERATION 2, 0:22)
        add(POTION_STRENGTH, 266);                                      // POTION OF STRENGTH (STRENGTH, 3:00)
        add(POTION_STRENGTH_LONG, 276.667);                             // POTION OF STRENGTH (STRENGTH, 8:00)
        add(POTION_STRENGTH_STRONG, 394);                               // POTION OF STRENGTH (STRENGTH 2, 1:30)
        add(POTION_WEAKNESS, 66);                                       // POTION OF WEAKNESS (WEAKNESS, 1:30)
        add(POTION_WEAKNESS_LONG, 76.667);                              // POTION OF WEAKNESS (WEAKNESS, 4:00)
        add(POTION_LUCK, null);                                         // POTION OF LUCK (LUCK, 5:00)
        add(Items.GLASS_BOTTLE, 1);                                     // GLASS BOTTLE
        add(Items.FERMENTED_SPIDER_EYE, 192);                           // FERMENTED SPIDER EYE
        add(Items.BLAZE_POWDER, 768);                                   // BLAZE POWDER
        add(Items.MAGMA_CREAM, 792);                                    // MAGMA CREAM
        add(Items.BREWING_STAND, 1539);                                 // BREWING STAND
        add(Items.CAULDRON, 1792);                                      // CAULDRON
        add(Items.SPECKLED_MELON, 1836.448);                            // GLISTERING MELON
        add(Items.GOLDEN_CARROT, 1844.448);                             // GOLDEN CARROT
        add(Items.RABBIT_FOOT, 32);                                     // RABBIT'S FOOT
        add(Items.DRAGON_BREATH, 769);                                  // DRAGON'S BREATH
        add(SPLASH_POTION_UNCRAFTABLE, null);                           // SPLASH UNCRAFTABLE POTION
        add(SPLASH_BOTTLE_WATER, 66);                                   // SPLASH WATER BOTTLE
        add(SPLASH_POTION_MUNDANE, 76.667);                             // MUNDANE SPLASH POTION
        add(SPLASH_POTION_THICK, 194);                                  // THICK SPLASH POTION
        add(SPLASH_POTION_AWKWARD, 74);                                 // AWKWARD SPLASH POTION
        add(SPLASH_POTION_NIGHT_VISION, 688.816);                       // SPLASH POTION OF NIGHT VISION (NIGHT VISION, 3:00)
        add(SPLASH_POTION_NIGHT_VISION_LONG, 699.483);                  // SPLASH POTION OF NIGHT VISION (NIGHT VISION, 8:00)
        add(SPLASH_POTION_INVISIBILITY, 752.816);                       // SPLASH POTION OF INVISIBILITY (INVISIBILITY, 3:00)
        add(SPLASH_POTION_INVISIBILITY_LONG, 763.483);                  // SPLASH POTION OF INVISIBILITY (INVISIBILITY, 8:00)
        add(SPLASH_POTION_LEAPING, 84.667);                             // SPLASH POTION OF LEAPING (JUMP BOOST, 3:00)
        add(SPLASH_POTION_LEAPING_LONG, 95.334);                        // SPLASH POTION OF LEAPING (JUMP BOOST, 8:00)
        add(SPLASH_POTION_LEAPING_STRONG, 212.667);                     // SPLASH POTION OF LEAPING (JUMP BOOST 2, 1:30)
        add(SPLASH_POTION_FIRE_RESISTANCE, 338);                        // SPLASH POTION OF FIRE RESISTANCE (FIRE RESISTANCE, 3:00)
        add(SPLASH_POTION_FIRE_RESISTANCE_LONG, 348.667);               // SPLASH POTION OF FIRE RESISTANCE (FIRE RESISTANCE, 8:00)
        add(SPLASH_POTION_SWIFTNESS, 84.667);                           // SPLASH POTION OF SWIFTNESS (SPEED, 3:00)
        add(SPLASH_POTION_SWIFTNESS_LONG, 95.334);                      // SPLASH POTION OF SWIFTNESS (SPEED, 8:00)
        add(SPLASH_POTION_SWIFTNESS_STRONG, 212.667);                   // SPLASH POTION OF SWIFTNESS (SPEED 2, 1:30)
        add(SPLASH_POTION_SLOWNESS, 148.667);                           // SPLASH POTION OF SLOWNESS (SLOWNESS, 1:30)
        add(SPLASH_POTION_SLOWNESS_LONG, 159.334);                      // SPLASH POTION OF SLOWNESS (SLOWNESS, 4:00)
        add(SPLASH_POTION_WATER_BREATHING, 82);                         // SPLASH POTION OF WATER BREATHING (WATER BREATHING, 3:00)
        add(SPLASH_POTION_WATER_BREATHING_LONG, 92.667);                // SPLASH POTION OF WATER BREATHING (WATER BREATHING, 8:00)
        add(SPLASH_POTION_HEALING, 686.149);                            // SPLASH POTION OF HEALING (INSTANT HEALTH)
        add(SPLASH_POTION_HEALING_STRONG, 814.149);                     // SPLASH POTION OF HEALING (INSTANT HEALTH 2)
        add(SPLASH_POTION_HARMING, 180.667);                            // SPLASH POTION OF HARMING (INSTANT DAMAGE)
        add(SPLASH_POTION_HARMING_STRONG, 308.667);                     // SPLASH POTION OF HARMING (INSTANT DAMAGE 2)
        add(SPLASH_POTION_POISON, 116.667);                             // SPLASH POTION OF POISON (POISON, 0:45)
        add(SPLASH_POTION_POISON_LONG, 127.334);                        // SPLASH POTION OF POISON (POISON, 1:30)
        add(SPLASH_POTION_POISON_STRONG, 244.667);                      // SPLASH POTION OF POISON (POISON 2, 0:21)
        add(SPLASH_POTION_REGENERATION, 1439.333);                      // SPLASH POTION OF REGENERATION (REGENERATION, 0:45)
        add(SPLASH_POTION_REGENERATION_LONG, 1450);                     // SPLASH POTION OF REGENERATION (REGENERATION, 1:30)
        add(SPLASH_POTION_REGENERATION_STRONG, 1567.333);               // SPLASH POTION OF REGENERATION (REGENERATION 2, 0:22)
        add(SPLASH_POTION_STRENGTH, 330);                               // SPLASH POTION OF STRENGTH (STRENGTH, 3:00)
        add(SPLASH_POTION_STRENGTH_LONG, 340.667);                      // SPLASH POTION OF STRENGTH (STRENGTH, 8:00)
        add(SPLASH_POTION_STRENGTH_STRONG, 458);                        // SPLASH POTION OF STRENGTH (STRENGTH 2, 1:30)
        add(SPLASH_POTION_WEAKNESS, 130);                               // SPLASH POTION OF WEAKNESS (WEAKNESS, 1:30)
        add(SPLASH_POTION_WEAKNESS_LONG, 140.667);                      // SPLASH POTION OF WEAKNESS (WEAKNESS, 4:00)
        add(SPLASH_POTION_LUCK, null);                                  // SPLASH POTION OF LUCK (LUCK, 5:00)
        add(LINGERING_POTION_UNCRAFTABLE, null);                        // LINGERING UNCRAFTABLE POTION
        add(LINGERING_BOTTLE_WATER, 322);                               // LINGERING WATER BOTTLE
        add(LINGERING_POTION_MUNDANE, 332.667);                         // MUNDANE LINGERING POTION
        add(LINGERING_POTION_THICK, 450);                               // THICK LINGERING POTION
        add(LINGERING_POTION_AWKWARD, 330);                             // AWKWARD LINGERING POTION
        add(LINGERING_POTION_NIGHT_VISION, 944.816);                    // LINGERING POTION OF NIGHT VISION (NIGHT VISION, 0:45)
        add(LINGERING_POTION_NIGHT_VISION_LONG, 955.483);               // LINGERING POTION OF NIGHT VISION (NIGHT VISION, 2:00)
        add(LINGERING_POTION_INVISIBILITY, 1008.816);                   // LINGERING POTION OF INVISIBILITY (INVISIBILITY, 0:45)
        add(LINGERING_POTION_INVISIBILITY_LONG, 1019.483);              // LINGERING POTION OF INVISIBILITY (INVISIBILITY, 2:00)
        add(LINGERING_POTION_LEAPING, 340.667);                         // LINGERING POTION OF LEAPING (JUMP BOOST, 0:45)
        add(LINGERING_POTION_LEAPING_LONG, 351.334);                    // LINGERING POTION OF LEAPING (JUMP BOOST, 2:00)
        add(LINGERING_POTION_LEAPING_STRONG, 468.667);                  // LINGERING POTION OF LEAPING (JUMP BOOST 2, 0:22)
        add(LINGERING_POTION_FIRE_RESISTANCE, 594);                     // LINGERING POTION OF FIRE RESISTANCE (FIRE RESISTANCE, 0:45)
        add(LINGERING_POTION_FIRE_RESISTANCE_LONG, 604.667);            // LINGERING POTION OF FIRE RESISTANCE (FIRE RESISTANCE, 2:00)
        add(LINGERING_POTION_SWIFTNESS, 340.667);                       // LINGERING POTION OF SWIFTNESS (SPEED, 0:45)
        add(LINGERING_POTION_SWIFTNESS_LONG, 351.334);                  // LINGERING POTION OF SWIFTNESS (SPEED, 2:00)
        add(LINGERING_POTION_SWIFTNESS_STRONG, 468.667);                // LINGERING POTION OF SWIFTNESS (SPEED 2, 0:22)
        add(LINGERING_POTION_SLOWNESS, 404.667);                        // LINGERING POTION OF SLOWNESS (SLOWNESS, 0:22)
        add(LINGERING_POTION_SLOWNESS_LONG, 415.334);                   // LINGERING POTION OF SLOWNESS (SLOWNESS, 1:00)
        add(LINGERING_POTION_WATER_BREATHING, 338);                     // LINGERING POTION OF WATER BREATHING (WATER BREATHING, 0:45)
        add(LINGERING_POTION_WATER_BREATHING_LONG, 348.667);            // LINGERING POTION OF WATER BREATHING (WATER BREAHTING, 2:00)
        add(LINGERING_POTION_HEALING, 942.149);                         // LINGERING POTION OF HEALING (INSTANT HEALTH)
        add(LINGERING_POTION_HEALING_STRONG, 1070.149);                 // LINGERING POTION OF HEALING (INSTANT HEALTH 2)
        add(LINGERING_POTION_HARMING, 436.667);                         // LINGERING POTION OF HARMING (INSTANT DAMAGE)
        add(LINGERING_POTION_HARMING_STRONG, 564.667);                  // LINGERING POTION OF HARMING (INSTANT DAMAGE 2)
        add(LINGERING_POTION_POISON, 372.667);                          // LINGERING POTION OF POISON (POISON, 0:11)
        add(LINGERING_POTION_POISON_LONG, 383.334);                     // LINGERING POTION OF POISON (POISON, 0:22)
        add(LINGERING_POTION_POISON_STRONG, 500.667);                   // LINGERING POTION OF POISON (POISON 2, 0:05)
        add(LINGERING_POTION_REGENERATION, 1695.333);                   // LINGERING POTION OF REGENERATION (REGENERATION, 0:11)
        add(LINGERING_POTION_REGENERATION_LONG, 1706);                  // LINGERING POTION OF REGENERATION (REGENERATION, 0:22)
        add(LINGERING_POTION_REGENERATION_STRONG, 1823.333);            // LINGERING POTION OF REGENERATION (REGENERATION 2, 0:05)
        add(LINGERING_POTION_STRENGTH, 586);                            // LINGERING POTION OF STRENGTH (STRENGTH, 0:45)
        add(LINGERING_POTION_STRENGTH_LONG, 596.667);                   // LINGERING POTION OF STRENGTH (STRENGTH, 2:00)
        add(LINGERING_POTION_STRENGTH_STRONG, 714);                     // LINGERING POTION OF STRENGTH (STRENGTH 2, 0:22)
        add(LINGERING_POTION_WEAKNESS, 386);                            // LINGERING POTION OF WEAKNESS (WEAKNESS, 0:22)
        add(LINGERING_POTION_WEAKNESS_LONG, 396.667);                   // LINGERING POTION OF WEAKNESS (WEAKNESS, 1:00)
        add(LINGERING_POTION_LUCK, null);                               // LINGERING POTION OF LUCK (LUCK, 1:15)
    }

    private void addMaterialsTabTestCases() {

        add(new ItemStack(Items.COAL, 1, 0), 32);                       // COAL
        add(new ItemStack(Items.COAL, 1, 1), 32);                       // CHARCOAL
        add(Items.DIAMOND, 8192);                                       // DIAMOND
        add(Items.IRON_INGOT, 256);                                     // IRON INGOT
        add(Items.GOLD_INGOT, 2048);                                    // GOLD INGOT
        add(Items.STICK, 4);                                            // STICK
        add(Items.BOWL, 6);                                             // BOWL
        add(Items.STRING, 12);                                          // STRING
        add(Items.FEATHER, 48);                                         // FEATHER
        add(Items.GUNPOWDER, 192);                                      // GUNPOWDER
        add(Items.WHEAT_SEEDS, 16);                                     // WHEAT SEEDS
        add(Items.WHEAT, 24);                                           // WHEAT
        add(Items.FLINT, 4);                                            // FLINT
        add(Items.LEATHER, 64);                                         // LEATHER
        add(Items.BRICK, 64);                                           // BRICK
        add(Items.CLAY_BALL, 64);                                       // CLAY BALL
        add(Items.REEDS, 32);                                           // SUGAR CANE
        add(Items.EGG, 32);                                             // EGG
        add(Items.GLOWSTONE_DUST, 384);                                 // GLOWSTONE DUST
        add(new ItemStack(Items.DYE, 1, 0), 16);                        // INK SAC
        add(new ItemStack(Items.DYE, 1, 1), 16);                        // ROSE RED
        add(new ItemStack(Items.DYE, 1, 2), 16);                        // CACTUS GREEN
        add(new ItemStack(Items.DYE, 1, 3), 16);                        // COCOA BEANS
        add(new ItemStack(Items.DYE, 1, 4), 864);                       // LAPIS LAZULI
        add(new ItemStack(Items.DYE, 1, 5), 16);                        // PURPLE DYE
        add(new ItemStack(Items.DYE, 1, 6), 16);                        // CYAN DYE
        add(new ItemStack(Items.DYE, 1, 7), 16);                        // LIGHT GRAY DYE
        add(new ItemStack(Items.DYE, 1, 8), 16);                        // GRAY DYE
        add(new ItemStack(Items.DYE, 1, 9), 16);                        // PINK DYE
        add(new ItemStack(Items.DYE, 1, 10), 16);                       // LIME DYE
        add(new ItemStack(Items.DYE, 1, 11), 16);                       // DANDELION YELLOW
        add(new ItemStack(Items.DYE, 1, 12), 16);                       // LIGHT BLUE DYE
        add(new ItemStack(Items.DYE, 1, 13), 16);                       // MAGENTA DYE
        add(new ItemStack(Items.DYE, 1, 14), 16);                       // ORANGE DYE
        add(new ItemStack(Items.DYE, 1, 15), 16);                       // BONE MEAL
        add(Items.SUGAR, 32);                                           // SUGAR
        add(Items.PUMPKIN_SEEDS, 36);                                   // PUMPKIN SEEDS
        add(Items.MELON_SEEDS, 16);                                     // MELON SEEDS
        add(Items.BLAZE_ROD, 1536);                                     // BLAZE ROD
        add(Items.GOLD_NUGGET, 227.556);                                // GOLD NUGGET
        add(Items.NETHER_WART, 24);                                     // NETHER WART
        add(Items.EMERALD, 8192);                                       // EMERALD
        add(Items.NETHER_STAR, 24576);                                  // NETHER STAR
        add(Items.NETHERBRICK, 1);                                      // NETHER BRICK
        add(Items.QUARTZ, 256);                                         // NETHER QUARTZ
        // TODO PRISMARINE SHARD
        // TODO PRISMARINE CRYSTALS
        // TODO RABBIT HIDE
        // TODO CHORUS FRUIT
        // TODO POPPED CHORUS FRUIT
        add(Items.BEETROOT_SEEDS, 16);                                  // BEETROOT SEEDS
    }

    public void save() {
        this.save(new File(Files.globalTestDirectory, "minecraft-1.10.2-test-suite.json"));
    }

    protected static class Arrows {

        protected static final ItemStack ARROW_UNCRAFTABLE = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.EMPTY);
        protected static final ItemStack ARROW_SPLASHING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.WATER);
        protected static final ItemStack ARROW_MUNDANE = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.MUNDANE);
        protected static final ItemStack ARROW_THICK = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.THICK);
        protected static final ItemStack ARROW_AWKWARD = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.AWKWARD);
        protected static final ItemStack ARROW_NIGHT_VISION = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.NIGHT_VISION);
        protected static final ItemStack ARROW_NIGHT_VISION_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_NIGHT_VISION);
        protected static final ItemStack ARROW_INVISIBILITY = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.INVISIBILITY);
        protected static final ItemStack ARROW_INVISIBILITY_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_INVISIBILITY);
        protected static final ItemStack ARROW_LEAPING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LEAPING);
        protected static final ItemStack ARROW_LEAPING_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_LEAPING);
        protected static final ItemStack ARROW_LEAPING_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_LEAPING);
        protected static final ItemStack ARROW_FIRE_RESISTANCE = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.FIRE_RESISTANCE);
        protected static final ItemStack ARROW_FIRE_RESISTANCE_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_FIRE_RESISTANCE);
        protected static final ItemStack ARROW_SWIFTNESS = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.SWIFTNESS);
        protected static final ItemStack ARROW_SWIFTNESS_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_SWIFTNESS);
        protected static final ItemStack ARROW_SWIFTNESS_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_SWIFTNESS);
        protected static final ItemStack ARROW_SLOWNESS = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.SLOWNESS);
        protected static final ItemStack ARROW_SLOWNESS_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_SLOWNESS);
        protected static final ItemStack ARROW_WATER_BREATHING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.WATER_BREATHING);
        protected static final ItemStack ARROW_WATER_BREATHING_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_WATER_BREATHING);
        protected static final ItemStack ARROW_HEALING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.HEALING);
        protected static final ItemStack ARROW_HEALING_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_HEALING);
        protected static final ItemStack ARROW_HARMING = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.HARMING);
        protected static final ItemStack ARROW_HARMING_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_HARMING);
        protected static final ItemStack ARROW_POISON = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.POISON);
        protected static final ItemStack ARROW_POISON_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_POISON);
        protected static final ItemStack ARROW_POISON_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_POISON);
        protected static final ItemStack ARROW_REGENERATION = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.REGENERATION);
        protected static final ItemStack ARROW_REGENERATION_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_REGENERATION);
        protected static final ItemStack ARROW_REGENERATION_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_REGENERATION);
        protected static final ItemStack ARROW_STRENGTH = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRENGTH);
        protected static final ItemStack ARROW_STRENGTH_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_STRENGTH);
        protected static final ItemStack ARROW_STRENGTH_STRONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.STRONG_STRENGTH);
        protected static final ItemStack ARROW_WEAKNESS = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.WEAKNESS);
        protected static final ItemStack ARROW_WEAKNESS_LONG = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), PotionTypes.LONG_WEAKNESS);
        protected static final ItemStack ARROW_LUCK = PotionUtils.addPotionToItemStack(new ItemStack(Items.TIPPED_ARROW), new PotionType("luck", new PotionEffect(MobEffects.LUCK, 6000)));
    }
}
