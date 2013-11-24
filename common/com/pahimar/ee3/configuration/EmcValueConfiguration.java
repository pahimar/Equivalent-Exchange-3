package com.pahimar.ee3.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.lib.Reference;

import cpw.mods.fml.common.FMLLog;

public class EmcValueConfiguration
{
    private static File configFile = null;
    private static Configuration emcValueConfiguration;
    
    public static final String CATEGORY_BLOCK_VALUES = "emcvalues";
    
    protected static void init(File configFile)
    {
        EmcValueConfiguration.configFile = configFile;
    }
    
    public static void create() {
        
        if (configFile != null)
        {
            emcValueConfiguration = new Configuration(configFile);
    
            try {
                for (Item item : Item.itemsList) {
                    
                    if (item != null) {
                        
                        List<ItemStack> itemStacks = new ArrayList<ItemStack>();
                        item.getSubItems(item.itemID, null, itemStacks);
                        
                        for (ItemStack stack : itemStacks) {
                        
                            if (stack != null && !EmcRegistry.hasEmcValue(stack)) {
                            
                            emcValueConfiguration.get(CATEGORY_BLOCK_VALUES, Integer.toString(stack.itemID) + ":" + Integer.toString(stack.getItemDamage()), 0, stack.getDisplayName());
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
        
        if (configFile != null) {
            
            emcValueConfiguration = new Configuration(configFile);
            
            if (emcValueConfiguration.hasCategory(CATEGORY_BLOCK_VALUES)) {
                
                for (Entry<String, Property> entry : emcValueConfiguration.getCategory(CATEGORY_BLOCK_VALUES).getValues().entrySet()) {
                    
                    Float value = (float)entry.getValue().getDouble(0);
                    
                    int split = entry.getKey().indexOf(":");
                    int id = Integer.parseInt(entry.getKey().substring(0, split));
                    int meta = Integer.parseInt(entry.getKey().substring(split + 1));
                    
                    if (value != 0.0F && id >= 0 && meta >= 0) {
                        
                        ItemStack stack = new ItemStack(id, 1, meta);
                        
                        if (stack != null) {
                            
                            valueMap.put(new CustomWrappedStack(stack), new EmcValue(value));
                        }
                    }
                }
            }
        }
        return valueMap;
    }
}
