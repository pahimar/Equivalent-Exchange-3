package com.pahimar.ee3.core.util;

import java.util.Comparator;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

import com.pahimar.ee3.item.ModItems;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.nbt.NBTHelper;

/**
 * Equivalent-Exchange-3
 * 
 * ItemDropHelper
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemUtil {

    private static double rand;

    public static String toString(ItemStack itemStack) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("ItemStack(");
        
        if (itemStack != null) {
            
            stringBuilder.append(String.format("%s", encodeItemStackAsString(itemStack)));
            
            if (itemStack.hasTagCompound()) {
                stringBuilder.append(String.format("%s%s", Strings.TOKEN_DELIMITER, NBTHelper.encodeNBTAsString((itemStack.getTagCompound()))));
            }
        }
        else {
            stringBuilder.append("null");
        }
        
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    public static String encodeItemStackAsString(ItemStack itemStack) {

        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(String.format("%s%s%s", itemStack.itemID, Strings.TOKEN_DELIMITER, itemStack.getItemDamage()));
        
        return stringBuilder.toString();
    }
    
    public static ItemStack decodeItemStackFromString(String encodedItemStack) {
        
        ItemStack decodedItemStack = null;
        
        final int UNDEFINED = -1;
        final int ERROR = -2;
        
        int itemId = UNDEFINED;
        int meta = UNDEFINED;
        
        String[] splitString = encodedItemStack.split(Strings.TOKEN_DELIMITER);
        
        // Grab itemId
        if (splitString.length >= 1) {
            
            try {
                itemId = Integer.parseInt(splitString[0]);
            } catch (NumberFormatException e) {
                itemId = ERROR;
            }
        }
        
        // Grab meta
        if (splitString.length >= 2) {
            
            try {
                meta = Integer.parseInt(splitString[1]);
            } catch (NumberFormatException e) {
                meta = ERROR;
            }
        }
        
        if (meta == UNDEFINED) {
            meta = OreDictionary.WILDCARD_VALUE;
        }
        
        if (itemId != UNDEFINED && itemId != ERROR) {
            if (meta != ERROR) {
                decodedItemStack = new ItemStack(itemId, 1, meta);
            }
        }
        
        return decodedItemStack;
    }

    /**
     * Compares two ItemStacks for equality, testing itemID, metaData,
     * stackSize, and their NBTTagCompounds (if they are present)
     * 
     * @param first
     *            The first ItemStack being tested for equality
     * @param second
     *            The second ItemStack being tested for equality
     * @return true if the two ItemStacks are equivalent, false otherwise
     */
    // FIXME Update to correspond with the new Comparator stuff for ItemStacks, and make NBT sensitive
    public static boolean compare(ItemStack first, ItemStack second) {

        // Check to see if either argument is null
        if ((first != null) && (second != null)) {
            // Check the item IDs
            if (first.itemID == second.itemID) {
                // Check the meta data

                if ((first.getItemDamage() == OreDictionary.WILDCARD_VALUE) || (second.getItemDamage() == OreDictionary.WILDCARD_VALUE)) {
                    //return true;
                }

                if (first.getItemDamage() == second.getItemDamage()) {
                    // Check the stack size
                    if (first.stackSize == second.stackSize) {
                        // If at least one of the ItemStacks has a NBTTagCompound, test for equality
                        if (first.hasTagCompound() || second.hasTagCompound()) {

                            // If one of the stacks has a tag compound, but not both, they are not equal
                            if (!(first.hasTagCompound() && second.hasTagCompound())) {
                                return false;
                            }
                            // Otherwise, they both have tag compounds and we need to test them for equality
                            else {
                                return first.getTagCompound().equals(second.getTagCompound());
                            }
                        }
                        // Otherwise, they must be equal if we have gotten this far (item IDs, meta data, and stack size all match)
                        else {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean hasColor(ItemStack itemStack) {

        return !itemStack.hasTagCompound() ? false : !itemStack.getTagCompound().hasKey(Strings.NBT_ITEM_DISPLAY) ? false : itemStack.getTagCompound().getCompoundTag(Strings.NBT_ITEM_DISPLAY).hasKey(Strings.NBT_ITEM_COLOR);
    }

    public static int getColor(ItemStack itemStack) {

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null)
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        else {

            NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);
            return displayTagCompound == null ? Integer.parseInt(Colours.PURE_WHITE, 16) : displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR) ? displayTagCompound.getInteger(Strings.NBT_ITEM_COLOR) : Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    public static void setColor(ItemStack itemStack, int color) {

        if (itemStack != null) {

            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound == null) {

                nbtTagCompound = new NBTTagCompound();
                itemStack.setTagCompound(nbtTagCompound);
            }

            NBTTagCompound colourTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

            if (!nbtTagCompound.hasKey(Strings.NBT_ITEM_DISPLAY)) {
                nbtTagCompound.setCompoundTag(Strings.NBT_ITEM_DISPLAY, colourTagCompound);
            }

            colourTagCompound.setInteger(Strings.NBT_ITEM_COLOR, color);
        }
    }

    public static void dropMiniumShard(EntityPlayer player, EntityLivingBase entity) {

        if (GeneralHelper.isHostileEntity(entity)) {
            rand = Math.random();

            if (rand < 0.15d) {
                entity.dropItem(ModItems.miniumShard.itemID, 1);
            }
        }
    }
    
    // FIXME Make NBT sensitive
    public static Comparator<ItemStack> ItemStackComparator = new Comparator<ItemStack>() {
    	
    	public int compare(ItemStack itemStack1, ItemStack itemStack2) {
    		
    		if (itemStack1.itemID == itemStack2.itemID) {
    			
    			if (itemStack1.getItemDamage() == itemStack2.getItemDamage()) {
    				
    				if ((itemStack1.getUnlocalizedName() != null) && (itemStack2.getUnlocalizedName() != null)) {
    					
    					if (!(itemStack1.hasTagCompound()) && !(itemStack2.hasTagCompound())) {
    						return itemStack1.getUnlocalizedName().toLowerCase().compareTo(itemStack2.getUnlocalizedName().toLowerCase());
    					}
    					else if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound()) {
    						// TODO We may have to go deeper with comparing NBTTagCompounds
    						return 0;
    					}
    					else if (itemStack1.hasTagCompound() && !(itemStack2.hasTagCompound())) {
    						return 1;
    					}
    					else {
    						return -1;
    					}
    				}
    				else if ((itemStack1.getUnlocalizedName() == null) && (itemStack2.getUnlocalizedName() == null)) {
    					return 0;
    				}
    				else {
    					if (itemStack1.getUnlocalizedName() == null) {
    						return -1;
    					}
    					else {
    						return 1;
    					}
    				}
    			}
    			else {
    				return (itemStack1.getItemDamage() - itemStack2.getItemDamage());
    			}
    		}
    		else {
    			return (itemStack1.itemID - itemStack2.itemID);
    		}
    	}
    	
    };
}
