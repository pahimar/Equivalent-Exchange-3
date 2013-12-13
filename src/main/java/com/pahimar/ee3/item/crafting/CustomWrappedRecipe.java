package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.pahimar.ee3.item.WrappedStack;

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

    public WrappedStack output;
    public List<WrappedStack> inputs;
    
    public CustomWrappedRecipe(WrappedStack output, List<WrappedStack> inputs) {
        this.output = output;
        this.inputs = collateStacks(inputs);
    }
    
    public CustomWrappedRecipe(WrappedStack output, WrappedStack ... inputs) {
        this(output, Arrays.asList(inputs));
    }
    
    private List<WrappedStack> collateStacks(List<WrappedStack> uncollatedStacks) {
        
        List<WrappedStack> collatedStacks = new ArrayList<WrappedStack>();
        
        for (WrappedStack stack : uncollatedStacks) {
            
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
