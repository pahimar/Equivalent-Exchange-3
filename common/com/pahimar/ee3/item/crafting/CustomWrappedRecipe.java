package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.pahimar.ee3.item.CustomWrappedStack;

/**
 * Equivalent-Exchange-3
 * 
 * CustomWrappedRecipe
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class CustomWrappedRecipe {

    public CustomWrappedStack output;
    public List<CustomWrappedStack> inputs;
    
    public CustomWrappedRecipe(CustomWrappedStack output, List<CustomWrappedStack> inputs) {
        this.output = output;
        this.inputs = collateStacks(inputs);
    }
    
    public CustomWrappedRecipe(CustomWrappedStack output, CustomWrappedStack ... inputs) {
        this(output, Arrays.asList(inputs));
    }
    
    private List<CustomWrappedStack> collateStacks(List<CustomWrappedStack> uncollatedStacks) {
        
        List<CustomWrappedStack> collatedStacks = new ArrayList<CustomWrappedStack>();
        
        for (CustomWrappedStack stack : uncollatedStacks) {
            
            if (collatedStacks.isEmpty()) {
                collatedStacks.add(stack);
            }
            else {
                
            }
        }
        
        // Sort the collated stacks, to be pretty
        Collections.sort(collatedStacks);
        
        return collatedStacks;
    }
}
