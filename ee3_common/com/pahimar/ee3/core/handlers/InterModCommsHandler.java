package com.pahimar.ee3.core.handlers;

import cpw.mods.fml.common.event.FMLInterModComms.IMCEvent;

public class InterModCommsHandler {

    // TODO Method to encode a given "crafting relationship" as NBT data
    
    /*
     * NBTTagCompound
     * - NBTTagList (recipeOutput)
     * - NBTTagList (recipeInputs)
     *   - NBTTagList (recipeInput-1)
     *   - NBTTagList (recipeInput-2)
     *   - ...
     *   - NBTTagList (recipeInput-n)
     *   
     *   Method to encode stacks as NBTTagLists should:
     *   - track the "type" of the stack
     *   - track the size of the stack
     *   - track type specific data of the stack
     *      - e.g. id/meta/NBTTagCompound for ItemStacks
     */

    public static void processIMCMessages(IMCEvent event) {

    }
}
