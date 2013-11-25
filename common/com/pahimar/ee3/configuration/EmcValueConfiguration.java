package com.pahimar.ee3.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.pahimar.ee3.core.helper.LogHelper;
import com.pahimar.ee3.emc.EmcComponent;
import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcType;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class EmcValueConfiguration
{
    private static File configFile = null;
    private static Configuration emcValueConfiguration;
    
    public static final String CATEGORY_EMC_VALUES = "emcvalues";
    
    protected static void init(File configFile)
    {
        EmcValueConfiguration.configFile = configFile;
    }
    
    public static void create() {
        
        if (configFile != null && !configFile.exists())
        {
            
            emcValueConfiguration = new Configuration(configFile);
            try {
                emcValueConfiguration.addCustomCategoryComment(CATEGORY_EMC_VALUES, String.format("This is a list of all items that don't have an EMC value.%1$s"
                        + "To add EMC values to these items put the desired EMC value after the '=' for the item.%1$s"
                        + "To add other components then the default(Corporeal) add a comma, the first letter of the comonent%1$s"
                        + "and the weight of the component.%1$s%1$s"
                        + "Example: '40.4,A1,K3' This will add a total value of 40.4 with 10.1 Amorphous and 30.3 Kinetic EMC."
                        , Configuration.NEW_LINE));
                
                for (Item item : Item.itemsList) {
                    
                    if (item != null) {
                        
                        List<ItemStack> itemStacks = new ArrayList<ItemStack>();
                        item.getSubItems(item.itemID, null, itemStacks);
                        
                        for (ItemStack stack : itemStacks) {
                        
                            if (stack != null && !EmcRegistry.hasEmcValue(stack)) {
                            
                            emcValueConfiguration.get(CATEGORY_EMC_VALUES, Integer.toString(stack.itemID) + ":" + Integer.toString(stack.getItemDamage()), "", stack.getDisplayName());
                            }
                        }
                    }
                }
            }
            catch (Exception e) {
                FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem creating its blockValue configuration");
            }
            finally {
                emcValueConfiguration.save();
            }
        }
    }
    
    public static Map<CustomWrappedStack, EmcValue> getConfigValues() {
        
        Map<CustomWrappedStack, EmcValue> valueMap = new HashMap<CustomWrappedStack, EmcValue>();
        
        if (configFile != null && configFile.exists()) {
            
            emcValueConfiguration = new Configuration(configFile);
            
            if (emcValueConfiguration.hasCategory(CATEGORY_EMC_VALUES)) {
                
                for (Entry<String, Property> entry : emcValueConfiguration.getCategory(CATEGORY_EMC_VALUES).getValues().entrySet()) {
                    
                    int split = entry.getKey().indexOf(":");
                    int id = Integer.parseInt(entry.getKey().substring(0, split));
                    int meta = Integer.parseInt(entry.getKey().substring(split + 1));
                    
                    if (id >= 0 && meta >= 0) {
                        
                        ItemStack stack = new ItemStack(id, 1, meta);
                        
                        if (stack != null) {
                            
                            String string = entry.getValue().getString();
                            
                            if (!string.isEmpty()) {
                                
                                string.replace("\\s", "");
                                float value = 0F;
                                
                                try {
                                    
                                    if (string.contains(",")) {
                                        
                                        value = Float.parseFloat(string.substring(0, string.indexOf(',')));
                                        
                                        int currentIndex = 0;
                                        List<EmcComponent> components = new ArrayList<EmcComponent>();
                                        
                                        while ((currentIndex = string.indexOf(",")) != -1) {
                                            
                                            string = string.substring(currentIndex + 1);
                                            char[] chars = string.substring(0, (string.indexOf(',') == -1)? string.length(): string.indexOf(',')).toCharArray();
                                            
                                            if (Character.isLetter(chars[0])) {
                                                
                                                for (EmcType type :EmcType.values()) {
                                                    
                                                    if (Character.toLowerCase(chars[0]) == type.name().toLowerCase().charAt(0)) {
                                                        
                                                        components.add(new EmcComponent(type, Integer.parseInt(String.copyValueOf(Arrays.copyOfRange(chars, 1, chars.length)))));
                                                    }
                                                }
                                            }
                                        }
                                        if (value != 0F) {
                                            
                                            valueMap.put(new CustomWrappedStack(stack), new EmcValue(value, components));
                                        }
                                    }
                                    else {
                                        
                                        value = Float.parseFloat(string);
                                        if (value != 0F) {
                                            
                                            valueMap.put(new CustomWrappedStack(stack), new EmcValue(value));
                                        }
                                    }
                                }
                                catch (Exception e) {
                                    
                                    LogHelper.severe("Configuration file has wrong syntax at: " + entry.getKey());
                                }
                            }
                        }
                    }
                }
            }
        }
        return valueMap;
    }
}
