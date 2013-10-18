package com.pahimar.ee3.emc;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.pahimar.ee3.item.CustomWrappedStack;
import com.pahimar.ee3.item.EnergyStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;

public class EmcDefaultValues {

    private static EmcDefaultValues emcDefaultValues = null;
    private Map<CustomWrappedStack, EmcValue> valueMap;

    private EmcDefaultValues() {

        valueMap = new HashMap<CustomWrappedStack, EmcValue>();
    }

    private static void lazyInit() {

        if (emcDefaultValues == null) {
            emcDefaultValues = new EmcDefaultValues();
            emcDefaultValues.init();
        }
    }

    private void init() {

        valueMap.put(new CustomWrappedStack(Block.stone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.grass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.ESSENTIA_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.dirt), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.cobblestone), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.sand), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.AMORPHOUS_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.leaves), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.AMORPHOUS_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.glass), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.tallGrass), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.AMORPHOUS_UNIT_COMPONENT)));
        for (int meta = 0; meta < 16; meta ++) {
            valueMap.put(new CustomWrappedStack(new ItemStack(Block.tallGrass.blockID, 1, meta)), new EmcValue(1, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 9), EmcComponent.AMORPHOUS_UNIT_COMPONENT)));
        }
        valueMap.put(new CustomWrappedStack(Block.deadBush), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.ice), new EmcValue(1, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        
        valueMap.put(new CustomWrappedStack(Block.wood), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), EmcComponent.ESSENTIA_UNIT_COMPONENT)));
        valueMap.put(new CustomWrappedStack(Block.oreIron), new EmcValue(256, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.oreGold), new EmcValue(2048, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.oreDiamond), new EmcValue(8192, EmcComponent.CORPOREAL_UNIT_COMPONENT));
        valueMap.put(new CustomWrappedStack(Block.oreCoal), new EmcValue(32, Arrays.asList(new EmcComponent(EmcType.CORPOREAL, 4), new EmcComponent(EmcType.KINETIC, 1))));

        valueMap.put(new CustomWrappedStack(new EnergyStack(EnergyStack.VANILLA_SMELTING_ENERGY_NAME)), new EmcValue(valueMap.get(new CustomWrappedStack(Block.oreCoal)).getComponentValueByType(EmcType.KINETIC) / (8 * EnergyStack.VANILLA_SMELTING_ENERGY_THRESHOLD), EmcComponent.KINETIC_UNIT_COMPONENT));
        
        // Temp, testing resolution on multiple passes through
        for (int i = 0; i < 16; i++) {
            attemptValueAssignment();
        }
    }
    
    private void attemptValueAssignment() {
        
        for (CustomWrappedStack stack : RecipeRegistry.getDiscoveredStacks()) {
            if (!valueMap.containsKey(stack)) {
                for (CustomWrappedStack recipeOutput : RecipeRegistry.getRecipeMappings().keySet()) {
                    if (recipeOutput.getWrappedStack().equals(stack.getWrappedStack())) {
                        Collection<List<CustomWrappedStack>> recipeInputLists = RecipeRegistry.getRecipeMappings().get(recipeOutput);
                        for (List<CustomWrappedStack> recipeInputs : recipeInputLists) {
                            if (listEmcCheck(recipeInputs)) {
                                valueMap.put(stack, computeEmcValueFromList(recipeOutput.getStackSize(), recipeInputs));
                            }
                        }
                    }
                }
            }
        }
    }
    
    private boolean listEmcCheck(List<CustomWrappedStack> stacks) {
        
        boolean everyElementHasEmc = true;
        
        if (stacks.size() > 0) {
            for (CustomWrappedStack stack : stacks) {
                if (!valueMap.containsKey(new CustomWrappedStack(stack.getWrappedStack()))) {
                    everyElementHasEmc = false;
                }
            }
        }
        else {
            return false;
        }
        
        return everyElementHasEmc;
    }
    
    public EmcValue computeEmcValueFromList(int recipeOutputSize, List<CustomWrappedStack> recipeInputs) {
        
        if (listEmcCheck(recipeInputs)) {
            EmcValue computedValue = new EmcValue();
            
            for (CustomWrappedStack stack : recipeInputs) {
                computedValue = new EmcValue(computedValue.value + (stack.getStackSize() * valueMap.get(new CustomWrappedStack(stack.getWrappedStack())).value));
            }
            
            // TODO Need to come up with a better way of handling sub values/components of calculated emc values
            computedValue = new EmcValue(computedValue.value / recipeOutputSize, EmcComponent.OMNI_UNIT_COMPONENT);
            
            return computedValue;
        }
        else {
            return null;
        }
    }
    
    

    public static Map<CustomWrappedStack, EmcValue> getDefaultValueMap() {

        lazyInit();
        return emcDefaultValues.valueMap;
    }
}
