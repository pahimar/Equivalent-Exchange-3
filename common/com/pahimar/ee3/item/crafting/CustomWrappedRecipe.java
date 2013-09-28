package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.pahimar.ee3.core.util.EnergyStack;
import com.pahimar.ee3.core.util.ItemUtil;
import com.pahimar.ee3.core.util.OreStack;
import com.pahimar.ee3.item.CustomWrappedStack;

public class CustomWrappedRecipe {

    public CustomWrappedStack output;
    public List<CustomWrappedStack> inputs;

    public CustomWrappedRecipe(Object output, List<?> inputs) {

        this.output = new CustomWrappedStack(output);
        this.inputs = collateInputStacks(inputs);
    }

    public CustomWrappedRecipe(Object output, Object... inputs) {

        this(output, Arrays.asList(inputs));
    }

    private List<CustomWrappedStack> collateInputStacks(List<?> uncollatedStacks) {

        List<CustomWrappedStack> collatedStacks = new ArrayList<CustomWrappedStack>();

        CustomWrappedStack stack = null;
        boolean found = false;

        for (Object object : uncollatedStacks) {

            found = false;

            if (CustomWrappedStack.canBeWrapped(object)) {
                
                stack = new CustomWrappedStack(object);
                
                if (collatedStacks.isEmpty()) {
                    collatedStacks.add(stack);
                }
                else {
                    
                    for (int i = 0; i < collatedStacks.size(); i++) {
                        if (collatedStacks.get(i).getWrappedStack() != null) {
                            if (stack.getWrappedStack() instanceof ItemStack && collatedStacks.get(i).getWrappedStack() instanceof ItemStack) {
                                if (ItemUtil.compare((ItemStack) stack.getWrappedStack(), (ItemStack) collatedStacks.get(i).getWrappedStack())) {
                                    collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + stack.getStackSize());
                                    found = true;
                                }
                            }
                            else if (stack.getWrappedStack() instanceof OreStack && collatedStacks.get(i).getWrappedStack() instanceof OreStack) {
                                if (OreStack.compareOreNames((OreStack) stack.getWrappedStack(), (OreStack) collatedStacks.get(i).getWrappedStack())) {
                                    collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + stack.getStackSize());
                                    found = true;
                                }
                                
                            }
                            else if (stack.getWrappedStack() instanceof EnergyStack && collatedStacks.get(i).getWrappedStack() instanceof EnergyStack) {
                                if (EnergyStack.compareEnergyNames((EnergyStack) stack.getWrappedStack(), (EnergyStack) collatedStacks.get(i).getWrappedStack())) {
                                    collatedStacks.get(i).setStackSize(collatedStacks.get(i).getStackSize() + stack.getStackSize());
                                    found = true;
                                }
                            }
                        }
                    }
                    
                    if (!found) {
                        collatedStacks.add(stack);
                    }
                }
            }

        }

        return collatedStacks;
    }
}
